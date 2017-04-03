package com.spd.validator;

import com.spd.exception.ImageException;
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


    public void validate(String mimeType, MultipartFile imageFile) throws IOException, ImageException
    {
        if (imageFile.getSize() > MAX_ALLOWED_FILE_SIZE) {
            throw new ImageException();
        }

        if (!Arrays.asList(allowedMimeTypes).contains(mimeType)) {
            throw new ImageException();
        }

        validateImageWidthHeight(imageFile);
    }

    private void validateImageWidthHeight(MultipartFile imageFile) throws IOException, ImageException {
        BufferedImage bimg = null;
        bimg = ImageIO.read(imageFile.getInputStream());
        int width = bimg.getWidth();
        int height = bimg.getHeight();

        if ((width < WIDTH_SMOLL_FILE || height < HEIGHT_SMOLL_FILE) || (width > WIDTH_BIG_FILE || height > HEIGHT_BIG_FILE)) {
            throw new ImageException();
        }
    }
}
