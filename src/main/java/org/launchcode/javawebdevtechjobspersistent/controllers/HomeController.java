package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.Job;
import org.launchcode.javawebdevtechjobspersistent.models.Skill;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.JobRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

   @Autowired
   private EmployerRepository employerRepository;

   @Autowired
   private SkillRepository skillRepository;

   @Autowired
   private JobRepository jobRepository;

   @GetMapping("")
   public String index(Model model) {
      model.addAttribute("title", "My Jobs");
      model.addAttribute("jobs", jobRepository.findAll());

      return "index";
   }

   @GetMapping("add")
   public String displayAddJobForm(Model model) {
      model.addAttribute("employers", employerRepository.findAll());
      model.addAttribute("skills", skillRepository.findAll());

      model.addAttribute("title", "Add Job");
      model.addAttribute(new Job());
      return "add";
   }

   @PostMapping("add")
   public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                   Errors errors, Model model,
                                   @RequestParam int employerId,
                                   @RequestParam List<Integer> skills) {

      if (errors.hasErrors()) {
         model.addAttribute("title", "Add Job");
         return "add";
      }

      Optional<Employer> employerOptional = employerRepository.findById(employerId);
      if (employerOptional.isPresent()) {
         Employer employer = employerOptional.get();
         newJob.setEmployer(employer);

         List<Skill> skillsById = (List<Skill>) skillRepository.findAllById(skills);
         newJob.setSkills(skillsById);

         jobRepository.save(newJob);

      }

      return "redirect:/";
   }

   @GetMapping("view/{jobId}")
   public String displayViewJob(Model model, @PathVariable int jobId) {
      Optional<Job> jobOptional = jobRepository.findById(jobId);
      String view;
      if (jobOptional.isPresent()) {
         model.addAttribute("job", jobOptional.get());
         view = "view";
      } else {
         model.addAttribute("title", "Add Job");
         view = "add";
      }

      return "view";
   }


}
