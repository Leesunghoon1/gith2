package com.myweb.www.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CommonExceptionAdvice {
	
	
	
	// 400 에러
	@ResponseStatus(HttpStatus.NOT_FOUND) // 400 에러
	@ExceptionHandler(NoHandlerFoundException.class)
	public String handler404(NoHandlerFoundException ex, Model model) {
		model.addAttribute("err", "400");
		return "customError";
	}
	
}
