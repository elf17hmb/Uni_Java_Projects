package de.fh_zwickau.oose;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
// Die folgende Zeile ist optional. Sie gibt die gewünschte Reihenfolge der Attribute an.
@XmlType(propOrder = { "autor", "buchtitel", "verlag", "isbn", "erscheinungsjahr"})
public class Buch {

	private String buchtitel;
	private String autor;			// in diesem Beispiel kann ein Buch nur einen Autor haben.
	private String verlag;
	private int erscheinungsjahr;
	private String isbn;

	/* 
	 * Für jedes Attribut mit get- und set-Methoden wird automatisch ein XML-Element
	 * generiert.
	 * Für die Methode getABC ist der Name des XML-Elementes standardmäßig ABC.
	 * Hier verwenden wir @XmlElement(name = "titel"), damit das Attribut
	 * als XML-Element "titel" angezeigt wird.
	 */
	@XmlElement(name = "titel")
	public String getBuchtitel() {
		return buchtitel;
	}

	public void setBuchtitel(String titel) {
		this.buchtitel = titel;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getVerlag() {
		return verlag;
	}

	public void setVerlag(String publisher) {
		this.verlag = publisher;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public int getErscheinungsjahr() {
		return erscheinungsjahr;
	}

	public void setErscheinungsjahr(int erscheinungsjahr) {
		this.erscheinungsjahr = erscheinungsjahr;
	}

}
