package Contact_Package;
import java.util.Date;

public class Meeting extends Event {
	private Contact contact;
	public Meeting(Date date, int len, Contact contact)
	{
		super(date, len);
		this.setContact(contact);
	}
	public void setContact(Contact contact)
	{
		this.contact = contact;
	}
	public Contact getContact()
	{
		return this.contact;
	}
	public void print()
	{
		super.print();
		System.out.println("meeting with: " + contact.getName());
	}
}
