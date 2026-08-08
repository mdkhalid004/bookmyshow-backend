package com.cfs.BookMyShow.service;



import com.cfs.BookMyShow.entity.Show;
import com.cfs.BookMyShow.entity.ShowSeat;
import java.util.List;

public interface ShowService {
    Show addShow(Show show);
    List<Show> getShowsByMovie(Long movieId);
    List<Show> getShowsByCity(String city);
    List<ShowSeat> getShowSeats(Long showId);
}
