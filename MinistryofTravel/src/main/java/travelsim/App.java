package travelsim;

import java.util.Arrays;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
    	List<Traveler> travelerList = Arrays.asList(
    			new Traveler("1", "Yousef Refaat", false, false, PCRStatus.NEGATIVE),
    			new Traveler("2", "Mohamed Shalaby", false, true, PCRStatus.NEGATIVE),
    			new Traveler("3", "Mahmoud Ahmed", false, false, PCRStatus.NEGATIVE),
    			new Traveler("4", "Yasmine Gamal", false, false, PCRStatus.POSITIVE),
    			new Traveler("6", "Manar Ashoor", true, false, PCRStatus.NEGATIVE),
    			new Traveler("7", "Mohamed Hany", true, true, PCRStatus.POSITIVE),
    			new Traveler("8", "Ahmed Saeed", false, false, PCRStatus.NEGATIVE)
    			);
    	
    	    
    	travelerList.stream()
    	.forEach(p -> p.getInfo(ROLES.PORT_OFFICER));
    	
    	travelerList.stream()
    	.forEach(p -> p.getInfo(ROLES.IMMIGRATION_OFFICER));
    	
    	travelerList.stream()
    	.forEach(p -> p.getInfo(ROLES.HEALTH_OFFICER));
    	
    	travelerList.stream()
    	.forEach(p -> p.getInfo(ROLES.PCR_CONDUCTOR));
    	    	
    }
}
