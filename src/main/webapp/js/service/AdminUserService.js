app.factory('AdminUserService', function ($resource, USER_RESOURCE_URL) {

    var userFactory = {};
                    //listByPage
    var userUpdate = $resource(USER_RESOURCE_URL + "/update", {}, {
            update:{method:'POST'}
    });
             
    var userCreate = $resource(USER_RESOURCE_URL + "/add", {}, {
             save:{method:'POST'}
    });

    var deleteUser = $resource(USER_RESOURCE_URL + "/delete", {}, {
             delete:{method:'POST'}
    });

    var getAllUsers = $resource(USER_RESOURCE_URL + "/list", {}, {
             query:{method:'GET', isArray: true }
    });
             
    var userDetails = $resource(USER_RESOURCE_URL + "/details", {}, {
             query:{method:'GET'}
    });

    var getAllCaseMgr = $resource(USER_RESOURCE_URL + "/managerList", {}, {
             query:{method:'GET', isArray: true }
    });

    var getAllUsersByPage = $resource(USER_RESOURCE_URL + "/listByPage", {}, {
               query:{method:'GET', isArray: false }
    });

    userFactory.getAllCaseMgr = getAllCaseMgr.query;
    userFactory.getAllUsers = getAllUsers.query;
    userFactory.getAllUsersByPage = getAllUsersByPage.query;
    userFactory.updateUser = userUpdate.update;
    userFactory.createUser = userCreate.save;
    userFactory.userDetails = userDetails.query;
    userFactory.deleteUser = deleteUser.delete;

    return userFactory;

});
