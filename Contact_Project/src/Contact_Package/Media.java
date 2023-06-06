package Contact_Package;

public abstract class Media {
	private double length;
	private String name;
	public Media(double length, String name)
	{
		this.length = length;
		this.name = name;
	}
	public abstract String playing();
	public double getLength( ) {return this.length;}
	public String getName( ) {return this.name;}
}
