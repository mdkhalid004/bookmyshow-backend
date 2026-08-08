package com.cfs.BookMyShow.entity;



import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "seats")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatNumber; // e.g., A1, A2
    private String seatType;   // PLATINUM, GOLD, SILVER

    @ManyToOne
    @JoinColumn(name = "screen_id", nullable = false)
    @JsonBackReference
    private Screen screen;
}
