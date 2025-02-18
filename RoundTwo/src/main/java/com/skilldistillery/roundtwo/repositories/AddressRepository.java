package com.skilldistillery.roundtwo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.roundtwo.entities.Address;

public interface AddressRepository extends JpaRepository<Address,Integer> {

}
