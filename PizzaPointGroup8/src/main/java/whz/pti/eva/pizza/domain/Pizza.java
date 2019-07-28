package whz.pti.eva.pizza.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import whz.pti.eva.BaseEntity;

@Entity
public class Pizza extends BaseEntity<Long>{

	
	public String name;
	private BigDecimal priceLarge;
	private BigDecimal priceMedium;
	private BigDecimal priceSmall;
	
	public Pizza() {}
	
	public Pizza(String name,BigDecimal priceSmall, BigDecimal priceMedium,BigDecimal priceLarge) {
		this.name = name;
		this.priceLarge = priceLarge;
		this.priceMedium = priceMedium;
		this.priceSmall = priceSmall;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPriceLarge() {
		return priceLarge;
	}

	public BigDecimal getPriceMedium() {
		return priceMedium;
	}

	public BigDecimal getPriceSmall() {
		return priceSmall;
	}
	
	
	
	
	
}
