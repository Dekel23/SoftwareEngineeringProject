package Contact_Package;

import java.util.ArrayList;

public class SMS {

    public static void main(String[] a) {
        ArrayList<Contact> c = new ArrayList<>();
        Contact contact1 = new Contact("A", "050-000-0000");
        Contact contact2 = new Contact("B", "050-000-0000");
        c.add(contact1);
        c.add(contact2);
        c.add(new Contact("C", "050-000-0000"));

        System.out.println("-====-1");
        SMS s = new SMS(c);
        s.addMessage(contact1, "hello from the other world!");
        s.addMessage(contact2, "next time go to another place");
        System.out.println("-====-2");
        s.printAllChats();
        System.out.println("-====-3");
        s.searchChats("other");
        System.out.println("-====-4");
        s.clearChat(contact1);
        System.out.println("-====-5");
        s.printChat("A");
        System.out.println("-====-6");
        s.printChat("B");
        s.exit();
    }


	  public final ArrayList<Contact> contacts;
    private final ArrayList<String> chats; // Index corresponds to contacts
    // each string is the entire chat

    public SMS(ArrayList<Contact> contacts) {
        this.contacts = new ArrayList<Contact>(contacts.size());
        this.chats = new ArrayList<String>(contacts.size());
        for (Contact contact : contacts) { // copy all contacts to a new list
            String name = contact.getName();
            String phoneNumber = contact.getPhoneNumber();

            this.contacts.add(new Contact(name, phoneNumber));
            this.chats.add("");
        }


    }

    public int nameIndex(String name) {
        for (int i=0; i<this.contacts.size(); i++) {
            Contact c = this.contacts.get(i);
            if(name.equals(c.getName())) { // sweeps list until name is matched
                return i;
            }
        }
        System.out.println("!!! CONTACT COULDN'T BE FOUND !!!"); // if return wasn't hit once, contact isn't included
        return -1;
    }

    public void addMessage(Contact contact, String message) { // question 1
        int index = this.contacts.indexOf(contact);
        if(index == -1) {
            System.out.println("!!! CONTACT DOESN'T EXIST !!!");
            return; // contact isn't included
        }
        String chat = chats.get(index);
        message = (chat.isEmpty() ? "" : "\n") + message;
        chats.set(index , chat + message); // append message with newline
    }
    /* indexOf uses the function 'equals', so even though
    * the copies of the list are hidden from other classes
    * the indexOf function can still work as intended */


    public void clearChat(Contact contact) { // question 2
        int index = this.contacts.indexOf(contact);
        if(index == -1) {
            System.out.println("!!! CONTACT DOESN'T EXIST !!!");
            return; // contact isn't included
        }
        chats.set(index, ""); // print chat
    }


    public void printChat(String name) { // question 3
        int index = nameIndex(name);
        if(index == -1) return; // contact isn't included
        System.out.println(chats.get(index)); // print chat
    }

    public void searchChats(String item) { // question 4
        boolean found = false;
        for (int i=0; i<this.chats.size(); i++) {
            if(chats.get(i).contains(item)) { // prints name if item is included
                found = true;
                System.out.println(contacts.get(i).getName());
            }
        }
        if (!found) { // sends message if item doesn't exist
            System.out.println("!!! ITEM COULDN'T BE FOUND !!!");
        }
    }

    public void printAllChats() { // question 5
        for (int i=0; i < this.chats.size(); i++) { // prints all the names and chats
            System.out.print(this.contacts.get(i).getName());
            System.out.println("'s chat:");
            System.out.println(this.chats.get(i));
        }
    }

    public void exit() { // clears all enteries
        this.contacts.clear();
        this.chats.clear();
    }
}