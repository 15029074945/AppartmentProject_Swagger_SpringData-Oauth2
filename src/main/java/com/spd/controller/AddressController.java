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

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "", httpMethod = "GET")
    public AddressBean getAddress(Authentication authentication, @RequestBody int idAnnouncement) {
        Address address = addressService.getAddressByAnnouncementId(authentication.getName(), idAnnouncement);
        return objectMapper.map(address, AddressBean.class);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "create or update address", httpMethod = "POST")
    public AddressBean createAnnouncement(Authentication authentication, @RequestBody AddressBean addressBean) {
        return addressService.
                saveAddress(authentication.getName(), addressBean);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "create or update address", httpMethod = "PUT")
    public AddressBean updateAnnouncement(Authentication authentication, @RequestBody AddressBean addressBean) {
        return addressService
                .saveAddress(authentication.getName(), addressBean);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @ApiOperation(value = "delete address", httpMethod = "DELETE")
    public void deleteAnnouncement(Authentication authentication, @RequestBody int idAnnouncement) {
        addressService.deleteAddress(authentication.getName(), idAnnouncement);
    }

}
