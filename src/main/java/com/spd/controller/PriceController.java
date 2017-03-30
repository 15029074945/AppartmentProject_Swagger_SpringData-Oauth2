package com.spd.controller;

import com.spd.bean.PriceBean;
import com.spd.service.PriceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/announcements/{idAnnouncement}/prices")
@Api(value = "price")
public class PriceController {

    private final PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "get prices", httpMethod = "GET")
    public List<PriceBean> getPrices(Authentication authentication, @PathVariable("idAnnouncement") int idAnnouncement) {
        return priceService.getPrices(authentication.getName(), idAnnouncement);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "create price", httpMethod = "POST")
    public void createPrice(Authentication authentication, @RequestBody PriceBean priceBeans, @PathVariable("idAnnouncement") int idAnnouncement) {
        priceService.savePrices(authentication.getName(), priceBeans, idAnnouncement);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "update price", httpMethod = "PUT")
    public void updatePrice(Authentication authentication, @RequestBody PriceBean priceBeans, @PathVariable("idAnnouncement") int idAnnouncement) {
        priceService.updatePrices(authentication.getName(), priceBeans, idAnnouncement);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @ApiOperation(value = "delete price", httpMethod = "DELETE")
    public void deletePrice(Authentication authentication, @PathVariable("idAnnouncement") int idAnnouncement) {
        priceService.deletePrices(authentication.getName(), idAnnouncement);
    }

}
