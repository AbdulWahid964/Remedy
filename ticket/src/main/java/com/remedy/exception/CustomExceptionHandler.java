package com.remedy.exception;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
/**
 * @author Abdul
 *
 */
@ControllerAdvice
public class CustomExceptionHandler {

	private static final Logger logger = LogManager.getLogger(CustomExceptionHandler.class);

	/* Author Abdul Wahid for ProductId Not Found Exception */
	@ExceptionHandler
	public ModelAndView objectIdHandlerMethod(TicketNotFoundException exception) {

		logger.info("Exception Caught " + exception.getMessage());
		ResourceResponseError error = new ResourceResponseError();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setTimeStamp(System.currentTimeMillis());
		error.setMessage(exception.getMessage());
		return new ModelAndView("error-page", "error", error);
	}

	@ExceptionHandler
	public ModelAndView resourceHandlerMethod(ResourceNotFoundException exception) {
		logger.info("Exception Caught " + exception.getMessage());
		ResourceResponseError error = new ResourceResponseError();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setTimeStamp(System.currentTimeMillis());
		error.setMessage(exception.getMessage());
		return new ModelAndView("error-page", "error", error);
	}
}
