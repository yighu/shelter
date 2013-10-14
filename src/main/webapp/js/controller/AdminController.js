function AdminListCtrl($scope, $rootScope, AdminUserService, ADMIN_LIST_PAGE_SIZE) {
    init();
    function init() {
        $scope.title = 'Admin';
        $rootScope.userId = null;
        $scope.currentPage = 1;
        firePageRequest(buildPageRequestObject($scope.currentPage));
    }

    $scope.enterDetail = function () {
        $rootScope.userId = this.user.userId;
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
        AdminUserService.getAllUsersByPage(pageRequest, function (data) {
                $scope.users = data.content;
                $scope.page = buildPageObject(data);
                $scope.userDataReceived = true;
                $scope.currentPage = pageRequest.page;

            },
            function (err) {
                $scope.userDataReceived = false;
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
        pageRequest.size = ADMIN_LIST_PAGE_SIZE;
        pageRequest.page = pageNumber;
        return pageRequest;
    }
}


function AdminCtrl($scope, $rootScope, AdminUserService, RoleService) {

    var userId = $rootScope.userId;
    $rootScope.userId = null;
    $rootScope.user = null;
    if (userId != null) {
        AdminUserService.userDetails({'userId':userId}, function (data) {
                $scope.user = data;
                init();


            },
            function (err) {
                console.log(err);
            });
    } else {
        init();
    }

    function init() {
        RoleService.getAllRoles(function (data) {
                $scope.roles = allRoles(data);

            },
            function (err) {
                console.log(err);
            });

        determineAction();

    }

    function allRoles(data) {
        var roles = [];
        angular.forEach(data, function (value, key) {
            var role = value.roleType;
            this.push(role);
        }, roles);
        return roles;
    }


    $scope.editUser = function () {
        init();
        var fn = $scope.user.userId ? AdminUserService.updateUser : AdminUserService.createUser;
        fn($scope.user, function (data) {
                console.log('received data', data);
            },
            function (err) {
                console.log('error', err);
            });

    }

    $scope.enterEdit = function () {
        $scope.title = 'Edit User';
    }

    $scope.enterAdd = function () {
        $scope.title = 'Add User';
    }

    function determineAction() {
        if ($scope.user) {
            $scope.editAction = 'Update';
            $scope.enterEdit();
        }
        else {
            $scope.editAction = 'Add';
            $scope.title = 'Add User';
        }
    }


}