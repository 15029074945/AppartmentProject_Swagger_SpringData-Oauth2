package com.spd.controller;

import com.spd.bean.AddressBean;
import com.spd.entity.Address;
import com.spd.mapper.ObjectMapper;
import com.spd.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/address")
@Api(value = "address")
public class AddressController {

    private final ObjectMapper objectMapper;
    private final AddressService addressService;

    @Autowired
    public AddressController(ObjectMapper objectMapper, AddressService addressService) {
        this.objectMapper = objectMapper;
        this.addressService = addressService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "create or update address", httpMethod = "POST")
    public AddressBean createOrUpdateAnnouncement(Authentication authentication, @RequestBody AddressBean addressBean) {

        Address address = objectMapper.map(addressBean, Address.class);

        Address newAnnouncement = addressService
                .saveAddress(authentication.getName(), address, addressBean.getIdAnnouncement());

        return objectMapper.map(newAnnouncement, AddressBean.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "delete address", httpMethod = "DELETE")
    public void deleteAnnouncement(@PathVariable("id") int id) {
        addressService.deleteAddress(id);
    }

}
