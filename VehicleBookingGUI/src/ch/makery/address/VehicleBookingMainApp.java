package ch.makery.address;

import java.io.IOException;

import ch.makery.address.model.Boat;
import ch.makery.address.model.Customer;
import ch.makery.address.model.Helicopter;
import ch.makery.address.model.Jetski;
import ch.makery.address.model.Plane;
import ch.makery.address.model.SUV;
import ch.makery.address.model.VehicleBase;
import ch.makery.address.model.VehicleBindingAdapter;
import ch.makery.address.model.VehicleManagement;
import ch.makery.address.model.eCar;
import ch.makery.address.view.VehicleBookingController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VehicleBookingMainApp extends Application {

	private Stage primaryStage;
	private AnchorPane rootLayout;

	private ObservableList<VehicleBindingAdapter> vehicleData = FXCollections.observableArrayList();

	private Customer customer;
	private VehicleManagement vehman;

	/**
	 * Constructor
	 */
	public VehicleBookingMainApp() {
		VehicleBase vb1 = new Plane("Boeing", 1000000);
		VehicleBase vb2 = new eCar("Tesla", 50000);
		VehicleBase vb3 = new Boat("MobyDick", 40000);
		VehicleBase vb4 = new Helicopter("PoliceHeli", 500000);
		VehicleBase vb5 = new Jetski("Jetski", 30000);
		VehicleBase vb6 = new SUV("Forester", 100000);

		customer = new Customer(002, "Zero Two");
		vehman = new VehicleManagement();
		// Add some sample data
		vehicleData.add(new VehicleBindingAdapter(vb1));
		vehicleData.add(new VehicleBindingAdapter(vb2));
		vehicleData.add(new VehicleBindingAdapter(vb3));
		vehicleData.add(new VehicleBindingAdapter(vb4));
		vehicleData.add(new VehicleBindingAdapter(vb5));
		vehicleData.add(new VehicleBindingAdapter(vb6));

		vehman.addVehicle(vb1);
		vehman.addVehicle(vb2);
		vehman.addVehicle(vb3);
		vehman.addVehicle(vb4);
		vehman.addVehicle(vb5);
		vehman.addVehicle(vb6);
	}

	public Customer getCustomer() {
		return customer;
	}

	public VehicleManagement getVehicleManagement() {
		return vehman;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("VehicleBookingGUI");

		initRootLayout();

	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(VehicleBookingMainApp.class.getResource("view/BookingOverview.fxml"));
			rootLayout = (AnchorPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

			// Give the controller access to the main app.
			VehicleBookingController controller = loader.getController();
			controller.setMainApp(this);
			controller.setCustomer(customer);
			controller.setVehicleManagement(vehman);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the data as an observable list of Persons.
	 * 
	 * @return
	 */
	public ObservableList<VehicleBindingAdapter> getVehicleData() {
		return vehicleData;
	}

	/**
	 * Returns the main stage.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
