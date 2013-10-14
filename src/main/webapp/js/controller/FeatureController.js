function FeatureListCtrl($scope, $rootScope, FeatureService) {
    init();

    function init() {
         $scope.title = 'Feature';
         $rootScope.featureId = null;
        FeatureService.getAllFeatures(function (data) {
                $scope.features = data;

            },
            function (err) {
                console.log(err);
            });
    }

    $scope.enterDetail = function() {
        $rootScope.featureId = this.feature.featureId;
         console.log( $rootScope.FeatureId);

    }

}


function FeatureCtrl($scope, $rootScope, FeatureService, PrivilegeService, RoleService) {

    var featureId = $rootScope.featureId;
    $rootScope.featureId = null;
    $rootScope.feature = null;
    if(featureId  != null){
        FeatureService.featureDetails({'featureId': featureId}, function (data) {
                $scope.feature = data;
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



    $scope.editFeature = function () {
        init();
        var fn = $scope.feature.featureId ? FeatureService.updateFeature : FeatureService.createFeature;
        fn($scope.feature, function (data) {
                console.log('received data', data);
            },
            function (err) {
                console.log('error', err);
        });

    }

    $scope.enterEdit = function() {
        $scope.title = 'Edit Feature';
    }

    $scope.enterAdd = function() {
        $scope.title = 'Add Feature';
    }

    function determineAction(){
        if ($scope.feature) {
            $scope.editAction = 'Update';
             $scope.enterEdit();
        }
        else {
            $scope.editAction = 'Add';
           $scope.title = 'Add Feature';
        }
    }

   function staticData() {
      RoleService.getAllRoles(function (data) {
                   $scope.roles  = allRoles(data);

               },
               function (err) {
                   console.log(err);
      });

      PrivilegeService.getAllPrivileges(function (data) {
                        $scope.actions = allPrivileges(data);

                   },
                   function (err) {
                       console.log(err);
      });


      $scope.workFlows = ['Camp', 'Client', 'Event', 'News', 'Admin'];
      $scope.features = ['query', 'details', 'add', 'update', 'list', 'delete','campCheckIn','scheduleVisit','allEvent','campSchedule','listNews','listComments'];

     }


   function allRoles(data) {
            var roles = [];
            angular.forEach(data, function(value, key) {
                     var role = value.roleType;
                     this.push(role);
            }, roles);
            return roles;
   }


   function allPrivileges(data) {
               var privileges = [];
               angular.forEach(data, function(value, key) {
                        var privilege = value.privilegeType;
                        this.push(privilege);
               }, privileges);
               return privileges;
      }

}