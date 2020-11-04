package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {

   @Size(min=3, max = 200, message = "Description must be between 3 and 200 characters")
   private String description;

   @ManyToMany(mappedBy = "skills", fetch = FetchType.LAZY)
   private List<Job> jobs;

   public Skill() {
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   @Override
   public String toString() {
      return super.getName();
   }

   public List<Job> getJobs() {
      return jobs;
   }

   public void setJobs(List<Job> jobs) {
      this.jobs = jobs;
   }
}
