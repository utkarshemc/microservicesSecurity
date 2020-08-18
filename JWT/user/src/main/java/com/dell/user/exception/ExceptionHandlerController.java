/**
 * 
 */
package com.dell.user.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author bhardu
 * @Since Apr 24, 2020
 */
@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserException.class)
	public void handleException(HttpServletResponse response, UserException e) throws IOException {
		response.sendError(e.getHttpStatus().value(), e.getMessage());
	}

}
