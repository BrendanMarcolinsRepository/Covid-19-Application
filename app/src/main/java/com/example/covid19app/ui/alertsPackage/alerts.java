package com.example.covid19app.ui.alertsPackage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.covid19app.Alert;
import com.example.covid19app.AlertDatabase;
import com.example.covid19app.Business;
import com.example.covid19app.ContactTracingDatabase;
import com.example.covid19app.ContactTracingModel;
import com.example.covid19app.HomePage;
import com.example.covid19app.ListAdapt;
import com.example.covid19app.Organisation;
import com.example.covid19app.R;
import com.example.covid19app.Users;
import com.example.covid19app.ui.contacttracing.ContactTracingViewModel;

import java.util.ArrayList;

public class alerts  extends Fragment
{
    private alertsModel alertsmodel;
    private Users user;
    private ContactTracingViewModel slideshowViewModel;
    private ContactTracingModel contacts;
    private ArrayList<String> empty;
    private ArrayList<ContactTracingModel> contactsArrayList;
    private ArrayList<ContactTracingModel> customers;
    private ListView listView;
    private ArrayAdapter<ContactTracingModel> arrayAdapter;
    private ArrayAdapter<String> arrayAdapter1;
    private String email, type;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        alertsmodel =
                new ViewModelProvider(this).get(alertsModel.class);
        View root = inflater.inflate(R.layout.fragment_alerts, container, false);

        HomePage homePage = (HomePage) getActivity();
        user = homePage.getUsers();
        email = homePage.getEmail();
        type = homePage.getUserType();
        customers = new ArrayList<ContactTracingModel>();
        empty = new ArrayList<String>();

        ArrayList<ContactTracingModel> contactTracingModel = new ArrayList<ContactTracingModel>();
        ContactTracingDatabase contactTracingDatabase = new ContactTracingDatabase(homePage);
        contactTracingModel = contactTracingDatabase.getAllQRContacts();
        ArrayList<Alert> alerts = new ArrayList<Alert>();
        AlertDatabase alertDatabase = new AlertDatabase(homePage);

        alerts = alertDatabase.getAllAlerts();


        for(ContactTracingModel c : contactTracingModel)
        {
            for(Alert a : alerts)
            {

                if(a.getLocation().matches(c.getAddress()))
                {

                    if(email.matches(c.getEmail()))
                    {
                        customers.add(c);
                    }
                }
            }
        }

        if(type.matches("Business"))
        {
            Business business = homePage.getBusiness();
            for(ContactTracingModel c : contactTracingModel)
            {
                for(Alert a : alerts)
                {
                    if(a.getEmail().matches(c.getEmail()))
                    {
                        if(business.getBusiness_address().matches(c.getAddress()))
                        {
                            customers.add(c);
                        }
                    }
                }
            }
        }

        if(type.matches("Organisation"))
        {
            Organisation organisation = homePage.getOrganisation();
            for(ContactTracingModel c : contactTracingModel)
            {
                for(Alert a : alerts)
                {
                    if(a.getEmail().matches(c.getEmail()))
                    {
                        if(organisation.getOrganisation_address().matches(c.getAddress()))
                        {
                            customers.add(c);
                        }
                    }
                }
            }
        }

        ListView listView;

        String[] ListItemsName = new String[0];
        if (customers.isEmpty())
        {

            ListItemsName = new String[]{"No Covid Case Have Been Identified\n Stay Safe"};
            //array for images for the venue
            Integer ImageName[] = {R.drawable.covid};
            ListAdapt listAdapter = new ListAdapt(homePage, ListItemsName, ImageName);
            listView = (ListView) root.findViewById(R.id.alertView);
            listView.setAdapter(listAdapter);

        }
        else
        {
            listView = (ListView) root.findViewById(R.id.alertView);
            arrayAdapter = new ArrayAdapter<>(homePage, R.layout.customer_history_list, R.id.listCustomerHistory, customers);
            listView.setAdapter(arrayAdapter);
        }

        return root;
    }
}
