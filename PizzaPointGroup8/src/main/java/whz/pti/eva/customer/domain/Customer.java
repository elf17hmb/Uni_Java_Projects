package whz.pti.eva.customer.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import whz.pti.eva.BaseEntity;
import whz.pti.eva.deliveryAddress.domain.DeliveryAddress;
import whz.pti.eva.security.domain.CurrentUser;

@Entity
public class Customer extends BaseEntity<Long>{
	
	private String email ="";
	private String loginName="";
	private String firstName ="";
	private String lastName ="";
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<DeliveryAddress> deliveryAddress = new ArrayList<>();
	
	public Customer(){}
	
	public Customer (String loginName, String email)
	{
		this.loginName = loginName;
	}

	public String getLoginName() {
		return loginName;
	}

	public List<DeliveryAddress> getDeliveryAdress() {
		return deliveryAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setDeliveryAddress(List<DeliveryAddress> deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void addDeliveryAddress(DeliveryAddress dadr) {
		deliveryAddress.add(dadr);
	}
	
	
}
