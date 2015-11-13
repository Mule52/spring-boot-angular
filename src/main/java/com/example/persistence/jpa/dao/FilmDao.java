package com.example.persistence.jpa.dao;

import com.example.persistence.jpa.entity.Film;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface FilmDao extends CrudRepository<Film, Short> {
}
