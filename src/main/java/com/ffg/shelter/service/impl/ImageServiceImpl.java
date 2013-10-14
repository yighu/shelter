package com.ffg.shelter.service.impl;

import com.ffg.shelter.model.Image;
import com.ffg.shelter.repository.ImageRepository;
import com.ffg.shelter.service.ImageService;
import org.resthub.common.service.CrudServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;


@Transactional
@Named("imageService")
public class ImageServiceImpl extends CrudServiceImpl<Image, Long, ImageRepository> implements ImageService {
    @Override
    @Inject
    public void setRepository(ImageRepository imageRepository) {

        super.setRepository(imageRepository);
    }

    @Override
    public Image findByClientId(Long id) {
        return this.repository.findByClientId(id);
    }

    @Override
    public Image findByCampId(Long id) {
        if (id != null) {
            return this.repository.findByCampId(id);
        }

        return null;
    }
}
