package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Job extends AbstractEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "Employer required")
//    @JoinColumn(name = "employer_id")
    private Employer employer;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Skill> skills;

    public Job() {
    }

    public Job(Employer anEmployer, List<Skill> someSkills) {
        super();
        this.employer = anEmployer;
        this.skills = someSkills;
    }

    // Getters and setters.


    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return super.getName();
    }
}
