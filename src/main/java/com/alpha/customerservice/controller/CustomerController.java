package com.alpha.customerservice.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.customerservice.dto.BookSeatsRequestDto;
import com.alpha.customerservice.dto.CreateCustomerDto;
import com.alpha.customerservice.dto.MovieResponseDto;
import com.alpha.customerservice.dto.ResponseStructure;
import com.alpha.customerservice.dto.SeatResponseDto;
import com.alpha.customerservice.entity.Customer;
import com.alpha.customerservice.service.Customerservice;




//import com.alpha.customerservice.dto.CreateCustomerDto;

@RestController
public class CustomerController {
	
	@Autowired
	Customerservice customerservice;
//------------------------------------------------------------------------------------------------
	@PostMapping("/customer/createaccount")
	public ResponseEntity<ResponseStructure<Customer>> createCustmoreAccount(@RequestBody CreateCustomerDto createCustomerDto) {
		return customerservice.createCustmoreAccount(createCustomerDto);
	}
//----------------------------------------------------------------------------------------------------------	
	@GetMapping("/findcustomer")
	public ResponseEntity<ResponseStructure<Customer>> findCustomer(@RequestParam long id) {
		System.out.println("Find Customer by id: "+id);
		return customerservice.findCustomer(id);
	}
//--------------------------------------------------------------------------------------------
	@DeleteMapping("/deletecustomer")
	public ResponseEntity<ResponseStructure<Customer>> DeleteCustomer(@RequestParam long id) {
		System.out.println("find customer by id: "+id);
		return customerservice.DeleteCustomer(id);
	}
//------------------------------------------------------------------------------------------------	
	@GetMapping("/customer/findmovie")
	public MovieResponseDto findmovie(@RequestParam String title) {
		System.out.println("finding movie by title: "+title);
		 return customerservice.findmovie(title);
	}
//---------------------------------------------------------------------------------------------------	
	@GetMapping("/customer/buyticket")
	public ResponseStructure<Object> findTheaterByMovie(@RequestParam String title,@RequestParam String language, @RequestParam LocalDate showdate) {
		 return customerservice.findingTheatersByMovieName(title,language,showdate);
	}
//------------------------------------------------------------------------------------------------------	
	@GetMapping("/customer/selectseats")
	public ResponseStructure<List<SeatResponseDto>> getAvailableSeats(@RequestParam long id) {
		return customerservice.getAvailableSeats(id);
	}
//-------------------------------------------------------------------------------------------	
	@PostMapping("/customer/bookseat")
	public ResponseStructure<List<SeatResponseDto>> bookseats(@RequestBody BookSeatsRequestDto bookSeatsRequestDto) {
		return customerservice.bookseats(bookSeatsRequestDto);
	}
}
