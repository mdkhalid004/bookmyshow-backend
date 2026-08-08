package com.cfs.BookMyShow.repository;



import com.cfs.BookMyShow.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByScreenId(Long screenId);
}
