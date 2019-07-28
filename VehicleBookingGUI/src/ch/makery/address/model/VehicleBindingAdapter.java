package ch.makery.address.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class VehicleBindingAdapter {
	private IntegerProperty distance;
	private BooleanProperty available;
	private StringProperty name;
	private ObjectProperty<OperatingEnvironment> environment;
	VehicleBase vbase;

	public VehicleBindingAdapter(VehicleBase vbase) {
		this.vbase = vbase;
		this.distance = new SimpleIntegerProperty(vbase.getMaxDistance());
		this.available = new SimpleBooleanProperty(vbase.isAvailable());
		this.name = new SimpleStringProperty(vbase.getName());
		this.environment = new SimpleObjectProperty<OperatingEnvironment>(vbase.getOperatingEnvironment());
		vbase.setBindingAdapter(this);
	}

	public void notifyVehicleBase() {
		vbase.setAvailable(available.getValue());
		vbase.setMaxDistance(distance.get());
		vbase.setName(name.getValue());
		vbase.setOperatingEnvironment(environment.getValue()); // TODO:könnte falsch
		// sein
	}

	public VehicleBase getBase() {
		return vbase;
	}

	public void setBase(VehicleBase vbase) {
		this.vbase = vbase;
	}

	public IntegerProperty getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		if (this.distance.get() != distance) {
			this.distance.set(distance);
			notifyVehicleBase();
		}
	}

	public BooleanProperty getAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		if (this.available.get() != available) {
			this.available.set(available);
			notifyVehicleBase();
		}
	}

	public StringProperty getName() {
		return name;
	}

	public void setName(String name) {
		if (this.name.get() != name) {
			this.name.set(name);
			notifyVehicleBase();
		}
	}

	public ObjectProperty<OperatingEnvironment> getEnvironment() {
		return environment;
	}

	public void setEnvironment(OperatingEnvironment environment) {
		if (this.environment.get() != environment) {
			this.environment.set(environment);
			notifyVehicleBase();
		}
	}
}
