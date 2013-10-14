
app.factory('NewsFeedService', function ($resource, NEWS_RESOURCE_URL) {
var newsFactory = {};

var news = $resource(NEWS_RESOURCE_URL + "/:newsFeedId", {}, {
query:{method:'GET'},
delete:{method:'DELETE'}

});

var newsUpdate = $resource(NEWS_RESOURCE_URL + "/updateNews", {}, {
update:{method:'POST'}
});

var newsCreate = $resource(NEWS_RESOURCE_URL + "/createNews", {}, {
save:{method:'POST'}
});

var getAllNews = $resource(NEWS_RESOURCE_URL + "/news", {}, {
query:{method:'GET', isArray: true }
});

 var findAllComments = $resource(NEWS_RESOURCE_URL + "/comments", {}, {
     query:{method:'GET', isArray: true }
});

    var newsDetails = $resource(NEWS_RESOURCE_URL + "/newsDetails", {}, {
        query:{method:'GET'}
});

    var eventDetails = $resource(NEWS_RESOURCE_URL + "/newsRetrieve", {}, {
        query:{method:'GET'}
    });

    var getAllNewsByPage = $resource(NEWS_RESOURCE_URL + "/page", {}, {
        query:{method:'GET', isArray: false }
    });


// New URI
newsFactory.getAllNews = getAllNews.query;
newsFactory.getAllNewsByPage = getAllNewsByPage.query;
newsFactory.updateNews = newsUpdate.update;
newsFactory.createNews = newsCreate.save;
newsFactory.newsDetails = newsDetails.query;
newsFactory.findAllComments = findAllComments.query;
newsFactory.getEventNews = eventDetails.query;


return newsFactory;


});
