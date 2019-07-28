package ch.makery.address.view;

import java.io.IOException;
import java.util.List;

import ch.makery.address.VehicleBookingMainApp;
import ch.makery.address.model.Customer;
import ch.makery.address.model.OperatingEnvironment;
import ch.makery.address.model.Vehicle;
import ch.makery.address.model.VehicleBase;
import ch.makery.address.model.VehicleBindingAdapter;
import ch.makery.address.model.VehicleManagement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class VehicleBookingController {
	@FXML
	private TableView<VehicleBindingAdapter> vehicleTable;
	@FXML
	private TableColumn<VehicleBindingAdapter, String> nameColumn;
	@FXML
	private TableColumn<VehicleBindingAdapter, OperatingEnvironment> environmentColumn;
	@FXML
	private TableColumn<VehicleBindingAdapter, Number> distanceColumn;
	@FXML
	private TableColumn<VehicleBindingAdapter, Boolean> availableColumn;

	@FXML
	private TextField distancetext;
	@FXML
	private RadioButton rbuttonAir;
	@FXML
	private RadioButton rbuttonLand;
	@FXML
	private RadioButton rbuttonWater;
	@FXML
	private ToggleGroup radiotest;
	@FXML
	private Label availableVehicles;
	// @FXML
	// private Button bsearch;
	// @FXML
	// private Button bshowallv;
	// @FXML
	// private Button bbook;

	// Reference to the main application.
	private VehicleBookingMainApp mainApp;

	private Customer customer;
	private VehicleManagement vehman;

	/**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 */
	public VehicleBookingController() {
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setVehicleManagement(VehicleManagement vehman) {
		this.vehman = vehman;
		updateAvailableVehicles();
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		nameColumn
				.setCellValueFactory(cellData -> cellData.getValue().getName());
		environmentColumn.setCellValueFactory(
				cellData -> cellData.getValue().getEnvironment());
		distanceColumn.setCellValueFactory(
				cellData -> cellData.getValue().getDistance());
		availableColumn.setCellValueFactory(
				cellData -> cellData.getValue().getAvailable());
		rbuttonAir.setUserData(OperatingEnvironment.AIR);
		rbuttonWater.setUserData(OperatingEnvironment.WATER);
		rbuttonLand.setUserData(OperatingEnvironment.LAND);

	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(VehicleBookingMainApp mainApp) {
		this.mainApp = mainApp;

		// Add observable list data to the table
		vehicleTable.setItems(mainApp.getVehicleData());
	}

	@FXML
	public void handleBookVehicleButton() {
		int selectedIndex = vehicleTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			VehicleBindingAdapter selectedv = vehicleTable.getSelectionModel()
					.selectedItemProperty().getValue();
			// System.out.println(selectedv.getAvailable().getValue() + "1");
			// //Debug
			if (selectedv != null && selectedv.getAvailable().getValue()) {
				try {
					vehman.bookVehicle(selectedv.getBase(), customer);
					updateAvailableVehicles();
				} catch (IOException e) {
					showAlert("Error", "Fehler beim Zugriff auf die Log-Datei",
							"UwU");
				}
				// System.out.println(selectedv.getAvailable().getValue() +
				// "2"); //Debug
			}
		} else {
			// Nichts ausgewählt
			showAlert("No Selecetion, onii-chan UwU",
					"No Vehicle Selected, desu OwO",
					"Please select a vehicle in the table.°3°/+*%");
		}
	}

	private void showAlert(String title, String header, String content) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.initOwner(mainApp.getPrimaryStage());
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);

		alert.showAndWait();
	}

	@FXML
	public void handleSearchVehicleButton() {
		if (radiotest.getSelectedToggle() != null) {
			try {
				int searchdistance = Integer.parseInt(distancetext.getText());
				List<Vehicle> veh = vehman.findMatchingVehicles(searchdistance,
						(OperatingEnvironment) radiotest.getSelectedToggle()
								.getUserData());
				ObservableList<VehicleBindingAdapter> vadapterlist = FXCollections
						.observableArrayList();
				for (Vehicle v : veh) {
					vadapterlist.add(((VehicleBase) v).getBindingAdapter());

				}
				vehicleTable.setItems(vadapterlist);
			} catch (NumberFormatException nfe) {
				showAlert("LUL", "False distance", "OMEGALUL");
			}
		} else {
			showAlert("Nothing selected", "SELECT THE ENVIRONMENT", ":D");
		}
	}

	@FXML
	public void handleShowAllVehiclesButton() {
		vehicleTable.setItems(mainApp.getVehicleData());
	}

	public void updateAvailableVehicles() {
		availableVehicles
				.setText(Integer.toString(vehman.countAvailableVehicles()));
	}

}
