/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Project;
import entity.ProjectUser;
import entity.Task;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Dennis
 */
public class Facade {
    
    EntityManagerFactory emf;
    
    public Facade(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public ProjectUser createProjectUser(String username, String email) {
        ProjectUser user = new ProjectUser(username, email);
        EntityManager em = getEntityManager();
        user.setCreated(new Date());
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return user;
    }
    
    public ProjectUser addProjectUser(ProjectUser user) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return user;
    }
    
    public ProjectUser findUser(int id) {
        EntityManager em = getEntityManager();
        ProjectUser user = null;
        try {
            em.find(ProjectUser.class, id);
        } finally {
            em.close();
        }
        return user;
    }
    
    public List<ProjectUser> findAllUsers() {
        List<ProjectUser> users;
        Query q = getEntityManager().createQuery("SELECT p FROM ProjectUser p");
        return users = q.getResultList();
    }
    
    public Project createProject(String name, String description) {
        Project project = new Project(name, description);
        project.setCreationDate(new Date());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(project);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return project;
    }
    
    public void assignUserToProject(ProjectUser user, Project project) {
        user.addProject(project);
        project.addProjectUser(user);
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(project);
            em.merge(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public Project findProject(int id) {
        Project project = null;
        EntityManager em = getEntityManager();
        try {
            project = em.find(Project.class, id);
        } finally {
            em.close();
        }
        return project;
    }
    
    public Task createTaskAndAssignToProject(String name, String desc, int hoursAssigned, Project project) {
        Task task = new Task(name, desc, hoursAssigned);
        task.setProject(project);
        project.addTask(task);
        EntityManager em = getEntityManager();
        
        try {
            em.getTransaction().begin();
            em.persist(task);
            em.merge(project);       
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return task;
    }
    
}
