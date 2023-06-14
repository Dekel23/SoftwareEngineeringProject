package Contact_Package;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

public class MediaApp {
	public static Scanner in = new Scanner(System.in);
	private static Vector <Media> medias = new Vector<Media>();
	
	// adding a media to the media list
	public static void addMedia()
	{
		Media newMedia;// create a new media
		System.out.println("Enter media type: true-song, false-video");// getting the media type
		while (!in.hasNextBoolean())
		{
			in.next();
			System.out.println("Enter media type: true-song, false-video");
		}
		if (in.nextBoolean())// making the new media type
			newMedia = new Song();
		else
			newMedia = new Video();
		medias.add(newMedia);// adding the media to the list
	}
	
	//play first media that has the same name
	public static void playByName(String name)
	{
		for(Media m : medias)
		{
			if (m.getName().equals(name))
			{
				System.out.println(m.playing());
				return;
			}
		}
		System.out.println("no media with this name");
	}
	
	// play all the medias
	public static void playAll()
	{
		Iterator<Media> it = medias.iterator();
		while(it.hasNext()) 
			  System.out.println(it.next().playing());
	}
	public static void menu()
	{
		int option;
		while (true) {
            System.out.println("Choose an option:");
            System.out.println("1- Add a new media");
            System.out.println("2- Play media by name");
            System.out.println("3- Play all medias");
            System.out.println("4- exit app");
            while (!in.hasNextInt())
    		{
    			in.nextLine();
    			System.out.println("enter integer");
    		}
            option = in.nextInt();
            switch(option)
            {
            case 1:
            	addMedia();
            	break;
            case 2:
            	System.out.println("enter name");
            	String name = in.next();
            	playByName(name);
            	break;
            case 3:
            	playAll();
            	break;
            case 4:
                return;
            default:
                System.out.println("Invalid option, please try again");
                break;
            }
    	}
	}
}
