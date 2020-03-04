package com.remedy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.remedy.entity.Users;
import com.remedy.service.TicketService;

/**
 * @author Abdul,Indu,varun
 *
 */
@RestController
public class HomeController {
	private static final Logger logger = LogManager.getLogger(HomeController.class);

	@Autowired
	TicketService ticketService;

	@RequestMapping("/")
	public ModelAndView showLogin(Model model) {
		logger.info("In showLogin Method");
		return new ModelAndView("login");
	}

	@RequestMapping("/home")
	public ModelAndView showHome(Model model, HttpServletRequest request) {

		logger.info("In Home Controller and setting roles");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String role = auth.getAuthorities().toString();
		String userName = auth.getName();

		Users user = ticketService.getCustomerId(userName);

		HttpSession session = request.getSession();

		session.setAttribute("customerId", user.getCustomerId());

		model.addAttribute("role", role);

		if (role.equals("[customer]")) {
			model.addAttribute("ticketList", ticketService.showTicketsByUserId(user.getCustomerId()));
		} else {
			model.addAttribute("ticketList", ticketService.showAllTickets());
		}
		return new ModelAndView("home");

	}
}
