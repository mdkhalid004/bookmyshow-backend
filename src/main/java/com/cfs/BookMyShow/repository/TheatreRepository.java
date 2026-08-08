package com.cfs.BookMyShow.repository;





import com.cfs.BookMyShow.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {
    List<Theatre> findByCityIgnoreCase(String city);
}