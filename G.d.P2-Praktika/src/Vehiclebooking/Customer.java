package Vehiclebooking;

import java.util.List;

public class Customer {
	private int id;
	private String name;
	VehicleManagement vehman;

	public Customer(int id, String name) {
		this.id = id;
		this.name = name;
	}

	protected int getId() {
		return id;
	}

	protected String getName() {
		return name;
	}

	protected boolean searchAndBookVehicle(int distance, OperatingEnvironment openv) {
		List<Vehicle> matchingveh = vehman.findMatchingVehicles(distance, openv);
		for (Vehicle v : matchingveh) {
			vehman.bookVehicle(this, v);
		}
		return true;
	}

	protected void setVehicleManagement(VehicleManagement management) {
		vehman = management;
	}
}
