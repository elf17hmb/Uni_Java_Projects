package PZM.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Abteilung
{
	StringProperty abtNr;
	StringProperty abtName;

	public Abteilung(ResultSet rs)
	{
		try
		{
			abtNr = new SimpleStringProperty(rs.getString("AbtNr"));
			abtName = new SimpleStringProperty(rs.getString("AbtName"));
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public StringProperty getAbtNr()
	{
		return abtNr;
	}

	public void setAbtNr(StringProperty abtNr)
	{
		this.abtNr = abtNr;
	}

	public StringProperty getAbtName()
	{
		return abtName;
	}

	public void setAbtName(StringProperty abtName)
	{
		this.abtName = abtName;
	}

}
