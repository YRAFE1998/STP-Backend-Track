package services;

import javax.persistence.EntityManager;
import java.util.List;

public class Employee {


    public static void addEmployee(EntityManager em, long id, String firstName, String lastName, String dept, String email, String nationalId, String role) {
        entities.Employee emp = new entities.Employee(id, firstName, lastName, dept, email, nationalId, role);
        em.persist(emp);
        em.getTransaction().commit();
    }

    public static List<entities.Employee> getAll(EntityManager em) {
        javax.persistence.Query query = em.createQuery("SELECT e FROM Employee e");
        List<entities.Employee> list = query.getResultList();
        return list;
    }

}
