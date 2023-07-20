package Default;

import java.util.ArrayList;
import Sensors.Sensor;

public class SecurityCompany {

	//Fields:
	private ArrayList<House> houses;

	//Constructor:
	public SecurityCompany(ArrayList<House> houses) {
		this.houses = houses;
	}
	
	
	//Printing events:
	public void printEvents() {
    	for (House house : this.houses) {
			for (Room room : house.getRooms()) {
	            for (Event event : room.getEvents()) { 
	                System.out.println(event.toString());
	            }
			}
    	}
    }
	
	
	//Printing sensors:
		public void printSensors() {
	    	for (House house : this.houses) {
				for (Room room : house.getRooms()) {
		            for (Sensor sensor : room.getSensors()) { 
		                System.out.println(sensor.toString());
		            }
				}
	    	}
	    }
	
	
	//Printing House owners:
		public void printOwners() {
	        for (House house : this.houses)
				for (String owner : house.getOwners()) 
		            System.out.println(owner);        
	    }
		
	//Add an Event to specific room and house
	    public void newEvent (Event newEvent, Room room, House house) {
	    	Event copiedEvent = new Event(newEvent);
	    	house.newEvent(copiedEvent, room);
	    }
	    
	
	//Add house:
	    public void addHouse(House house)	{
	        House copiedHouse = new House(house);
	        this.houses.add(copiedHouse);
	        }
	    
	    
	 //Delete house:
	    public void deleteHouse(House houseToDelete) {
	        this.houses.remove(houseToDelete);
	    }

	  
	//Add room to a specific house:
	    public void addRoom(Room room, House house) {
	    	Room copiedRoom = new Room(room);
	    	house.addRoom(copiedRoom);
	    }
	    
	    
	//Delete a room house:
	    public void deleteRoom(String nameOfRoom, House house) throws Exception{
	    	int foundRoom = 0;
	    	for (Room room : house.getRooms()) {
	            if (room.getName().equals(nameOfRoom)) {
	            	house.getRooms().remove(room);
	            	foundRoom = 1;
	            	return;
	            }
	    	}
	    	
	    	if (foundRoom == 0) {
	    		throw new Exception("The entered room was not found");
	    	}
	    	
	    }
	    
	//Add sesnor to specific house and room
	    public void newSensor(Sensor newSensor, Room room, House house) {
	    	Sensor copiedSensor = new Sensor(newSensor.getId(), newSensor.getInstallDate(), newSensor.isActive());
	    	house.newSensor(copiedSensor, room);
	    }
	    
	    
	//Delete sensor:
	    public void deleteSensor(String nameOfRoom, Sensor Sensor, House house) throws Exception{
	    	int foundRoom = 0;
	    	for (Room room : house.getRooms()) {
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
	  
	    
	 // Add an owner to specific house
	    public void addHouseOwner(String owner, House house) {
	    	house.addHouseOwner(owner);
	    }
	    
	 // Delete an owner from specific house
	    public void deleteHouseOwner(String owner, House house) {
	        house.deleteHouseOwner(owner);
	    }
	    
	 // Charge a specific sensor in a specific house
	    public void chargeSensor(String nameOfRoom, Sensor Sensor, House house) throws Exception{
	    	int foundRoom = 0;
	    	for (Room room : house.getRooms()) {
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
	    
	 //Activate House:
	    public void activeHouse(House house)
	    {
	    	house.allActive();
	    }
	    
	 //Disactive House:
	    public void disActiveHouse(House house)
	    {
	    	house.allInActive();
	    }
	    
	 //Active all houses in security company
	    public void activeAllHouses()
	    {
	    	for (House house : this.houses)
	    		house.allActive();
	    }
	    
	  //Disactive all houses in security company
	    public void DisactiveAllHouses()
	    {
	    	for (House house : this.houses)
	    		house.allInActive();
	    }
	    
	  //Duplicate House
	    public void duplicate(House house)
	    {
	    	House copyHouse = new House(house);
	    	this.addHouse(copyHouse);
	    }
	 
	    
	//printing sensors low
	    public void printLowBattery()
	    {
	    	//for (Room room : house.getRooms())
	    	for (House house : this.houses)
	    	{
	    		for (Room room : house.getRooms())
	    		{
	    			for (Sensor sensor : room.getSensors())
	    			{
	    				if(sensor.getBatteryLvl()<=20)
	    					System.out.println(sensor.toString());
	    			}
	    		}
	    	}
	    }    
	    	   
}
