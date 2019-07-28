package ch.makery.address.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VehicleManagement {
	private Map<Integer, Vehicle> bookedVehicles;
	private List<Vehicle> vehicles;

	public VehicleManagement() {
		bookedVehicles = new HashMap<>();
		vehicles = new ArrayList<>();
	}

	public void addVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
	}

	// public List<Vehicle> findMatchingVehicles(int minRequiredDistance,
	// OperatingEnvironment environment) {
	// List<Vehicle> matched = new ArrayList<Vehicle>();
	// for (Vehicle v : vehicles) {
	// if (v.canOperateOn(environment) && v.getMaxDistance() >= minRequiredDistance
	// && v.isAvailable()) {
	// matched.add(v);
	// }
	// }
	// return matched;
	// }

	public List<Vehicle> findMatchingVehicles(int minRequiredDistance, OperatingEnvironment environment) {
		return vehicles.stream().filter(
				v -> v.canOperateOn(environment) && v.getMaxDistance() >= minRequiredDistance && v.isAvailable())
				.collect(Collectors.toList());
	}

	public boolean bookVehicle(Vehicle vehicle, Customer customer) throws IOException {
		if (bookedVehicles.containsValue(vehicle) || !vehicles.contains(vehicle)) {
			return false;
		} else {
			vehicle.book();
			bookedVehicles.put(customer.getId(), vehicle); // Fehler immer unter gleichen Key
			logBookingVehicle(vehicle, customer);
			showBookedVehicles();
			return true;
		}
	}

	public void showBookedVehicles() {
		System.out.println("Diese Vehicles sind gebucht: ");
		// for (Vehicle v : bookedVehicles.values()) {
		// System.out.println("- " + v.getName());
		// }
		bookedVehicles.values().forEach(v -> System.out.println(v.getName()));
		// bookedVehicles.values().forEach(System.out::println); toString Override
		System.out.println(bookedVehicles.values().size());
	}

	public void logBookingVehicle(Vehicle vehicle, Customer customer) throws IOException {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String s = timestamp + " Das Vehikel " + vehicle.getName() + ", wurde für den " + customer.getName()
				+ ", gebucht";
		try (PrintWriter writer = new PrintWriter(new FileWriter("resource/VehicleBooking.txt", true))) {
			writer.println(s);
		} catch (IOException x) {
			throw x;
		}
		System.out.println("logbook");
	}

	public int countAvailableVehicles() {
		return (int) vehicles.stream().filter(v -> v.isAvailable()).count();
	}
}
