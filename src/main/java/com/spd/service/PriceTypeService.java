package com.spd.service;

import com.spd.entity.PriceType;
import com.spd.repository.PriceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceTypeService {

    private final PriceTypeRepository priceTypeRepository;

    @Autowired
    public PriceTypeService(PriceTypeRepository priceTypeRepository) {
        this.priceTypeRepository = priceTypeRepository;
    }

    public String getType(int id) {
        return priceTypeRepository.findOne(id).getType();
    }

    public PriceType getPriceTypeByType(String type) {
        return priceTypeRepository.findOneByType(type);
    }
}
