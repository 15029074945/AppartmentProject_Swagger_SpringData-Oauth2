package com.spd.controller;

import com.spd.bean.AddressBean;
import com.spd.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/announcements/{idAnnouncement}/address")
@Api(value = "address")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "", httpMethod = "GET")
    public AddressBean getAddress(Authentication authentication, @PathVariable("idAnnouncement") int idAnnouncement) {
        return addressService.getAddressByAnnouncementId(authentication.getName(), idAnnouncement);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "create or update address", httpMethod = "POST")
    public AddressBean createAnnouncement(Authentication authentication, @RequestBody AddressBean addressBean, @PathVariable("idAnnouncement") int idAnnouncement) {
        return addressService.createAddress(authentication.getName(), addressBean, idAnnouncement);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "create or update address", httpMethod = "PUT")
    public void updateAnnouncement(Authentication authentication, @RequestBody AddressBean addressBean, @PathVariable("idAnnouncement") int idAnnouncement) {
        addressService.updateAddress(authentication.getName(), addressBean, idAnnouncement);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @ApiOperation(value = "delete address", httpMethod = "DELETE")
    public void deleteAnnouncement(Authentication authentication, @PathVariable("idAnnouncement") int idAnnouncement) {
        addressService.deleteAddress(authentication.getName(), idAnnouncement);
    }

}
