package com.example.covid19app.organisations.rolloutPackage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.covid19app.Health;
import com.example.covid19app.HealthDatabase;
import com.example.covid19app.HomePage;
import com.example.covid19app.Organisation;
import com.example.covid19app.R;
import com.example.covid19app.VacinatedModel;
import com.example.covid19app.VacinationDatabase;
import com.example.covid19app.businessUI.ContractTracing.ContractTracingModel;
import com.example.covid19app.healthPackage.patientRecord.recordsModel;
import com.example.covid19app.organisations.organisationHistory.organisationHistoryModel;

import java.util.ArrayList;

public class rollout extends Fragment
{
    private  ArrayList<String> empty;
    private  ArrayList<String> healthDisplay;
    private ListView listView;
    private Button button;
    private EditText search;
    private View root;
    private HealthDatabase healthDatabase;
    private  ArrayList<Health> healthArrayList;
    private  ArrayList<Health> orgHealthArrayList;
    private Organisation organisation;
    private String patientLooking, type;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayAdapter<String> arrayAdapter1;
    private HomePage homePage;

    private rolloutModel rolloutmodel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        rolloutmodel =
                new ViewModelProvider(this).get(rolloutModel.class);
        View root = inflater.inflate(R.layout.fragment_rollout, container, false);
        homePage = (HomePage) getActivity();
        type = homePage.getUserType();
        healthDatabase = new HealthDatabase(homePage);
        healthArrayList = new ArrayList<Health>();
        healthArrayList = healthDatabase.getAllHealth();
        orgHealthArrayList = new ArrayList<Health>();
        empty = new ArrayList<String>();
        healthDisplay = new ArrayList<String>();
        listView = (ListView) root.findViewById(R.id.nurseRollout);
        boolean done = false;


        for(Health v : healthArrayList)
        {
            organisation = homePage.getOrganisation();
            if(organisation.getOrganisation_address().matches(v.getAddress()))
            {
                orgHealthArrayList.add(v);
            }


        }

        showRollout();




        return root;
    }

    public void showRollout()
    {
        if(orgHealthArrayList.isEmpty())
        {

            empty.add("There are currently no nurses rolling out the vaccine");

            arrayAdapter1 = new ArrayAdapter<String>(homePage,R.layout.customer_history_list,R.id.listCustomerHistory,empty);
            listView.setAdapter(arrayAdapter1);
        }
        else
        {

            for(Health v : orgHealthArrayList)
            {
                organisation = homePage.getOrganisation();
                if(organisation.getOrganisation_address().matches(v.getAddress()))
                {

                    orgHealthArrayList.add(v);
                    healthDisplay.add(String.format("Nurse Name: %s %s \nOrganisation: %s\nAddress %s",v.getFirstName(), v.getLastName(), organisation.getOrganisation_name(), v.getAddress()));
                    organisation = homePage.getOrganisation();

                    arrayAdapter = new ArrayAdapter<>(homePage,R.layout.customer_history_list,R.id.listCustomerHistory,healthDisplay);
                    listView.setAdapter(arrayAdapter);
                }
            }
        }
    }


}