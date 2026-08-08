package com.cfs.BookMyShow.service;



import com.cfs.BookMyShow.entity.Booking;
import java.util.List;

public interface BookingService {
    Booking bookTickets(Long userId, Long showId, List<Long> seatIds);
    void cancelBooking(Long id);
}