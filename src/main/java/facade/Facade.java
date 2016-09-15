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
    
    /**
     * Create a new user based on name and email, and persist to DB
     * @param username
     * @param email
     * @return
     */
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
    
    /**
     * Add a user to DB
     * @param user
     * @return
     */
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
    
    /**
     * Find a specific user, based on user id.
     * @param id
     * @return
     */
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
    
    /**
     * Find and return a list of all users, using a Query.
     * @return
     */
    public List<ProjectUser> findAllUsers() {
        List<ProjectUser> users;
        Query q = getEntityManager().createQuery("SELECT p FROM ProjectUser p");
        return users = q.getResultList();
    }
    
    /**
     * Create and persist a project
     * @param name
     * @param description
     * @return
     */
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
    
    /**
     * This method assigns a user to a project and persists it to DB
     * @param user
     * @param project
     */
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
    
    /**
     * This method finds a specific project based on project id.
     * @param id
     * @return
     */
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
    
    /**
     * The purpose of this method is to create a new task, and assign it to a 
     * specific project, persisting and merging. 
     * @param name
     * @param desc
     * @param hoursAssigned
     * @param project
     * @return
     */
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
