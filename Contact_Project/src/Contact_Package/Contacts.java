package Contact_Package;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;



public class Contacts {
	private ArrayList<Contact> contactList;
	Scanner in;
	
	public Contacts(Scanner in) {
		contactList = new ArrayList<Contact>();
		this.in = in;
	}
	
    public Contact get(int index) {
        if(index<0 || index>=contactList.size()) {
            System.out.println("invalid index.");
            return null;
        }
        return contactList.get(index);
    }

	public void printContacts()
    {
    	if (contactList.size() == 0)
            System.out.println("No contacts found");
        else
            for (Contact contact : contactList) // goes on all the contacts in contactList
            {
                contact.print();
            }
    }
    
    public void sortByName()
    {
    	// sort the list by comparing between the name field of the objects
    	Collections.sort(contactList, new NameComp());
    }
    
    public void sortByNumber()
    {
    	// sort the list by comparing between the phoneNumber field of the objects
    	Collections.sort(contactList, new NumberComp());
    }
    
    public void deleteContact()
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
    
    public void addContact()
    {
    	 System.out.println("Enter contact first name:");
         String name = in.next();
         boolean cont = nameInBook(name);
         if (cont == true)
         {
        	 System.out.println("Already have contact with this name:(");
         }
         else
         {
        	 System.out.println("Enter contact phone number (in the format 05d-ddd-dddd):");
             String phoneNumber = in.next();
             Contact newContact = new Contact(name, phoneNumber); // creating new contact
             contactList.add(newContact); // adding contact to the list
             System.out.println("Contact added successfully");
         }
    }
    
    public void reversePhoneNumber() {
        int size = contactList.size();
        for (int i = 0; i < size / 2; i++) { // goes on half the list
            Contact temp = contactList.get(i); // creating temp contact to swap the contacts in the list
            contactList.set(i, contactList.get(size - i - 1));
            contactList.set(size - i - 1, temp);
        }
    }
    
    public void loadToFile()throws IOException {
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

    public void loadFromFile()throws FileNotFoundException {
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
    public void removeDuplicates()
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

    public ArrayList<Contact> searchOccurrences()
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

    public boolean nameInBook(String nameToAdd)
    {
    	for (int i =0; i< contactList.size(); i++)
    	{
    		if(contactList.get(i).getName().equals(nameToAdd))
    		{
    			return true;
    		}
    	}
    	return false;
    }
}
