package com.example.persistence.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the film_actor database table.
 * 
 */
@Entity
@Table(name="film_actor")
@NamedQuery(name="FilmActor.findAll", query="SELECT f FROM FilmActor f")
public class FilmActor implements Serializable {
	private static final long serialVersionUID = 1L;
	private FilmActorPK id;
	private Timestamp lastUpdate;
	private Actor actor;
	private Film film;

	public FilmActor() {
	}


	@EmbeddedId
	public FilmActorPK getId() {
		return this.id;
	}

	public void setId(FilmActorPK id) {
		this.id = id;
	}


	@Column(name="last_update", nullable=false)
	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


	//bi-directional many-to-one association to Actor
	@ManyToOne
	@JoinColumn(name="actor_id", nullable=false, insertable=false, updatable=false)
	public Actor getActor() {
		return this.actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}


	//bi-directional many-to-one association to Film
	@ManyToOne
	@JoinColumn(name="film_id", nullable=false, insertable=false, updatable=false)
	public Film getFilm() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

}