package Vehiclebooking;

public interface Vehicle {
	boolean isAviable();

	int getMaxDistance();

	void book();

	boolean canOperateOn(OperatingEnvironment openv);

	OperatingEnvironment getOperatingEnviroment();

	String getName();
}
