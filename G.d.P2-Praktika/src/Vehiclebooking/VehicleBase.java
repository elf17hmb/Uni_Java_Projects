package Vehiclebooking;

public abstract class VehicleBase implements Vehicle {
	private int distance;
	private boolean available = true;
	private String name;
	private OperatingEnvironment environment;

	public VehicleBase(String name, int distance, OperatingEnvironment environment) {
		this.name = name;
		this.distance = distance;
		this.environment = environment;
	}

	public OperatingEnvironment getOperatingEnvironment() {
		return environment;
	}

	public void setOperatingEnvironment(OperatingEnvironment environment) {
		this.environment = environment;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public boolean isAvailable() {
		return available;
	}

	@Override
	public int getMaxDistance() {
		return 0;
	}

	@Override
	public void book() {

	}

}
