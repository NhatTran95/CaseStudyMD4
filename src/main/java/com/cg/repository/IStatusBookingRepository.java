package com.cg.repository;

import com.cg.domain.StatusBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStatusBookingRepository extends JpaRepository<StatusBooking,Long> {
}
