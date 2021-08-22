package code.project.controllers;

import code.project.dao.ExhibitionDAO;
import code.project.model.Exhibition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ExhibitionController {

private final ExhibitionDAO exhibitionDAO;

    @Autowired
    public ExhibitionController(ExhibitionDAO exhibitionDAO) {
        this.exhibitionDAO = exhibitionDAO;
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('GUEST, USER, ADMIN')")
    public String index(Model model) {
        model.addAttribute("exhibitions", exhibitionDAO.index());
        return "views/mainPage";
    }

    @GetMapping("/{theme}")
    @PreAuthorize("hasAnyRole('GUEST, USER, ADMIN')")
    public String show(@PathVariable("theme") String theme, Model model) {
    model.addAttribute("exhibition", exhibitionDAO.show(theme));
    return "views/show";
    }

    @GetMapping ("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String newExhibition (@ModelAttribute("exhibition") Exhibition exhibition) {
        return  "views/new";
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public String create (@ModelAttribute ("exhibition") Exhibition exhibition) {
        exhibitionDAO.save(exhibition);
        return "redirect:/";
    }

    @GetMapping("/{theme}/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public String edit (Model model, @PathVariable("theme") String theme) {
        model.addAttribute("exhibition", exhibitionDAO.show(theme));
        return "views/edit";
    }

    @PatchMapping("/{theme}")
    @PreAuthorize("hasRole('ADMIN')")
    public String update(@ModelAttribute("exhibition") Exhibition exhibition, @PathVariable("theme") String theme) {
        exhibitionDAO.update(theme, exhibition);
        return "redirect:/";
    }

    @DeleteMapping("/{theme}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete (@PathVariable ("theme") String theme) {
        exhibitionDAO.delete(theme);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "views/login";
    }

}






