/**
 * Created with JetBrains WebStorm.
 * User: N060974
 * Date: 5/19/13
 * Time: 11:06 AM
 * To change this template use File | Settings | File Templates.
 */
app.factory('ClientService', function ($resource, CLIENT_RESOURCE_URL) {
    var clientFactory = {};

    var allClients = $resource(CLIENT_RESOURCE_URL, {}, {
        save:{method:'POST'},
        query:{method:'GET'},
        update:{method:'POST'}

    });

    var client = $resource(CLIENT_RESOURCE_URL + "/:clientId", {}, {
        query:{method:'GET'},
        delete:{method:'DELETE'}

    });

    var clientUpdate = $resource(CLIENT_RESOURCE_URL + "/update", {}, {
        update:{method:'POST'}
    });

    var clientCreate = $resource(CLIENT_RESOURCE_URL + "/add", {}, {
            save:{method:'POST'}
    });

   var deleteClient = $resource(CLIENT_RESOURCE_URL + "/delete", {}, {
              del:{method:'DELETE'}
   });

   var getAllClients = $resource(CLIENT_RESOURCE_URL + "/list", {}, {
              query:{method:'GET', isArray: true }

   });

   var clientDetails = $resource(CLIENT_RESOURCE_URL + "/details", {}, {
               query:{method:'GET'}
       });

    var clientSearch = $resource(CLIENT_RESOURCE_URL + "/query", {}, {
        search:{
            method:'GET',
            isArray: true
        }
    });

     var getAllClientsByPage = $resource(CLIENT_RESOURCE_URL + "/listByPage", {}, {
                       query:{method:'GET', isArray: false }
            });

     clientFactory.getAllClientsByPage = getAllClientsByPage.query;
     clientFactory.search = clientSearch.search;
     clientFactory.getAllClients = getAllClients.query;
     clientFactory.updateClient = clientUpdate.update;
     clientFactory.createClient = clientCreate.save;
     clientFactory.removeClient = deleteClient.del;
     clientFactory.clientDetails = clientDetails.query;

    return clientFactory;


});


