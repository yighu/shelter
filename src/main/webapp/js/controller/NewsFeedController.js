

function NewsCtrl($scope, $rootScope, NewsFeedService, $q,  $timeout, NEWS_LIST_PAGE_SIZE, NEWS_LIST_DELAY) {

    $timeout(function() {
        console.log("IN");
        init();
    }, NEWS_LIST_DELAY);

    function init() {

        $scope.isNewsAction = 0;
        $rootScope.news = null;
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
        NewsFeedService.getAllNewsByPage(pageRequest, function (data) {
               $scope.currentPage = pageRequest.page;
               $scope.news = data.content;
               $scope.page = buildPageObject(data);
               $scope.showComments = false;
               $scope.message = "";
               deferred.resolve(true);

            },
            function (err) {

                $scope.showComments = true;
                console.log(err);
                $scope.error = 'Error getting data';
                deferred.resolve(true);
            });
        $scope.enterNewsFeed();


        deferred.promise.then(function (value) {
            $scope.newsDataReceived = value;
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
        pageRequest.size = NEWS_LIST_PAGE_SIZE;
        pageRequest.page = pageNumber;
        return pageRequest;

    }




   $scope.addcomment = function() {
       $rootScope.newsId = this.news.eventId;
   //    $rootScope.news= this.news;

    };

    $scope.enterNewsFeed = function() {
        $scope.title = 'Newsfeed';
    }

    $scope.show = function (index) {
        newsId = this.news.eventId;
        NewsFeedService.findAllComments({'newsFeedId': newsId}, function (data) {
                $scope.comments = data;
            },
            function (err) {
                console.log(err);
            });
        $scope.isNewsAction = index;
    };

    $scope.isShowing = function(index){
        return  $scope.isNewsAction === index;
    };

}


function NewsDetailsCtrl($scope, $timeout, $rootScope, $q, $location, NewsFeedService, CampService, NEWS_LIST_DELAY) {

    $timeout(function() {
        console.log("IN");
        init();
    }, NEWS_LIST_DELAY);


  //  $scope.comments = $rootScope.comments;

    //TODO move to utility module
    var endsWith = function (str, suffix) {
        return str.indexOf(suffix, str.length - suffix.length) !== -1;
    };

    var isActive = function (tabName) {
        return endsWith($location.path(), tabName);
    }

    $scope.isActive = isActive;

   function addCampsToScope() {
        CampService.getAllCamps(function (data) {
                $scope.camps = data;
		console.log("camps:");
		console.log(data);
                buildCamps();
            },
            function (err) {
                console.log(err);
            });

    }

    function init() {
        $scope.replycharCount = 150;
        $scope.newsId = $rootScope.newsId;
        var newsId = $scope.newsId;

        NewsFeedService.getEventNews({'eventId': newsId}, function (data) {
                $scope.news = data;
            },
            function (err) {
                console.log(err);
            });
        ;


        $scope.comments = null;
    //    enterNews();
        staticData();
      //  addCampsToScope();
        determineAction();
    }

    function staticData()
    {
        $scope.types = ['COMMENT', 'UPDATE', 'ALERT', 'GENERAL'];
        $scope.replycharCount = 150;
    }
    function buildCamps(){
        var values = $scope.camps;
        $scope.campsNamesList = [];
        angular.forEach(values, function(value, key){
            var campName =   value.name;
            this.push(campName);
        }, $scope.campsNamesList);

    }

    $scope.isHybrid = function() {
        //TODO
        return true;
    }

    $scope.isWeb = function() {
        //TODO
        return true;
    }

/*    $scope.editClient = function () {
        init();

        $scope.enterNews();

        //TODO handle children
        $scope.client.children = null;

        var fn = $scope.client.id ? ClientService.updateClient : ClientService.createClient;
        fn($scope.client, function (data) {
                console.log('received data', data);
            },
            function (err) {
                console.log('error', err);
            });

    }
*/
    $scope.navClick = function() {
        click = $scope[$scope.nav.click];
        if(typeof click === 'function') {
            click();
        }
    }

    $scope.cancel = function () {
        $scope.news = null;
    }

    $scope.enterNews = function() {
        $scope.title = 'Submit Reply';
    }

    $scope.charCount = function() {
      var char = 150;
        var newcount = char - $scope.comments.comments.length;
        if(newcount < 0)
        {
            newcount = 0;
            $scope.comments.comments= $scope.comments.comments.substr(0,150);
        }
      $scope.replycharCount = newcount;

    }

    $scope.enterNews = function() {
        $scope.title = 'Create News Entry';
    }

    function determineAction(){
            $scope.editAction = 'Create';
            $scope.title = 'Create News';
    }

    $scope.createNews = function () {
   //     init();

        $scope.enterNews();
        var deferred = $q.defer();
        //TODO handle children
        try{
            if($scope.comments == null)
            {
                $scope.error = "Error: Complete this page to add a comment.";
            }
            else
            {
                $scope.comments.eventId = $scope.news.eventId;
                $scope.comments.campName = $scope.news.campName;

                var fn =  NewsFeedService.createNews;
                if($scope.comments.messageType != '' && $scope.comments.messageType != null && $scope.comments.messageType != 'Please Select')
                {
                    if($scope.comments.comments != '' && $scope.comments.comments != null)
                    {
                        fn($scope.comments, function (data) {
                            console.log('received data', data);
                            $scope.news = null;

                            $rootScope.newsId = null;
                            deferred.resolve('/news');
                        },
                        function (err) {
                            console.log('error', err);
                            $scope.error = "Error: Unable to submit comment";
                        });
                    }
                    else
                        $scope.error = "Error: comment field is empty";
                }
                else
                    $scope.error = "Error: a message type was not selected";
            }
            deferred.promise.then(function (value) {
                $location.path(value);
            });
        }
        catch(err)
        {
            console.log('error', err);
            $scope.error = "Error";
        }
    }
}


