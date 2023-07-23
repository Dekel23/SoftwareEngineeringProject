package Sensors;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Sensor {
	private static int counter = 0; // Count the number of sensors for id 
    protected final int id; // Id for each event
    protected boolean isActive; // Set if the sensors is active
	protected final Date installDate;
    protected int batteryLvl; // Int between 0 to 100
    
	// Constructor
    public Sensor(int id, Date installDate, boolean isActive)
    {
    	this.id = counter++;
    	this.installDate = installDate;
    	this.batteryLvl = 100;
    	this.isActive = isActive;
    }
    
	// Charge Sensor to full
    public void charge() { this.batteryLvl = 100; }
    	
	public boolean isActive() { return isActive; }
	public void setActive(boolean isActive) { this.isActive = isActive; }
		
	public int getBatteryLvl() { return batteryLvl; }
	public void setBatteryLvl(int batteryLvl) throws Exception
	{ 
		if (batteryLvl > 100 || batteryLvl < 0)
			throw new Exception("Invalid battery level");

        this.batteryLvl = batteryLvl; 
	}
	
	public Date getInstallDate() { return installDate; }
	
	public int getId() {return id;}

	// Check if object equal to this sensor
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Sensor) {
			Sensor o = (Sensor) obj;
			return o.getId() == this.getId();
		}

		return false;
	}

	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return "ID=" + this.id + " Installation Date=" + dateFormat.format(this.installDate) + " Battery=" + this.batteryLvl + " Status=" + this.isActive;
	}

	// Each son has this function
	public abstract Sensor makeCopy();
}
