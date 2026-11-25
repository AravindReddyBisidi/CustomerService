
package com.alpha.customerservice.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.alpha.customerservice.dto.CreateCustomerDto;
import com.alpha.customerservice.dto.BookSeatsRequestDto;
import com.alpha.customerservice.dto.MovieResponseDto;
import com.alpha.customerservice.dto.ResponseStructure;
import com.alpha.customerservice.dto.SeatResponseDto;
import com.alpha.customerservice.entity.Customer;
import com.alpha.customerservice.repository.CustomerRepository;

@Service
public class Customerservice {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RestTemplate restTemplate;

    // Create Customer Account
    public ResponseEntity<ResponseStructure<Customer>> createCustmoreAccount(
            CreateCustomerDto createCustomerDto) {

        Optional<Customer> optionalcustomer =
                customerRepository.findByPhone(createCustomerDto.getPhone());

        if (optionalcustomer.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Customer already exists with this phone number");
        }

        Customer newCustomer = new Customer();

        newCustomer.setName(createCustomerDto.getName());
        newCustomer.setAge(createCustomerDto.getAge());
        newCustomer.setGender(createCustomerDto.getGender());
        newCustomer.setMail(createCustomerDto.getMail());
        newCustomer.setPhone(createCustomerDto.getPhone());

        Customer saveCustomer = customerRepository.save(newCustomer);

        ResponseStructure<Customer> responseStructure =
                new ResponseStructure<>();

        responseStructure.setStatuscode(HttpStatus.CREATED.value());
        responseStructure.setMessage("New Customer created Successfully");
        responseStructure.setData(saveCustomer);

        return new ResponseEntity<>(
                responseStructure, HttpStatus.CREATED);
    }

    // Find Customer
    public ResponseEntity<ResponseStructure<Customer>> findCustomer(long id) {

        Customer getCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Customer Not Found"));

        ResponseStructure<Customer> responseStructure =
                new ResponseStructure<>();

        responseStructure.setStatuscode(HttpStatus.OK.value());
        responseStructure.setMessage("Customer Found");
        responseStructure.setData(getCustomer);

        return new ResponseEntity<>(
                responseStructure, HttpStatus.OK);
    }

    // Delete Customer
    public ResponseEntity<ResponseStructure<Customer>> DeleteCustomer(long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Customer Not Found"));

        customerRepository.delete(customer);

        ResponseStructure<Customer> responseStructure =
                new ResponseStructure<>();

        responseStructure.setStatuscode(HttpStatus.OK.value());
        responseStructure.setMessage("Customer Deleted Successfully");
        responseStructure.setData(null);

        return new ResponseEntity<>(
                responseStructure, HttpStatus.OK);
    }

    // Find Movie
    public MovieResponseDto findmovie(String title) {

        String url = "http://localhost:8080/findmovie?title=" + title;

        return restTemplate.getForObject(url, MovieResponseDto.class);
    }

    // Find Theaters By Movie
    public ResponseStructure<Object> findingTheatersByMovieName(
            String title, String language, LocalDate showdate) {
        String url = "http://localhost:8080/buyticket?title="+ title+ "&language=" + language+ "&showdate=" + showdate;
        Object obj = restTemplate.getForObject(url, Object.class);

        ResponseStructure<Object> responseStructure =new ResponseStructure<>();
        responseStructure.setStatuscode(HttpStatus.OK.value());
        responseStructure.setMessage("Theaters Found");
        responseStructure.setData(obj);

        return responseStructure;
    }

    // Get Available Seats
    public ResponseStructure<List<SeatResponseDto>> getAvailableSeats(long id) {
        String url = "http://localhost:8080/selectseats?id=" + id;

ResponseEntity<ResponseStructure<List<SeatResponseDto>>> response =restTemplate.exchange(url,HttpMethod.GET,null,new ParameterizedTypeReference<ResponseStructure<List<SeatResponseDto>>>() {});
        return response.getBody();
    }

    // Book Seats
    public ResponseStructure<List<SeatResponseDto>> bookseats(BookSeatsRequestDto bookSeatsRequestDto) {
        String url = "http://localhost:8080/bookseat";
        HttpEntity<BookSeatsRequestDto> entity =new HttpEntity<>(bookSeatsRequestDto);
ResponseEntity<ResponseStructure<List<SeatResponseDto>>> response =restTemplate.exchange(url,HttpMethod.POST,entity,new ParameterizedTypeReference<ResponseStructure<List<SeatResponseDto>>>() {});

        return response.getBody(); 
    }
}
