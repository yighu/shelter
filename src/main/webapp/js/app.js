'use strict';


// Declare app level module which depends on filters, and services
var app = angular.module('CommunityShelterApp',['ngResource', 'Centralway.lungo-angular-bridge']);

app.config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
    $routeProvider.when('/home', {templateUrl: "partials/home.html", controller: "HomeCtrl"});
    $routeProvider.when('/clients', {templateUrl: "partials/client-list.html", controller: "ClientListCtrl"});
    $routeProvider.when('/client/detail', {templateUrl: "partials/client.html", controller: "ClientCtrl"});
    $routeProvider.when('/notes', {templateUrl: "partials/notes.html", controller: "CaseNotesCtrl"});
    $routeProvider.when('/addNotes', {templateUrl: "partials/addNotes.html", controller: "CaseNotesCreateCtrl"});
    $routeProvider.when('/client/edit', {templateUrl: "partials/client.html", controller: "ClientCtrl"});
    $routeProvider.when('/client/editChildren', {templateUrl: "partials/client.html", controller: "ClientCtrl"});
    $routeProvider.when('/client/editState', {templateUrl: "partials/client.html", controller: "ClientCtrl"});
    $routeProvider.when('/client/editHouse', {templateUrl: "partials/client.html", controller: "ClientCtrl"});
    $routeProvider.when('/client/editContact', {templateUrl: "partials/client.html", controller: "ClientCtrl"});
    $routeProvider.when('/client/caseNotes', {templateUrl: "partials/client.html", controller: "CaseNotesCtrl"});
    $routeProvider.when('/client/addCaseNotes', {templateUrl: "partials/client.html", controller: "CaseNotesCtrl"});
    $routeProvider.when('/camps/list', {templateUrl: "partials/camp-list.html", controller: "CampListCtrl"});
    $routeProvider.when('/camps/map', {templateUrl: "partials/camp-list.html", controller: "CampListCtrl"});
    $routeProvider.when('/camp/detail', {templateUrl: "partials/camp.html", controller: "CampCtrl"});
    $routeProvider.when('/camp/edit', {templateUrl: "partials/camp.html", controller: "CampCtrl"});
    $routeProvider.when('/camp/editDesc', {templateUrl: "partials/camp.html", controller: "CampCtrl"});
    $routeProvider.when('/camp/editEntity', {templateUrl: "partials/camp.html", controller: "CampCtrl"});
    $routeProvider.when('/scheduler/camps', {templateUrl: "partials/scheduler.html", controller: "SchedulerCtrl"});
    $routeProvider.when('/scheduler/calendar', {templateUrl: "partials/scheduler.html", controller: "SchedulerCtrl"});
    $routeProvider.when('/scheduler/scheduleVisit', {templateUrl: "partials/scheduler.html", controller: "SchedulerCtrl"});
    $routeProvider.when('/sos', {templateUrl: "partials/sos.html", controller: "SosCtrl"});
    $routeProvider.when('/search', {templateUrl: "partials/search.html", controller: "SearchCtrl"});
    $routeProvider.when('/searchResults/clientSearch', {templateUrl: "partials/searchResults.html", controller: "SearchResultsCtrl"});
    $routeProvider.when('/searchResults/campSearch', {templateUrl: "partials/searchResults.html", controller: "SearchResultsCtrl"});
    $routeProvider.when('/news', {templateUrl: "partials/news.html", controller: "NewsCtrl"});
    $routeProvider.when('/newsDetails/addReply', {templateUrl: "partials/newsDetails.html", controller: "NewsDetailsCtrl"});
    $routeProvider.when('/newsDetails/comments', {templateUrl: "partials/newsDetails.html", controller: "NewsDetailsCtrl"});
    $routeProvider.when('/adminlist', {templateUrl: "partials/admin-list.html", controller: "AdminListCtrl"});
    $routeProvider.when('/adminedit', {templateUrl: "partials/admin.html", controller: "AdminCtrl"});
    $routeProvider.when('/featureList', {templateUrl: "partials/feature-list.html", controller: "FeatureListCtrl"});
    $routeProvider.when('/featureEdit', {templateUrl: "partials/feature.html", controller: "FeatureCtrl"});
    $routeProvider.when('/privilegeList', {templateUrl: "partials/privilege-list.html", controller: "PrivilegeListCtrl"});
    $routeProvider.when('/privilegeEdit', {templateUrl: "partials/privilege.html", controller: "PrivilegeCtrl"});
    $routeProvider.when('/roleList', {templateUrl: "partials/role-list.html", controller: "RoleListCtrl"});
    $routeProvider.when('/roleEdit', {templateUrl: "partials/role.html", controller: "RoleCtrl"});
    $routeProvider.otherwise({redirectTo: '/home'});
    $locationProvider.html5Mode(false);
}]);


