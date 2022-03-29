package com.example.covid19app;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseCreator
{


    public UsersDataBase createThatUserDatabase(Context context)
    {
        UsersDataBase usersDataBase = new UsersDataBase(context);
        String data = "";
        StringBuffer stringBuffer = new StringBuffer();
        InputStream is = context.getResources().openRawResource(R.raw.users);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));


        try
        {
            //businessImport = importBusinessData();
            //businessDatabase = new BusinessDatabase(this);
            //businessDatabase.addBusiness(businessImport);

            while((data = reader.readLine()) != null)
            {
                stringBuffer.append(data + "n");
                String[] strParts = data.split( "," );

                Users users = new Users(Integer.parseInt(strParts[0]), strParts[1], strParts[2], strParts[3], strParts[4], strParts[5],strParts[6]);
                usersDataBase.addUsers(users);
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return usersDataBase;
    }

    public OrganisationDatabase createThatOrgDatabase(Context context)
    {
        OrganisationDatabase organisationDatabase = new OrganisationDatabase(context);
        String data = "";
        StringBuffer stringBuffer = new StringBuffer();
        InputStream is = context.getResources().openRawResource(R.raw.organisation);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));


        try
        {
            //businessImport = importBusinessData();
            //businessDatabase = new BusinessDatabase(this);
            //businessDatabase.addBusiness(businessImport);

            while((data = reader.readLine()) != null)
            {
                stringBuffer.append(data + "n");
                String[] strParts = data.split( "," );

                Organisation organisation = new Organisation(Integer.parseInt(strParts[0]), strParts[1], strParts[2], strParts[3], strParts[4], strParts[5]);
                organisationDatabase.addOrganisation(organisation);
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return organisationDatabase;
    }


    public HealthDatabase createThatHealthDatabase(Context context)
    {
        HealthDatabase healthDatabase = new HealthDatabase(context);
        String data = "";
        StringBuffer stringBuffer = new StringBuffer();
        InputStream is = context.getResources().openRawResource(R.raw.health);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));


        try
        {
            //businessImport = importBusinessData();
            //businessDatabase = new BusinessDatabase(this);
            //businessDatabase.addBusiness(businessImport);

            while((data = reader.readLine()) != null)
            {
                stringBuffer.append(data + "n");
                String[] strParts = data.split( "," );
                System.out.println(strParts[0]+ strParts[1]+ strParts[2]+strParts[3]+ strParts[4]+ strParts[5]+strParts[6]);
                Health health = new Health(Integer.parseInt(strParts[0]), strParts[1], strParts[2], strParts[3], strParts[4], strParts[5],strParts[6]);
                healthDatabase.addHealths(health);
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return healthDatabase;
    }

    public BusinessDatabase createThatBusinessDatabase(Context context)
    {
        BusinessDatabase businessDatabase = new BusinessDatabase(context);
        String data = "";
        StringBuffer stringBuffer = new StringBuffer();
        InputStream is = context.getResources().openRawResource(R.raw.business);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));


        try
        {
            //businessImport = importBusinessData();
            //businessDatabase = new BusinessDatabase(this);
            //businessDatabase.addBusiness(businessImport);

            while((data = reader.readLine()) != null)
            {
                stringBuffer.append(data + "n");
                String[] strParts = data.split( "," );

                Business businessImport = new Business(Integer.parseInt(strParts[0]), strParts[1], strParts[2], strParts[3], strParts[4], strParts[5]);
                businessDatabase.addBusiness(businessImport);
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return businessDatabase;
    }

    public AlertDatabase createThatAlertDatabase(Context context)
    {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String str = formatter.format(date);
        AlertDatabase alertDatabase = new AlertDatabase(context);
        Alert alert = new Alert(1,"Brendan", "Marcolin","marcolinb01@gmail.com","123 Campbelltown Hospital Street", str);
        alertDatabase.addBusiness(alert);
        return alertDatabase;
    }

    public VaccineDatabase createThoseVaccine(Context context)
    {
        VaccineDatabase vaccineDatabase = new VaccineDatabase(context);
        Vaccine vaccine = new Vaccine(1,"Phizer", "Covid19","123");
        vaccineDatabase.addVaccinated(vaccine);
        return vaccineDatabase;
    }

}
