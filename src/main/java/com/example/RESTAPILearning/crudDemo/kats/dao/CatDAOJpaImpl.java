package com.example.RESTAPILearning.crudDemo.kats.dao;

import com.example.RESTAPILearning.crudDemo.kats.entity.Cat;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //@Repository’s job is to catch persistence-specific exceptions and
// re-throw them as one of Spring’s unified unchecked exceptions.
//it's for DAO classes and is a sub annotation of @Component, like @Restcontroller
public class CatDAOJpaImpl implements CatDAO {

    //define field for entitymanager
    private EntityManager entityManager;

    //set up constructor injection, entitymanager is  autocreated by Spring Boot
    public CatDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public List<Cat> findAll() {
        // create a query
        TypedQuery<Cat> theQuery = entityManager.createQuery("FROM Cat ORDER BY lastName ASC", Cat.class);

        //Execute query and get result list
        List<Cat> cats = theQuery.getResultList();

        //return the results
        return cats;
    }

    @Override
    public Cat findById(int id) {
        Cat cat = entityManager.find(Cat.class, id);

        return cat;
    }

    @Transactional
    @Override
    public Cat save(Cat theCat) {
        Cat cat = entityManager.merge(theCat);

        return cat;
    }

    @Transactional
    @Override
    public void deleteById(int id) {

        Cat theCat = entityManager.find(Cat.class, id);
        entityManager.remove(theCat);

    }

    @Override
    public List<Cat> findAllByOrderByLastNameAsc() {
        return null;
    }
}
