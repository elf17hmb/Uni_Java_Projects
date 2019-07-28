package de.fh_zwickau.oose;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// @XmlRootElement: Das Wurzelelement des XML-Dokuments wird durch die Klasse Bestand bestimmt:
@XmlRootElement
public class Bestand {

	// @XmlElement: definiert den Namen des XML-Elements
	@XmlElement(name = "buch")
	private ArrayList<Buch> buecherliste;

	public void setBuecher(ArrayList<Buch> buecherliste) {
		this.buecherliste = buecherliste;
	}

	public ArrayList<Buch> getBuecherliste() {
		return buecherliste;
	}
}
