package com.example.covid19app;

public class Vaccine
{
    private int ID;
    private String brand;
    private String vaccineName;
    private String vaccineNumber;

    public Vaccine(int id, String brand, String vaccineName, String vaccineNumber)
    {
        ID = id;
        this.brand = brand;
        this.vaccineName = vaccineName;
        this.vaccineNumber = vaccineNumber;

    }

    public Vaccine() { }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getVaccineNumber() {
        return vaccineNumber;
    }

    public void setVaccineNumber(String vaccineNumber) {
        this.vaccineNumber = vaccineNumber;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
