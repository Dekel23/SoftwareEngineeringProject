// group number 4
package Contact_Package;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import java.util.Date;
public class Main {
	public static Scanner in = new Scanner (System.in);
    public static void main(String[] args)
    {
        menu();
    }

    public static void menu()
    {
        boolean exit = false;
        ArrayList<Contact> contactList = new ArrayList<Contact>();
        SMS sms = new SMS(contactList);
        MyCalendar cal = new MyCalendar();
        Contacts con = new Contacts(in);
    	int option;
    	while (!exit) {
            System.out.println("Choose an option:");
            System.out.println("1- Contact");
            System.out.println("2- SMS");
            System.out.println("3- Calander");
            System.out.println("4- Exit");
            
            option = in.nextInt();
            in.nextLine();
            switch(option) {
                case 1:
                appCon(con, sms, cal);
                break;
                case 2:
                appSMS(con, sms);
                break;

                case 3:
                appCal(con, cal);
                break;
                
                case 4:
                exit=true;
                break;
                default:
                System.out.println("Invalid option, please try again");
                break;
            }


            
    	}
    }

    public static void appCal(Contacts con, MyCalendar cal) {
        int option, len;
        boolean exit = false;
        Date d = null;
        Contact c=null;
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while(!exit) {
            System.out.println("Choose an option:");
            System.out.println("1- add event");
            System.out.println("2- delete event");
            System.out.println("3- print date's events");
            System.out.println("4- print contact's events");
            System.out.println("5- delete event collision");
            System.out.println("6- print all events");
            System.out.println("7- Exit");
            option = in.nextInt();
            in.nextLine();
            switch(option) {
                case 1:
                System.out.print("Choose index of contact in contact list (-1 is contactless event): ");
                option = in.nextInt();
                in.nextLine();
                System.out.println("choose date in yyyy-mm-dd hh:mm:ss format");
                String s = in.nextLine();
                try {
                    d = parser.parse(s);
                }
                catch(ParseException e) {
                    System.out.println("parse was unsuccessful: ");
                    break;
                }
                System.out.println("length of meeting: ");
                len = in.nextInt();
                in.nextLine();
                if(option < 0) {
                    cal.addEvent(new Event(d, len));
                }
                else {
                    c = con.get(option);
                    if(c == null) break;
                    cal.addEvent(new Meeting(d, len, c));
                }
                break;
                case 2:
                cal.deleteEvent();
                break;
                case 3:
                cal.printFromDay();
                break;
                case 4:
                System.out.print("Choose index of contact in contact list: ");
                option = in.nextInt();
                in.nextLine();
                c = con.get(option);
                if(c == null) break;
                cal.printContactMeetings(c);
                break;
                case 5:
                cal.deleteOverlaps();
                break;
                case 6:
                cal.print();
                break;
                case 7:
                System.out.println("Exiting phone book");
                exit = true;
                break;
                default:
                System.out.println("Invalid option, please try again");
                break;
            }
        }
    }
    
    public static void appSMS(Contacts con, SMS sms) {
        int option;
        boolean exit = false;
        Contact c=null;
        String s;
        while(!exit) {
            System.out.println("Choose an option:");
            System.out.println("1- Send message (adds contact if isn't included)");
            System.out.println("2- Delete chat history");
            System.out.println("3- Print chat from name");
            System.out.println("4- Search matching sentence");
            System.out.println("5- Print all chats");
            System.out.println("6- Exit");
            option = in.nextInt();
            in.nextLine();
            switch(option)
            {
                case 1:
                System.out.print("Choose index of contact in contact list: ");
                option = in.nextInt();
                in.nextLine();
                c = con.get(option);
                if(c == null) break;
                System.out.print("send message: ");
                s = in.nextLine();
                sms.addMessage(c, s);
                break;
                case 2:
                System.out.print("Choose index of contact in contact list: ");
                option = in.nextInt();
                in.nextLine();
                c = con.get(option);
                if(c == null) break;
                System.out.print("send message: ");
                sms.clearChat(c);
                break;
                case 3:
                System.out.print("pick name of contact: ");
                sms.printChat(in.nextLine());
                break;
                case 4:
                System.out.print("search matching sentence: ");
                sms.searchChats(in.nextLine());
                break;
                case 5:
                sms.printAllChats();
                break;
                case 6:
                System.out.println("Exiting phone book");
                exit = true;
                break;
                default:
                System.out.println("Invalid option, please try again");
                break;
            }
        }
    }
    
    public static void appCon(Contacts con, SMS sms, MyCalendar cal) {
        int option;
        boolean exit = false;
        while (!exit) {
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
            in.nextLine();
            switch(option)
            {
            case 1:
                con.addContact();
                break;
            case 2:
                System.out.println("Enter contact first name to delete:");
                String deleteName = in.nextLine();
                Contact d = con.deleteContact(deleteName);
                if(d != null) {
                    sms.removeContact(d);
                    cal.deleteContact(d);
                }
                break;
            case 3:
                con.printContacts();
                break;
            case 4:
                ArrayList<Contact> searchResults = con.searchOccurrences();
                printContacts(searchResults);
                break;
            case 5:
                con.sortByName();
                break;
            case 6:
                con.sortByNumber();
                break;
            case 7:
                con.removeDuplicates();
                break;
            case 8:
                con.reversePhoneNumber();
                break;
            case 9:
                try {con.loadToFile();}
                catch(Exception e) {
                System.out.println("ERROR: problem opening the given file");}
                break;
            case 10:
                try {con.loadFromFile();}
                catch(Exception e) {
                System.out.println("ERROR: problem opening the given file");};
                break;
            case 11:
                System.out.println("Exiting phone book");
                exit = true;
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
    
    // public static void sortByName(ArrayList<Contact> contactList)
    // {
    // 	// sort the list by comparing between the name field of the objects
    // 	contactList.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
    // }
    
    // public static void sortByNumber(ArrayList<Contact> contactList)
    // {
    // 	// sort the list by comparing between the phoneNumber field of the objects
    // 	contactList.sort((o1, o2) -> o1.getPhoneNumber().compareTo(o2.getPhoneNumber()));
    // }
    
    // public static void deleteContact(ArrayList<Contact> contactList)
    // {
    //     System.out.println("Enter contact first name to delete:");
    //     String deleteName = in.next();
    //     boolean deleted = false;
    //     for (int i = 0; i < contactList.size(); i++)
    //     {
    //         if (contactList.get(i).getName().equals(deleteName)) // search for appearance of the name
    //         {
    //             contactList.remove(i); // remove from list
    //             deleted = true;
    //             System.out.println("Contact deleted successfully");
    //             break; // end loop so only one will contact will be deleted
    //         }
    //     }
    //     if (!deleted)
    //         System.out.println("Contact not found");
    // }
    
    // public static void addContact(ArrayList<Contact> contactList)
    // {
    // 	 System.out.println("Enter contact first name:");
    //      String name = in.next();
    //      System.out.println("Enter contact phone number (in the format 05d-ddd-dddd):");
    //      String phoneNumber = in.next();
    //      Contact newContact = new Contact(name, phoneNumber); // creating new contact
    //      contactList.add(newContact); // adding contact to the list
    //      System.out.println("Contact added successfully");
    // }
    
    // public static void reversePhoneNumber(ArrayList<Contact> contactList) {
    //     int size = contactList.size();
    //     for (int i = 0; i < size / 2; i++) { // goes on half the list
    //         Contact temp = contactList.get(i); // creating temp contact to swap the contacts in the list
    //         contactList.set(i, contactList.get(size - i - 1));
    //         contactList.set(size - i - 1, temp);
    //     }
    // }
    
    // public static void loadToFile(ArrayList<Contact> contactList)throws IOException {
	// 	// create file and writer
    // 	System.out.println("write the path of the file you want to load to");
    // 	String filePath= in.next();
	// 	File file = new File(filePath);
	// 	FileWriter writer = new FileWriter(file, true);  // true for append!
		
	// 	// write the data
	// 	String s = "";
    // 	for (Contact contact : contactList)
    // 	{
    // 		s+=contact.getName()+":"+contact.getPhoneNumber()+"\n";
    // 	}
	// 	writer.write(s);
		
	// 	// close the writer
	// 	writer.close();
	// }

    // public static void loadFromFile(ArrayList<Contact> contactList)throws FileNotFoundException {
    // 	System.out.println("write the path of the file you want to append from");
    // 	String filePath = in.next();
    // 	int count =1;
	// 	// create file and reader
	// 	File file = new File(filePath);
	// 	Scanner reader = new Scanner(file);
		
	// 	// read the data
	// 	while(reader.hasNext()) {
	// 		String s = reader.nextLine(); //reads a line
	// 		int i=0;
	// 		String name = "", phoneNumber= "";
    // 		while(i<s.length()&& s.charAt(i)!=':') //loading the name of the contact
    // 		{
    // 			name+= s.charAt(i);
    // 			i++;
    // 		}
    // 		i++;//skips the ':'
    // 		while(i<s.length()) //loading the phone number of the contact
    // 		{
    // 			phoneNumber+= s.charAt(i);
    // 			i++;
    // 		}
    // 		if(name!="" && phoneNumber.matches("^05\\d-\\d{3}-\\d{4}$"))//if contact is valid, enters
    // 		{
    // 			Contact newContact = new Contact(name, phoneNumber);
    // 	         contactList.add(newContact);
    // 	         System.out.println("Contact on line " + count + " added successfully");
    // 		}
    // 		else
    // 			System.out.println("ERROR: contact of line " + count + " is invalid. skipped.");
    // 		count++;
	// 	}
		
	// 	// close the reader
	// 	reader.close();
	// }
    // public static void removeDuplicates(ArrayList<Contact> contactList)
    // {
    // 	for (int i = 0; i < contactList.size(); i++) {
    // 		Contact currentElement = contactList.get(i);
    //         for (int j = i + 1; j < contactList.size(); j++) {
    //             if (currentElement.equals(contactList.get(j))) {
    //             	contactList.remove(j);
    //                 j--; // Decrement j since the size of the ArrayList has reduced
    //             }
    //         }
    //     }
    // }

    // public static ArrayList<Contact> searchOccurrences(ArrayList<Contact> contactList)
    // {
    //     System.out.println("Enter contact first name to search:");
    //     String name = in.next();
    //     ArrayList<Contact> nameContactList = new ArrayList<Contact>();
    //     for (Contact contact : contactList)
    //     {
    //         if (contact.getName().equals(name)) // if the contact has the entered name
    //             nameContactList.add(contact); //add to new list
    //     }
    //     return nameContactList; // return list of contact with the entered name
    // }
}