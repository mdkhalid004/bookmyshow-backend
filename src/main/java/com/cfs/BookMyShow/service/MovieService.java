package com.cfs.BookMyShow.service;



import com.cfs.BookMyShow.entity.Movie;
import java.util.List;

public interface MovieService {
    Movie addMovie(Movie movie);
    List<Movie> getAllMovies();
    Movie getMovieById(Long id);
    List<Movie> getMoviesByGenre(String genre);
    List<Movie> getMoviesByLanguage(String language);
}