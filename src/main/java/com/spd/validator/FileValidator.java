package com.spd.validator;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

public class FileValidator {


    private static final int MAX_ALLOWED_FILE_SIZE = 2 * 1024 * 1024; // 2 мб
    private static final int WIDTH_BIG_FILE = 2048;
    private static final int HEIGHT_BIG_FILE = 1546;
    private static final int WIDTH_SMOLL_FILE = 800;
    private static final int HEIGHT_SMOLL_FILE = 600;


    private final String[] allowedMimeTypes = {"image/jpeg", "image/png", "image/gif"};


    public boolean validate(String mimeType, MultipartFile imageFile) {

        System.out.println(imageFile.getSize() + " " + imageFile.getName());
        try {
            System.out.println(imageFile.getBytes() + " " + imageFile.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Boolean isSizeValid = imageFile.getSize() < MAX_ALLOWED_FILE_SIZE;

        Boolean isMimeTypeValid = Arrays.asList(allowedMimeTypes).contains(mimeType);

        return isSizeValid && isMimeTypeValid;
    }


    public boolean validateImageWidthHeight(MultipartFile imageFile) {
        BufferedImage bimg = null;
        try {
            bimg = ImageIO.read(imageFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int width = bimg.getWidth();
        int height = bimg.getHeight();

        return !((width < WIDTH_SMOLL_FILE || height < HEIGHT_SMOLL_FILE) || (width > WIDTH_BIG_FILE || height > HEIGHT_BIG_FILE));
    }
}
