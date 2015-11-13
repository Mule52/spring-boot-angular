package com.example.persistence.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the film database table.
 * 
 */
@Entity
@Table(name="film")
@NamedQuery(name="Film.findAll", query="SELECT f FROM Film f")
public class Film implements Serializable {
	private static final long serialVersionUID = 1L;
	private int filmId;
	private String description;
	private Timestamp lastUpdate;
	private int length;
	private String rating;
	private Date releaseYear;
	private byte rentalDuration;
	private BigDecimal rentalRate;
	private BigDecimal replacementCost;
	private String specialFeatures;
	private String title;
	private Language language1;
	private Language language2;
	private List<FilmActor> filmActors;
	private List<FilmCategory> filmCategories;
	private List<Inventory> inventories;

	public Film() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="film_id", unique=true, nullable=false)
	public int getFilmId() {
		return this.filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}


	@Lob
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Column(name="last_update", nullable=false)
	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
	}


	@Column(length=1)
	public String getRating() {
		return this.rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="release_year")
	public Date getReleaseYear() {
		return this.releaseYear;
	}

	public void setReleaseYear(Date releaseYear) {
		this.releaseYear = releaseYear;
	}


	@Column(name="rental_duration", nullable=false)
	public byte getRentalDuration() {
		return this.rentalDuration;
	}

	public void setRentalDuration(byte rentalDuration) {
		this.rentalDuration = rentalDuration;
	}


	@Column(name="rental_rate", nullable=false, precision=10, scale=2)
	public BigDecimal getRentalRate() {
		return this.rentalRate;
	}

	public void setRentalRate(BigDecimal rentalRate) {
		this.rentalRate = rentalRate;
	}


	@Column(name="replacement_cost", nullable=false, precision=10, scale=2)
	public BigDecimal getReplacementCost() {
		return this.replacementCost;
	}

	public void setReplacementCost(BigDecimal replacementCost) {
		this.replacementCost = replacementCost;
	}


	@Column(name="special_features")
	public String getSpecialFeatures() {
		return this.specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}


	@Column(nullable=false, length=255)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	//bi-directional many-to-one association to Language
	@ManyToOne
	@JoinColumn(name="language_id", nullable=false)
	public Language getLanguage1() {
		return this.language1;
	}

	public void setLanguage1(Language language1) {
		this.language1 = language1;
	}


	//bi-directional many-to-one association to Language
	@ManyToOne
	@JoinColumn(name="original_language_id")
	public Language getLanguage2() {
		return this.language2;
	}

	public void setLanguage2(Language language2) {
		this.language2 = language2;
	}


	//bi-directional many-to-one association to FilmActor
	@OneToMany(mappedBy="film")
	public List<FilmActor> getFilmActors() {
		return this.filmActors;
	}

	public void setFilmActors(List<FilmActor> filmActors) {
		this.filmActors = filmActors;
	}

	public FilmActor addFilmActor(FilmActor filmActor) {
		getFilmActors().add(filmActor);
		filmActor.setFilm(this);

		return filmActor;
	}

	public FilmActor removeFilmActor(FilmActor filmActor) {
		getFilmActors().remove(filmActor);
		filmActor.setFilm(null);

		return filmActor;
	}


	//bi-directional many-to-one association to FilmCategory
	@OneToMany(mappedBy="film")
	public List<FilmCategory> getFilmCategories() {
		return this.filmCategories;
	}

	public void setFilmCategories(List<FilmCategory> filmCategories) {
		this.filmCategories = filmCategories;
	}

	public FilmCategory addFilmCategory(FilmCategory filmCategory) {
		getFilmCategories().add(filmCategory);
		filmCategory.setFilm(this);

		return filmCategory;
	}

	public FilmCategory removeFilmCategory(FilmCategory filmCategory) {
		getFilmCategories().remove(filmCategory);
		filmCategory.setFilm(null);

		return filmCategory;
	}


	//bi-directional many-to-one association to Inventory
	@OneToMany(mappedBy="film")
	public List<Inventory> getInventories() {
		return this.inventories;
	}

	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}

	public Inventory addInventory(Inventory inventory) {
		getInventories().add(inventory);
		inventory.setFilm(this);

		return inventory;
	}

	public Inventory removeInventory(Inventory inventory) {
		getInventories().remove(inventory);
		inventory.setFilm(null);

		return inventory;
	}

}