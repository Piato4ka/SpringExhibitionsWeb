package code.project.controllers;

import code.project.model.Exhibition;
import code.project.model.User;
import code.project.service.ExhibitionService;
import code.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;


@Controller
public class ExhibitionController {

    @Autowired
    private UserService userService;

    @Autowired
    private ExhibitionService exhibitionService;

    @Autowired
    public ExhibitionController(UserService userService, ExhibitionService exhibitionService) {
        this.userService = userService;
        this.exhibitionService = exhibitionService;
    }

    public ExhibitionController() {
    }


    @GetMapping("/")
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        if (user == null)
        {
            user = new User();
        }
        model.addAttribute("user", user);
        model.addAttribute("exhibitions", exhibitionService.index());
        return "views/mainPage";
    }

    @GetMapping("/show/{theme}")

    public String show(@PathVariable("theme") String theme, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        if (user == null)
        {
            user = new User();
        }
        model.addAttribute("user", user);
        model.addAttribute("exhibition", exhibitionService.show(theme));
        return "views/show";
    }

    @GetMapping("/new")
    public String newExhibition(@ModelAttribute("exhibition") Exhibition exhibition) {
        return "views/new";
    }


    @PostMapping()
    public String create(@ModelAttribute("exhibition") Exhibition exhibition) {
        exhibitionService.saveExhibition(exhibition);
        return "redirect:/";
    }

    @GetMapping("/{theme}/edit")
    public String edit(Model model, @PathVariable("theme") String theme) {
        model.addAttribute("exhibition", exhibitionService.show(theme));
        return "views/edit";
    }

    @PatchMapping("/{theme}")
    public String update(@ModelAttribute("exhibition") Exhibition exhibition, @PathVariable("theme") String theme) {
        exhibitionService.update(theme, exhibition);
        return "redirect:/";
    }

    @DeleteMapping("/{theme}")
    public String delete(@PathVariable("theme") String theme) {
        exhibitionService.delete(theme);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "views/login";
    }


    @GetMapping(value = "/registration")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("views/registration");
        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUserName(user.getUserName());
        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("views/registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("views/registration");

        }
        return modelAndView;
    }


}
