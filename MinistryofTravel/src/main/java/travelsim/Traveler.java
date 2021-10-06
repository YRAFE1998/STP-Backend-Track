package travelsim;

import java.util.ArrayList;

public class Traveler {

	private String Id;
	private String name;
	private ArrayList<String> placesVisited = new ArrayList<String>();
	private boolean hasFever;
	private boolean hasCough;
	private PCRStatus pcrStatus;
	private ArrayList<String> allergiesList = new ArrayList<String>();
	private ArrayList<String> specialConditions = new ArrayList<String>();
	
	public Traveler(String id, String name, boolean hasFever, boolean hasCough, PCRStatus pcrStatus) {
		Id = id;
		this.name = name;
		this.hasFever = hasFever;
		this.hasCough = hasCough;
		this.pcrStatus = pcrStatus;
	}

	
	

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getPlacesVisited() {
		return placesVisited;
	}

	public void setPlacesVisited(ArrayList<String> placesVisited) {
		this.placesVisited = placesVisited;
	}

	public boolean isHasFever() {
		return hasFever;
	}

	public void setHasFever(boolean hasFever) {
		this.hasFever = hasFever;
	}

	public boolean isHasCough() {
		return hasCough;
	}

	public void setHasCough(boolean hasCough) {
		this.hasCough = hasCough;
	}

	public PCRStatus getPcrStatus() {
		return pcrStatus;
	}

	public void setPcrStatus(PCRStatus pcrStatus) {
		this.pcrStatus = pcrStatus;
	}

	public ArrayList<String> getAllergiesList() {
		return allergiesList;
	}

	public void setAllergiesList(ArrayList<String> allergiesList) {
		this.allergiesList = allergiesList;
	}

	public ArrayList<String> getSpecialConditions() {
		return specialConditions;
	}

	public void setSpecialConditions(ArrayList<String> specialConditionStrings) {
		this.specialConditions = specialConditionStrings;
	}
	
	public void getInfo(ROLES role) {
		if(role == ROLES.PORT_OFFICER)
			System.out.println(
					"\n \nname: " + getName() + " Reached the Port Officer" +
					"\nPCR status: " + getPcrStatus() + 
					"\nSpecial Conditions: " + (getSpecialConditions().isEmpty()?"No special conditions":getSpecialConditions().toString()) );
		
		else if(role == ROLES.IMMIGRATION_OFFICER)
			System.out.println(
					"\n \nname: " + getName() + " Reached the Immigration Officer" +
					"\nPCR status: " + getPcrStatus() + 
					"\nSpecial Conditions: " + (getSpecialConditions().isEmpty()?"No special conditions":getSpecialConditions().toString()) +
					"\nPlaces visited in the last 14 months: " + (getPlacesVisited().isEmpty()?"No places visited in the last 14 days":getPlacesVisited().toString()));
		
		else if(role == ROLES.HEALTH_OFFICER)
			System.out.println(toString());
		else 
			System.out.println(
					"\n \nname: " + name + " Reached the PCR test conductor" +
					"\nhasFever: " + hasFever +
					"\nhasCough: " + hasCough 
					);
	}

	@Override
	public String toString() {
		return "\n \nname: " + name + " Reached the Health Officer" +
				"\nplacesVisited: " + (getPlacesVisited().isEmpty()?"No places visited in the last 14 days":getPlacesVisited().toString()) +
				"\nhasFever: " + hasFever +
				"\nhasCough: " + hasCough + 
				"\npcrStatus: " + pcrStatus + 
				"\nallergiesList: " + (getAllergiesList().isEmpty()?"No allergies":getAllergiesList().toString()) +
				"\nSpecial Conditions: " + (getSpecialConditions().isEmpty()?"No special conditions":getSpecialConditions().toString());
	}
	
}
