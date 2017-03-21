package com.spd.repository;

import com.spd.entity.BookingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRequestRepository extends JpaRepository<BookingRequest, Integer> {

}
