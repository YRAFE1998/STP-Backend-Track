package home;
import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "employeeId")
	private long id;
	
	@Column(name = "firstName",length=30, nullable=false, unique=false)
	private String firstName;
	
	@Column(name = "lastName", length = 30, nullable = false, unique = false)
	private String lastName;
	
	
	@Column(name = "dept", length = 30, nullable = false, unique = false)
	private String dept;
	
	@Column(name="email", length = 50, nullable = false, unique = true)
	private String email;
	
	@Column(name="phoneNumber", length = 12, nullable = true, unique = true)
	private String phoneNumber;

	
	@Column(name="nationalID", length = 14, nullable = false, unique = true)
	private String nationalID;
	
	@Column(name = "role", length = 30, nullable = false, unique = false)
	private String role;
	
	
	@OneToMany(mappedBy = "projects")
	private Set<Projects> projects;
	
	@ManyToMany
	@JoinTable(
			name = "employee_workson_project",
			joinColumns = @JoinColumn(name="projectId"),
			inverseJoinColumns = @JoinColumn(name="employeeId")
			)
	Set<Projects> employee_works_on_project;
	
		

	public Employee() {
		
	}

	public Employee(Long id, String firstName, String lastName, String dept) {
		this.setId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setDept(dept);
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