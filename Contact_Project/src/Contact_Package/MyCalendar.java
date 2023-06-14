package Contact_Package;
import java.util.*;
import java.util.ArrayList;
//Calendar is already a java Object, so to avoid problems in the future I called the class MyCalendar.
public class MyCalendar implements Printable {
	public static Scanner in = new Scanner(System.in);
	private ArrayList<Event> eventList = new ArrayList<Event>(); 

	public void addEvent(Event event)
	{
		eventList.add(event);
	}
	public void addEvent() {
		int year, month, day, hour, minute,len;
		System.out.println("input year of the meeting that you want to create:");
		year = in.nextInt();
		System.out.println("input month of the meeting that you want to create:");
		month = in.nextInt();
		System.out.println("input day of the meeting that you want to create:");
		day = in.nextInt();
		System.out.println("input hour of the meeting that you want to create:");
		hour = in.nextInt();
		System.out.println("input minute of the meeting that you want to create:");
		minute = in.nextInt();
		System.out.println("enter the time of the event you want to create");
		System.out.println("add the length of the event:");
		len = in.nextInt();
		Calendar c = Calendar.getInstance();
		c.set(year,month+1,day+1,hour,minute,0);
		Date date = c.getTime();
		System.out.println("add discription:");
		String s = in.next();
		NotMeeting event = new NotMeeting(date, len, s);
		eventList.add(event);
	}
	public void addEvent(Contact contact) {
		int year, month, day, hour, minute,len;
		System.out.println("input year of the meeting that you want to create:");
		year = in.nextInt();
		System.out.println("input month of the meeting that you want to create:");
		month = in.nextInt();
		System.out.println("input day of the meeting that you want to create:");
		day = in.nextInt();
		System.out.println("input hour of the meeting that you want to create:");
		hour = in.nextInt();
		System.out.println("input minute of the meeting that you want to create:");
		minute = in.nextInt();
		System.out.println("enter the time of the event you want to create");
		System.out.println("add the length of the event:");
		len = in.nextInt();
		Calendar c = Calendar.getInstance();
		c.set(year,month+1,day+1,hour,minute,0);
		Date date = c.getTime();
		Meeting event = new Meeting(date, len, contact);
		eventList.add(event);
	}
	public void orderByDate()
	{
		eventList.sort((o1, o2) -> o1.getDate().compareTo(o2.getDate()));
	}
	public void deleteContact(Contact contact)
	//deletes all meetings with the given contact
	{
		for (Event event : eventList)
	{
		if(event instanceof Meeting)
		{
			Meeting temp = (Meeting) event;
			if(temp.getContact() == contact)
				eventList.remove(event);
		}
	}
	}
	public void deleteEvent()
	{
		int year, month, day, hour, minute;
		System.out.println("input year of the meeting that you want to delete:");
		year = in.nextInt();
		System.out.println("input month of the meeting that you want to delete:");
		month = in.nextInt();
		System.out.println("input day of the meeting that you want to delete:");
		day = in.nextInt();
		System.out.println("input hour of the meeting that you want to delete:");
		hour = in.nextInt();
		System.out.println("input minute of the meeting that you want to delete:");
		minute = in.nextInt();
		System.out.println("enter the time of the event you want to delete");
		Calendar c = Calendar.getInstance();
		c.set(year,month+1,day+1,hour,minute,0);
		Date date = c.getTime();
		for (int i = 0; i < eventList.size(); i++) {
			if (eventList.get(i).getDate().compareTo(date)==0) 
			{
				eventList.remove(i--);
		}
	}
}


	public void printContactMeetings(Contact contact)
	//prints all meetings with the given contact
	{
		this.orderByDate();
		for (Event event : eventList)
		{
			if(event instanceof Meeting)
			{
				Meeting temp = (Meeting) event;
				if(temp.getContact() == contact)
					temp.print();
		}
	}
	}
	public void printFromDay()
	//prints all meetings with the given day
	{
		int year, month, day;
		this.orderByDate();
		System.out.println("input year");
		year = in.nextInt();
		System.out.println("input month");
		month = in.nextInt();
		System.out.println("input day");
		day = in.nextInt();
		Calendar c = Calendar.getInstance();
		c.set(year,month+1,day+1,0,0,0);
		Date date = c.getTime();
		for (Event event : eventList)
		{

				if(event.getDate().getDay()==date.getDay())
					event.print();
		}
	}
	public void deleteOverlaps()
	{
		this.orderByDate();
		for (int i = 0; i < eventList.size()-1; i++) {
			long dif = eventList.get(i).getDate().getTime()-eventList.get(i+1).getDate().getTime();
			if (dif>eventList.get(i).getLen()*1000*60) //getLen is in minutes, I convert it to milliseconds and compare it to dif
			{
				eventList.remove(i+1);// if the events overlap, the latest gets deleted.
			}
		}
	}

	public void print()
	{
		System.out.println("scheduled events:");
		for (Event event : eventList)
	{
		event.print();
	}	}
}
