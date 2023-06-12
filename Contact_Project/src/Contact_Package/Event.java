package Contact_Package;
import java.util.Date;
import java.util.Scanner;
public class Event {
	public static Scanner in = new Scanner (System.in);
	private Date date;
	private int len;
	public Event(Date date, int len)
	{
		this.setDate(date);
		this.setLen(len);
	}

	public void setDate(Date date)
	{
		this.date = date;
	}
	public Date getDate()
	{
		return this.date;
	}
	public void setLen(int len)
	{
		this.len=len;
	}
	public int getLen()
	{
		return this.len;
	}
	public void print()
	{
		System.out.println(date.toLocaleString());
		System.out.println("event length: " + len + "minutes");
	}
}
