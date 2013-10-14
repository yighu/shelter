function ClientListCtrl($scope, $rootScope, $q, $timeout, ClientService, CLIENT_LIST_DELAY, CLIENT_LIST_PAGE_SIZE) {

    $timeout(function () {
        init();
    }, CLIENT_LIST_DELAY);


    function init() {
        $rootScope.client = null;
        $scope.currentPage = 1;
        firePageRequest(buildPageRequestObject($scope.currentPage));

    }


    $scope.enterDetail = function () {
        $rootScope.clientId = this.client.id;
    }

    $rootScope.imageURL = function (clientId) {
        if (clientId) {
            return imageURL(clientId);
        }
        else {
            return '';
        }
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

        ClientService.getAllClientsByPage(pageRequest, function (data) {
                $scope.clients = data.content;
                deferred.resolve(true);
                $scope.page = buildPageObject(data);
                $scope.currentPage = pageRequest.page;

            },
            function (err) {
                $scope.error = 'Error getting data';
                deferred.resolve(true);
            });


        deferred.promise.then(function (value) {
            $scope.clientDataReceived = value;
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
        pageRequest.size = CLIENT_LIST_PAGE_SIZE;
        pageRequest.page = pageNumber;
        return pageRequest;
    }
}

function ClientCtrl($scope, $rootScope, $location, $q, ClientService, CampService, PhoneGapCameraService, HTML5CameraService, safeApply, ImageUtil, IMAGE_RESIZE_WIDTH, AdminUserService) {

    function init(dataReceivedCallback) {

        var clientId = $rootScope.clientId;
        $rootScope.clientId = null;
        $rootScope.client = null;
        if (clientId != null) {
            var deferred = $q.defer();
            $scope.waitingForClientData = true
            ClientService.clientDetails({'clientId':clientId}, function (data) {
                    $scope.client = data;
                    initData();
                    addCaseManagerToScope();
                    associateCampToClient();
                    if (dataReceivedCallback) dataReceivedCallback();
                    deferred.resolve(false);
                },
                function (err) {
                    console.log(err);
                    deferred.resolve(false);
                });
            deferred.promise.then(function (value) {
                $scope.waitingForClientData = value;
            });
        } else {
            staticData();
            initData();
            addCampsToScope();
            addCaseManagerToScope();

            $scope.client = {
                pregnant:"N/A"
            }
        /*
        $scope.client = {
             alias:"aaa",
             attitudeTowardHousing:"Interested",
             caseMgr:"John Buzz",
             clothing:true,
             dateOfBirth:"10/10/2000",
             emergencyContactName:"614-222-3333",
             emergencyContactPhone:"614-222-3333",
             firstName:"qq",
             gender:"Female",
             healthStatus:"Medium",
             housingStage:"Application",
             lastContactDate:"10/10/2010",
             lastName:"qq",
             mentalStatus:"Coping",
             mentalStatusDescription:"fdsfds",
             numOfChildren:"2",
             numPets:"3",
             pregnant:"No",
             primaryCamp:"Camp Three",
             primaryPhone:"614-222-3333",
             priority:"Medium",
             secondaryCamp:"Camp Two",
             secondaryPhone:"614-222-3333",
             status:"Active",
             substanceAbuseStatus:"Some",
             substanceDescription:"fdsfds",
             violentDescription:"fgfgsd",
             violentStatus:"Aggressive"
         }
         */
        }
    }

    init();
    //TODO move to utility module
    var endsWith = function (str, suffix) {
        return str.indexOf(suffix, str.length - suffix.length) !== -1;
    };

    var isActive = function (tabName) {
        return endsWith($location.path(), tabName);
    }

    $scope.isActive = isActive;

    $scope.casenotes = function () {
        $rootScope.clientId = $scope.client.id;
    }


    function associateCampToClient() {
        var values = $scope.client.camp;
        var campsBoundClient = [];
        angular.forEach(values, function (value, key) {
            var campName = value.campName;
            this.push(campName);
        }, campsBoundClient);
        if (campsBoundClient[0] != null) {
            $scope.primaryCamp = campsBoundClient[0];
        }
        if (campsBoundClient[1] != null) {
            $scope.secondaryCamp = campsBoundClient[1];
        }

    }

    function staticData() {
        $scope.statuses =
            ['Active', 'Housed', 'Deceased', 'Disappeared', 'Hospitalized'];
        $scope.clientPriorities =
            ['Low', 'Medium', 'High'];
        $scope.pregnantStages =
            ['N/A', 'No', '1st Trimester', '2nd Trimester', '3rd Trimester'];
        $scope.houseAttitudes =
            ['Refusal', 'Resistant', 'Tentative', 'Interested', 'Committed'];
        $scope.housingStages =
            ['Development', 'Application', 'Waiting', 'Pool', 'Approved', 'Housed'];
        $scope.needs = ['Transportation', 'Health care', 'Food', 'Clothing', 'Medical Supplies', 'Personal Hygiene', 'Linkage to Services'];
        $scope.numOfChildren = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10'];
        $scope.numPets = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10'];

    }

    function addCampsToScope() {
        CampService.getAllCamps(function (data) {
                $scope.camps = data;
                buildCamps();
            },
            function (err) {
                console.log(err);
            });

    }

    function determineAction() {
        if ($scope.client && $scope.client.id) {
            $scope.editAction = 'Update';

        }
        else {
            $scope.editAction = 'Add';
            $scope.title = 'Add Client';
        }
    }

    function buildFullName() {
        if ($scope.client && $scope.client.firstName && $scope.client.lastName) {
            $scope.title = $scope.client.firstName + ' ' + $scope.client.lastName;
        }

    }

    function addCaseManagerToScope() {
        var values = [];
        if ($scope.client != null) {
            values = $scope.client.listOfCaseMgr;
            $scope.caseMgr1 = $scope.client.listOfCaseMgr[0];
            $scope.caseMgr2 = $scope.client.listOfCaseMgr[1];
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

    function initData() {
        staticData();
        addCampsToScope();
        determineAction();
        buildFullName();
    }

    function buildCamps() {
        var values = $scope.camps;
        $scope.campsNamesList = [];
        angular.forEach(values, function (value, key) {
            var campName = value.name;
            this.push(campName);
        }, $scope.campsNamesList);

    }


    $scope.uploadPicture = function (clientId, type) {
        var URI = '/upload/client/'
        try {
            PhoneGapCameraService.uploadImage({'id':clientId, 'type':type},
                function (message) {
                    alert(error);
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
                    alert(error);
                    $scope.$apply(function () {
                        $scope.imgStatus = '';
                    });
                }, URI);

            $scope.imgStatus = 'loading';
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

    $scope.uploadHTML5Picture = function (clientId, file) {
        $scope.imgStatus = 'loading';
        var URI = '/upload/client/';

        try {
            //var file = document.getElementById('file').files[0];
            safeApply($scope, function() {
                $scope.imgStatus = 'loading';
            });
            var reader = new FileReader();
            reader.onload = function (e) {
                ImageUtil.resizeImage(e.target.result, IMAGE_RESIZE_WIDTH, function(file){
                    HTML5CameraService.uploadImage({'id': clientId, 'file': file},
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
    }

    $scope.isHybrid = function () {
        return window.isHybrid;
    }

    $scope.isWeb = function () {
        return !$scope.isHybrid();
    }

    $scope.createOrUpdate = function() {
        var fn = $scope.client.id ? ClientService.updateClient : ClientService.createClient;
        $scope.waitingForClientData = true;
        var deferred = $q.defer();
        fn($scope.client,
            function (data) {
                $rootScope.clientId = data.id;
                deferred.resolve('/client/detail');
                init(function () {
                    $scope.waitingForClientData = false;
                });
            },
            function (err) {
                console.log('error in createOrUpdate', err);
                $scope.error = "Invalid Data";
                $scope.errors = err.data.errors;
                $scope.waitingForClientData = false;
                deferred.resolve('/client/edit');
            });

        deferred.promise.then(function (value) {
            $location.path(value);
        });
    }

    $scope.navClick = function () {
        click = $scope[$scope.nav.click];
        if (typeof click === 'function') {
            click();
        }
    }


    $scope.removeClient = function () {
        var deferred = $q.defer();
        var confirmRemoval = confirm('Are you sure you want to remove this client?');
        if (!confirmRemoval) {
            deferred.resolve('/client/detail');
        }
        else {
            ClientService.removeClient({clientId:$scope.client.id},
                function (data) {
                    console.log('data in ClientController.removeClient', data);
                    deferred.resolve('/clients');
                },
                function (err) {
                    console.log('error in ClientController.removeClient', err);
                    deferred.resolve('/client/detail');
                    alert('Error removing client');
                });
        }
        deferred.promise.then(function (value) {
            $location.path(value);
        });
    }


    $scope.cancel = function () {
        if ($scope.client && $scope.client.id) {
            $scope.cancelHref = '#/client/detail';
            $rootScope.clientId = $scope.client.id;
            init();
        }
        else {
            $scope.cancelHref = '#/clients';
            $scope.client = null;
            $rootScope.clientId = null;
        }


    }

    $scope.enterEdit = function () {
        $scope.title = 'Edit Client';
    }

    $scope.enterEditChildren = function () {
        $scope.title = 'Edit Children';
        //TODO testing only
        // $scope.client.children = [
        //{'firstName':'Mike', 'lastName': 'Doe', 'gender':'Male', 'present': true},
        //{'firstName':'Maria', 'lastName': 'Doe',  'gender':'Female', 'present': false}
        //];
    }

    $scope.enterEditState = function () {
        if ($scope.client && $scope.client.id) {
            $scope.title = 'Edit State';
        }
    }

    $scope.enterEditHouse = function () {
        if ($scope.client && $scope.client.id) {
            $scope.title = 'Edit House';
        }
    }

    $scope.enterEditContact = function () {
        if ($scope.client && $scope.client.id) {
            $scope.title = 'Edit Contacts';
        }
    }


}
