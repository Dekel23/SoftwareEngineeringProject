// group number 4
package Contact_Package;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.io.IOException;
public class Main {
	public static Scanner in = new Scanner (System.in);
    public static void main(String[] args)
    {
        ArrayList<Contact> contactList = new ArrayList<Contact>();
        menu(contactList);
    }
    
    public static void menu(ArrayList<Contact> contactList)
    {
    	int option;
    	while (true) {
            System.out.println("Choose an option:");
            System.out.println("1- Add a new contact");
            System.out.println("2- Delete an existing contact");
            System.out.println("3- Print all contacts");
            System.out.println("4- Search for contacts by first name");
            System.out.println("5- sort contact list by first name");
            System.out.println("6- sort contact list by phone number");
            System.out.println("7- remove duplicates in the contact list");
            System.out.println("8- reverse order the contact list");
            System.out.println("9- load contact list to file");
            System.out.println("10- append contact list from file");
            System.out.println("11- Exit");
            option = in.nextInt();
            switch(option)
            {
            case 1:
            	addContact(contactList);
            	break;
            case 2:
            	deleteContact(contactList);
            	break;
            case 3:
            	printContacts(contactList);
            	break;
            case 4:
                ArrayList<Contact> searchResults = searchOccurrences(contactList);
                printContacts(searchResults);
                break;
            case 5:
            	sortByName(contactList);
            	break;
            case 6:
            	sortByNumber(contactList);
            	break;
            case 7:
            	removeDuplicates(contactList);
            	break;
            case 8:
            	reversePhoneNumber(contactList);
            	break;
            case 9:
            	try {loadToFile(contactList);}
            	catch(Exception e) {
            	System.out.println("ERROR: problem opening the given file");}
            	break;
            case 10:
            	try {loadFromFile(contactList);}
            	catch(Exception e) {
            	System.out.println("ERROR: problem opening the given file");};
    			break;
            case 11:
                System.out.println("Exiting phone book");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option, please try again");
                break;
            }
    	}
    }
    
    public static void printContacts(ArrayList<Contact> contactList)
    {
    	if (contactList.size() == 0)
            System.out.println("No contacts found");
        else
            for (Contact contact : contactList) // goes on all the contacts in contactList
            {
                contact.print();
            }
    }
    
    public static void sortByName(ArrayList<Contact> contactList)
    {
    	// sort the list by comparing between the name field of the objects
    	contactList.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
    }
    
    public static void sortByNumber(ArrayList<Contact> contactList)
    {
    	// sort the list by comparing between the phoneNumber field of the objects
    	contactList.sort((o1, o2) -> o1.getPhoneNumber().compareTo(o2.getPhoneNumber()));
    }
    
    public static void deleteContact(ArrayList<Contact> contactList)
    {
        System.out.println("Enter contact first name to delete:");
        String deleteName = in.next();
        boolean deleted = false;
        for (int i = 0; i < contactList.size(); i++)
        {
            if (contactList.get(i).getName().equals(deleteName)) // search for appearance of the name
            {
                contactList.remove(i); // remove from list
                deleted = true;
                System.out.println("Contact deleted successfully");
                break; // end loop so only one will contact will be deleted
            }
        }
        if (!deleted)
            System.out.println("Contact not found");
    }
    
    public static void addContact(ArrayList<Contact> contactList)
    {
    	 System.out.println("Enter contact first name:");
         String name = in.next();
         System.out.println("Enter contact phone number (in the format 05d-ddd-dddd):");
         String phoneNumber = in.next();
         Contact newContact = new Contact(name, phoneNumber); // creating new contact
         contactList.add(newContact); // adding contact to the list
         System.out.println("Contact added successfully");
    }
    
    public static void reversePhoneNumber(ArrayList<Contact> contactList) {
        int size = contactList.size();
        for (int i = 0; i < size / 2; i++) { // goes on half the list
            Contact temp = contactList.get(i); // creating temp contact to swap the contacts in the list
            contactList.set(i, contactList.get(size - i - 1));
            contactList.set(size - i - 1, temp);
        }
    }
    
    public static void loadToFile(ArrayList<Contact> contactList)throws IOException {
		// create file and writer
    	System.out.println("write the path of the file you want to load to");
    	String filePath= in.next();
		File file = new File(filePath);
		FileWriter writer = new FileWriter(file, true);  // true for append!
		
		// write the data
		String s = "";
    	for (Contact contact : contactList)
    	{
    		s+=contact.getName()+":"+contact.getPhoneNumber()+"\n";
    	}
		writer.write(s);
		
		// close the writer
		writer.close();
	}

    public static void loadFromFile(ArrayList<Contact> contactList)throws FileNotFoundException {
    	System.out.println("write the path of the file you want to append from");
    	String filePath = in.next();
    	int count =1;
		// create file and reader
		File file = new File(filePath);
		Scanner reader = new Scanner(file);
		
		// read the data
		while(reader.hasNext()) {
			String s = reader.nextLine(); //reads a line
			int i=0;
			String name = "", phoneNumber= "";
    		while(i<s.length()&& s.charAt(i)!=':') //loading the name of the contact
    		{
    			name+= s.charAt(i);
    			i++;
    		}
    		i++;//skips the ':'
    		while(i<s.length()) //loading the phone number of the contact
    		{
    			phoneNumber+= s.charAt(i);
    			i++;
    		}
    		if(name!="" && phoneNumber.matches("^05\\d-\\d{3}-\\d{4}$"))//if contact is valid, enters
    		{
    			Contact newContact = new Contact(name, phoneNumber);
    	         contactList.add(newContact);
    	         System.out.println("Contact on line " + count + " added successfully");
    		}
    		else
    			System.out.println("ERROR: contact of line " + count + " is invalid. skipped.");
    		count++;
		}
		
		// close the reader
		reader.close();
	}
    public static void removeDuplicates(ArrayList<Contact> contactList)
    {
    	for (int i = 0; i < contactList.size(); i++) {
    		Contact currentElement = contactList.get(i);
            for (int j = i + 1; j < contactList.size(); j++) {
                if (currentElement.equals(contactList.get(j))) {
                	contactList.remove(j);
                    j--; // Decrement j since the size of the ArrayList has reduced
                }
            }
        }
    }

    public static ArrayList<Contact> searchOccurrences(ArrayList<Contact> contactList)
    {
        System.out.println("Enter contact first name to search:");
        String name = in.next();
        ArrayList<Contact> nameContactList = new ArrayList<Contact>();
        for (Contact contact : contactList)
        {
            if (contact.getName().equals(name)) // if the contact has the entered name
                nameContactList.add(contact); //add to new list
        }
        return nameContactList; // return list of contact with the entered name
    }
}