package com.example.RESTAPILearning.crudDemo.empl_and_students.rest;

import com.example.RESTAPILearning.crudDemo.empl_and_students.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("old/api")
public class StudentRestController {

    private List<Student> theStudents;
    // define @PostConstruct to load this only once
    @PostConstruct
    public void loadData() {

        theStudents = new ArrayList<>();
        theStudents.add(new Student("Rabujev", "Jamal"));
        theStudents.add(new Student("Malsa", "Se"));
        theStudents.add(new Student("Malsa", "Syra"));
    }


    //define endpoint "/students" to return a list of students

    @GetMapping("/students")
    public List<Student> getStudents() {
        return theStudents;
    }


    //define endpoint for "/students/{studentId}" - to return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        //check studentId not oufOfBound
        if (studentId > theStudents.size() - 1 || studentId < 0 )
            throw new StudentNotFoundException("Student id not found - " + studentId);
        //just index of the list to keep it simple for now
        return theStudents.get(studentId);
    }



}




















