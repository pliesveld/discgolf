package com.pliesveld.discgolf.web.controller.errror;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pliesveld.discgolf.exception.CourseNotFoundException;
import com.pliesveld.discgolf.exception.DiscGolfException;
import com.pliesveld.discgolf.exception.GameException;
import com.pliesveld.discgolf.exception.PlayerNotFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class WebExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CourseNotFoundException.class, PlayerNotFoundException.class})
    public ResponseEntity<?> onGameExceptionHandler(HttpServletRequest servletRequest, Exception ex) {
       return ResponseEntity.status(404).body(new ErrorInfo(servletRequest.getRequestURI(), ex.getMessage()));
    }

    @ExceptionHandler(DiscGolfException.class)
    public ResponseEntity<?> onDiscGolfExceptionHandler(HttpServletRequest servletRequest, Exception ex) {
       return ResponseEntity.badRequest().body(new ErrorInfo(servletRequest.getRequestURI(), ex.getMessage()));
    }
}

