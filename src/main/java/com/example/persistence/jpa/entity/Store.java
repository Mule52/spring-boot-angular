package com.example.persistence.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the store database table.
 * 
 */
@Entity
@Table(name="store")
@NamedQuery(name="Store.findAll", query="SELECT s FROM Store s")
public class Store implements Serializable {
	private static final long serialVersionUID = 1L;
	private byte storeId;
	private Timestamp lastUpdate;
	private List<Customer> customers;
	private List<Inventory> inventories;
	private List<Staff> staffs;
	private Address address;
	private Staff staff;

	public Store() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="store_id", unique=true, nullable=false)
	public byte getStoreId() {
		return this.storeId;
	}

	public void setStoreId(byte storeId) {
		this.storeId = storeId;
	}


	@Column(name="last_update", nullable=false)
	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


	//bi-directional many-to-one association to Customer
	@OneToMany(mappedBy="store")
	public List<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Customer addCustomer(Customer customer) {
		getCustomers().add(customer);
		customer.setStore(this);

		return customer;
	}

	public Customer removeCustomer(Customer customer) {
		getCustomers().remove(customer);
		customer.setStore(null);

		return customer;
	}


	//bi-directional many-to-one association to Inventory
	@OneToMany(mappedBy="store")
	public List<Inventory> getInventories() {
		return this.inventories;
	}

	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}

	public Inventory addInventory(Inventory inventory) {
		getInventories().add(inventory);
		inventory.setStore(this);

		return inventory;
	}

	public Inventory removeInventory(Inventory inventory) {
		getInventories().remove(inventory);
		inventory.setStore(null);

		return inventory;
	}


	//bi-directional many-to-one association to Staff
	@OneToMany(mappedBy="store")
	public List<Staff> getStaffs() {
		return this.staffs;
	}

	public void setStaffs(List<Staff> staffs) {
		this.staffs = staffs;
	}

	public Staff addStaff(Staff staff) {
		getStaffs().add(staff);
		staff.setStore(this);

		return staff;
	}

	public Staff removeStaff(Staff staff) {
		getStaffs().remove(staff);
		staff.setStore(null);

		return staff;
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


	//bi-directional many-to-one association to Staff
	@ManyToOne
	@JoinColumn(name="manager_staff_id", nullable=false)
	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

}