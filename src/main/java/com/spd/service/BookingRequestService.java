package com.spd.service;

import com.spd.bean.BookingRequestBean;
import com.spd.repository.BookingRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingRequestService {

    private final BookingRequestRepository bookingRequestRepository;

    @Autowired
    public BookingRequestService(BookingRequestRepository bookingRequestRepository) {
        this.bookingRequestRepository = bookingRequestRepository;
    }

    public List<BookingRequestBean> getBookingRequest(Integer idAnnouncement) {
        return null;
    }
}
