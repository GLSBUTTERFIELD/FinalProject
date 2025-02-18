package com.skilldistillery.roundtwo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.roundtwo.entities.Address;
import com.skilldistillery.roundtwo.services.AddressService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	// --------------------------------------------------------------------------------------\\
//  GET ALL
	@GetMapping("addresses")
	public List<Address> index() {
		return addressService.showAll();
	}
	// --------------------------------------------------------------------------------------\\
//  GET by Id
	@GetMapping("addresses/{addressId}")
	public Address showAddress(@PathVariable("addressId") int addressId, HttpServletRequest req,
			HttpServletResponse res) {
		Address foundAddress = null;
		try {
			foundAddress = addressService.findAddressById(addressId);
			if (foundAddress == null) {
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		} catch (Exception e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			foundAddress = null;
			e.printStackTrace();
		}
		return foundAddress;
	}
	// --------------------------------------------------------------------------------------\\

	//POST new gathering
		@PostMapping("addresses")
		public Address add(@RequestBody Address address, HttpServletResponse res, HttpServletRequest req) {
			Address createdAddress = null;
			try {
				createdAddress = addressService.newAddress(address);
				if (createdAddress == null) {
					res.setStatus(HttpServletResponse.SC_NOT_FOUND);
				} else {
					res.setStatus(HttpServletResponse.SC_CREATED);
					res.setHeader("Location", req.getRequestURL().append('/').append(createdAddress.getId()).toString());
				}
			} catch (Exception e) {
				res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				createdAddress = null;
				e.printStackTrace();
			}
			return createdAddress;
		}
}
