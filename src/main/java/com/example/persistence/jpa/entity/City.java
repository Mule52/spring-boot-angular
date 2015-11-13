package com.example.persistence.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the city database table.
 * 
 */
@Entity
@Table(name="city")
@NamedQuery(name="City.findAll", query="SELECT c FROM City c")
public class City implements Serializable {
	private static final long serialVersionUID = 1L;
	private int cityId;
	private String city;
	private Timestamp lastUpdate;
	private List<Address> addresses;
	private Country country;

	public City() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="city_id", unique=true, nullable=false)
	public int getCityId() {
		return this.cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}


	@Column(nullable=false, length=50)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}


	@Column(name="last_update", nullable=false)
	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


	//bi-directional many-to-one association to Address
	@OneToMany(mappedBy="city")
	public List<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Address addAddress(Address address) {
		getAddresses().add(address);
		address.setCity(this);

		return address;
	}

	public Address removeAddress(Address address) {
		getAddresses().remove(address);
		address.setCity(null);

		return address;
	}


	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="country_id", nullable=false)
	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}