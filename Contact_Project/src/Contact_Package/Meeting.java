package Contact_Package;
import java.util.Date;
public class Meeting extends Event {
	private Contact contact;
	public Meeting(String name, Date date, int len, Contact contact)
	{
		super(name, date, len);
		this.setContact(contact);
	}
	public void setContact(Contact contact)
	{
		this.contact = contact;
	}
}
