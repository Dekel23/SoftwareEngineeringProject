package Contact_Package;
import java.util.ArrayList;
//Calendar is already a java Object, so to avoid problems in the future I called the class MyCalendar.
public class MyCalendar {
private ArrayList<Event> eventList = new ArrayList<Event>(); 
public void addEvent(Event event)
{
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
public void printContactMeetings(Contact contact)
//prints all meetings with the given contact
{
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
	}
}
}
