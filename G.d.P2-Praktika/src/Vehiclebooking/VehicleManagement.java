package Vehiclebooking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleManagement {
	List<Vehicle> vehicles = new ArrayList<>();
	Map<Integer, List<Vehicle>> bookedVehicles = new HashMap<>();

	public VehicleManagement() {
	}

	public List<Vehicle> findMatchingVehicles(int distance, OperatingEnvironment openv) {
		List<Vehicle> matchingv = new ArrayList<>();
		for (Vehicle v : vehicles) {
			if (v.canOperateOn(openv) && v.getMaxDistance() >= distance) {
				matchingv.add(v);
			}
		}
		return matchingv;
	}

	public long countAvailableVehicles() {
		long i = 0;
		for (Vehicle v : vehicles) {
			if (v.isAviable()) {
				i++;
			}
		}
		return i;
	}

	public boolean bookVehicle(Customer c, Vehicle v) {
		int cid = c.getId();
		List<Vehicle> cvehicle;
		if (bookedVehicles.containsKey(cid)) {
			cvehicle = bookedVehicles.get(cid);
			bookedVehicles.remove(cid);

		} else {
			cvehicle = new ArrayList<>();
		}
		cvehicle.add(v);
		bookedVehicles.put(cid, cvehicle);
		return true;
	}

	public void addVehicle(Vehicle v) {
		vehicles.add(v);
	}

	public void showBookedVehicles() {
		Collection<List<Vehicle>> vcollectionlist = bookedVehicles.values();
		for (List<Vehicle> vlist : vcollectionlist) {
			for (Vehicle v : vlist) {
				System.out.println(v.getName());
			}
		}
	}

	private void logBookingProcess(Vehicle v, Customer c) {
		// TODO
	}

}
