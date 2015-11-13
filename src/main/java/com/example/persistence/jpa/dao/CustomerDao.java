package com.example.persistence.jpa.dao;

import com.example.persistence.jpa.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CustomerDao extends CrudRepository<Customer, Integer>, PagingAndSortingRepository<Customer, Integer> {
}