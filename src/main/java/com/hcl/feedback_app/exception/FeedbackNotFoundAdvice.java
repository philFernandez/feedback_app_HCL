package com.hcl.feedback_app.exception;
/**
 * @author Phil Fernandez
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FeedbackNotFoundAdvice {
   @ResponseBody
   @ExceptionHandler(FeedbackNotFoundException.class) 
   @ResponseStatus(HttpStatus.NOT_FOUND)
   public String feedbackNotFoundHandler(FeedbackNotFoundException ex) {
       return ex.getMessage();
   }
}
