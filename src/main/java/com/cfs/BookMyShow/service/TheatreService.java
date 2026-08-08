package com.cfs.BookMyShow.service;



import com.cfs.BookMyShow.entity.Theatre;
import java.util.List;

public interface TheatreService {
    Theatre addTheatre(Theatre theatre);
    List<Theatre> getTheatresByCity(String city);
    Theatre getTheatreById(Long id);
}
