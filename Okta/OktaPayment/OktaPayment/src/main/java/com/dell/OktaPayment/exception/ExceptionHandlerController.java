/**
 * 
 */
package com.dell.OktaPayment.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author bhardu
 * @Since Apr 30, 2020
 */
@RestControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(PaymentException.class)
	public void handleException(HttpServletResponse response, PaymentException e) throws IOException {
		response.sendError(e.getHttpStatus().value(), e.getMessage());
	}

}
