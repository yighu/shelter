package com.ffg.shelter.service;

import com.ffg.shelter.model.Image;
import org.resthub.common.service.CrudService;


public interface ImageService extends CrudService<Image, Long> {

    Image findByClientId(Long id);

    Image findByCampId(Long id);
}
