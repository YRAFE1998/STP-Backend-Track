package mavenproject;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import rest.controller.*;
import entities.Employee;
import entities.Project;

@ApplicationPath("api")
public class Application extends javax.ws.rs.core.Application {

	public static void main(String[] args) {



		/*try{
			System.out.println("Enter 1 to close");
			int anInt1 = System.in.read();
			if(anInt1 == 1)
				controller.close(emFactory);
		} catch(IOException ioe){
			System.out.println(ioe.toString());
		}
		*/
		/*
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

       	//getEmployeesinProjectName(em, "Alex Bank");
       	//addEmployeetoProject(em, 3, 1);
       	//Employee.displayAllemployees(em);
       	*/

	}


}
