package com.spd.mapper;

import com.spd.bean.AddressBean;
import com.spd.bean.UserInformationBean;
import com.spd.entity.Address;
import com.spd.entity.User;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory mapperFactory) {
        /*mapperFactory.classMap(User.class, UserInformationBean.class)
                .field("userEmails{email}", "extraEmails{}")
                .field("userTelephones{telephone}", "extraTelephones{}")
                .byDefault()
                .register();*/
        mapperFactory.classMap(Address.class, AddressBean.class)
                .field("id", "idAnnouncement")
                .byDefault()
                .register();
    }

}
