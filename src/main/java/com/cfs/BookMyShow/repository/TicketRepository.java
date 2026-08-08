package com.cfs.BookMyShow.repository;



import com.cfs.BookMyShow.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
