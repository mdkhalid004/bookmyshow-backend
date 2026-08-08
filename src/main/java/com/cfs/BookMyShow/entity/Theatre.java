package com.cfs.BookMyShow.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "theatres")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String city;
    private String address;

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Screen> screens;
}
