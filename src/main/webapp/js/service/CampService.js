/**
 * Created with JetBrains WebStorm.
 * User: N060974
 * Date: 5/19/13
 * Time: 11:06 AM
 * To change this template use File | Settings | File Templates.
 */
app.factory('CampService', function ($resource, CAMP_RESOURCE_URL) {
    var campFactory = {};

    var allCamps = $resource(CAMP_RESOURCE_URL, {}, {
        save:{method:'POST'},
        query:{method:'GET'},
        update:{method:'POST'}

    });

    var queryCamps = $resource(CAMP_RESOURCE_URL,  {}, {
        query:{method:'GET'}

    });

    var camps = $resource(CAMP_RESOURCE_URL + "/:campId", {}, {
        query:{method:'GET'},
        delete:{method:'DELETE'}

    });

    var campSearch = $resource(CAMP_RESOURCE_URL + "/query", {}, {
        search:{
            method:'GET',
            isArray: true
        }
    });


    var campUpdate = $resource(CAMP_RESOURCE_URL + "/update", {}, {
        update:{method:'POST'}
    });

    var campCreate = $resource(CAMP_RESOURCE_URL + "/add", {}, {
            save:{method:'POST'}
    });

   var deleteCamp = $resource(CAMP_RESOURCE_URL + "/delete", {}, {
              del:{method:'DELETE'}
   });

   var getAllCamps = $resource(CAMP_RESOURCE_URL + "/list", {}, {
              query:{method:'GET', isArray: true }

   });

   var campDetails = $resource(CAMP_RESOURCE_URL + "/details", {}, {
               query:{method:'GET'}
       });

    var getAllCampsByPage = $resource(CAMP_RESOURCE_URL + "/listByPage", {}, {
                   query:{method:'GET', isArray: false }
        });

    campFactory.search = campSearch.search;
    campFactory.getAllCamps = getAllCamps.query;
    campFactory.getAllCampsByPage = getAllCampsByPage.query;
    campFactory.createCamp = campCreate.save;
    campFactory.updateCamp = campUpdate.update;
    campFactory.campDetails = campDetails.query;
    campFactory.removeCamp = deleteCamp.del;

    return campFactory;


});