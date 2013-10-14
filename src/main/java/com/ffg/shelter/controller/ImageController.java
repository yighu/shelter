package com.ffg.shelter.controller;

import com.ffg.shelter.model.Camp;
import com.ffg.shelter.model.Client;
import com.ffg.shelter.model.Image;
import com.ffg.shelter.service.CampService;
import com.ffg.shelter.service.ClientService;
import com.ffg.shelter.service.ImageService;
import org.resthub.web.controller.ServiceBasedRestController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


@Controller
@RequestMapping(value = "/api/image")
public class ImageController extends ServiceBasedRestController<Image, Long, ImageService> {

    private ClientService clientService;
    private CampService campService;

    @Inject
    @Named("imageService")
    @Override
    public void setService(ImageService service) {
        this.service = service;

    }

    @Inject
    @Named("clientService")
    public void setService(ClientService service) {
        this.clientService = service;
    }

    @Inject
    @Named("campService")
    public void setCampService(CampService campService) {
        this.campService = campService;
    }

    @RequestMapping(value = "client/{clientId}")
    @ResponseBody
    public void getImageByClientId(@PathVariable Long clientId, HttpServletResponse response) throws IOException {
        byte[] bytes = new byte[]{};

        if (clientId != null) {
            Image image = this.service.findByClientId(clientId);
            if (image != null) {
                bytes = image.getImageContent();
            }
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(bytes);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        response.setContentLength(out.size());
        response.getOutputStream().write(out.toByteArray());
        response.getOutputStream().flush();

    }

    @RequestMapping(value = "/upload/client/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void upload(@RequestParam MultipartFile imageContent, @PathVariable Long id) throws IOException {
        byte[] imageContentBytes = imageContent.getBytes();
        Image image = this.service.findByClientId(id);
        if (image == null) {
            image = new Image();
        }
        image.setImageContent(imageContentBytes);
        Client client = clientService.findById(id);
        image.setClientId(client.getId());
        if (image.getId() == null) {
            this.service.create(image);
        } else {
            this.service.update(image);
        }

    }

    @RequestMapping(value = "/uploadString/{clientId}", method = RequestMethod.POST)
    @ResponseBody
    public void upload(@RequestParam String imageContent, @PathVariable Long clientId) throws IOException {
        byte[] imageContentBytes = imageContent.getBytes();
        Image image = new Image();
        image.setImageContent(imageContentBytes);
        Client client = clientService.findById(clientId);
        image.setClientId(client.getId());
        this.service.create(image);


    }

    @RequestMapping(value = "camp")
    @ResponseBody
    public void getImageByCampId(@RequestParam(value = "campId", required = false) Long id, HttpServletResponse response) throws IOException {
        byte[] bytes = new byte[]{};

        if (id != null) {
            Image image = this.service.findByCampId(id);
            if (image != null) {
                bytes = image.getImageContent();
            }
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(bytes);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        response.setContentLength(out.size());
        response.getOutputStream().write(out.toByteArray());
        response.getOutputStream().flush();

    }

    @RequestMapping(value = "/upload/camp/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void uploadCampImage(@RequestParam MultipartFile imageContent, @PathVariable Long id) throws IOException {
        byte[] imageContentBytes = imageContent.getBytes();
        Image image = this.service.findByCampId(id);
        if (image == null) {
            image = new Image();
        }
        image.setImageContent(imageContentBytes);
        Camp camp = campService.findById(id);
        image.setCampId(camp.getId());
        if (image.getId() == null) {
            this.service.create(image);
        } else {
            this.service.update(image);
        }

    }
}
