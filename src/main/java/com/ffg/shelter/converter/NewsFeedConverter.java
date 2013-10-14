package com.ffg.shelter.converter;

import com.ffg.shelter.model.NewsType;

public class NewsFeedConverter {


    public static NewsType convertNewsType(String source) {

        for (NewsType type : NewsType.values()) {
            if (source.equalsIgnoreCase(type.name())) {
                return type;
            }
        }
        return null;
    }
}
