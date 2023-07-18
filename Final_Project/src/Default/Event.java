package Default;

import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

// Enum that has the type of events that can happen (some can happen had the same time)
enum typeOfCause {
    FIRE("Fire"),
    WALK_IN("Walk in"),
    ON_COUCH("On couch");

    private final String textRepresentation; // Representation of enum

    // Constructor of enum
    private typeOfCause(String textRepresentation) {
        this.textRepresentation = textRepresentation;
    }

    @Override
    public String toString() {
        return textRepresentation;
    }
}

public class Event {
    private static int counter = 0; // Count the number of events for id 
    private final int id; // Id for each event
    private final Date time; // Time of the event in the format "yyyy-MM-dd HH:mm"
    private int emergencyLvl; // Number between 1 to 5 include
    private ArrayList<typeOfCause> cause; // List of what happend

    // Input is a string that has yyyy/mm/dd HH:mm emergencyLvl causelist (seperated by ',')
    // For downloading data form file
    public Event(String information) throws Exception 
    {
        String datePart; //Date part and the rest
        String restString1;

        String emergencyLevel; //Emerganct level and the cause list
        String causeList;

        // Split the String at the second space so it will have the date splitted
        try{
            int firstSpaceIndex = information.indexOf(" ");
            int secondSpaceIndex = information.indexOf(" ", firstSpaceIndex + 1);
        
            datePart = information.substring(0, secondSpaceIndex);
            restString1 = information.substring(secondSpaceIndex + 1);
        } catch (Exception e){
            throw new Exception("String had less than 2 spaces");
        }

        // Check valid date
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date dateTime;
        try {
            // Parse the string and convert it to a Date object
            dateTime = dateTimeFormat.parse(datePart);
        } catch (Exception e){
            throw new Exception("Invalid date");
        }

        // Split the rest of the string to emergancy level and cause list
        try{
            int firstSpaceIndex = restString1.indexOf(" ");
        
            emergencyLevel = restString1.substring(0, firstSpaceIndex);
            causeList = restString1.substring(firstSpaceIndex + 1);
        } catch (Exception e){
            throw new Exception("String had less than 3 spaces");
        }

        // Check valid emergancy level input and convert to Integer
        Integer emergLvl;
        try{
            emergLvl = Integer.parseInt(emergencyLevel);
        } catch (Exception e){
            throw new Exception("Invalid emergancy input");
        }

        // Check valid typeOfCause List, and convert the cause to that
        ArrayList<typeOfCause> causes = new ArrayList<typeOfCause>();
        String[] splittedStrings = causeList.split(",");
        for (String str : splittedStrings) {
            try {
                typeOfCause enumValue = typeOfCause.valueOf(str);
                causes.add(enumValue);
            } catch (Exception e) {
                throw new Exception ("Invalid causeList values");
            }
        }
        
        // Make new Event object
        this.cause = new ArrayList<>(causes);
        this.setEmergencyLvl(emergLvl.intValue());;
        this.time = dateTime;
        this.id = counter++;
    }

    // Copy constructor
    public Event(Event other)
    {
        this.id = other.getId();
        this.time = (Date)other.getTime().clone();
        this.emergencyLvl = other.getEmergencyLvl();
        this.cause = new ArrayList<>(other.getCause());
    }

    // constructor
    public Event(int Lvl, ArrayList<typeOfCause> causes, Date time) throws Exception
    {
        this.setEmergencyLvl(Lvl);
        this.time = (Date)time.clone();
        this.cause = new ArrayList<>(causes);
        this.id = counter++;

    }

    public int getId() { return id;}

    public Date getTime() {return time;}

    public ArrayList<typeOfCause> getCause() {return cause;}
    public void setCause(ArrayList<typeOfCause> causes) {this.cause = new ArrayList<>(causes);}

    public int getEmergencyLvl() {return emergencyLvl;}
    public void setEmergencyLvl(int Lvl) throws Exception {
        if (Lvl < 1 || Lvl > 5)
            throw new Exception ("Invalid emergancy values");

        this.emergencyLvl = Lvl;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String str = "ID=" + this.id + " Time" + dateFormat.format(this.time) + " Emergency Level=" + this.emergencyLvl + "\n";
        str += "Causes=\n";

        for (typeOfCause cause : this.cause)
            str += cause.toString() + "\n";

        return str;
    }
}
