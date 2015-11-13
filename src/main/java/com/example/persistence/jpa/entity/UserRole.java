package com.example.persistence.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_roles database table.
 * 
 */
@Entity
@Table(name="user_roles")
@NamedQuery(name="UserRole.findAll", query="SELECT u FROM UserRole u")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;
	private int userRoleId;
	private String role;
	private User user;

	public UserRole() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_role_id", unique=true, nullable=false)
	public int getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}


	@Column(nullable=false, length=45)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="username", nullable=false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}