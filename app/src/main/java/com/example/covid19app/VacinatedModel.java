package com.example.covid19app;

public class VacinatedModel
{
    private int patientID;
    private String nurseName;
    private String patientFirstName;
    private String organisationName;
    private String patientLastName;
    private String patientEmail;
    private String patientDOB;
    private String patientMedicare;
    private String vaccineName;
    private String vaccineNumber;
    private String vaccineAdministrated;

    public VacinatedModel(){}

    public VacinatedModel(int patientID, String nurseName, String patientFirstName, String organisationName, String patientLastName, String patientEmail, String patientDOB, String patientMedicare, String vaccineName,
                          String vaccineNumber, String vaccineAdministrated)
    {
        this.patientID = patientID;
        this.nurseName = nurseName;
        this.patientFirstName = patientFirstName;
        this.organisationName = organisationName;
        this.patientLastName = patientLastName;
        this.patientEmail = patientEmail;
        this.patientDOB = patientDOB;
        this.patientMedicare = patientMedicare;
        this.vaccineName = vaccineName;
        this.vaccineNumber = vaccineNumber;
        this.vaccineAdministrated = vaccineAdministrated;
    }


    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientDOB() {
        return patientDOB;
    }

    public void setPatientDOB(String patientDOB) {
        this.patientDOB = patientDOB;
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

    public String getVaccineAdministrated() {
        return vaccineAdministrated;
    }

    public void setVaccineAdministrated(String vaccineAdministrated) {
        this.vaccineAdministrated = vaccineAdministrated;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getPatientMedicare() {
        return patientMedicare;
    }

    public void setPatientMedicare(String patientMedicare) {
        this.patientMedicare = patientMedicare;
    }

    @Override
    public String toString() {
        return String.format("Patient Name: %s %s\nDate of Birth: %s \nEmail %s\nVaccine Name %s\nVaccine Serial %s \nVaccine Administrated %s",
                getPatientFirstName(),getPatientLastName(),getPatientDOB(),getPatientEmail(),getVaccineName(),getVaccineNumber(),getVaccineAdministrated());
    }

    public String getNurseName() {
        return nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }
}
