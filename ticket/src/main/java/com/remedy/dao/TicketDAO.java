package com.remedy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.remedy.entity.Ticket;

public interface TicketDAO extends CrudRepository<Ticket, Integer> {

	@Query(nativeQuery = true, value = "select * from ticket_details t where t.customer_id=:customerId ")
	public List<Ticket> getCustomerListById(@Param("customerId") int customerId);

	@Query(nativeQuery = true, value = "select * from ticket_details t where t.state='new' ")
	public List<Ticket> findAllByState();
	
	@Modifying
	@Query(nativeQuery = true,value="update ticket_details set state='closed' where ticket_id=:ticketId")
	public void closeTicket(@Param("ticketId") int ticketId);

}
