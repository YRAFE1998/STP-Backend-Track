package sumerge.backendtrack;

/**
 * Hello world!
 *
 */
import java.util.Scanner;

import org.apache.commons.codec.binary.Base64;


public class App
{
    public static void main( String[] args )
    {
    	boolean flag = true;
    	String inputstring = null;
    	System.out.print("Enter a string: ");	

    	while (flag) {
    		Scanner sc= new Scanner(System.in); //System.in is a standard input stream  
        	inputstring = sc.nextLine();
        	if(inputstring instanceof String)
        		flag=false;
        	else
        		System.out.println("Please enter a string");
    	}
    	
    	byte[] encodedBytes = Base64.encodeBase64(inputstring.getBytes());
    	System.out.println("You have entered: "+ inputstring);
    	System.out.println("encodedBytes " + new String(encodedBytes));

    }
}
