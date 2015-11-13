package com.example.persistence.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@Table(name="customer")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	private int customerId;
	private byte active;
	private Date createDate;
	private String email;
	private String firstName;
	private String lastName;
	private Timestamp lastUpdate;

	@JsonIgnore
	private Address address;

	@JsonIgnore
	private Store store;

	@JsonIgnore
	private List<Payment> payments;

	@JsonIgnore
	private List<Rental> rentals;

	public Customer() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customer_id", unique=true, nullable=false)
	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	@Column(nullable=false)
	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date", nullable=false)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	@Column(length=50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Column(name="first_name", nullable=false, length=45)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	@Column(name="last_name", nullable=false, length=45)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	@Column(name="last_update", nullable=false)
	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


	//bi-directional many-to-one association to Address
	@ManyToOne
	@JoinColumn(name="address_id", nullable=false)
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}


	//bi-directional many-to-one association to Store
	@ManyToOne
	@JoinColumn(name="store_id", nullable=false)
	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}


	//bi-directional many-to-one association to Payment
	@OneToMany(mappedBy="customer")
	public List<Payment> getPayments() {
		return this.payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public Payment addPayment(Payment payment) {
		getPayments().add(payment);
		payment.setCustomer(this);

		return payment;
	}

	public Payment removePayment(Payment payment) {
		getPayments().remove(payment);
		payment.setCustomer(null);

		return payment;
	}


	//bi-directional many-to-one association to Rental
	@OneToMany(mappedBy="customer")
	public List<Rental> getRentals() {
		return this.rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public Rental addRental(Rental rental) {
		getRentals().add(rental);
		rental.setCustomer(this);

		return rental;
	}

	public Rental removeRental(Rental rental) {
		getRentals().remove(rental);
		rental.setCustomer(null);

		return rental;
	}

}