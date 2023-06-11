package Contact_Package;
import java.util.Date;
import java.util.Scanner;
public class Event {
	public static Scanner in = new Scanner (System.in);
	private String name;
	private Date date;
	private int len;
	public Event(String name, Date date, int len)
	{
		this.setDate(date);
		this.setLen(len);
		this.setName(name);
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public void setDate(Date date)
	{
		this.date = date;
	}
	public void setLen(int len)
	{
		this.len=len;
	}
	public Date getDate()
	{
		return date;
	}
	public int getLen()
	{
		return len;
	}
}
