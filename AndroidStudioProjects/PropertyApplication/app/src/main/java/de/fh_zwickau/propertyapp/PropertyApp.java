package de.fh_zwickau.propertyapp;

import  android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;

import static android.R.layout.simple_spinner_item;

/******************************************************************************
 * Mobile Anwendungen, Westsächsische Hochschule Zwickau.
 *
 * FILENAME:   	PropertyApp.java
 * AUTHORS:     Rainer Wasinger
 * 
 * NOTE:    	***THIS CLASS NEEDS MODIFIYING BY THE STUDENTS.***
 * 				In particular, see the method: initialiseSearchPageGUI. 
 * 
 * PROJECT DESCRIPTION:
 * This whole project consists of three Activities:
 * 		- PropertyApp. This is the starting activity as defined in the
 * 			AndroidManifest.xml file, i.e. it is the main activity for the
 * 			application ("android.intent.action.MAIN") and is in the LAUNCHER
 * 			category, meaning it is associated with a program icon.
 * 			The associated GUI for PropertyApp is contained in searchpage.xml.
 * 		- PropertyList. This activity is called when the user selects either
 * 			the buy or rent radio button and then clicks the search button.
 * 			The activity is responsible for presenting the user with a list
 * 			of property results (either to buy or to rent).
 * 			The associated GUI for PropertyList is contained inside 
 * 			propertylisting.xml (as well as propertylistrow.xml).
 * 		- PropertyDetails. This activity is called when the user selects one of
 * 			the listed properties. The activity is responsible for providing
 * 			more detailed information on an individual property.
 * 			The associated GUI for PropertyDetails is contained inside
 * 			propertydetails.xml.
 *
 * CLASS DESCRIPTION: 
 * PropertyApp. This is the starting class for the Android application. 
 * In this Activity class:
 *  	- adapters are defined to bind program code to the GUI widgets, and 
 *  		listeners for the GUI widgets (i.e. the search button) are also 
 *  		defined.
 *  	- XML property data files (for either buy or sell; see the directory
 *  		/res/raw for the location of these files) are read (when the search 
 *  		button is clicked) and stores the property information in a Vector 
 *  		data structure. A minimised set of this data is written to a local 
 *  		SQLite database called "propertydata", and properties that are 
 *  		displayed within the various activities are based on information 
 *  		retrieved from this database and stored as a Property object (see 
 *  		Property.java).
 *  	- An Intent is triggered (when the search button is clicked (and after 
 *  		a 'buy' or 'rent' radio box has been selected)). An Intent is 
 *  		basically a message passed to the Android OS saying, e.g. open up 
 *  		a specific activity within this application. Note that for our
 *  		Intent, we include a component (i.e. the class of the activity that 
 *  		is supposed to receive the intent), and we also define extras (i.e.
 *  		a bundle of other information that we want to pass along to the 
 *  		receiver of the intent), in this case, whether the user has 
 *  		requested 'buy' or 'rent' properties to be listed.
 * 
 *****************************************************************************/


public class PropertyApp extends AppCompatActivity implements OnItemSelectedListener {
	private static final String TAG = "PropertyApp";

	// EXTRA_PROPERTY_ID and EXTRA_TO_RENT are used to store Intent Extras.
	// EXTRA_PROPERTY_ID represents the property's ID and EXTRA_TO_RENT
	// can have the values 'true' (i.e. rent) or 'false' (i.e. buy).
	public static final String EXTRA_PROPERTY_ID="de.fh_zwickau.propertyapp.property_id";
	public static final String EXTRA_TO_RENT="de.fh_zwickau.propertyapp.to_rent";

	//*************************************************************************
	//* TODO [03]: Fügen Sie Java-Code-Variablen ein, die zum Füllen der Spinner auf der
	//* Suchseite Spinner erforderlich sind.
	//* Vorgeschlagenes Format: String [] spinnerVar1 = {"", "", ""} ;, wobei
	//* 'spinnerVar1' der Name des String-Arrays ist (z. B. propertyTypeItems,
	//* stateItems und numberedItems).
	//*
	//* Hinweis: Spinner entsprechen dem Dropdown-Selektor, den Sie in anderen
	//* Toolkits finden können (z. B. JComboBox in Java / Swing).
	//*
	//*************************************************************************
	//INSERT CODE HERE.
	String[] propertyType = {"Apartment", "House", "Villa"};
	String[] state = {"New York", "Washington D.C.", "California"};
	String[] bathrooms = {"1", "2", "3"};
	String[] bedrooms = {"1", "2", "3"};

	//*************************************************************************

	PropertyDB mDb = null;

    /**
     * onCreate. Called when this activity is first created. Also responsible
     * for setting the content view.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

		//*********************************************************************
		//* TODO [02]: Setzen Sie den Activity-Inhalt aus einer Layout-Ressource, d.h.
		//* verknüpfen Sie die Aktivität mit der XML-GUI. Schauen Sie sich die
		//* onCreate-Methode in PropertyDetails.java an, um ein Beispiel dafür zu sehen.
		//*
    	//*********************************************************************
    	//INSERT CODE HERE.
		setContentView(R.layout.searchpage);
    	//*********************************************************************
        
        initialiseSearchPageGUI();
    } //onCreate
	
    /** 
     * initialiseSearchPageGUI. Sets up the GUI components that are relevant to 
     * this project.
     */
    public void initialiseSearchPageGUI() {
		//*********************************************************************
		//* TODO [04]: Fügen Sie Java-Code ein, der zum Initialisieren der GUI-Widgets
		//* der Suchseite der Anwendung erforderlich ist.
		//* Vorschlag: Hier werden die Spinner (z.B. PropertyType, state, bathrooms und
		//* carSpaces), RadioGroup (z.B. buyOrRent), RadioButtons (z.B. buy, rent),
		//* EditTexts (z.B. suburb, priceFrom, priceTo) und Buttons (z.B. searchBtn)
		//* erstellt und mit den IDs verknüpft, die Sie in der Datei searchpage.xml
		//* definiert haben.
		//*
		//* Hier sind auch die Spinner-Adapter (z.B. ArrayAdapter<String>) und der
		//* Listener der Buttons (z.B. setOnClickListener) definiert.
		//*
		//* Hier wird auch der Intent erstellt (z. B. wenn der Benutzer den Search-Button
		//* drückt), um die PropertyList-Activity auszulösen.
		//*
		//* Hinweis: Adapter werden verwendet, um Programmcode mit den GUI-Widgets zu
		//* verknüpfen, z.B. Verknüpfen der definierten Elemente in einem Spinner
		//* (Programmcode) mit dem in der XML-Layoutdatei definierten Spinner-Widget.
		//*
		//* Hinweis: Listener werden verwendet, um auf Benutzerinteraktion mit den GUI-Widgets
		//* zu reagieren, z.B. Auswählen einer Radio-Box und das Drücken einer Taste.
		//*
		//*********************************************************************
		// 04A: Erstellen Sie die Spinner und verknüpfen Sie diese über den ArrayAdapter
		// mit den String-Arrays (d.h. den Daten), die Sie oben in TODO [03] definiert haben.
		//
    	//04A: INSERT CODE HERE.
		Spinner propertyTypeSpinner = (Spinner) findViewById(R.id.spinner_Property);
		Spinner stateSpinner = (Spinner) findViewById(R.id.spinner_State);
		Spinner bathroomsSpinner = (Spinner) findViewById(R.id.spinner_Bathrooms);
		Spinner bedroomsSpinner = (Spinner) findViewById(R.id.spinner_Bedrooms);

		ArrayAdapter<String> spinnerPropertyAdapter = new ArrayAdapter<String>(this, simple_spinner_item, propertyType);
		ArrayAdapter<String> spinnerStateAdapter = new ArrayAdapter<String>(this, simple_spinner_item, state);
		ArrayAdapter<String> spinnerBathroomsAdapter = new ArrayAdapter<String>(this, simple_spinner_item, bathrooms);
		ArrayAdapter<String> spinnerBedroomsAdapter = new ArrayAdapter<String>(this, simple_spinner_item, bedrooms);

		spinnerPropertyAdapter.setDropDownViewResource(android.R.layout
				.simple_spinner_dropdown_item);
		spinnerStateAdapter.setDropDownViewResource(android.R.layout
				.simple_spinner_dropdown_item);
		spinnerBathroomsAdapter.setDropDownViewResource(android.R.layout
				.simple_spinner_dropdown_item);
		spinnerBedroomsAdapter.setDropDownViewResource(android.R.layout
				.simple_spinner_dropdown_item);

		propertyTypeSpinner.setAdapter(spinnerPropertyAdapter);
		propertyTypeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		stateSpinner.setAdapter(spinnerStateAdapter);
		stateSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		bathroomsSpinner.setAdapter(spinnerBathroomsAdapter);
		bathroomsSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		bedroomsSpinner.setAdapter(spinnerBedroomsAdapter);
		bedroomsSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

    	//
		// 04B: Erstellen Sie die Suchschaltfläche und den zugehörigen OnClick-Listener.
		// Überprüfen Sie innerhalb der onClick-Methode, welcher Radio Button ausgewählt
		// wurde, und rufen Sie dann die populatePropertyCatalog(boolean toRent)-Methode
		// auf und lösen Sie einen Intent aus, um die PropertyList-Activity zu starten.
		// Verwenden Sie auch die Methode putExtra, um zusätzliche Informationen zum
		// Intent hinzuzufügen, und zwar, ob das Optionsfeld "buy" oder "rent" ausgewählt
		// wurde (d.h. mithilfe der Klassenvariablen EXTRA_TO_RENT). Diese zusätzlichen
		// Informationen werden von der PropertyList-Activity verwendet, um zu ermitteln,
		// welche XML-Datei verwendet werden soll.
		//
    	//04B: INSERT CODE HERE.

		//
    	//*********************************************************************
    } //initialiseSearchPageGUI

    public void searchOnClick(View v){
        RadioGroup rg = findViewById(R.id.radioGroup);
        int checkedId = rg.getCheckedRadioButtonId();
        if(checkedId == R.id.radioButton_Rent){
            populatePropertyCatalog(true);
            Intent intent = new Intent(PropertyApp.this, PropertyList.class);
            intent.putExtra("EXTRA_TO_RENT", true);
            startActivity(intent);
        }
        if(checkedId == R.id.radioButton_Buy){
            populatePropertyCatalog(false);
            Intent intent = new Intent(PropertyApp.this, PropertyList.class);
            intent.putExtra("EXTRA_TO_RENT", false);
            startActivity(intent);
        }
    }
	/**
	 * populatePropertyCatalog. This function is responsible for storing the
	 * XML property data into a local tree structure. The  instantiation to the
	 * XMLParser is created in this method. The instantiation to the 
	 * XMLPropertyCatalog is created in XMLUnmarshaller.java.
	 */
    public void populatePropertyCatalog(boolean toRent) {
		Log.d(TAG, "populatePropertyCatalog");

		if (mDb != null) { // Close the previous database instantiation in case 
			mDb.close();   // it is not already closed.
		}

		mDb = new PropertyDB(this);
		mDb.open(toRent, true);
   } //populatePropertyCatalog
    
    /**
     * onItemSelected. This relates to the Spinner widget functionality and
     * is not needed for the Android labs.
     */
	public void onItemSelected(AdapterView<?> parent, View v, int position, 
			long id) {
 	} //onItemSelected

    /**
     * onNothingSelected. This relates to the Spinner widget functionality and
     * is not needed for the Android labs.
     */
    public void onNothingSelected(AdapterView<?> parent) {
   	} //onNothingSelected

	/**
	 * onDestroy. Called when the activity is destroyed.
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy");
	} // onDestroy

} //PropertyApp
