package ru.kata.spring.boot_security.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class ControllerExceptionHandler {
    Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception e) {
        logger.error(e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
