package Contact_Package;
import java.util.*;
import java.util.Date;
import java.util.Calendar;
import java.util.Scanner;
//this is a parent class for Meeting and NotMeeting, who both inherit it.
import java.util.Scanner;
public class Event implements Printable {
	public static Scanner in = new Scanner (System.in);
	private Date date;
	private int len;
	public Event(Date date, int len)
	{
		this.setDate(date);
		this.setLen(len);
	}
	public Event(int year, int month, int day, int hour, int minute, int len)
	{
		Calendar c = Calendar.getInstance();
		c.set(year,month+1,day+1,hour,minute,0);
		Date date = c.getTime();
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
		while(len>60||len<1)
		{
			System.out.println("the given time length for the event is invalid, please enter a number between 1 and 60.");
			len=in.nextInt();
		}
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
