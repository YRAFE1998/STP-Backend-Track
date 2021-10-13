package entities;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.ws.rs.Path;

@Entity
@Table(name="projects")
public class Project {
		@Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name = "id")
		private long id;

		
		@Column(name="name")
		private String name;

		@Column(name="startDate")
		private Date startDate;

		@ManyToOne()
		@JoinColumn(name ="managerID")
		private Employee manager;		
		
		@ManyToMany
		@JoinTable(
				name = "employee_workson_project",
				joinColumns = @JoinColumn(name="projectID",table = "projects", referencedColumnName = "id"),
				inverseJoinColumns = @JoinColumn(name="employeeID",table = "employees", referencedColumnName = "id")
				)
		private Set<Employee> employee_works_on_project = new HashSet();
		
		
		public Set<Employee> getEmployee_works_on_project() {
			return employee_works_on_project;
		}

		public void addEmployeetoProject(Employee employee_works_on_project) {
			this.employee_works_on_project.add(employee_works_on_project);
		}
		public Project(long id, String name, Date startDate) {
			this.id = id;
			this.name = name;
			this.startDate = startDate;
			//this.manager = manager;
			//this.employee_works_on_project = employee_works_on_project;
		}

		public Project() {
		}
		
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Date getStartDate() {
			return startDate;
		}

		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}

		public Employee getManager() {
			return manager;
		}

		public void setManager(Employee manager) {
			this.manager = manager;
		}

		/*public Set<Employee> getEmployees() {
			return employees;
		}

		public void setEmployees(Set<Employee> employees) {
			this.employees = employees;
		}

		public Set<Employee> getEmployee_works_on_project() {
			return employee_works_on_project;
		}

		public void setEmployee_works_on_project(Set<Employee> employee_works_on_project) {
			this.employee_works_on_project = employee_works_on_project;
		}
*/
		@Override
		public String toString() {
			return "Projects [id=" + id + ", name=" + name + ", startDate=" + startDate;
			//+ ", employees=" + employees + ", employee_works_on_project=" + employee_works_on_project + "]";
		}

}
