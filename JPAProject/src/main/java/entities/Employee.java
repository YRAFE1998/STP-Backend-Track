package entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.ws.rs.Path;

@Entity
@Table(name = "employees")
@Path("employee")
public class Employee {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dept=" + dept
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", nationalID=" + nationalID + ", role=" + role
				+ "]";
	}

	@Column(name = "firstName",length=30, nullable=false, unique=false)
	private String firstName;
	
	@Column(name = "lastName", length = 30, nullable = false, unique = false)
	private String lastName;
	
	
	@Column(name = "department", length = 50, nullable = false, unique = false)
	private String dept;
	
	@Column(name="email", length = 50, nullable = false, unique = true)
	private String email;
	
	@Column(name="phoneNumber", length = 12, nullable = true, unique = true)
	private String phoneNumber;

	
	@Column(name="nationalID", length = 14, nullable = false, unique = true)
	private String nationalID;
	
	@Column(name = "employeeRole", length = 50, nullable = false, unique = false)
	private String role;
	
	@OneToMany
	private List<Project> managedProjects;
	
	
	
	@ManyToMany
	@JoinTable(
			name = "employee_workson_project",
			joinColumns = @JoinColumn(name="projectID"),
			inverseJoinColumns = @JoinColumn(name="employeeID")
			)
	private Set<Project> employee_works_on_project = new HashSet();
	
		

	public Set<Project> getEmployee_works_on_project() {
		return employee_works_on_project;
	}

	public void addProjecttoEmployee(Project employee_works_on_project) {
		this.employee_works_on_project.add(employee_works_on_project);
	}


	
	public Employee() {
		
	}

	public Employee(long id, String firstName, String lastName, String dept, String email,
			String nationalID, String role) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dept = dept;
		this.email = email;
		this.nationalID = nationalID;
		this.role = role;
	}


	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
		}

	public String getFirstName() {
		return firstName;
		}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
		}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
		}
	public String getDept() {
		return dept;
		}

	public void setDept(String dept) {
		this.dept = dept;
	}
	
}
