package com.example.covid19app;

import java.io.Serializable;

public class Organisation implements Serializable
{
    private int id;
    private String organisation_name;
    private String organisation_email;
    private String organisation_password;
    private String organisation_phone;
    private String organisation_address;


    public Organisation(int id, String organisation_name, String organisation_email,String organisation_password, String organisation_phone, String organisation_address)
    {
        this.id = id;
        this.organisation_name = organisation_name;
        this.organisation_email = organisation_email;
        this.organisation_password = organisation_password;
        this.organisation_phone = organisation_phone;
        this.organisation_address = organisation_address;

    }

    public Organisation() { }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrganisation_name() {
        return organisation_name;
    }

    public void setOrganisation_name(String organisation_name) {
        this.organisation_name = organisation_name;
    }

    public String getOrganisation_email() {
        return organisation_email;
    }

    public void setOrganisation_email(String organisation_email) {
        this.organisation_email = organisation_email;
    }

    public String getOrganisation_password() {
        return organisation_password;
    }

    public void setOrganisation_password(String organisation_password) {
        this.organisation_password = organisation_password;
    }

    public String getOrganisation_phone() {
        return organisation_phone;
    }

    public void setOrganisation_phone(String organisation_phone) {
        this.organisation_phone = organisation_phone;
    }

    public String getOrganisation_address() {
        return organisation_address;
    }

    public void setOrganisation_address(String organisation_address) {
        this.organisation_address = organisation_address;
    }
}
