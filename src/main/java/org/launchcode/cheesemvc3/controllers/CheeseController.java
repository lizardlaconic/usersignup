package org.launchcode.cheesemvc3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
@RequestMapping("cheese")
public class CheeseController {

  static ArrayList<String> cheeses = new ArrayList<>();

  @RequestMapping(value = "")
  public String index(Model model) {

    //cheeses.add("cheddar");
    //cheeses.add("swiss");
    //cheeses.add("munster");

    model.addAttribute("cheeses", cheeses);
    model.addAttribute("title", "my cheeses");
    return "cheese/index";
  }

  @RequestMapping(value = "add", method = RequestMethod.GET)
  public String displayAddCheeseForm(Model model) {
    model.addAttribute("title", "Add Cheese");
    return "cheese/add";
  }

  //old fashioned parameter grabbing
  /*@RequestMapping(value = "add", method = RequestMethod.POST)
  public String processAddCheeseForm(HttpServletRequest request) {
    String cheeseName = request.getParameter("cheeseName");
  }*/

  @RequestMapping(value = "add", method = RequestMethod.POST)
  public String processAddCheeseForm(@RequestParam String cheeseName) {
    cheeses.add(cheeseName);
    return "redirect:";
  }

}
