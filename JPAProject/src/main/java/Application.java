
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import com.mysql.cj.Query;

import entities.Employee;
import entities.Project;

@ApplicationPath("api/jpaproject")
public class Application {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("JPAProject");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		
		//addEmployee(em,1, "Yousef", "Elmasry", "Engineering", "email1@email.com", "298010114213", "Associate software engineer");
		//addEmployee(em,2, "Mohamed", "Adel", "Engineering", "email2@email.com", "296010214213", "Associate software engineer");
		//addEmployee(em,3, "Ahmed", "Khaled", "Engineering", "email3@email.com", "298010543145", "Associate software engineer");
		//addEmployee(em,4, "Gasser", "Tarek", "Engineering", "email4@email.com", "297015125014", "Associate software engineer");
		//addEmployee(em,5, "Kamal", "Ayman", "Engineering", "email7@email.com", "297115125014", "software engineer lead");		
		
		//Employee retrievedemp = em.find(Employee.class, (long)1);  
        //Employee retrievedemp2 = em.find(Employee.class, (long)2);
		//addProject(em, 1, "HSBC digi", new Date(2022, 1, 15), retrievedemp);
		//addProject(em, 2, "Alex Bank", new Date(2021, 5, 9), retrievedemp2);
		//addProject(em, 3, "Electrolux", new Date(2019, 12, 21), retrievedemp2);
		//addProject(em, 4, "Caro stadium", new Date(2019, 1, 10), retrievedemp);
		        
       	displayAllemployees(em);
       	//getEmployeesinProjectName(em, "Alex Bank");
       	//addEmployeetoProject(em, 3, 1);
       	
       	
		emFactory.close();
		em.close();	
	}
	
	
	
	public static void addEmployee(EntityManager em, long id, String firstName, String lastName, String dept,String email, String nationalId, String role) {
		Employee emp = new Employee(id, firstName, lastName, dept, email, nationalId, role);
		em.persist(emp);
		em.getTransaction().commit();
	}
	
	
	public static void addProject(EntityManager em,long id, String name, Date date, Employee projectManager) {
		Project project = new Project(id,name , date);
		project.setManager(projectManager);
		em.persist(project);
		em.getTransaction().commit();
	}

	//@Produces("content/plain")
	//@GET @Path("employees")
	public static String displayAllemployees(EntityManager em) {
		javax.persistence.Query query = em.createQuery("SELECT e FROM Employee e");
		List<Employee> list = query.getResultList();
		for (Employee emp : list) {
			System.out.println(emp.toString());
		}
		
		return "Success";
	}
	
	//@Produces("content/plain")
	//@GET @Path("employees/project/{projectName}")
	public static String getEmployeesinProjectName(EntityManager em, @PathParam("projectName") String projectName){
		javax.persistence.Query query = em.createQuery("select p.employee_works_on_project from Project p where p.name = :projectName").setParameter("projectName", projectName);
		List<Employee> list =  query.getResultList();
		for (Employee emp : list) {
			System.out.println(emp.toString());
		}
		return "Success";
	}
	
	//@Produces("content/plain")
	//@PUT @Path("employee/{employeeID}/project/{projectID}")
	public static String addEmployeetoProject(EntityManager em,@PathParam("employeeID") long employeeId,@PathParam("projectID") long projectId) {
		Employee retrievedemp = em.find(Employee.class, employeeId);  
        Project retrievedProject = em.find(Project.class, projectId);
        retrievedemp.addProjecttoEmployee(retrievedProject);
		em.persist(retrievedemp);
		em.getTransaction().commit();
		return "Success";

	}
}
