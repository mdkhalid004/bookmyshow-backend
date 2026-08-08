package com.cfs.BookMyShow.serviceimpl;

import com.cfs.BookMyShow.entity.Theatre;
import com.cfs.BookMyShow.exception.ResourceNotFoundException;
import com.cfs.BookMyShow.repository.TheatreRepository;
import com.cfs.BookMyShow.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheatreServiceImpl implements TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    @Override
    public Theatre addTheatre(Theatre theatre) {

        if (theatre.getScreens() != null) {
            theatre.getScreens().forEach(screen -> {
                screen.setTheatre(theatre);


                if (screen.getSeats() != null) {
                    screen.getSeats().forEach(seat -> {
                        seat.setScreen(screen);
                    });
                }
            });
        }

        return theatreRepository.save(theatre);
    }

    @Override
    public List<Theatre> getTheatresByCity(String city) {
        return theatreRepository.findByCityIgnoreCase(city);
    }

    @Override
    public Theatre getTheatreById(Long id) {
        return theatreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Theatre not found with id: " + id));
    }
}