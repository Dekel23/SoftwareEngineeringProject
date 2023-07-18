package Final_Project;

import java.util.Date;

public class SmokeDetector extends Sensor {
    private static int price = 50; 

	public SmokeDetector(int id, Date installDate, boolean isActive) {
		super(id, installDate, isActive);
	}
	
	public static void setPrice(int price) throws Exception
	{
		if (price < 0)
			throw new Exception("Invalid price");
		
		SmokeDetector.price = price;
	}
    
    public static int getPrice() { return SmokeDetector.price; } 
}