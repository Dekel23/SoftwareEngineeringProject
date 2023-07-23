package Default;
import java.util.Date;
import java.util.HashSet;

import javax.swing.JList;
import javax.swing.JOptionPane;

import Sensors.Camera;
import Sensors.MotionDetector;
import Sensors.Sensor;
import Sensors.SmokeDetector;

public class Main {
    // get selected index from list of options
    public static int choose(String title, String[] arr) {
        JList<String> list = new JList<>(arr);
        JOptionPane.showMessageDialog(null, list, title, JOptionPane.PLAIN_MESSAGE);
        return list.getSelectedIndex();
    }

    // select multiple causes
    public static HashSet<typeOfCause> chooseCauses() throws Exception {
        String[] s = new String[] {"Fire", "Walk in", "On couch"};
        JList<String> list = new JList<>(s);
        JOptionPane.showMessageDialog(null, list, "Choose causes (multiple choice)", JOptionPane.PLAIN_MESSAGE);
        if(list.getSelectedIndices().length == 0) throw new Exception("nothing selected!");
        HashSet<typeOfCause> res = new HashSet<typeOfCause>();
        for(int i: list.getSelectedIndices()) {
            if(i==0) res.add(typeOfCause.FIRE);
            if(i==1) res.add(typeOfCause.WALK_IN);
            if(i==2) res.add(typeOfCause.ON_COUCH);
        }
        return res;

    }

    public static House chooseHouse(SecurityCompany cmp) {
        int o = choose("CHOOSE HOUSE", cmp.getHouseNames().toArray(new String[0]));
        return cmp.getHouse(o);
    }

    public static Room chooseRoom(House h) {
        int o = choose("CHOOSE ROOM", h.getRoomNames().toArray(new String[0]));
        return h.getRooms().get(o);
    }

    public static String chooseOwner(House h) {
        int o = choose("CHOOSE ROOM", h.getOwners().toArray(new String[0]));
        return h.getOwners().get(o);
    }

    public static Sensor chooseSensor(Room r) {
        int o = choose("CHOOSE SENSOR", r.getSensorNames().toArray(new String[0]));
        return r.getSensors().get(o);
    }

    public static String input(String title) {
        return JOptionPane.showInputDialog(null, title, "", JOptionPane.PLAIN_MESSAGE);
    }

    public static void main(String[] sa) {
        int o;
        boolean quit = false;
        House h;
        Room r;
        String in0, in1;
        Sensor s;
        HashSet<typeOfCause> cs;
        SecurityCompany cmp = new SecurityCompany();
        while(!quit) {
            try {
                o = choose("SELECT ACTION TYPE", new String[] {"print action", "add object", "delete object", "other actions", "quit"});
                switch (o) {
                case 0: // print objects
                    o = choose("SELECT WHAT TO PRINT", new String[] {"events", "sensors", "owners", "low battery sensors", "houses"});
                    switch (o) {
                        case 0:
                        cmp.printEvents();
                        break;
                        case 1:
                        cmp.printSensors();
                        break;
                        case 2:
                        cmp.printOwners();
                        break;
                        case 3:
                        cmp.printLowBattery();
                        break;
                        case 4:
                        cmp.printHouses();
                        case -1:
                    }
                break;
                case 1: // add onject
                    o = choose("SELECT WHAT TO ADD", new String[] {"event", "house", "room", "sensor", "house owner"});
                    switch (o) {
                        case 0:
                        h = chooseHouse(cmp);
                        r = chooseRoom(h);
                        o = choose("EMERGENCY LEVEL", new String[] {"1", "2", "3", "4", "5"});
                        cs = chooseCauses();
                        cmp.newEvent(new Event(o+1, cs, new Date()), r, h);
                        break;
                        case 1:
                        in0 = input("house name");
                        in1 = input("house address");
                        cmp.addHouse(new House(in0, in1));
                        break;
                        case 2:
                        h = chooseHouse(cmp);
                        in0 = input("room name");
                        cmp.addRoom(new Room(in0), h);
                        break;
                        case 3:
                        h = chooseHouse(cmp);
                        r = chooseRoom(h);
                        o = choose("SENSOR TYPE", new String[] {"camera", "motion detector", "smoke detector"});
                        if(o==0) cmp.newSensor(new Camera(0, new Date(), true), r, h);
                        else if(o==1) cmp.newSensor(new MotionDetector(0, new Date(), true), r, h);
                        else if(o==2) cmp.newSensor(new SmokeDetector(0, new Date(), true), r, h);
                        break;
                        case 4:
                        h = chooseHouse(cmp);
                        in0 = input("owner name");
                        cmp.addHouseOwner(in0, h);
                        break;
                        case -1:
                    }
                break;
                case 2: // remove object
                    o = choose("SELECT WHAT TO REMOVE", new String[] {"house", "room", "sensor", "house owner"});
                    switch (o) {
                        case 0:
                        h = chooseHouse(cmp);
                        cmp.deleteHouse(h);
                        break;
                        case 1:
                        h = chooseHouse(cmp);
                        r = chooseRoom(h);
                        cmp.deleteRoom(r.getName(), h);
                        break;
                        case 2:
                        h = chooseHouse(cmp);
                        r = chooseRoom(h);
                        s = chooseSensor(r);
                        cmp.deleteSensor(r.getName(), s, h);
                        break;
                        case 3:
                        h = chooseHouse(cmp);
                        in0 = chooseOwner(h);
                        cmp.deleteHouseOwner(in0, h);
                        break;
                        case -1:
                    }
                break;
                case 3: // other options
                    o = choose("SELECT ACTION", new String[] {"charge sensor", "activate house", "deactivate house"});
                    switch (o) {
                        case 0:
                        h = chooseHouse(cmp);
                        r = chooseRoom(h);
                        s = chooseSensor(r);
                        cmp.chargeSensor(r.getName(), s, h);
                        break;
                        case 1:
                        h = chooseHouse(cmp);
                        cmp.activeHouse(h);
                        break;
                        case 2:
                        h = chooseHouse(cmp);
                        cmp.disActiveHouse(h); // dis???
                        break;
                        case -1:
                    }
                break;
                case 4:
                quit=true;
                break;
                case -1:
                }
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Unable to do this action, press ok to go back", "ERROR!", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
