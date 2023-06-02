package com.example.RESTAPILearning.crudDemo.empl_and_students.rest;

public class StudentNotFoundException extends RuntimeException{

    public StudentNotFoundException(String message) {
        super(message);
    }


}
