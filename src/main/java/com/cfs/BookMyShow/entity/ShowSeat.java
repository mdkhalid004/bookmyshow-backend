package com.cfs.BookMyShow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "show_seats")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    @JsonIgnore
    private Show show;

    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    private String status; // AVAILABLE, LOCKED, BOOKED

    @Version
    private Long version;
}