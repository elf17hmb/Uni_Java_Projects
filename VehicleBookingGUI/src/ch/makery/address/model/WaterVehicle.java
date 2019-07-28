package ch.makery.address.model;

public abstract class WaterVehicle extends VehicleBase {

	public WaterVehicle(String name, int distance) {
		super(name, distance, OperatingEnvironment.WATER);
	}
}
