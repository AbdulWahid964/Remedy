package com.remedy.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.remedy.entity.Ticket;
import com.remedy.entity.Users;
import com.remedy.exception.ResourceNotFoundException;
import com.remedy.exception.TicketNotFoundException;
import com.remedy.service.TicketService;

@RestController
public class TicketController {

	private static final Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	TicketService ticketService;

	@RequestMapping("/ticket")
	public ModelAndView showTicketForm(Model model) {

		logger.info("In showTicketForm method");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();

		Ticket ticket = new Ticket();
		Users users = new Users();
		users.setCustomerName(userName);
		ticket.setUser(users);

		return new ModelAndView("ticket", "ticket", ticket);
	}

	@PostMapping("/ticket")
	public ModelAndView saveTicket(@Validated @ModelAttribute("ticket") Ticket ticket, BindingResult result,
			HttpSession session) {

		logger.info("In saveTicket method");
		if (result.hasErrors()) {

			logger.info("In validation Error");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String userName = auth.getName();
			Users users = new Users();
			users.setCustomerName(userName);
			ticket.setUser(users);
			return new ModelAndView("ticket");
		}
		int customerId = (int) session.getAttribute("customerId");
		Users user = new Users();
		user.setCustomerId(customerId);
		ticket.setUser(user);
		ticketService.saveTicket(ticket);
		return new ModelAndView("redirect:/home");
	}

	@GetMapping("/ticket/{ticketId}")
	public ModelAndView getTicketDetails(@PathVariable("ticketId") int ticketId) {

		logger.info("In getTicketDetails method");

		Ticket ticket = ticketService.editTicket(ticketId);
		if (ticket == null) {
			logger.error("Product is null");
			throw new ResourceNotFoundException("Product Not Found Exception");
		}
		return new ModelAndView("ticket", "ticket", ticket);
	}

	@GetMapping("/tickets/ticket/{ticketId}")
	public ModelAndView closeTicketDetails(@PathVariable("ticketId") int ticketId) {

		logger.info("In closeTicketDetails method");
		Ticket ticket = ticketService.findTicketById(ticketId);
		if (ticket != null) {
			ticketService.closeTicket(ticketId);
		} else {
			throw new TicketNotFoundException("Invalid Ticket Id");
		}
		return new ModelAndView("redirect:/home");
	}
}
