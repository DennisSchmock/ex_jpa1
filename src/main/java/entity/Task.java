/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Dennis
 */
@Entity
public class Task implements Serializable {

    @ManyToOne
    private Project project;
     
    private String name;
    private String description;
    private int hoursAssigned;
    private int hoursUsed;

    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Task() {
    }

    public Task(String name, String description, int hoursAssigned) {
        this.name = name;
        this.description = description;
        this.hoursAssigned = hoursAssigned;
    }

    
    //Getters and setters below this point
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the hoursAssigned
     */
    public int getHoursAssigned() {
        return hoursAssigned;
    }

    /**
     * @param hoursAssigned the hoursAssigned to set
     */
    public void setHoursAssigned(int hoursAssigned) {
        this.hoursAssigned = hoursAssigned;
    }

    /**
     * @return the hoursUsed
     */
    public int getHoursUsed() {
        return hoursUsed;
    }

    /**
     * @param hoursUsed the hoursUsed to set
     */
    public void setHoursUsed(int hoursUsed) {
        this.hoursUsed = hoursUsed;
    }

    /**
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(Project project) {
        this.project = project;
    }
    
}
