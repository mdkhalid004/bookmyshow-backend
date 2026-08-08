package com.cfs.BookMyShow.serviceimpl;


import com.cfs.BookMyShow.entity.*;
import com.cfs.BookMyShow.exception.ResourceNotFoundException;
import com.cfs.BookMyShow.exception.SeatNotAvailableException;
import com.cfs.BookMyShow.repository.*;
import com.cfs.BookMyShow.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Override
    @Transactional // Database transaction & Locking boundary
    public Booking bookTickets(Long userId, Long showId, List<Long> seatIds) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ResourceNotFoundException("Show not found"));


        List<ShowSeat> showSeats = showSeatRepository.findAllByIdWithPessimisticLock(seatIds);

        if (showSeats.size() != seatIds.size()) {
            throw new ResourceNotFoundException("Some selected seats do not exist");
        }


        for (ShowSeat showSeat : showSeats) {
            if (!"AVAILABLE".equals(showSeat.getStatus())) {
                throw new SeatNotAvailableException("Seat " + showSeat.getSeat().getSeatNumber() + " is already booked or locked!");
            }
        }


        double totalAmount = 0.0;
        List<Ticket> tickets = new ArrayList<>();

        Booking booking = Booking.builder()
                .user(user)
                .show(show)
                .bookingStatus("CONFIRMED")
                .createdAt(LocalDateTime.now())
                .build();

        for (ShowSeat showSeat : showSeats) {
            showSeat.setStatus("BOOKED");
            showSeatRepository.save(showSeat);

            Ticket ticket = Ticket.builder()
                    .booking(booking)
                    .showSeat(showSeat)
                    .build();
            tickets.add(ticket);


            totalAmount += show.getPrice();
        }

        booking.setTotalAmount(totalAmount);
        booking.setTickets(tickets);

        return bookingRepository.save(booking);
    }

    @Transactional
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));


        booking.setBookingStatus("CANCELLED");


        for (Ticket ticket : booking.getTickets()) {
            ShowSeat showSeat = ticket.getShowSeat();
            showSeat.setStatus("AVAILABLE");
            showSeatRepository.save(showSeat);
        }

        bookingRepository.save(booking);
    }
}