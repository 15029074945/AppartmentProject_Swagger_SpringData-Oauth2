package com.spd.controller;

import com.spd.bean.PriceBean;
import com.spd.service.PriceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/prices")
@Api(value = "price")
public class PriceController {

    private final PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "get prices", httpMethod = "GET")
    public List<PriceBean> getPrices(Authentication authentication, @RequestBody int idAnnouncement) {
        return priceService.getPrices(authentication.getName(), idAnnouncement);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "create price", httpMethod = "POST")
    public void createPrice(Authentication authentication, @RequestBody List<PriceBean> priceBeans) {
        priceService.savePrices(authentication.getName(), priceBeans);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "update price", httpMethod = "PUT")
    public void updatePrice(Authentication authentication, @RequestBody List<PriceBean> priceBeans) {
        priceService.updatePrices(authentication.getName(), priceBeans);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @ApiOperation(value = "delete price", httpMethod = "DELETE")
    public void deletePrice(Authentication authentication, @RequestBody int idAnnouncement) {
        priceService.deletePrices(authentication.getName(), idAnnouncement);
    }

}
