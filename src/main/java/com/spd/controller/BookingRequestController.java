package com.spd.controller;

import com.spd.bean.BookingRequestBean;
import com.spd.service.BookingRequestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/booking")
@Api(value = "booking")
public class BookingRequestController {

    private final BookingRequestService bookingRequestService;

    @Autowired
    public BookingRequestController(BookingRequestService bookingRequestService) {
        this.bookingRequestService = bookingRequestService;
    }

    @RequestMapping(value = "/{idAnnouncement}", method = RequestMethod.GET)
    @ApiOperation(value = "get booking request", httpMethod = "GET")
    public List<BookingRequestBean> getBookingRequest(@PathVariable("idAnnouncement") Integer idAnnouncement) {
        return bookingRequestService.getBookingRequest(idAnnouncement);
    }

}
