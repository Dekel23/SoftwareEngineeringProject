package Sensors;

import java.util.Date;

public class SmokeDetector extends Sensor {
    private static int price = 50; // Price of any smoke detector

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

	@Override
	public SmokeDetector makeCopy() {
		return new SmokeDetector(this.id, (Date)this.installDate.clone(), this.isActive);
	}

	@Override
	public String toString() {
		return super.toString() + " Price=" + SmokeDetector.price;
	}
}