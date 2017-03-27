package com.spd.service;

import com.spd.repository.AttenderRepository;
import org.springframework.stereotype.Service;

@Service
public class AttenderService {

    private final AttenderRepository attenderRepository;

    public AttenderService(AttenderRepository attenderRepository) {
        this.attenderRepository = attenderRepository;
    }

}
