package com.skilldistillery.roundtwo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.roundtwo.entities.Address;
import com.skilldistillery.roundtwo.repositories.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepo;
	
	@Override
	public List<Address> showAll() {
		return addressRepo.findAll();
	}

	@Override
	public Address findAddressById(int id) {
		return addressRepo.findById(id).orElse(null);
	}

	@Override
	public Address newAddress(Address newAddress) {
		return addressRepo.saveAndFlush(newAddress);
	}
	

}
