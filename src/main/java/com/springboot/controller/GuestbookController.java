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
        // Query query = new Query("Greeting").addSort("date", Query.SortDirection.DESCENDING);
        // List<Entity> greetings = datastoreService.prepare(query).asList(FetchOptions.Builder.withLimit(30));
        // model.addAttribute("greetings", greetings);

        //System.out.println("\n\n\n\n " + thcheck + "\n\n\n\n\n\n\n\n\n\n");
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
        //model.addAttribute("letterCombinations", letterCombinations);
        model.addAttribute("totalCombos", letterCombinations.size());
        model.addAttribute("totalPages", letterCombinations.size() / 50 + 1);
        return "list";
        // return new OutputService(letterCombinations, letterCombinations.size(), letterCombinations.size()/50+1);
    }

    @RequestMapping(value = "/page/{no}")
    @ResponseBody
    OutputService combos1(@RequestParam("number") long number, @PathVariable int no) {

        //System.out.println("\n\n\n\n " + number + "\n\n\n\n\n\n  pathvariable: " + no + "\n\n\n\n");
        int pageSize = no * 50;
        //Solution solution = new Solution();
        // check check
        //model.addAttribute("letterCombinations", letterCombinations);
        ArrayList<String> pageOutputList = new ArrayList<String>();
        for (int i = pageSize - 50; i < pageSize && i < letterCombinations.size(); i++) {
            pageOutputList.add(letterCombinations.get(i));
        }

        //  for(int i=no*50;i>(no-1)*50;i--){
        //     pageOutputList.add(letterCombinations.get(i));
        // }
        return new OutputService(pageOutputList, letterCombinations.size(), letterCombinations.size() / 50 + 1);
        //return new OutputService(letterCombinations, letterCombinations.size(), letterCombinations.size()/50+1);
    }

}
