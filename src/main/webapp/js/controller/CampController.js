function CampListCtrl($scope, $rootScope, $location, $q, $timeout, CampService, GoogleMapsService, RoleService, PhoneGapLocationService, CAMP_LIST_PAGE_SIZE) {
    init();


    function init() {
        $scope.title = 'Camp List';
        $scope.camps = null;
        $scope.currentPage = 1;
        firePageRequest(buildPageRequestObject($scope.currentPage));
        checkAdmin(RoleService, $rootScope);
    }


    $rootScope.imageCampURL = function (campId) {
        if (campId) {
            return imageCampURL(campId);
        }
        else {
            return '';
        }
    }


    //TODO move to utility module
    var endsWith = function (str, suffix) {
        return str.indexOf(suffix, str.length - suffix.length) !== -1;
    };

    var isActive = function (tabName) {
        return endsWith($location.path(), tabName);
    }

    $scope.isActive = isActive;

    $scope.addCampDetailsToScope = function () {
        $rootScope.campId = this.camp.id;
    }


    $scope.enterList = function () {
        //$scope.map = null;
        //$scope.mapDataReady = false;
        document.getElementById('camps_map').innerHTML = '';
        $scope.title = 'Camp List';
    }

    $scope.wordrap = function (str, width, brk, cut) {

        brk = brk || '\n';
        width = width || 75;
        cut = cut || false;

        if (!str) {
            return str;
        }

        var regex = '.{1,' + width + '}(\\s|$)' + (cut ? '|.{' + width + '}|.+$' : '|\\S+?(\\s|$)');

        return str.match(RegExp(regex, 'g')).join(brk);

    }

    $scope.showCampsOnMap = function (elemId) {
     CampService.getAllCamps(function (data) {
                   listCampsOnMap(elemId, data);
     },
                     function (err) {
                         console.log(err);
                     });
    }

     function listCampsOnMap(elemId, allCamps) {
        //var coords ={latitude: 38.57332, longitude: -109.55075};
        var deferred = $q.defer();
        PhoneGapLocationService.getLocation(function (coords) {
            $timeout(function () {
                var markers = [];
                for (i in allCamps) {
                    var camp = allCamps[i];
                    //EDIT THE ICONS FOR THE MARKERS BELOW -> ALSO ADD INFOWINDOWS
                    markers.push({lat:camp.latitude, lng:camp.longitude, badge:camp.badge, title:camp.name,
                        status:camp.status, description:$scope.wordrap(camp.description, 30, '<br>'), id:camp.id});
                }


                $scope.map = GoogleMapsService.createListMap(document.getElementById('camps_map'),
                    {lat:coords.latitude, lng:coords.longitude, title:'Current Location'});

                GoogleMapsService.placeListMarkers($scope.map,
                    markers,
                    {lat:coords.latitude, lng:coords.longitude, title:'Current Location'});

                deferred.resolve(true);
            }, 10);
        });

        deferred.promise.then(function (value) {
            $scope.mapDataReady = value;
        });
        $scope.title = 'Camps Map';
    }


    $scope.previousPage = function () {
        var firstPage = $scope.page.firstPage;
        var pageNumber = $scope.currentPage;
        if (!firstPage) {
            pageNumber = $scope.currentPage - 1;
        }
        firePageRequest(buildPageRequestObject(pageNumber));
    }


    $scope.nextPage = function () {
        var totalPages = $scope.page.totalPages;
        var pageNumber = $scope.currentPage;
        if (totalPages != $scope.currentPage) {
            pageNumber = $scope.currentPage + 1;
        }
        firePageRequest(buildPageRequestObject(pageNumber));

    }

    function firePageRequest(pageRequest) {
        var deferred = $q.defer();
        CampService.getAllCampsByPage(pageRequest, function (data) {
                $scope.camps = data.content;
                $scope.page = buildPageObject(data);
                $scope.currentPage = pageRequest.page;
                deferred.resolve(true);
            },
            function (err) {
                $scope.error = 'Error getting data';
                deferred.resolve(true);
            });

        deferred.promise.then(function (value) {
            $scope.campDataReceived = value;
        });

    }

    function buildPageObject(data) {
        var page = {};
        page.size = data.size;
        page.totalElements = data.totalElements;
        page.totalPages = data.totalPages;
        page.numberOfElements = data.numberOfElements;
        page.firstPage = data.firstPage;
        page.lastPage = data.lastPage;
        return page;
    }

    function buildPageRequestObject(pageNumber) {
        var pageRequest = {};
        pageRequest.size = CAMP_LIST_PAGE_SIZE;
        pageRequest.page = pageNumber;
        return pageRequest;
    }
}


function CampCtrl($scope, $rootScope, $location, $q, PhoneGapLocationService, CampService, GoogleMapsService, $timeout, PhoneGapCameraService, HTML5CameraService, safeApply, ImageUtil, IMAGE_RESIZE_WIDTH, AdminUserService) {

    function init(dataReceivedCallback) {
        var campId = $rootScope.campId;
        $rootScope.campId = null;
        $rootScope.camp = null;
        if (campId != null) {
            CampService.campDetails({'campId':campId}, function (data) {
                    $scope.camp = data;
                    initData();
                    addCaseManagerToScope();
                    if (dataReceivedCallback) dataReceivedCallback();
                    $scope.campDataReceived = true;
                },
                function (err) {
                    console.log(err);
                    $scope.campDataReceived = true;
                    $scope.error = 'Error getting data';
                });
        } else {
            initData();
            addCaseManagerToScope();
        }
    }

    init();

    function showCampOnMap(elemId, camp) {
        $scope.map = GoogleMapsService.createMap(document.getElementById(elemId),
            {lat:camp.latitude, lng:camp.longitude, title:camp.name});

        GoogleMapsService.placeMarkers($scope.map,
            [],
            {lat:camp.latitude, lng:camp.longitude, title:camp.name, badge:camp.badge, status:camp.status});
    }

    //TODO move to utility module
    var endsWith = function (str, suffix) {
        return str.indexOf(suffix, str.length - suffix.length) !== -1;
    };

    var isActive = function (tabName) {
        return endsWith($location.path(), tabName);
    }

    $scope.isActive = isActive;


    function addCaseManagerToScope() {
        var values = [];
        if ($scope.client != null) {
            values = $scope.camp.listOfCaseMgr;
            loadCaseManager(values);

        } else {
            AdminUserService.getAllCaseMgr(function (data) {
                    values = data;
                    loadCaseManager(values);
                },
                function (err) {
                    console.log(err);
                });
        }

    }

    function loadCaseManager(values) {
        $scope.caseMgrList = [];
        angular.forEach(values, function (value, key) {
            var caseMgr = value.name;
            this.push(caseMgr);
        }, $scope.caseMgrList);

    }

    function determineAction() {
        if ($scope.camp && $scope.camp.id) {
            $scope.editAction = 'Update';

        }
        else {
            $scope.editAction = 'Add';
            $scope.title = 'Add Camp';
        }
    }

    function buildName() {
        if ($scope.camp != null) {
            $scope.title = $scope.camp.name;
        }
    }

    function staticData() {
        $scope.alerts =
            ['Bring a Partner', 'CPD Escort', 'Parks & Rec', 'Metro', 'ODOT', 'RR'];
        $scope.types =
            ['Woods', 'Bridge', 'Abandoned Building', 'Park', 'Downtown', 'Other'];
        $scope.badges =
            ['Friendly', 'Armed', 'Violent'];
    }

    function initData() {
        staticData();
        determineAction();
        buildName();

        if ($scope.camp) {
            $timeout(function () {
                showCampOnMap('camp_map', $scope.camp);
            }, 1);
        }

    }

    $scope.uploadPicture = function (campId, type) {
        $scope.imgStatus = 'loading';
        var URI = '/upload/camp/';
        try {
            PhoneGapCameraService.uploadImage({'id':campId, 'type':type},
                function (message) {
                    $scope.$apply(function () {
                        $scope.imgStatus = '';
                    });
                },
                function (response) {
                    $scope.$apply(function () {
                        $scope.imgStatus = 'loaded';
                    });
                },
                function (error) {
                    $scope.$apply(function () {
                        $scope.imgStatus = '';
                    });
                }, URI);
        }
        catch (error) {
            $scope.$apply(function () {
                $scope.imgStatus = '';
            });
            alert(error);
        }
    }

    $scope.proxyClickTo = function(elemId) {
        document.getElementById(elemId).click();
    }

    $scope.uploadHTML5Picture = function (campId, file) {
        $scope.imgStatus = 'loading';
        var URI = '/upload/camp/';


        try {
            //var file = document.getElementById('file').files[0];
            safeApply($scope, function() {
                $scope.imgStatus = 'loading';
            });
            var reader = new FileReader();
            reader.onload = function (e) {
                ImageUtil.resizeImage(e.target.result, IMAGE_RESIZE_WIDTH, function(file){
                    HTML5CameraService.uploadImage({'id': campId, 'file': file},
                        function (response) {
                            safeApply($scope, function() {
                                $scope.imgStatus = 'loaded';
                            });
                        },
                        function (error) {
                            safeApply($scope, function() {
                                $scope.imgStatus = '';
                            });
                            console.log(error);
                        }, URI);
                });
            }

            reader.readAsDataURL(file);

        }
        catch (error) {
            safeApply($scope, function() {
                $scope.imgStatus = '';
            });
            console.log(error);
        }


//        try {
//            HTML5CameraService.uploadImage({'id':campId, 'file':document.getElementById('file').files[0]},
//                function (response) {
//                    $scope.imgStatus = 'loaded';
//                },
//                function (error) {
//                    $scope.imgStatus = '';
//                }, URI);
//        }
//        catch (error) {
//            $scope.imgStatus = '';
//            console.log(error);
//        }
    }

    $scope.isHybrid = function () {
        return window.isHybrid;
    }

    $scope.isWeb = function () {
        return !$scope.isHybrid();
    }

    $scope.createOrUpdate = function() {
        var fn = $scope.camp.id ? CampService.updateCamp : CampService.createCamp;
        $scope.waitingForCampData = true;
        var deferred = $q.defer();
        fn($scope.camp, function (data) {
                $rootScope.campId = data.id;
                deferred.resolve('/camp/detail');
                init(function () {
                    $scope.waitingForCampData = false;
                });
            },
            function (err) {
                console.log(err);
                $scope.waitingForCampData = false;
                $scope.error = "Invalid Data";
                $scope.errors = err.data.errors;
                deferred.resolve('/camp/edit');
            });

        deferred.promise.then(function (value) {
            $location.path(value);
        });
    }

    $scope.removeCamp = function () {
        var deferred = $q.defer();
        var confirmRemoval = confirm('Are you sure you want to remove this camp?');
        if (!confirmRemoval) {
            deferred.resolve('/camp/detail');
        }
        else {
            CampService.removeCamp({campId:$scope.camp.id},
                function (data) {
                    console.log('data in CampController.removeCamp', data);
                    deferred.resolve('/camps/list');
                },
                function (err) {
                    console.log('error in CampController.removeCamp', err);
                    deferred.resolve('/camp/detail');
                    alert('Error removing camp');
                });
        }
        deferred.promise.then(function (value) {
            $location.path(value);
        });
    }

    $scope.cancel = function () {
        if ($scope.camp && $scope.camp.id) {
            $scope.cancelHref = '#/camp/detail';
            $rootScope.campId = $scope.camp.id;
            init();
        }
        else {
            $scope.cancelHref = '#/camps/list';
            $scope.camp = null;
            $rootScope.campId = null;
        }
    }

    $scope.setCurrentLocation = function (camp) {
        PhoneGapLocationService.getLocation(function (coords) {
            $scope.$apply(function () {
                $scope.camp.latitude = coords.latitude;
                $scope.camp.longitude = coords.longitude;
            });
        });
    }


    $scope.enterEdit = function () {
        if ($scope.camp && $scope.camp.id) {
            $scope.title = 'Edit Camp';
        }
        else {
            $scope.title = 'Add Camp';
        }
    }

    $scope.enterEditDesc = function () {
        if ($scope.camp && $scope.camp.id) {
            $scope.title = 'Edit Description';
        }
    }

    $scope.enterEditEntity = function () {
        if ($scope.camp && $scope.camp.id) {
            $scope.title = 'Edit Entities';
        }
    }

}