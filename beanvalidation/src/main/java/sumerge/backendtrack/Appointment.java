package sumerge.backendtrack;

import java.sql.Date;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class Appointment {

	@NotEmpty(message="Name must have a value")
	@Size(min=10,max=100, message = "Name must atleast be 10 characters, and atmost be 100 characters")
	private String name;
	
	@Min(value=0, message = "Minimum value must be 0")
	@Max(value=150, message = "Maximum value must be 150")
	@Positive
	private int age;
	
	
	private Adress adress;
	
	@Email(message="You must enter a valid email")
	private String email;
	
	@NotNull(message="Doctor Name must atleast be 10 characters, and atmost be 100 characters")
	@Size(min=10,max=100,message = "Doctor name m")
	private String doctorName;
	
	@FutureOrPresent
	private LocalDate date;

	public Appointment(String name, int age, Adress adress, String email, String doctorName, LocalDate date) {
		this.name = name;
		this.age = age;
		this.adress = adress;
		this.email = email;
		this.doctorName = doctorName;
		this.date = date;
	}
	
	
	
}


class Adress{
	
	@NotEmpty(message = "Adress must have a street")
	private String streetName;
	
	@NotNull(message = "Adress must have a building number")
	private int buildingNumber;
	
	public Adress( String streetName, int buildingNumber) {
		this.streetName = streetName;
		this.buildingNumber = buildingNumber;
	}
}
