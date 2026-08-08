package com.cfs.BookMyShow.serviceimpl;



import com.cfs.BookMyShow.entity.Movie;
import com.cfs.BookMyShow.exception.ResourceNotFoundException;
import com.cfs.BookMyShow.repository.MovieRepository;
import com.cfs.BookMyShow.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));
    }

    @Override
    public List<Movie> getMoviesByGenre(String genre) {
        return movieRepository.findByGenreIgnoreCase(genre);
    }

    @Override
    public List<Movie> getMoviesByLanguage(String language) {
        return movieRepository.findByLanguageIgnoreCase(language);
    }
}
