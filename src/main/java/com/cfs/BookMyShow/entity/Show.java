package com.cfs.BookMyShow.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "shows")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "screen_id", nullable = false)
    @JsonIgnoreProperties({"seats", "theatre"})
    private Screen screen;

    private LocalDateTime startTime;
    private Double price;
}