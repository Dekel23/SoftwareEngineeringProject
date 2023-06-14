package Contact_Package;

import java.util.Scanner;

// define an abstract of any kind of media 
public abstract class Media {
	public static Scanner in = new Scanner(System.in);
	private double length;// media as a length which is double
	private String name;// media has its name
	
	// create new media 
	public Media()
	{
		System.out.println("Enter media name");
		String name = in.next();// get media name
		System.out.println("Enter media length");// get media length and make sure its right
		double length;
		do 
		{
			while (!in.hasNextDouble())
			{
				in.nextLine();
				System.out.println("enter a positive double");
			}
			length = in.nextDouble();
			if (length <= 0)
				System.out.println("enter a positive double");
		} while (length <= 0);
		this.length = length;
		this.name = name;
	}
	public Media(double length, String name)
	{
		while (!this.setLength(length))
		{
			System.out.println("enter a positive double");
			while (!in.hasNextDouble())
			{
				in.nextLine();
				System.out.println("enter a positive double");
			}
			length = in.nextDouble();
		}
		this.name = name;
	}
	public abstract String playing();// any media has its playing function 
	public double getLength( ) {return this.length;}
	public String getName( ) {return this.name;}
	public boolean setLength(double length)// set length if its positive and return if its done
	{
		if (length <= 0)
			return false;
		this.length = length;
		return true;
	}
	public void setName(String name)
	{
		this.name = name;
	}
}
