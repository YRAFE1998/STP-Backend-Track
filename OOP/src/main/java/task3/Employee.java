package task3;

public class Employee {
	
	private String name;
	private String title;
	private String phoneNumber;
		
	public Employee(String name, String title, String phoneNumber) {
		this.name = name;
		this.title = title;
		this.phoneNumber = phoneNumber;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	@Override
	public String toString() {
		return "[name=" + name + ", title=" + title + ", phoneNumber=" + phoneNumber + "]";
	}
	
	
	
}
