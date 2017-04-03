package com.spd.controller;

import com.spd.bean.ImageBean;
import com.spd.entity.Image;
import com.spd.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("api/v1/images")
@Api(value = "creating  and viewing images")

public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "upload image", httpMethod = "POST")
    public ImageBean uploadImage(@RequestParam(value = "file") MultipartFile fileImage) throws Exception {
        Image image = imageService.saveImage(fileImage);
        ImageBean imageBean = new ImageBean();
        imageBean.setId(image.getId());
        imageBean.setUrl("/api/v1/images/" + Integer.toString(image.getId()));
        return imageBean;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "get image", httpMethod = "GET", produces = "image/jpeg, image/png, image/gif")
    public void getImage(@PathVariable("id") int id, HttpServletResponse response) throws IOException {
        Image image = imageService.getImage(id);

        response.setContentType(image.getMimeType());
        response.setContentLength(image.getData().length);
        response.getOutputStream().write(image.getData());
        response.getOutputStream().close();
    }
}
