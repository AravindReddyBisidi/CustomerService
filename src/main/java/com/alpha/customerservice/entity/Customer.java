package com.alpha.customerservice.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String mail;

    private String phone;

    private int age;

    private String gender;

    @OneToMany(mappedBy = "customer")
    private List<Ticket> ticket;

    // Default Constructor
    public Customer() {
    }

    // Parameterized Constructor
    public Customer( String name, String mail, String phone,
                    int age, String gender, List<Ticket> ticket) {

       
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.age = age;
        this.gender = gender;
        this.ticket = ticket;
    }

    // Getters and Setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Ticket> getTicket() {
        return ticket;
    }

    public void setTicket(List<Ticket> ticket) {
        this.ticket = ticket;
    }
}