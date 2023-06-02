package com.example.RESTAPILearning.crudDemo.empl_and_students.rest;

public class StudentErrorResponse {

    //Fields
    private String message;
    private int status;
    private long timeStamp;

    //Constructors
    public StudentErrorResponse(String message, int status, long timeStamp) {
        this.message = message;
        this.status = status;
        this.timeStamp = timeStamp;
    }

    public StudentErrorResponse() {

    }

    //Getters

    public String getMessage() {
        return this.message;
    }
    public int getStatus() {
        return this.status;
    }
    public long getTimeStamp() {
        return this.timeStamp;
    }

    //Setters
    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
