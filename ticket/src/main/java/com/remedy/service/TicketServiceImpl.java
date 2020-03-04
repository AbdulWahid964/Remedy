package com.remedy.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.remedy.controller.UserController;
import com.remedy.dao.TicketDAO;
import com.remedy.dao.UsersDao;
import com.remedy.entity.Ticket;
import com.remedy.entity.Users;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

	private static final Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	UsersDao userDao;

	@Autowired
	TicketDAO ticketDao;

	@Override
	public Users getCustomerId(String customerName) {
		logger.info("In findByCustomerName method");
		return userDao.findByCustomerName(customerName);
	}

	@Override
	public void saveTicket(Ticket ticket) {
		logger.info("In saveTicket method");
		ticketDao.save(ticket);
	}

	@Override
	public List<Ticket> showTicketsByUserId(int customerId) {
		logger.info("In showTicketsByUserId method");
		return ticketDao.getCustomerListById(customerId);
	}

	@Override
	public List<Ticket> showAllTickets() {
		logger.info("In showAllTickets method");
		return ticketDao.findAllByState();
	}

	@Override
	public Ticket editTicket(int ticketId) {
		logger.info("In editTicket method");
		return ticketDao.findOne(ticketId);
	}

	@Override
	public void closeTicket(int ticketId) {
		logger.info("In closeTicket method");
		ticketDao.closeTicket(ticketId);
	}

	@Override
	public Ticket findTicketById(int ticketId) {
		logger.info("In findTicketById method");
		return ticketDao.findOne(ticketId);
	}

}
