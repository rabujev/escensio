package com.example.RESTAPILearning.crudDemo.kats.dao;

import com.example.RESTAPILearning.crudDemo.kats.entity.Cat;

import java.util.List;

public interface CatDAO {

    List<Cat> findAll();

    //get employee
    Cat findById(int id);

    //add OR update employee depending on id provided, if 0 it adds
    Cat save(Cat theCat);

    //delete employee

    void deleteById(int id);

    //add a method to sort by last name
    public List<Cat> findAllByOrderByLastNameAsc();


}
