function SearchCtrl($scope, $rootScope)
{
    $scope.title = 'Search';

    $scope.enterClient = function(name) {
        $rootScope.name = name;
        $rootScope.isClient = 'Y';

    }
    $scope.enterCamp = function(name) {
        $rootScope.name = name;
        $rootScope.isClient = 'N';

    }
}

function SearchResultsCtrl($scope, $rootScope, ClientService, CampService, $timeout, CLIENT_LIST_DELAY) {

    $timeout(function() {
        init();
    }, CLIENT_LIST_DELAY);


    function PagingHelper(object)
    {
        $scope.currentPage = 0;
        $scope.pageSize = 5;
        $scope.data = [];
        $scope.numberOfPages = function() {
            return Math.ceil(object.length/$scope.pageSize);
        }

        for(var i=0; i<object.length; i++)
        {
            $scope.data.push(object[i]);
        }
    }
    function init() {
       var name = $rootScope.name;
        if($rootScope.isClient == 'Y')
        {
            clientSearch(name);
        }
        else if ($rootScope.isClient == 'N')
        {

            campSearch(name);
        }
        else
        $scope.clientNumResults = "No results for undefined search entry ";
    }
     function clientSearch(firstName) {
        try
        {
            if(firstName != " " && firstName != null && firstName != undefined)
            {
                console.log(hasWhiteSpace(firstName))

                    ClientService.search({'firstName': firstName}, function (data) {
                    if(data[0] != undefined)
                    {
                        $scope.clients = data;
                        var listItems = $scope.clients.length;
                        $scope.clientNumResults = listItems + " results";

                   //     PagingHelper($scope.clients);
                    }
                    else
                    {
                        if(firstName != undefined)
                            $scope.clientNumResults = "No results for search entry " + firstName;
                        else
                            $scope.clientNumResults = "No results for undefined search entry ";

                    }
                },
                function (err) {
                    $scope.clientNumResults = "An error has occurred";
                    console.log(err);

                });
            }
            else
            {
                if(firstName == undefined)
                {
                    firstName = '';
                    ClientService.search({'firstName': firstName}, function (data) {
                        if(data[0] != undefined)
                        {
                            $scope.clients = data;
                            var listItems = $scope.clients.length;
                            $rootScope.name = '';
                            $scope.clientNumResults = listItems + " results";
                //            PagingHelper($scope.clients);
                        }
                    },
                        function (err) {
                            console.log(err);
                            $scope.clientNumResults = "An error has occurred";

                        });

                }
                else
                {
                    $scope.clientNumResults = "No results for undefined search entry";
                }
            }
        }
        catch(err)
        {
            $scope.clientNumResults = "An error has occurred";
            console.log(err);
        }
    }

    function campSearch(campName) {
        try
        {

            if(campName != " " && campName != null && campName != undefined)
            {
                CampService.search({'campName': campName}, function (data) {
                if(data[0] != undefined)
                {
                    $scope.camps = data;
                    $rootScope.name = '';
                    var listItems = $scope.camps.length;
                    $scope.campNumResults = listItems + " results";
                //    PagingHelper($scope.camps);
                }
                else {
                    $scope.campNumResults ="No results for search entry " + campName;
                }
               },
               function (err) {
                        console.log(err);
                        $scope.campNumResults ="An error has occurred";
               });

            }
            else
            {
                campName = '';
                CampService.search({'campName': campName}, function (data) {
                        $scope.camps = data;
                        $rootScope.name = '';
                        var listItems = $scope.camps.length;
                        $scope.campNumResults = listItems + " results";
                //        PagingHelper($scope.camps);
                    },
                    function (err) {
                        console.log(err);
                    });
            }
        }
        catch(err)
        {
            $scope.campNumResults = "An error has occurred";
            console.log(err);
        }
    }

    $scope.addCampDetailsToScope = function () {
        $rootScope.campId = this.camp.id;
    }

    $scope.enterDetail = function() {
        $rootScope.clientId = this.client.id;
    }

    $rootScope.imageURL = function(clientId) {
        return imageURL(clientId);
    }

/*    $scope.filter('startFrom', function() {
        return function(input, start) {
            start = +start;
            return input.slice(start);
        }
    });
 */
}

function hasWhiteSpace(s) {
    return /\s/g.test(s);
}

