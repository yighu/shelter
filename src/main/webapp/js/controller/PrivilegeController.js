function PrivilegeListCtrl($scope, $rootScope, PrivilegeService) {
    init();
    function init() {
         $scope.title = 'Privilege';
         $rootScope.privilegeId = null;
        PrivilegeService.getAllPrivileges(function (data) {
                $scope.privileges = data;
                var privilege = $scope.privileges[0];
                var typePr = privilege.privilegeType;

            },
            function (err) {
                console.log(err);
            });
    }

    $scope.enterDetail = function() {
        $rootScope.privilegeId = this.privilege.id;
         console.log( $rootScope.privilegeId);

    }

}


function PrivilegeCtrl($scope, $rootScope, PrivilegeService) {

    var privilegeId = $rootScope.privilegeId;
    $rootScope.privilegeId = null;
    $rootScope.privilege = null;
    if(privilegeId  != null){
        PrivilegeService.PrivilegeDetails({'privilegeId': privilegeId}, function (data) {
                $scope.Privilege = data;
                init();


            },
            function (err) {
                console.log(err);
            });
    } else{
        init();
    }

    function init() {
        staticData();
        determineAction();

    }

    function staticData() {
        $scope.actions =
            ['Update', 'View'];
    }

    $scope.editPrivilege = function () {
        init();
        var fn = $scope.privilege.privilegeId ? PrivilegeService.updatePrivilege : PrivilegeService.createPrivilege;
        fn($scope.privilege, function (data) {
                console.log('received data', data);
            },
            function (err) {
                console.log('error', err);
        });

    }

    $scope.enterEdit = function() {
        $scope.title = 'Edit Privilege';
    }

    $scope.enterAdd = function() {
        $scope.title = 'Add Privilege';
    }

    function determineAction(){
        if ($scope.Privilege    ) {
            $scope.editAction = 'Update';
             $scope.enterEdit();
        }
        else {
            $scope.editAction = 'Add';
           $scope.title = 'Add Privilege';
        }
    }



}