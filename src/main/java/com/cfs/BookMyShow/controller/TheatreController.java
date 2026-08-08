package com.cfs.BookMyShow.controller;

import com.cfs.BookMyShow.entity.Theatre;
import com.cfs.BookMyShow.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theatres")
public class TheatreController {

    @Autowired
    private TheatreService theatreService;


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theatre> addTheatre(@RequestBody Theatre theatre) {
        return ResponseEntity.ok(theatreService.addTheatre(theatre));
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<Theatre>> getTheatresByCity(@PathVariable String city) {
        return ResponseEntity.ok(theatreService.getTheatresByCity(city));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Theatre> getTheatreById(@PathVariable Long id) {
        return ResponseEntity.ok(theatreService.getTheatreById(id));
    }
}