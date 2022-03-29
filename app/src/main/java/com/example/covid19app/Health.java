package com.example.covid19app;

public class Health
{
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String address;
    private String mobile;
    private int id;


    Health()
    {

    }

    public Health(int id, String fN, String lN, String pW, String email, String mobile, String address)
    {
        this.firstName = fN;
        this.lastName = lN;
        this.password = pW;
        this.email = email;
        this.address = address;
        this.mobile = mobile;
        this.id = id;
    }

    public int getIdNumber(){return id;}

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public void setId(int id) { this.id = id; }

    public void setEmail(String email) { this.email = email; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail() { return email; }

    public String getFullName()
    {
        return String.format("%s %s", firstName, lastName);
    }
}
