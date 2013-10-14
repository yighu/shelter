app.factory('FeatureService', function ($resource, FEATURE_RESOURCE_URL) {

    var featureFactory = {};
    var featureUpdate = $resource(FEATURE_RESOURCE_URL + "/update", {}, {
            update:{method:'POST'}
    });

    var featureCreate = $resource(FEATURE_RESOURCE_URL + "/add", {}, {
             save:{method:'POST'}
    });

             var deleteFeature = $resource(FEATURE_RESOURCE_URL + "/delete", {}, {
                           delete:{method:'POST'}
                });

                var getAllFeatures = $resource(FEATURE_RESOURCE_URL + "/list", {}, {
                           query:{method:'GET', isArray: true }

                });

                var featureDetails = $resource(FEATURE_RESOURCE_URL + "/details", {}, {
                            query:{method:'GET'}
                    });
                    var getAllWorkFlowFeatures = $resource(FEATURE_RESOURCE_URL + "/listPrivileges", {}, {
                                               query:{method:'GET', isArray: true }

                                    });

              featureFactory.getAllFeatures = getAllFeatures.query;
              featureFactory.getAllWorkFlowFeatures = getAllWorkFlowFeatures.query;
              featureFactory.updateFeature = featureUpdate.update;
              featureFactory.createFeature = featureCreate.save;
              featureFactory.featureDetails = featureDetails.query;
              featureFactory.deleteFeature = deleteFeature.delete;

            return featureFactory;

});
