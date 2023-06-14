package Contact_Package;

import java.util.Comparator;

public class NumberComp implements Comparator<Contact>{

	@Override
	public int compare(Contact o1, Contact o2) {
		
		return o2.getPhoneNumber().compareTo(o1.getPhoneNumber());
	}

}
