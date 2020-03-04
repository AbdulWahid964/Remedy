package com.remedy.controller;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.remedy.dao.TicketDAO;
import com.remedy.entity.Ticket;
import com.remedy.exception.ResourceNotFoundException;
import com.remedy.exception.TicketNotFoundException;
import com.remedy.service.TicketService;
import com.remedy.service.TicketServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TicketControllerTest {

	TicketController ticketController;
	private Ticket ticket;
	@Mock
	private BindingResult result;
	@Mock
	TicketDAO ticketDao;
	TicketService ticketService;
	Field field, field1, field2;
	@Mock
	private Model model;
	@Mock
	private SecurityContextHolderStrategy strategy;
	@Mock
	private SecurityContext securityContext;
	@Mock
	private Authentication auth;
	@SuppressWarnings("rawtypes")
	@Mock
	private List list;
	@Mock
	HttpSession session;
	
	@Before
	public void setUp() throws Exception {
		ticketController = new TicketController();
		ticketService = new TicketServiceImpl();
		
		field = TicketController.class.getDeclaredField("ticketService");
		field.setAccessible(true);
		field.set(ticketController, ticketService);
		field1 = TicketServiceImpl.class.getDeclaredField("ticketDao");
		field1.setAccessible(true);
		field1.set(ticketService, ticketDao);
		field2 = SecurityContextHolder.class.getDeclaredField("strategy");
		field2.setAccessible(true);
		field2.set(SecurityContextHolder.class, strategy);
		ticket = new Ticket();
		ticket.setTicketId(1);
		ticket.setShortDescription("Test");
		ticket.setState("new");
		ticket.setPriority("High");
	}
	@Test
	public void testEditTicket() {
		Mockito.when(ticketDao.findOne(Matchers.anyInt())).thenReturn(ticket);
		assertEquals("ticket", ticketController.getTicketDetails(1).getViewName());
	}
	@Test(expected = ResourceNotFoundException.class)
	public void testEditTicket_Exception() {
		ticketController.getTicketDetails(1);
	}
	@Test
	public void testEditProductException() throws Exception {
		Ticket ticket = ticketService.editTicket(100);
		int status = 404;
		String content = "Ticket Not Found Exception";
		if (ticket == null) {
			assertEquals(404, status);
			assertEquals(content, "Ticket Not Found Exception");
		}
	}
	@Test
	public void testCloseTicket() {
		Mockito.when(ticketDao.findOne(Matchers.anyInt())).thenReturn(ticket);
		assertEquals("redirect:/home", ticketController.closeTicketDetails(1).getViewName());
	}
	@Test(expected = TicketNotFoundException.class)
	public void testCloseTicket_hasError() {
		ticketController.closeTicketDetails(1);
	}
	@After
	public void tearDown() throws Exception {
		auth = null;
		field = null;
		field1 = null;
		field2 = null;
		list = null;
		model = null;
		ticketController = null;
		ticketDao = null;
		ticket = null;
		ticketService = null;
		result = null;
		securityContext = null;
		strategy = null;
	}
}
