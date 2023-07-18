package Final_Project;
import java.util.Date;
public abstract class Sensor {
    private final int id;
    private boolean isActive;
	private final Date installDate;
    private int batteryLvl;
    
    public Sensor(int id, Date installDate, boolean isActive)
    {
    	this.id = id;
    	this.installDate = installDate;
    	this.batteryLvl = 100;
    	this.isActive = isActive;
    }
    
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
}
