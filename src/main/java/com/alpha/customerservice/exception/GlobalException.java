package com.alpha.customerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import com.alpha.customerservice.dto.ResponseStructure;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(CustomerAlreadyExistWithPhoneException.class)
	public ResponseEntity<ResponseStructure<String>> handlerCustomerAlreadyExistWithPhoneException() {
		ResponseStructure<String> responseStructure=new ResponseStructure<String>();
		responseStructure.setStatuscode(HttpStatus.FOUND.value());
		responseStructure.setMessage("Customer Already Present");
		responseStructure.setData("Already Exist");
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.FOUND);
	}
	
	@ExceptionHandler(CustomerNotFoundByIdExceprion.class)
	public ResponseEntity<ResponseStructure<String>> handlerCustomerNotFoundByIdExceprion() {
		ResponseStructure<String> responseStructure=new ResponseStructure<String>();
		responseStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Customer Not Found");
		responseStructure.setData("Customer Not Found");
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(HttpClientErrorException.NotFound.class)
	public ResponseStructure<String> handler() {

	    ResponseStructure<String> response = new ResponseStructure<>();

	    response.setStatuscode(HttpStatus.NOT_FOUND.value());
	    response.setMessage("Show not found. Please enter a valid Show ID.");
	    response.setData(null);

	    return response; 
	}
}
