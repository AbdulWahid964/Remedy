package com.remedy.service;

import java.util.List;

import com.remedy.entity.Ticket;
import com.remedy.entity.Users;

public interface TicketService {

	public Users getCustomerId(String customerName);
	public void saveTicket(Ticket ticket);
	public List<Ticket> showTicketsByUserId(int customerId);
	public List<Ticket> showAllTickets();
	public Ticket editTicket(int ticketId);
	public void closeTicket(int ticketId);
	public Ticket findTicketById(int ticketId);
}
