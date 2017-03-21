package com.spd.mapper;

import com.spd.dto.UserInformationDTO;
import com.spd.entity.User;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory mapperFactory) {
        mapperFactory.classMap(User.class, UserInformationDTO.class)
                .field("userEmails{email}", "extraEmails{}")
                .field("userTelephones{telephone}", "extraTelephones{}")
                .byDefault()
                .register();
    }

}
