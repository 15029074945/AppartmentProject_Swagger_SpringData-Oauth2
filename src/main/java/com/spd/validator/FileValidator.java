package com.spd.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

public class FileValidator implements Validator {


    private static final int MAX_ALLOWED_FILE_SIZE = 5 * 1024 * 1024; // 5 мб
    private final String[] allowedMimeTypes = {"image/jpeg", "image/png", "image/gif"};

    @Override
    public boolean supports(Class<?> arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
    }

    public boolean validate(String mimeType, MultipartFile imageFile) {

        Boolean isSizeValid = imageFile.getSize() < MAX_ALLOWED_FILE_SIZE;

        Boolean isMimeTypeValid = Arrays.asList(allowedMimeTypes).contains(mimeType);

        return isSizeValid && isMimeTypeValid;
    }
}
