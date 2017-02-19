package com.pliesveld.discgolf.web.controller.errror;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pliesveld.discgolf.exception.GameException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class WebExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GameException.class)
    public ResponseEntity<?> onGameExceptionHandler(HttpServletRequest servletRequest, Exception ex) {
       return ResponseEntity.badRequest().body(new ErrorInfo(servletRequest.getRequestURI(), ex.getMessage()));
    }
}
