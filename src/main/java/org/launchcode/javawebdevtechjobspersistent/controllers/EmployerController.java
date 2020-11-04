package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("employers")
public class EmployerController {

    @Autowired
    private EmployerRepository employerRepository;

    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute("title", "Add New Employer");
        model.addAttribute(new Employer());
        return "employers/add";
    }

    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer,
                                    Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "employers/add";
        }

        Employer saveEmployer = employerRepository.save(newEmployer);
        // Redirect on endpoint !!GET!! "view/{employerId}"
//        return "redirect:/employers/view/"+saveEmployer.getId();
        return "redirect:/employers";
    }

    @GetMapping("view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable int employerId) {

      //  Optional optEmployer = null;
        Optional<Employer> optEmployer = employerRepository.findById(employerId);
        if (optEmployer.isPresent()) {
            Employer employer = optEmployer.get();
            model.addAttribute("employer", employer);
            return "employers/view";
        } else {
            return "redirect:../";
        }
    }

    @GetMapping
    public String displayAllEmployers(Model model){

        Iterable<Employer> employerIterable = employerRepository.findAll();
        List<Employer> employers = new ArrayList<>();
        employerIterable.forEach(employers :: add);
        model.addAttribute("employers", employers);

        return "employers/index";
    }
}
