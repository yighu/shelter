function RoleListCtrl($scope, $rootScope, RoleService) {
              init();
        function init() {
         $scope.title = 'Role';
         $rootScope.roleId = null;
        RoleService.getAllRoles(function (data) {
                $scope.roles = data;

            },
            function (err) {
                console.log(err);
            });
    }

    $scope.enterDetail = function() {
        $rootScope.roleId = this.role.roleId;
         console.log( $rootScope.roleId);

    }

}


function RoleCtrl($scope, $rootScope, RoleService) {

    var roleId = $rootScope.roleId;
    $rootScope.roleId = null;
    if(roleId  != null){
        RoleService.roleDetails({'roleId': roleId}, function (data) {
                $scope.role = data;
                init();
         },
            function (err) {
                console.log(err);
            });
    } else{
        init();
        $scope.role = null;
    }

    function init() {
        staticData();
        determineAction();

    }


    function staticData() {
        $scope.roles =
            ['Admin', 'Case Manager', 'Other'];
    }

    $scope.editRole = function () {
        init();
        var fn = $scope.role.roleId ? RoleService.updateRole : RoleService.createRole;
        fn($scope.role, function (data) {
                console.log('received data', data);
            },
            function (err) {
                console.log('error', err);
        });

    }

    $scope.enterEdit = function() {
        $scope.title = 'Edit Role';
    }

    $scope.enterAdd = function() {
        $scope.title = 'Add Role';
    }

    function determineAction(){
        if ($scope.role) {
            $scope.editAction = 'Update';
             $scope.enterEdit();
        }
        else {
            $scope.editAction = 'Add';
            $scope.title = 'Add Role';
        }
    }



}