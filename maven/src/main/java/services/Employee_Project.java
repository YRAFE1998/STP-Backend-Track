package services;

import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class Employee_Project {

    public static List<Employee> getEmployeesinProjectName(EntityManager em, String projectName){
        javax.persistence.Query query = em.createQuery("select p.employee_works_on_project from Project p where p.name = :projectName").setParameter("projectName", projectName);
        List<entities.Employee> list =  query.getResultList();
        return list;

    }

    public static String addEmployeetoProject(EntityManager em,long employeeId, long projectId) {
        Employee retrievedemp = em.find(Employee.class, employeeId);
        Project retrievedProject = em.find(Project.class, projectId);
        retrievedemp.addProjecttoEmployee(retrievedProject);
        em.persist(retrievedemp);
        em.getTransaction().commit();
        return "Success";

    }


}
