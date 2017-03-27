package com.spd.controller;

import com.spd.bean.ImageBean;
import com.spd.entity.Image;
import com.spd.service.ImageService;
import com.spd.validator.FileValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

@RestController
@RequestMapping("api/v1/images")
@Api(value = "creating  and viewing images")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "upload image", httpMethod = "POST")
    public ImageBean uploadImage(@RequestParam(value = "image") MultipartFile imageFile, HttpServletResponse response) throws IOException {
        String mimeType;
        FileValidator fileValidator = new FileValidator();
        try {
            MagicMatch match = Magic.getMagicMatch(imageFile.getBytes());
            mimeType = match.getMimeType();
        } catch (Exception e) {
            mimeType = "";
        }

        if (!fileValidator.validate(mimeType, imageFile)) {
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }

        Image image = imageService.saveImage(mimeType, imageFile.getBytes());
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
