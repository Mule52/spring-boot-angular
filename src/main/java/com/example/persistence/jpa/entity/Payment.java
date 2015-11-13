package com.example.persistence.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the payment database table.
 * 
 */
@Entity
@Table(name="payment")
@NamedQuery(name="Payment.findAll", query="SELECT p FROM Payment p")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;
	private int paymentId;
	private BigDecimal amount;
	private Timestamp lastUpdate;
	private Date paymentDate;
	private Customer customer;
	private Rental rental;
	private Staff staff;

	public Payment() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="payment_id", unique=true, nullable=false)
	public int getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}


	@Column(nullable=false, precision=10, scale=2)
	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	@Column(name="last_update", nullable=false)
	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="payment_date", nullable=false)
	public Date getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
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


	//bi-directional many-to-one association to Rental
	@ManyToOne
	@JoinColumn(name="rental_id")
	public Rental getRental() {
		return this.rental;
	}

	public void setRental(Rental rental) {
		this.rental = rental;
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