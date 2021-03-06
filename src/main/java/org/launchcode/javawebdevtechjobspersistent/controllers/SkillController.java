package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Job;
import org.launchcode.javawebdevtechjobspersistent.models.JobData;
import org.launchcode.javawebdevtechjobspersistent.models.Skill;
import org.launchcode.javawebdevtechjobspersistent.models.data.JobRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.SkillRepository;
import org.launchcode.javawebdevtechjobspersistent.models.dto.JobSkillDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author Oksana
 */
@Controller
@RequestMapping("skills")
public class SkillController {

   @Autowired
   private SkillRepository skillRepository;

   @Autowired
   private JobRepository jobRepository;

   @GetMapping("add")
   public String displayAddSkillForm(Model model) {
      model.addAttribute(new Skill());
      return "skills/add";
   }

   @PostMapping("add")
   public String processAddSkillForm(@ModelAttribute @Valid Skill newSkill,
                                     Errors errors, Model model) {
      if (errors.hasErrors()) {
         return "skills/add";
      }
      skillRepository.save(newSkill);

      return "redirect:";
   }


   @GetMapping("view/{skillId}")
   public String displayViewSkill(Model model, @PathVariable int skillId) {
      Optional<Skill> optSkill = skillRepository.findById(skillId);
      if (optSkill.isPresent()) {
         Skill skill = optSkill.get();

         model.addAttribute("skill", skill);
         return "skills/view";
      } else {
         return "redirect:../";
      }
   }


   @GetMapping("")
   public String displayAllSkills(Model model) {
      model.addAttribute("skills", skillRepository.findAll());

      return "skills/index";
   }


}
