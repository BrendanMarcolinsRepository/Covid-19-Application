package com.example.covid19app;


public class ContactTracingModel
{
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String mobile;
    private String arrival;
    private String departure;
    private String date;


    public ContactTracingModel(int id, String firstName, String lastName, String email, String address, String mobile,String arrival,String departure,String date)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.mobile = mobile;
        this.arrival = arrival;
        this.departure = departure;
        this.date = date;
    }

    public ContactTracingModel() { }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("Customer Name: %s %s\nEmail: %s\nMobile %s \nLocation: %s\nArrival: %s   Departure: %s \nDate %s\n\n ",
                getFirstName(), getLastName(),getEmail(),getMobile(),getAddress(),getArrival(),getDeparture(),getDate());
    }
}
