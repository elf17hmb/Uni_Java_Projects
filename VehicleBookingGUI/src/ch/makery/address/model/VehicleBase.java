package ch.makery.address.model;

public abstract class VehicleBase implements Vehicle {
	private int distance;
	private boolean available = true;
	private String name;
	private OperatingEnvironment environment;

	private VehicleBindingAdapter vbadapter;

	public VehicleBase(String name, int distance, OperatingEnvironment environment) {
		super();
		this.name = name;
		this.distance = distance;
		this.environment = environment;
	}

	public void setBindingAdapter(VehicleBindingAdapter vbadapter) {
		this.vbadapter = vbadapter;
	}

	public VehicleBindingAdapter getBindingAdapter() {
		return vbadapter;
	}

	public void notifyBindingAdapter() {
		vbadapter.setAvailable(available);
		vbadapter.setName(name);
		vbadapter.setDistance(distance);
		vbadapter.setEnvironment(environment);
	}

	@Override
	public OperatingEnvironment getOperatingEnvironment() {
		return environment;
	}

	public void setOperatingEnvironment(OperatingEnvironment environment) {
		if (!this.environment.equals(environment)) {
			this.environment = environment;
			notifyBindingAdapter();
		}
	}

	@Override
	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		if (this.available != available) {
			this.available = available;
			notifyBindingAdapter();
		}

	}

	@Override
	public int getMaxDistance() {
		return distance;
	}

	public void setMaxDistance(int distance) {
		if (this.distance != distance) {
			this.distance = distance;
			notifyBindingAdapter();
		}

	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (!this.name.equals(name)) {
			this.name = name;
			notifyBindingAdapter();
		}

	}

	@Override
	public boolean canOperateOn(OperatingEnvironment environment) {
		if (environment == this.environment) {
			return true;
		}
		return false;
	}

	@Override
	public void book() {
		setAvailable(false);
	}
}
