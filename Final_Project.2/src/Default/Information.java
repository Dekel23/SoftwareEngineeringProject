package Default;

import Sensors.Sensor;

public interface Information {
	public void printEvents();
	public void printSensors();
	public void addSensor(Sensor sensor);
	public void deleteSensor(Sensor sensor);
	public void chargeSensor(Sensor sensor) throws Exception;
}
