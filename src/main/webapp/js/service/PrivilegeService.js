app.factory('PrivilegeService', function ($resource, PRIVILEGE_RESOURCE_URL) {

    var privilegeFactory = {};
    var privilegeUpdate = $resource(PRIVILEGE_RESOURCE_URL + "/update", {}, {
            update:{method:'POST'}
    });

    var privilegeCreate = $resource(PRIVILEGE_RESOURCE_URL + "/add", {}, {
             save:{method:'POST'}
    });

             var deletePrivilege = $resource(PRIVILEGE_RESOURCE_URL + "/delete", {}, {
                           delete:{method:'POST'}
                });

                var getAllPrivileges = $resource(PRIVILEGE_RESOURCE_URL + "/all", {}, {
                           query:{method:'GET', isArray: true }

                });

                var privilegeDetails = $resource(PRIVILEGE_RESOURCE_URL + "/details", {}, {
                            query:{method:'GET'}
                    });

              privilegeFactory.getAllPrivileges = getAllPrivileges.query;
              privilegeFactory.updatePrivilege = privilegeUpdate.update;
              privilegeFactory.createPrivilege = privilegeCreate.save;
              privilegeFactory.privilegeDetails = privilegeDetails.query;
              privilegeFactory.deletePrivilege = deletePrivilege.delete;

            return privilegeFactory;

});
