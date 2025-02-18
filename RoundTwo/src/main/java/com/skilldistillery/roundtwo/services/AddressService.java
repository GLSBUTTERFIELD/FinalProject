package com.skilldistillery.roundtwo.services;

import java.util.List;

import com.skilldistillery.roundtwo.entities.Address;

public interface AddressService {

	public List<Address> showAll();
	
	public Address findAddressById(int id);
	
	public Address newAddress(Address address);
}
