package Contact_Package;

public class Video extends Media {
	public Video(double length, String name) { super(length, name);}
	public Video() {super();}
	public String playing() {
		return ("the video that's playing is " + super.getName() + " and his length is" + super.getLength());
	}
}