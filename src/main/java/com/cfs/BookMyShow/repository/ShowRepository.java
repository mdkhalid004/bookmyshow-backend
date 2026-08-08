package com.cfs.BookMyShow.repository;


import com.cfs.BookMyShow.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long> {
    List<Show> findByMovieId(Long movieId);
    List<Show> findByScreenTheatreCityIgnoreCase(String city);
}
