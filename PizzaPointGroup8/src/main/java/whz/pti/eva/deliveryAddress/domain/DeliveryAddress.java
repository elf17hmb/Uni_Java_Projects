package whz.pti.eva.deliveryAddress.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import whz.pti.eva.BaseEntity;
import whz.pti.eva.customer.domain.Customer;

@Entity
public class DeliveryAddress extends BaseEntity<Long>{
	
	private String street;
	private String housenumber;
	private String town;
	private String postalCode;
	
	@ManyToMany(mappedBy="deliveryAddress",cascade=CascadeType.ALL)
	private List<Customer> customer;
	
	public DeliveryAddress()
	{
		
	}
	
	public DeliveryAddress(String street, String housenumber, String town, String postalCode) {
		this.street=street;
		this.housenumber=housenumber;
		this.town = town;
		this.postalCode = postalCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHousenumber() {
		return housenumber;
	}

	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public List<Customer> getCustomer() {
		return customer;
	}

	public void setCustomer(List<Customer> customer) {
		this.customer = customer;
	}
	
}
