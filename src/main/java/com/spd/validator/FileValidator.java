package com.spd.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

public class FileValidator implements Validator {


    private static final int MAX_ALLOWED_FILE_SIZE = 5 * 1024 * 1024; // 5 мб
    private static final int WIDTH_BIG_FILE = 2048;
    private static final int HEIGHT_BIG_FILE = 1546;
    private static final int WIDTH_SMOLL_FILE = 800;
    private static final int HEIGHT_SMOLL_FILE = 600;


    private final String[] allowedMimeTypes = {"image/jpeg", "image/png", "image/gif"};

    @Override
    public boolean supports(Class<?> arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
    }

    public boolean validateImageWidthHeight( MultipartFile imageFile) {
        BufferedImage bimg = null;
        try {
            bimg = ImageIO.read(imageFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int width          = bimg.getWidth();
        int height         = bimg.getHeight();

        return !((width < WIDTH_SMOLL_FILE || height < HEIGHT_SMOLL_FILE) || (width > WIDTH_BIG_FILE || height > HEIGHT_BIG_FILE));
    }

    public boolean validate(String mimeType, MultipartFile imageFile) {

        Boolean isSizeValid = imageFile.getSize() < MAX_ALLOWED_FILE_SIZE;

        Boolean isMimeTypeValid = Arrays.asList(allowedMimeTypes).contains(mimeType);

        return isSizeValid && isMimeTypeValid;
    }
}
