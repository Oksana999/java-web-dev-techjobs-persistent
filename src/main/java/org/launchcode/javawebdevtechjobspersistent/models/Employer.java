package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {

   @Size(min=3, max = 200, message = "Location must be between 3 and 200 characters")
   private String location;

   @OneToMany(mappedBy = "employer", fetch = FetchType.LAZY)
   private List<Job> jobs = new ArrayList<>();


   public Employer() {
   }

   public String getLocation() {
      return location;
   }

   public void setLocation(String location) {
      this.location = location;
   }
}
