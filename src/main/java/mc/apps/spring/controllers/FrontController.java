package mc.apps.spring.controllers;

import mc.apps.spring.jpa.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FrontController {
    private static final Logger logger = LogManager.getLogger(FrontController.class);
    private static final String DEFAULT_PATH = "/";

    final UserService userService;
    final ArticleService articleService;

    public FrontController(UserService userService, ArticleService articleService) {
        this.userService = userService;
        this.articleService = articleService;
    }

    @RequestMapping(value={"/","/{action}"})
    public String display(@PathVariable(required = false) String action, @RequestParam(name="error", required=false) String param, Model model, Authentication authentication){

        if(param!=null){
            model.addAttribute("error", "Bad credentials! try again.");
            return "login00";
        }

        model.addAttribute("title", (action==null)?"Index":formatted(action));
        String page = (action==null)?"index":"template";  //(action.equals("login")?action:"template");

        model.addAttribute("logged", (authentication==null)?"":authentication.getName());
        model.addAttribute("user", new User());

        if(action!=null)
             modelToView(action, model);
        return page;
    }


    private void modelToView(String action, Model model) {
        switch (action){
            case "articles":
                List<Article> items = articleService.list();
                model.addAttribute("items", items);
                break;
            default:
                break;
        }
    }


    @PostMapping(value="/signup")
    public String post(@ModelAttribute User user){
        logger.log(Level.INFO, "user = "+user);
        userService.add(user);
        return "redirect:"+DEFAULT_PATH;
    }
//    @PostMapping(value="/{action}")
//    public String post(@PathVariable String action, @ModelAttribute BaseEntity entity){
//        logger.log(Level.INFO, "action = "+action);
//
//        switch (action){
//            case "signup":
//                User user = (User)entity;
//                logger.log(Level.INFO, "user = "+user);
//                break;
//            // ...
//            default:
//                break;
//        }
//
//        return "redirect:"+DEFAULT_PATH;
//    }
    private String formatted(String page) {
        return page.substring(0,1).toUpperCase()+page.substring(1).toLowerCase();
    }
}
