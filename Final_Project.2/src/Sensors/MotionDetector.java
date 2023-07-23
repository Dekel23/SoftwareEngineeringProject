package Sensors;

import java.util.Date;

public class MotionDetector extends Sensor {
    private static int price = 50; // Price of any Motion Detector

	public MotionDetector(int id, Date installDate, boolean isActive) {
		super(id, installDate, isActive);
	}
	
	public static void setPrice(int price) throws Exception
	{
		if (price < 0)
			throw new Exception("Invalid price");
		
		MotionDetector.price = price;
	}
    
    public static int getPrice() { return MotionDetector.price; } 

	@Override
	public MotionDetector makeCopy() {
		return new MotionDetector(this.id, (Date)this.installDate.clone(), this.isActive);
	}

	@Override
	public String toString() {
		return super.toString() + " Price=" + MotionDetector.price + "type= MotionDetector";
	}
}