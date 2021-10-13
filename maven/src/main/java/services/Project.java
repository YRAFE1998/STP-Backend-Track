package services;

import entities.Employee;

import javax.persistence.EntityManager;
import java.sql.Date;

public class Project {

    public static void addProject(EntityManager em, long id, String name, Date date, Employee projectManager) {
        entities.Project project = new entities.Project(id, name, date);
        project.setManager(projectManager);
        em.persist(project);
        em.getTransaction().commit();
    }
}
