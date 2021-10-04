package sumerge.backendtrack;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String name;
    	int age;
    	Adress adress;
    	String email;
    	String doctorName;
    	LocalDate appointmentDate;
    	boolean isValid = false;
    	while(!isValid) {

        	System.out.println("Enter your name");
            Scanner scanner = new Scanner(System.in);
            name=scanner.nextLine();
            
            System.out.println("Enter your age");
            scanner = new Scanner(System.in);
            age = scanner.nextInt();
            
            System.out.println("Enter your adress building number");
            scanner = new Scanner(System.in);
            int buildingNumber = scanner.nextInt();
            
            System.out.println("Enter your adress street name");
            scanner = new Scanner(System.in);
            String streetName = scanner.nextLine();
            
            adress = new Adress(streetName, buildingNumber);
            
            
            System.out.println("Enter your email");
            scanner = new Scanner(System.in);
            email= scanner.nextLine();
            
            System.out.println("Enter your doctor name");
            scanner = new Scanner(System.in);
            doctorName = scanner.nextLine();
            
            
            System.out.println("Enter the appointment's date in this format (day/month/year)");
            scanner = new Scanner(System.in);
            String dateString = scanner.nextLine();
            boolean flag = true;
            while(flag) {
            	String [] dateStringsplit = dateString.split("/"); 
            	if(dateStringsplit.length==3) {
            		if(Integer.parseInt(dateStringsplit[0]) <= 31 && Integer.parseInt(dateStringsplit[0]) > 0 && Integer.parseInt(dateStringsplit[1]) <= 12 && Integer.parseInt(dateStringsplit[1]) > 0) {
            			flag = false;
            		}
            		else {
            			System.out.println("Invalid date Enter the appointment's date in this format (day/month/year)");
            	        scanner = new Scanner(System.in);
            	        dateString = scanner.nextLine();			
    				}
            		
            	}
            	else {
        			System.out.println("Invalid date Enter the appointment's date in this format (day/month/year)");
        	        scanner = new Scanner(System.in);
        	        dateString = scanner.nextLine();			
    			}
            	
            }
            
            appointmentDate = LocalDate.of(Integer.parseInt(dateString.split("/")[2]) , Integer.parseInt(dateString.split("/")[1]) , Integer.parseInt(dateString.split("/")[0]));
            
            Appointment appointment = new Appointment(name, age, adress, email, doctorName, appointmentDate);
            
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Adress>> adressViolations = validator.validate(adress);
            Set<ConstraintViolation<Appointment>> violations = validator.validate(appointment);
            
            for (ConstraintViolation<Appointment> violation : violations) {
            	System.out.println(violation.getMessage()); 
            }
            
            for (ConstraintViolation<Adress> violation : adressViolations) {
            	System.out.println(violation.getMessage()); 
            }
            isValid = violations.isEmpty() && adressViolations.isEmpty();
    	}
    }
}
