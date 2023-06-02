package com.example.RESTAPILearning.crudDemo.kats.controller;

import com.example.RESTAPILearning.crudDemo.kats.dao.CatDAO;
import com.example.RESTAPILearning.crudDemo.kats.entity.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/cats")
@Controller
public class CatController {

    // quick and dirty for now : inject cat DAO directly (constr inject)
    // should Service Design Pattern instead, esp with multiple data sources
    private CatDAO catDAO;

    //Autowired optional here since there is only 1 constructor for this spring bean
    @Autowired
    public CatController(CatDAO catDAO) {
        this.catDAO = catDAO;
    }


    //expose endpoint for listing all acts

    @GetMapping("/list")
    public String findAll(Model model) {

        //get the cats list from the db
        List<Cat> theCats = catDAO.findAll();

        // add the java list the spring model
        model.addAttribute("cats", theCats);

        //return the template cats/list-cats
        return "cats/list-cats";
    }

    //expose endpoint to send the add cat form
    @GetMapping("/showFormForAdd")
    public String addCat(Model theModel) {

        //create model attribute to bind form data
        Cat theCat = new Cat();

        theModel.addAttribute("cat", theCat);

        //return the template cats/cat-form
        return "cats/cat-form";
    }

    // endpoint for saving the added cat via form
    @PostMapping("/save")
    public String saveCat(@ModelAttribute("cat") Cat theCat) {

        //save the submitted Cat to the db
        catDAO.save(theCat);

        //use redirect to prevent duplicate submissions upon refresh = Post/Redirect/Get PRG Pattern
        return "redirect:/cats/list";
    }

    //endpoint for the cat edit form
    @GetMapping("/showFormForEdit")
    public String showFormForEdit(@RequestParam("catId") int theId, Model theModel) {

        // get the cat from the DAO
        Cat theCat = catDAO.findById(theId);

        //pre-populate the form with retrieved cat via model
        theModel.addAttribute("cat", theCat);

        //navigate to the form
        return "cats/cat-form";
    }

    //endpoint for the cat deletion
    @GetMapping("/delete")
    public String delete(@RequestParam("catId") int theId) {

        //delete the cat via id
        catDAO.deleteById(theId);

        //redirect to cats list
        return "redirect:/cats/list";
    }


    @GetMapping("/cats/{catId}")
    public Cat getCat(@PathVariable int catId) {
        Cat cat = catDAO.findById(catId);

        if (cat == null) {
            throw new RuntimeException("Cat id not found : " + catId);
        }
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
