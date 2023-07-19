package Default;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Sensors.Sensor;

// This class represent a management of the security issues a house.
public class House{
	
    private String name;
    private String address;
    private ArrayList<String> owners;
    private ArrayList<Room> rooms;

    // Getters
    public ArrayList<Room> getRooms() {
    	return this.rooms;
    }
    
    public ArrayList<String> getOwners() {
    	return this.owners;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public String getAddress() {
    	return this.address;
    }
    
    // Constructor
    public House(String name, String address, ArrayList<String> owners, ArrayList<Room> rooms) {
        this.name = name;
        this.address = address;
        this.owners = new ArrayList<>(owners);
        this.rooms = new ArrayList<>(rooms);
    }
    
    // Copy constructor
    public House(House other){
        this.name = other.getName();
        this.address = other.getAddress();
        this.owners = new ArrayList<>(other.getOwners());
        this.rooms = new ArrayList<>(other.getRooms());
    }
    
    // Checks if a person is an owner of the house
    public boolean isOwner(String isOwner) {
    	return owners.contains(isOwner);    	
    }
    
    // Print the house's events
    public void printEvents() {
    	for (Room room : this.rooms) {
            for (Event event : room.getEvents()) { 
                System.out.println(event.toString());
            }
    	}
    }
    
    // Print the house's owners
    public void printOwners() {
        for (String owner : this.owners) 
            System.out.println(owner);        
    }
    
    // Turn all the sensors in the house to be active
    public void allActive() {
    	for (Room room : this.rooms) {
            for (Sensor sensor : room.getSensors()) { 
                sensor.setActive(true);
            }
    	}
    }
    
    // Turn all the sensors in the house to be inactive
    public void allInActive() {
    	for (Room room : this.rooms) {
            for (Sensor sensor : room.getSensors()) { 
                sensor.setActive(false);
            }
    	}
    }
    
    // Add a Event to a specific room in the house 
    public void newEvent (Event newEvent, Room room) {
    	Event copiedEvent = new Event(newEvent);
    	room.alarm(copiedEvent);
    }
    
    // Add a sensor to a specific room in the house
    public void newSensor (Sensor newSensor, Room room) {
    	room.addSensor(newSensor.makeCopy());
    }
    
    // Delete a sensor from a specific room in the house
    public void deleteSensor(String nameOfRoom, Sensor Sensor) throws Exception{
    	int foundRoom = 0;
    	for (Room room : this.rooms) {
            if (room.getName().equals(nameOfRoom)) {
            	room.deleteSensor(Sensor);
            	foundRoom = 1;
            	return;
            }
    	}
    	
    	if (foundRoom == 0) {
    		throw new Exception("The entered room was not found");
    	}
    }
    
    // Add an owner of the house from its owners arrayList
    public void addHouseOwner(String owner) {
    	this.owners.add(owner);
    }
    
    // Delete an owner of the house from its owners arrayList
    public void deleteHouseOwner(String owner) {
        this.owners.remove(owner);
    }
    
    // Add a room to the house
    public void addRoom(Room room) {
    	Room copiedRoom = new Room(room);
    	this.rooms.add(copiedRoom);
    }
    
    // Delete a room from the house:
    public void deleteRoom(String nameOfRoom) throws Exception{
    	int foundRoom = 0;
    	for (Room room : this.rooms) {
            if (room.getName().equals(nameOfRoom)) {
            	this.rooms.remove(room);
            	foundRoom = 1;
            	return;
            }
    	}
    	
    	if (foundRoom == 0) {
    		throw new Exception("The entered room was not found");
    	}
    	
    }
    
    // Charge a specific sensor in a specific house
    public void chargeSensor(String nameOfRoom, Sensor Sensor) throws Exception{
    	int foundRoom = 0;
    	for (Room room : this.rooms) {
            if (room.getName().equals(nameOfRoom)) {
            	room.chargeSensor(Sensor);
            	foundRoom = 1;
            	return;
            }
    	}
    	
    	if (foundRoom == 0) {
    		throw new Exception("The entered room was not found");
    	}
    }

    // Print all the sensors in the house
    public void printSensors() {
    	for (Room room : this.rooms) {
            for (Sensor sensor : room.getSensors()) { 
                System.out.println(sensor.toString());
            }
    	}
    }
    
    // Sort the rooms arrayList by the number of evens in each room from smallest to biggest
    public void sortByEventsNum() {
        Collections.sort(rooms, new Comparator<Room>() {
            @Override
            public int compare(Room room1, Room room2) {
                return Integer.compare(room1.getEvents().size(), room2.getEvents().size());
            }
        });
    }
    
}
