// group number 4
package Contact_Package;
import java.util.Scanner;
public class Contact{
	public static Scanner in = new Scanner (System.in);
	private String name;
    private String phoneNumber;

    public static void main(String[] arg) {
        Contact contact1 = new Contact("my name is", "050-000-0000");
        Contact contact2 = new Contact("my name is", "050-000-0000");
        Contact contact3 = new Contact("my name was", "050-000-0001");


        System.out.print("contact1: ");
        contact1.print();
        System.out.print("contact2: ");
        contact2.print();
        System.out.print("contact3: ");
        contact3.print();
        System.out.print("contact1 == contact2? ");
        System.out.println(contact1.equals(contact2));
        System.out.print("contact1 == contact3? ");
        System.out.println(contact1.equals(contact3));
        
        System.out.println();

        System.out.println("contact1 number changed to 050-000-0001");
        contact1.setPhoneNumber("050-000-0001");
        System.out.print("contact1 number: ");
        System.out.println(contact1.getPhoneNumber());

        System.out.println();

        System.out.println("contact1 number changed to 'my name will'");
        contact1.setName("my name will");
        System.out.print("contact1 name: ");
        System.out.println(contact1.getName());

        System.out.println();

        System.out.print("contact1: ");
        contact1.print();

        System.out.println("trying to set phone number to 'test': ");
        contact1.setPhoneNumber("test");
        
        System.out.print("contact1 number: ");
        System.out.println(contact1.getPhoneNumber());

    }

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
    
    public String toString()
    {
    	return (this.name + ":" + this.phoneNumber);
    }

    public void print() {
        System.out.println(this.toString());
    }

}
