/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.it2learn;

import entity.Project;
import entity.ProjectUser;
import facade.Facade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Dennis
 */
public class Main {
    public static void main(String[] args) {
        Persistence.generateSchema("pu", null);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        Facade facade = new Facade(emf);
        ProjectUser user1 = facade.createProjectUser("Dennis Schmock", "dennis@schmock.eu");
        ProjectUser user2 = facade.createProjectUser("2Dennis Schmock", "dennis@schmock.eu");
        
        Project pro1 = facade.createProject("1Build this", "Very nice!!");
        Project pro2 = facade.createProject("2Build this", "Very nice!!");
        Project pro3 = facade.createProject("3Build this", "Very nice!!");
        
        facade.assignUserToProject(user1, pro1);
        facade.assignUserToProject(user1, pro2);
        facade.assignUserToProject(user1, pro3);
        facade.assignUserToProject(user2, pro3);
        
        facade.createTaskAndAssignToProject("New windows", "Yes", 50, pro3);
        
        
        List<ProjectUser> users = facade.findAllUsers();
        for (ProjectUser user : users) {
            System.out.println(user.getUserName()+ "\n");
        }
    }
    
}
