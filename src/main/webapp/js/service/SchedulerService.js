/**
 * Created with JetBrains WebStorm.
 * User: N060974
 * Date: 5/19/13
 * Time: 11:06 AM
 * To change this template use File | Settings | File Templates.
 */
 app.factory('SchedulerService', function ($resource, SCHEDULER_RESOURCE_URL) {


    var schedulerFactory = {};



    var getCampSchedule = $resource(SCHEDULER_RESOURCE_URL + "/campSchedule", {}, {
                            query:{method:'GET', isArray: true }

                 });

    var scheduleVisit = $resource(SCHEDULER_RESOURCE_URL + "/scheduleVisit", {}, {
                  save:{method:'POST'}

       });

    var campCheckIn = $resource(SCHEDULER_RESOURCE_URL + "/campCheckIn", {}, {
                  save:{method:'POST'}

       });

    var allEvent = $resource(SCHEDULER_RESOURCE_URL + "/allEvent", {}, {
                  query:{method:'GET', isArray: true }

       });


    schedulerFactory.getEvents = allEvent.query;
    schedulerFactory.getCampSchedule = getCampSchedule.query;
    schedulerFactory.scheduleVisit = scheduleVisit.save;
    schedulerFactory.campCheckIn = campCheckIn.save;
    schedulerFactory.allEvent = allEvent.query;


    return schedulerFactory;


});