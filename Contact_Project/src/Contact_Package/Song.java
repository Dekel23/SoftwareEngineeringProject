package Contact_Package;

public class Song extends Media {
	public Song(double length, String name) { super(length, name);}
	public Song() {super();}
	public String playing() {
		return ("the song that's playing is " + super.getName() + " and his length is" + super.getLength());
	}
}
