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
@Table(name="projects")
public class Projects {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "projectId")
	private long id;

	
	@Column(name="name")
	private String name;

	@Column(name="date")
	private Date startDate;

	@OneToMany(mappedBy = "employees")
	private Set<Employee> employees;
	
	@ManyToMany
	@JoinTable(
			name = "employee_workson_project",
			joinColumns = @JoinColumn(name="employeeId"),
			inverseJoinColumns = @JoinColumn(name="projectId")
			)
	Set<Employee> employee_works_on_project;
	
	
	
	
	
	
	
}
