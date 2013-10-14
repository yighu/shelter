function HomeCtrl($scope, $rootScope, RoleService) {
        init();

    function init() {
        $scope.title = 'CSB';
        checkAdmin(RoleService,  $rootScope);
    }
}