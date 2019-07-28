package PZM;

import java.io.IOException;

import PZM.view.PzmController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application
{

	private Stage primaryStage;
	private AnchorPane rootLayout;

//	public static void main(String[] args)
//	{
//		// Create a variable for the connection string.
//		String connectionUrl = "jdbc:sqlserver://DESKTOP-K7FKTFD\\SQLEXPRESS:61180;databaseName=PZM1;user=Eldiar;password=eldiiar98";
//
//		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();)
//		{
//			String SQL = "SELECT TOP 10 * FROM Kunde";
//			ResultSet rs = stmt.executeQuery(SQL);
//
//			// Iterate through the data in the result set and display it.
//			while (rs.next())
//			{
//				System.out.println(rs.getString("KuNr") + " " + rs.getString("KuName"));
//			}
//		}
//		// Handle any errors that may have occurred.
//		catch (SQLException e)
//		{
//			e.printStackTrace();
//		}
//
//	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("PZM1");
		initRootLayout();

	}

	public void initRootLayout()
	{
		try
		{
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/PzmOverview.fxml"));
			rootLayout = (AnchorPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

			// Give the controller access to the main app.
			PzmController controller = loader.getController();
			controller.setMain(this);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
