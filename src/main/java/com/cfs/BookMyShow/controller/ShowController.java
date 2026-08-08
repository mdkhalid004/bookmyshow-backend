package com.cfs.BookMyShow.controller;

import com.cfs.BookMyShow.entity.Show;
import com.cfs.BookMyShow.entity.ShowSeat;
import com.cfs.BookMyShow.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    @Autowired
    private ShowService showService;


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Show> addShow(@RequestBody Show show) {
        return ResponseEntity.ok(showService.addShow(show));
    }


    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Show>> getShowsByMovie(@PathVariable Long movieId) {
        return ResponseEntity.ok(showService.getShowsByMovie(movieId));
    }


    @GetMapping("/city/{city}")
    public ResponseEntity<List<Show>> getShowsByCity(@PathVariable String city) {
        return ResponseEntity.ok(showService.getShowsByCity(city));
    }


    @GetMapping("/{showId}/seats")
    public ResponseEntity<List<ShowSeat>> getShowSeats(@PathVariable Long showId) {
        return ResponseEntity.ok(showService.getShowSeats(showId));
    }
}