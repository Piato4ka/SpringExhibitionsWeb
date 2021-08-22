package code.project.controllers;

import code.project.dao.ExhibitionDAO;
import code.project.model.Exhibition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/main")
public class ExhibitionController {

private final ExhibitionDAO exhibitionDAO;

    @Autowired
    public ExhibitionController(ExhibitionDAO exhibitionDAO) {
        this.exhibitionDAO = exhibitionDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("exhibitions", exhibitionDAO.index());
        return "views/mainPage";
    }

    @GetMapping("/{theme}")
    public String show(@PathVariable("theme") String theme, Model model) {
    model.addAttribute("exhibition", exhibitionDAO.show(theme));
    return "views/show";
    }

    @GetMapping ("/new")
    public String newExhibition (@ModelAttribute("exhibition") Exhibition exhibition) {
        return  "views/new";
    }

    @PostMapping()
    public String create (@ModelAttribute ("exhibition") Exhibition exhibition) {
        exhibitionDAO.save(exhibition);
        return "redirect:/main";
    }

    @GetMapping("/{theme}/edit")
    public String edit (Model model, @PathVariable("theme") String theme) {
        model.addAttribute("exhibition", exhibitionDAO.show(theme));
        return "views/edit";
    }

    @PatchMapping("/{theme}")
    public String update(@ModelAttribute("exhibition") Exhibition exhibition, @PathVariable("theme") String theme) {
        exhibitionDAO.update(theme, exhibition);
        return "redirect:/main";
    }

    @DeleteMapping("/{theme}")
    public String delete (@PathVariable ("theme") String theme) {
        exhibitionDAO.delete(theme);
        return "redirect:/main";
    }
}






