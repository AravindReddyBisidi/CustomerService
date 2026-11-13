package com.alpha.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alpha.customerservice.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}