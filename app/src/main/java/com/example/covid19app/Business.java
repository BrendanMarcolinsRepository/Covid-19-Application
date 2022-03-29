package com.example.covid19app;

import java.io.Serializable;

public class Business implements Serializable
{
    private int id;
    private String business_name;
    private String business_email;
    private String business_password;
    private String business_phone;
    private String business_address;


    public Business(int id, String business_name, String business_email,String business_password, String business_phone, String business_address)
    {
        this.id = id;
        this.business_name = business_name;
        this.business_email = business_email;
        this.business_password = business_password;
        this.business_phone = business_phone;
        this.business_address = business_address;

    }

    public Business() { }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getBusiness_email() {
        return business_email;
    }

    public void setBusiness_email(String business_email) {
        this.business_email = business_email;
    }

    public String getBusiness_phone() {
        return business_phone;
    }

    public void setBusiness_phone(String business_phone) {
        this.business_phone = business_phone;
    }

    public String getBusiness_address() {
        return business_address;
    }

    public void setBusiness_address(String business_address) {
        this.business_address = business_address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBusiness_password() { return business_password; }

    public void setBusiness_password(String business_password) { this.business_password = business_password; }
}


