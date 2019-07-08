package org.launchcode.cheesemvc3.controllers;

import org.launchcode.cheesemvc3.models.CheeseType;
import org.launchcode.cheesemvc3.models.cheese;
import org.launchcode.cheesemvc3.models.data.CheeseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("cheese")
public class CheeseController {

  @Autowired
  private CheeseDao cheeseDao;

  //static ArrayList<String> cheeses = new ArrayList<>();
  //static HashMap<String, String> cheeses = new HashMap<>();
  //moving to model

  @RequestMapping(value = "")
  public String index(Model model) {

    //cheeses.add("cheddar");
    //cheeses.add("swiss");
    //cheeses.add("munster");

    model.addAttribute("cheeses", cheeseDao.findAll());
    model.addAttribute("title", "my cheeses");
    return "cheese/index";
  }

  ////////////////////////////ADD////////////////////////////////////////

  @RequestMapping(value = "add", method = RequestMethod.GET)
  public String displayAddCheeseForm(Model model) {
    model.addAttribute("title", "Add Cheese");
    model.addAttribute("cheese", new cheese());
    model.addAttribute("cheeseTypes", CheeseType.values());
    return "cheese/add";
  }


  @RequestMapping(value = "add", method = RequestMethod.POST)
  //public String processAddCheeseForm(@RequestParam String cheeseName, @RequestParam String cheeseDesc) {
  public String processAddCheeseForm(@ModelAttribute @Valid cheese newCheese, Errors errors, Model model) {

    if (errors.hasErrors()) {
      model.addAttribute("title", "Add Cheese");
      return "cheese/add";
    }

    //cheeses.put(cheeseName,cheeseDesc);
    //cheese newcheese = new cheese(cheeseName, cheeseDesc);
    cheeseDao.save(newCheese);
    return "redirect:";
  }

  ////////////////////////REMOVE///////////////////////////////////

  @RequestMapping(value = "remove", method = RequestMethod.GET)
  public String displayRemoveCheeseForm(Model model) {
    model.addAttribute("cheeses", cheeseDao.findAll());
    model.addAttribute("title", "Remove Cheese");
    return "cheese/remove";
  }

  @RequestMapping(value = "remove", method = RequestMethod.POST)
  public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

    //int ix, ij;

    for(int cheeseId : cheeseIds) {
      cheeseDao.deleteById(cheeseId);
    }

    /*for(ix=0;ix<death.size();ix++) {
      //hey this will remove all cheese with same name, should probably break.
      //watch intro to spring boot models part 2 to see the right way to do this.
      for(ij=0;ij<cheeses.size();ij++){
        if(cheeses.get(ij).getName().equalsIgnoreCase(death.get(ix))){
          cheeses.remove(ij);
        }
      }
      //cheeses.remove(death.get(ix));
    }*/

    return "redirect:";
  }

  ////////////////////EDIT/////////////////////////////////////////

  @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
  public String displayEditForm(Model model, @PathVariable int cheeseId) {
    //model.addAttribute("cheese", CheeseData.getById(cheeseId));
    model.addAttribute("title", "Edit Cheese");
    model.addAttribute("cheeseTypes", CheeseType.values());

    return "cheese/edit";

  }

  @RequestMapping(value = "edit", method = RequestMethod.POST)
  public String processEditForm(Model model, int cheeseId, @ModelAttribute @Valid cheese cheez, Errors errors) {
    //cheese editedCheese = CheeseData.getById(cheeseId);

    if (errors.hasErrors()){
      model.addAttribute("cheese", cheez);
      //model.addAttribute("cheese", CheeseData.getById(cheeseId));
      model.addAttribute("title", "Edit Cheese");
      model.addAttribute("cheeseTypes", CheeseType.values());
      return "cheese/edit";
    }

    //cheese c = CheeseData.getById(cheeseId);
    //c.setName(cheez.getName());
    //c.setDescription(cheez.getDescription());
    //c.setType(cheez.getType());
    return "redirect:";
  }
}
