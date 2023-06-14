package Contact_Package;
import java.util.Date;

public class NotMeeting extends Event{
	private String description;
	public NotMeeting(Date date, int len, String description)
	{
		super (date, len);
		this.setDescription(description);
	}
	public void setDescription(String d)
	{
		this.description =d;
	}
	public String getDescription()
	{
		return description;
	}
	public void print()
	{
		super.print();
		System.out.println("description: " + description);
	}
}