package com.ffg.shelter.converter;

import com.ffg.shelter.model.Feature;
import com.ffg.shelter.view.FeatureView;


public interface FeatureTransformer {

    FeatureView transformFromEntityToFeatureView(Feature feature);

    Feature transformFromViewToEntity(FeatureView featureView);

}
