package com.example.RESTAPILearning.crudDemo.kats.rest;

import com.example.RESTAPILearning.crudDemo.kats.dao.CatDAO;
import com.example.RESTAPILearning.crudDemo.kats.entity.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class CatsRestController {

    // quick and dirty for now : inject cat DAO directly (constr inject)
    // should Service Design Pattern instead, esp with multiple data sources
    private CatDAO catDAO;

    //Autowired optional here since there is only 1 constructor for this spring bean
    @Autowired
    public CatsRestController(CatDAO catDAO) {
        this.catDAO = catDAO;
    }


    //expose endpoint for GET all
    @GetMapping("/cats")
    public List<Cat> findAll(Model model) {
        model.addAttribute("value2", "this is from model object in controller");
        return catDAO.findAll();
    }

    @GetMapping("/cats/{catId}")
    public Cat getCat(@PathVariable int catId) {
        Cat cat = catDAO.findById(catId);

        if (cat == null) {
            throw new RuntimeException("Cat id not found : " + catId);
        }
        return cat;
    }

    @PostMapping("/cats")
    public Cat addCat(@RequestBody Cat theCat) {

        //In case they pass an id in JSON while they shouldn't obviously, we set the id to 0 so the merge method creates
        theCat.setId(0);

        Cat cat = catDAO.save(theCat);
        return cat;
    }

    @PutMapping("/cats")
    public Cat updateCat(@RequestBody Cat theCat) {

        Cat cat = catDAO.save(theCat);
        return cat;
    }

    @DeleteMapping("/cats/{catId}")
    public String  deleteCat(@PathVariable int catId) {

        Cat tempCat = catDAO.findById(catId);

        //Exception if cat not found
        if (tempCat == null)
            throw new RuntimeException("Cat id not found : " + catId);

        catDAO.deleteById(catId);

    return "deleted cat id : " + catId;
    }


}


















