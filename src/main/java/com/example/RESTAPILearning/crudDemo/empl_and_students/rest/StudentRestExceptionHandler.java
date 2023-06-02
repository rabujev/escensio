package com.example.RESTAPILearning.crudDemo.empl_and_students.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {

        // create a StudentErrorResponse

        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());  //this is how you do it in Spring
        error.setMessage(exc.getMessage());   //exc is ofc the name we gave the exception
        error.setTimeStamp(System.currentTimeMillis());  //java lang function

        // return ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); //this how you do it
    }

    //other Exception handler to catch all
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleAllException(Exception exc) {

        // create a StudentErrorResponse

        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());  //this is how you do it in Spring
        error.setMessage(exc.getMessage());   //exc is ofc the name we gave the exception
        error.setTimeStamp(System.currentTimeMillis());  //java lang function

        // return ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST); //this how you do it

    }
}
