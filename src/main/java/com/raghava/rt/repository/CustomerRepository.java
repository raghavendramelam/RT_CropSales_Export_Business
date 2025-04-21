package com.raghava.rt.repository;

import com.raghava.rt.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    public Optional<Customer> findByMobile( Long mobile);
}
