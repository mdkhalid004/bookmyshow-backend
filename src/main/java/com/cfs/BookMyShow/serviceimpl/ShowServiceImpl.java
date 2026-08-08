package com.cfs.BookMyShow.serviceimpl;

import com.cfs.BookMyShow.entity.Movie;
import com.cfs.BookMyShow.entity.Screen;
import com.cfs.BookMyShow.entity.Seat;
import com.cfs.BookMyShow.entity.Show;
import com.cfs.BookMyShow.entity.ShowSeat;
import com.cfs.BookMyShow.exception.ResourceNotFoundException;
import com.cfs.BookMyShow.repository.MovieRepository;
import com.cfs.BookMyShow.repository.ScreenRepository;
import com.cfs.BookMyShow.repository.SeatRepository;
import com.cfs.BookMyShow.repository.ShowRepository;
import com.cfs.BookMyShow.repository.ShowSeatRepository;
import com.cfs.BookMyShow.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShowServiceImpl implements ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ScreenRepository screenRepository;

    @Override
    @Transactional
    public Show addShow(Show show) {

        Movie movie = movieRepository.findById(show.getMovie().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + show.getMovie().getId()));

        Screen screen = screenRepository.findById(show.getScreen().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Screen not found with id: " + show.getScreen().getId()));


        show.setMovie(movie);
        show.setScreen(screen);


        Show savedShow = showRepository.save(show);

        List<Seat> seats = seatRepository.findByScreenId(screen.getId());
        for (Seat seat : seats) {
            ShowSeat showSeat = ShowSeat.builder()
                    .show(savedShow)
                    .seat(seat)
                    .status("AVAILABLE")
                    .build();
            showSeatRepository.save(showSeat);
        }

        return savedShow;
    }

    @Override
    public List<Show> getShowsByMovie(Long movieId) {
        return showRepository.findByMovieId(movieId);
    }

    @Override
    public List<Show> getShowsByCity(String city) {
        return showRepository.findByScreenTheatreCityIgnoreCase(city);
    }

    @Override
    public List<ShowSeat> getShowSeats(Long showId) {
        return showSeatRepository.findByShowId(showId);
    }
}