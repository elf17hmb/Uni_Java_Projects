package PZM.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import PZM.Main;
import PZM.model.Abteilung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PzmController
{
	@FXML
	private TextField kuNr;
	@FXML
	private TextField kuName;
	@FXML
	private TextField plz;
	@FXML
	private TextField ort;

	@FXML
	private TextField proNr;
	@FXML
	private TextField proKuNr;
	@FXML
	private TextField proName;
	@FXML
	private TextField geplStuUmfang;

	@FXML
	private TableView<Abteilung> table;
	@FXML
	private TableColumn<Abteilung, String> abtNr;
	@FXML
	private TableColumn<Abteilung, String> abtName;

	private Main main;

	public void setMain(Main main)
	{
		this.main = main;
	}

	@FXML
	public void initialize()
	{
		abtNr.setCellValueFactory(cellData -> cellData.getValue().getAbtNr());
		abtName.setCellValueFactory(cellData -> cellData.getValue().getAbtName());

	}

	@FXML
	public void handleGetDataOnClick()
	{
		// Create a variable for the connection string.
		String connectionUrl = "jdbc:sqlserver://DESKTOP-K7FKTFD\\SQLEXPRESS:61180;databaseName=PZM1;user=Eldiar;password=eldiiar98";

		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();)
		{
			String SQL = "SELECT * FROM Kunde WHERE KuNr=1";
			ResultSet rs = stmt.executeQuery(SQL);

			// Iterate through the data in the result set and display it.
			while (rs.next())
			{
				kuNr.setText(rs.getString("KuNr"));
				kuName.setText(rs.getString("KuName"));
				plz.setText(rs.getString("PLZ"));
				ort.setText(rs.getString("Ort"));
			}
		}
		// Handle any errors that may have occurred.
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	public void handleProjectGetDataOnClick()
	{
		// Create a variable for the connection string.
		String connectionUrl = "jdbc:sqlserver://DESKTOP-K7FKTFD\\SQLEXPRESS:61180;databaseName=PZM1;user=Eldiar;password=eldiiar98";

		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();)
		{
			String SQL = "SELECT * FROM Projekt WHERE ProNr=0";
			ResultSet rs = stmt.executeQuery(SQL);

			// Iterate through the data in the result set and display it.
			while (rs.next())
			{
				proNr.setText(rs.getString("ProNr"));
				proKuNr.setText(rs.getString("KuNr"));
				proName.setText(rs.getString("ProName"));
				geplStuUmfang.setText(rs.getString("GeplStuUmfang"));
			}
		}
		// Handle any errors that may have occurred.
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	public void handleAbteilungGetDataOnClick()
	{
		// Create a variable for the connection string.
		String connectionUrl = "jdbc:sqlserver://DESKTOP-K7FKTFD\\SQLEXPRESS:61180;databaseName=PZM1;user=Eldiar;password=eldiiar98";

		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();)
		{
			String SQL = "SELECT TOP 10 * FROM Abteilung";
			ResultSet rs = stmt.executeQuery(SQL);

			ObservableList<Abteilung> list = FXCollections.observableArrayList();
			// Iterate through the data in the result set and display it.
			while (rs.next())
			{
				list.add(new Abteilung(rs));

			}
			table.setItems(list);
		}
		// Handle any errors that may have occurred.
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

}
