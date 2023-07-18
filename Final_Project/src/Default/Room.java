package Default;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import Sensors.*;

public class Room implements Information {
    private String name;
    private ArrayList<Sensor> sensors;
    private ArrayList<Event> events;

    public Room(String name, ArrayList<Sensor> sensors, ArrayList<Event> events) {
        this.name = name;
        this.sensors = new ArrayList<>(sensors);
        this.events = new ArrayList<>(events);
    }

    public Room(String name, ArrayList<Sensor> sensors) {
        this(name, sensors, new ArrayList<>());
    }

    public Room(String name) {
        this(name, new ArrayList<>(), new ArrayList<>());
    }

    // Add a new event to the events list
    public void alarm(Event event) {
        Event copyEvent = new Event(event);
        events.add(copyEvent);
    }

    // Sort events by date
    public void sortEvents() {
        Collections.sort(this.events, new Comparator<Event>() {
            @Override
            public int compare(Event event1, Event event2) {
                return event1.getTime().compareTo(event2.getTime());
            }
        });
    }

    @Override
    public void printEvents() {
        for (Event event : this.events) 
            System.out.println(event.toString());
    }

    @Override
    public void addSensor(Sensor sensor) {
        this.sensors.add(sensor.makeCopy());
    }

    @Override
    public void deleteSensor(Sensor sensor) {
        this.sensors.remove(sensor);
    }

    @Override
    public void chargeSensor(Sensor sensor) throws Exception {
        sensor.charge();
    }

    @Override
    public void printSensors() {
        for (Sensor sensor : this.sensors) 
            System.out.println(sensor.toString());
    }

    @Override
    public String toString() {
        String str = "Room Name=" + name;
        str += " Sensors=\n";

        for (Sensor sensor : this.sensors) 
            str += sensor.toString() + "\n";

        str += " Events=\n";

        for (Event event : this.events) 
            str += event.toString() + "\n";

        return str;
    }
}