package com.example.persistence.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the film_text database table.
 * 
 */
@Entity
@Table(name="film_text")
@NamedQuery(name="FilmText.findAll", query="SELECT f FROM FilmText f")
public class FilmText implements Serializable {
	private static final long serialVersionUID = 1L;
	private short filmId;
	private String description;
	private String title;

	public FilmText() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="film_id", unique=true, nullable=false)
	public short getFilmId() {
		return this.filmId;
	}

	public void setFilmId(short filmId) {
		this.filmId = filmId;
	}


	@Lob
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Column(nullable=false, length=255)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}