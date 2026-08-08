package com.cfs.BookMyShow.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties("bookings")
    private User user;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    @JsonIgnoreProperties({"bookings", "showSeats"})
    private Show show;

    private double totalAmount;
    private String bookingStatus; // CONFIRMED, CANCELLED, PENDING
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Ticket> tickets;
}