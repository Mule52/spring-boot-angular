package com.example.persistence.jpa.dao;

import javax.transaction.Transactional;

import com.example.persistence.jpa.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ActorDao extends CrudRepository<Actor, Integer>, PagingAndSortingRepository<Actor, Integer> {

    // http://docs.spring.io/spring-data/data-jpa/docs/current/reference/html/#jpa.query-methods.query-creation
    Iterable<Actor> findByLastNameContaining(String lastName);

    Integer deleteByActorId(Integer id); // use query derivation for delete/remove
}