package com.cfs.BookMyShow.controller;

import com.cfs.BookMyShow.dto.BookingRequest;
import com.cfs.BookMyShow.entity.Booking;
import com.cfs.BookMyShow.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;




    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Booking> bookTickets(@RequestBody BookingRequest request) {
        Booking booking = bookingService.bookTickets(
                request.getUserId(),
                request.getShowId(),
                request.getSeatIds()
        );
        return ResponseEntity.ok(booking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.ok("Booking cancelled successfully and seats are now available.");
    }
}