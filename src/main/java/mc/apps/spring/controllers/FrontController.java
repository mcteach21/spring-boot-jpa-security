package mc.apps.spring.controllers;

import mc.apps.spring.jpa.User;
import mc.apps.spring.jpa.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

@Controller
public class FrontController {
    private static final Logger logger = LogManager.getLogger(FrontController.class);
    private static final String DEFAULT_PATH = "/";

    final UserService userService;
    public FrontController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value={"/","/{action}"})
    public String display(@PathVariable(required = false) String action, @RequestParam(name="error", required=false) String param, Model model, Authentication authentication){

        if(param!=null){
            model.addAttribute("error", "bad credentials! try again.");
            return "login";
        }

        model.addAttribute("title", (action==null)?"Index":formatted(action));
        String page = (action==null)?"index":(action.equals("login")?action:"template");

        model.addAttribute("logged", (authentication==null)?"":authentication.getName());
        model.addAttribute("user", new User());

        return page;
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
