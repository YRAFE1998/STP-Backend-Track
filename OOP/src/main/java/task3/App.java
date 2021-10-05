package task3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class App 
{
    public static void main( String[] args )
    {
    	//create 7 different employees
        Employee emp1 = new Employee("Shady Azouz", "Associate software Engineer", "01102533348");
        Employee emp2 = new Employee("Yousef Refaat", "Associate software Engineer", "01228899932");
        Employee emp3 = new Employee("Ahmed Tarek", "Associate software Engineer", "0152289655");
        Employee emp4 = new Employee("Hussain Yahia", "Software Engineer", "0100025655");
        Employee emp5 = new Employee("Shady Abdelwahab", "CEO", "01005578441");
        Employee emp6 = new Employee("Ahmed Hussein", "Software Engineer Lead", "01003332325");
        Employee emp7 = new Employee("Youmna Yousry", "Software Engineer Lead", "01122578164");
        
        //create an Arraylist and add the employees to it
        ArrayList<Employee> employees = new ArrayList<Employee>();
        employees.add(emp7);
        employees.add(emp4);
        employees.add(emp3);
        employees.add(emp6);
        employees.add(emp1);
        employees.add(emp5);
        employees.add(emp2);
        
        
        //print the employee list before any modification
        /*System.out.println("Initial Employee List \n");

        for(Employee emp: employees)
        	System.out.println(emp);
         */        
        
        
        //create the compare function as a lambda function to be used later as a comparator, 
        //Note that it returns true if titles for both employees are equal, else it returns false
       
        employeeComparator compareCriteria = (Employee employee1, Employee employee2) -> {
        	if(employee1.getTitle().equals(employee2.getTitle())) {
        		return true;
        	}
        	return false;
        };
        
        
        
        //Now we are going to group the employees together by job titles,
        //That is why we are passing the compare function to the method,
        //so it can knows how to compare between 2 employees
        
        groupemployees(employees, compareCriteria);
        
        
        
        
        //Print the employees list after modification(Grouping)
        /*
        System.out.println("\n \n Grouped List of employees according to title: \n");
        
        for(Employee emp: employees)
    		System.out.println(emp);
        
        System.out.println("\n \n");
        */
        
        generateListofTitles(employees, compareCriteria);
        
    }
    
    public static void groupemployees(ArrayList<Employee> employees, employeeComparator compareCriteria) {
    	
    	
    	//Here there will be two indeces, the mIndex represents the moving index to search for
    	//the matching elements,
    	//the sIndex is the index for the last element in the current group
    	
    	int mIndex = 1;
    	int sIndex = 0;
    	
    	
    	//While we haven't reached the group before the last one)
    	//NOTE: WE DON'T HAVE TO REACH THE LAST ELEMENT, AS THERE ARE TWO CASES,
    	//EITHER THE LAST ELEMENT BELONGS TO A NEW GROUP, 
    	//OR IT BELONGS TO THE SAME GROUP AS WHERE THE SINDEX IS
    	//SO IN BOTH CASES, WE DON'T HAVE TO MOVE THE LAST ELEMENT
    	while(sIndex< employees.size()-1) {
    		if(compareCriteria.compare(employees.get(sIndex), employees.get(mIndex))) {
    			if(mIndex-sIndex>1) {
    				Collections.swap(employees, mIndex, sIndex+1);
    			}
    			sIndex++;
    			mIndex++;
    		}
    		else {
				mIndex++;
			}
    		if(mIndex>= employees.size()) {
    			sIndex++;
    			mIndex=sIndex+1;
    		}
    	}
    }
    
    public static void generateListofTitles(ArrayList<Employee> employees, employeeComparator compareCriteria) {
    	ArrayList<EmployeeGroup> groups = new ArrayList<EmployeeGroup>();
		Employee employeex = employees.get(0);
    	groups.add(new EmployeeGroup(employeex.getTitle()));
    	groups.get(0).addEmployee(employees.get(0));
    	
    	for(int i=1;i<employees.size();i++) {
    		Employee employee1 = employees.get(i);
    		Employee employee2 = groups.get(groups.size()-1).employeesdetails.get(0);
    		if(compareCriteria.compare(employee1, employee2)) {
    			groups.get(groups.size()-1).addEmployee(employee1);
    		}
    		else {
    			groups.add(new EmployeeGroup(employee1.getTitle()));
    			groups.get(groups.size()-1).addEmployee(employee1);
    		}
    	}
    	
    	
    	Comparator<EmployeeGroup> sortComparator = (EmployeeGroup eg1, EmployeeGroup eg2) -> eg1.getCount()>eg2.getCount()?-1:1;
    	
    	Collections.sort(groups,sortComparator);
    	
    	for (EmployeeGroup employeeGroup : groups) {
			System.out.println(employeeGroup);
		}
    }
    
    
    
    interface employeeComparator{
    	boolean compare(Employee e1, Employee e2);
    }
    
    
    static class EmployeeGroup{
    	String title;
    	ArrayList<Employee> employeesdetails = new ArrayList<Employee>();
    	
    	public EmployeeGroup(String title) {
			this.title = title;
		}
    
    	public void addEmployee(Employee employee) { 
    		employeesdetails.add(employee);
    	}
    	
    	public int getCount() {
    		return this.employeesdetails.size();
    	}
    	

		@Override
		public String toString() {
			return "EmployeeGroup [count=" + employeesdetails.size() + ", title=" + (employeesdetails.size()>2?title:"Special title") + ", employeesdetails=" + employeesdetails
					+ "]";
		}
    	
     }
    
}
