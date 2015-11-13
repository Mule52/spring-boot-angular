package com.example.persistence.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the inventory database table.
 * 
 */
@Entity
@Table(name="inventory")
@NamedQuery(name="Inventory.findAll", query="SELECT i FROM Inventory i")
public class Inventory implements Serializable {
	private static final long serialVersionUID = 1L;
	private int inventoryId;
	private Timestamp lastUpdate;
	private Film film;
	private Store store;
	private List<Rental> rentals;

	public Inventory() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="inventory_id", unique=true, nullable=false)
	public int getInventoryId() {
		return this.inventoryId;
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}


	@Column(name="last_update", nullable=false)
	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


	//bi-directional many-to-one association to Film
	@ManyToOne
	@JoinColumn(name="film_id", nullable=false)
	public Film getFilm() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
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


	//bi-directional many-to-one association to Rental
	@OneToMany(mappedBy="inventory")
	public List<Rental> getRentals() {
		return this.rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public Rental addRental(Rental rental) {
		getRentals().add(rental);
		rental.setInventory(this);

		return rental;
	}

	public Rental removeRental(Rental rental) {
		getRentals().remove(rental);
		rental.setInventory(null);

		return rental;
	}

}