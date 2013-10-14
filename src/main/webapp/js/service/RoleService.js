app.factory('RoleService', function ($resource, ROLE_RESOURCE_URL) {

    var roleFactory = {};
    var roleUpdate = $resource(ROLE_RESOURCE_URL + "/update", {}, {
            update:{method:'POST'}
    });

    var roleCreate = $resource(ROLE_RESOURCE_URL + "/add", {}, {
             save:{method:'POST'}
    });

             var deleteRole = $resource(ROLE_RESOURCE_URL + "/delete", {}, {
                           delete:{method:'POST'}
                });

                var getAllRoles = $resource(ROLE_RESOURCE_URL + "/all", {}, {
                           query:{method:'GET', isArray: true }

                });

                var roleDetails = $resource(ROLE_RESOURCE_URL + "/details", {}, {
                            query:{method:'GET'}
                    });

                    var checkAdmin = $resource("admin", {}, {
                                                query:{method:'GET'}
                                        });

              roleFactory.getAllRoles = getAllRoles.query;
              roleFactory.checkAdmin = checkAdmin.query;
              roleFactory.updateRole = roleUpdate.update;
              roleFactory.createRole = roleCreate.save;
              roleFactory.roleDetails = roleDetails.query;
              roleFactory.deleteRole = deleteRole.delete;

            return roleFactory;

});
