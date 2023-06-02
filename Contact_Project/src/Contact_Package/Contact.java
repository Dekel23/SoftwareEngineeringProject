// group number 4
package Contact_Package;
import java.util.Scanner;
public class Contact {
	public static Scanner in = new Scanner (System.in);
	private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber)
    {
        this.setName(name);
        this.setPhoneNumber(phoneNumber);
    }

    public void setName(String name)
    {
        this.name = name; // any name acceptable
    }

    public void setPhoneNumber(String phoneNumber)
    {
        boolean isValid = phoneNumber.matches("^05\\d-\\d{3}-\\d{4}$"); // check if the phone number is in the right form
        while (!isValid) // call again until phone number is in the right form
        {
            System.out.println("Phone number need to have the form 05d-ddd-dddd when d means digit, enter phone number again");
            phoneNumber = in.next();
            isValid = phoneNumber.matches("^05\\d-\\d{3}-\\d{4}$");
        }
        this.phoneNumber = phoneNumber;	
    }

    public String getName()
    {
        return this.name;
    }

    public String getPhoneNumber()
    {
    	return this.phoneNumber;
    }
    
    public boolean equals(Object obj)
    {
    	if (obj == this)
    		return true;
    	if (!(obj instanceof Contact)) // check of obj is from the type of Contact
    		return false;
    	Contact other = (Contact)obj; // convert from obj to contact object
    	return (this.name.equals(other.getName()) && this.phoneNumber.equals(other.getPhoneNumber()));
    }
    
    public void print()
    {
    	System.out.println(this.name + ":" + this.phoneNumber);
    }
}