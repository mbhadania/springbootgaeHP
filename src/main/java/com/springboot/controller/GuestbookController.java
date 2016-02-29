package com.springboot.controller;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GuestbookController {

    @Autowired
    DatastoreService datastoreService;
    @Autowired
    UserService userService;

    @Value("${spring.thymeleaf.cache}")
    private String thcheck;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    String list(Model model, HttpServletRequest request) {
        
        User user = userService.getCurrentUser();
        if (user != null) {
            String username = user.getEmail();
            model.addAttribute("username", username.contains("@") ? username.split("@")[0] : username);
            model.addAttribute("logoutUrl", userService.createLogoutURL(request.getRequestURI()));
        } else {
            model.addAttribute("loginUrl", userService.createLoginURL(request.getRequestURI()));
        }
        return "list";
    }

    // FInding combinations
    ArrayList<String> letterCombinations = new ArrayList<String>();
    @RequestMapping(value = "/", method = RequestMethod.POST)
    String combos(@RequestParam("number") long number, Model model) {

        model.addAttribute("number", number);
        Solution solution = new Solution();
        try {
            letterCombinations = solution.letterCombinations(number + "");
        } catch (Exception e) {
            System.out.println(e);
        }
        model.addAttribute("totalCombos", letterCombinations.size());
        model.addAttribute("totalPages", letterCombinations.size() / 50 + 1);
        return "list";   
    }

    // To service Ajax: REST Controller 
    // Single page contains 50 combinations.
    @RequestMapping(value = "/page/{no}")
    @ResponseBody
    OutputService combos1(@RequestParam("number") long number, @PathVariable int no) { 
        
        int pageSize = no * 50;
        ArrayList<String> pageOutputList = new ArrayList<String>();
        for (int i = pageSize - 50; i < pageSize && i < letterCombinations.size(); i++) {
            pageOutputList.add(letterCombinations.get(i));
        }
        return new OutputService(pageOutputList, letterCombinations.size(), letterCombinations.size() / 50 + 1);
    }
}
