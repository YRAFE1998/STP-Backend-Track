package rest.controller;

import entities.Employee;
import services.Employee_Project;

import java.awt.*;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("controller")
public class Controller {
    private EntityManager entityManager;


    public Controller(){
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("JPAProject");

		this.entityManager = emFactory.createEntityManager();

		start();
	}
    public void start(){
        entityManager.getTransaction().begin();
    }

    public void close(EntityManagerFactory factory){
		factory.close();
		entityManager.close();
	}


	@GET
	@Path("hello")
	public static Response getpath(){
    	System.out.println("In here");
    	return Response.ok("{name:'hi'}", MediaType.APPLICATION_JSON).build();
    }

	@GET
	@Path("employee")
	@Produces(MediaType.APPLICATION_JSON)
	public Response displayAllemployees() {
    	String str = "[";
    	List<Employee> employees =  services.Employee.getAll(entityManager);
    	for(Employee emp: employees)
    		str+=emp.toString() + ",";

    	str+="]";
		return Response.ok(str, MediaType.APPLICATION_JSON).build() ;
    }

	@PUT
	@Path("employee/{employeeID}/project/{projectID}")
	public Response addemptoProject(@PathParam("employeeID") long employeeId, @PathParam("projectID") long projectId){
		Employee_Project.addEmployeetoProject(entityManager,employeeId,projectId);
		return Response.ok("Successfully added").build();
	}

	@GET
	@Path("employees/project/{projectName}")
	public Response getemployeeinProjectbyName(@PathParam("projectName") String projectName){
		String str = "[";
		List<Employee> employees = Employee_Project.getEmployeesinProjectName(entityManager, projectName);
		for(Employee emp: employees)
			str+=emp.toString() + ",";
		str+="]";
		return Response.ok(str).build();
	}


}
