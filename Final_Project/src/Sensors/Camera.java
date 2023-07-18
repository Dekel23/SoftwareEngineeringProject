package Sensors;

import java.util.Date;

public class Camera extends Sensor {
    private static int price = 50; // Price of any camera

	public Camera(int id, Date installDate, boolean isActive) {
		super(id, installDate, isActive);
	}
	
	public static void setPrice(int price) throws Exception
	{
		if (price < 0)
			throw new Exception("Invalid price");
		
		Camera.price = price;
	}
    
    public static int getPrice() { return Camera.price; } 

	@Override
	public Camera makeCopy() {
		return new Camera(this.id, (Date)this.installDate.clone(), this.isActive);
	}

	@Override
	public String toString() {
		return super.toString() + " Price=" + Camera.price;
	}
}