package com.example.persistence.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the rental database table.
 * 
 */
@Entity
@Table(name="rental")
@NamedQuery(name="Rental.findAll", query="SELECT r FROM Rental r")
public class Rental implements Serializable {
	private static final long serialVersionUID = 1L;
	private int rentalId;
	private Timestamp lastUpdate;
	private Date rentalDate;
	private Date returnDate;
	private List<Payment> payments;
	private Customer customer;
	private Inventory inventory;
	private Staff staff;

	public Rental() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rental_id", unique=true, nullable=false)
	public int getRentalId() {
		return this.rentalId;
	}

	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}


	@Column(name="last_update", nullable=false)
	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="rental_date", nullable=false)
	public Date getRentalDate() {
		return this.rentalDate;
	}

	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="return_date")
	public Date getReturnDate() {
		return this.returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}


	//bi-directional many-to-one association to Payment
	@OneToMany(mappedBy="rental")
	public List<Payment> getPayments() {
		return this.payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public Payment addPayment(Payment payment) {
		getPayments().add(payment);
		payment.setRental(this);

		return payment;
	}

	public Payment removePayment(Payment payment) {
		getPayments().remove(payment);
		payment.setRental(null);

		return payment;
	}


	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="customer_id", nullable=false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	//bi-directional many-to-one association to Inventory
	@ManyToOne
	@JoinColumn(name="inventory_id", nullable=false)
	public Inventory getInventory() {
		return this.inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}


	//bi-directional many-to-one association to Staff
	@ManyToOne
	@JoinColumn(name="staff_id", nullable=false)
	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

}