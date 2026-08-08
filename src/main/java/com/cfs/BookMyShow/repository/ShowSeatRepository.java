package com.cfs.BookMyShow.repository;



import com.cfs.BookMyShow.entity.ShowSeat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

    List<ShowSeat> findByShowId(Long showId);


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT ss FROM ShowSeat ss WHERE ss.id IN :seatIds")
    List<ShowSeat> findAllByIdWithPessimisticLock(@Param("seatIds") List<Long> seatIds);
}
