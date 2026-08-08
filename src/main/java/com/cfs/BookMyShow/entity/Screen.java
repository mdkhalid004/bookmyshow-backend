package com.cfs.BookMyShow.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "screens")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // e.g., Screen 1, IMAX

    @ManyToOne
    @JoinColumn(name = "theatre_id", nullable = false)
    @JsonBackReference
    private Theatre theatre;

    @OneToMany(mappedBy = "screen", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Seat> seats;
}