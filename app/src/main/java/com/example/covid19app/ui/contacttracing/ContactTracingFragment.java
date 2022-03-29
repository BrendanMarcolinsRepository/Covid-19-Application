package com.example.covid19app.ui.contacttracing;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.example.covid19app.Alert;
import com.example.covid19app.AlertDatabase;
import com.example.covid19app.ContactTracingDatabase;
import com.example.covid19app.ContactTracingModel;
import com.example.covid19app.HomePage;



import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.covid19app.ListAdapt;
import com.example.covid19app.R;
import com.example.covid19app.Users;
import com.example.covid19app.businessUI.ContractTracing.ContractTracingModel;

import java.util.ArrayList;

public class ContactTracingFragment extends Fragment
{

    private ContactTracingViewModel slideshowViewModel;
    private Users user;
    private ContactTracingModel contacts;
    private ArrayList<String> empty;
    private ArrayList<ContactTracingModel> contactsArrayList;
    private ArrayList<ContactTracingModel> customers;
    private ListView listView;
    private String email;
    private ArrayAdapter<Alert> arrayAdapter;
    private ArrayAdapter<String> arrayAdapter1;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(ContactTracingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_contacttracing, container, false);


        HomePage homePage = (HomePage) getActivity();
        user = homePage.getUsers();
        email = homePage.getEmail();
        contactsArrayList = new ArrayList<ContactTracingModel>();
        customers = new ArrayList<ContactTracingModel>();

        empty = new ArrayList<String>();


        ArrayList<ContactTracingModel> contactTracingModel = new ArrayList<ContactTracingModel>();
        ContactTracingDatabase contactTracingDatabase = new ContactTracingDatabase(homePage);
        contactTracingModel = contactTracingDatabase.getAllQRContacts();
        ArrayList<Alert> alerts = new ArrayList<Alert>();
        AlertDatabase alertDatabase = new AlertDatabase(homePage);

        alerts = alertDatabase.getAllAlerts();


        String[] ListItemsName = new String[0];
        if (alerts.isEmpty())
        {

            ListView listView;
            ListItemsName = new String[]{"No Covid Case Have Been Identified\n Stay Safe"};
            //array for images for the venue
            Integer ImageName[] = {R.drawable.covid};
            ListAdapt listAdapter = new ListAdapt(homePage, ListItemsName, ImageName);
            listView = (ListView) root.findViewById(R.id.contactTracingList);
            listView.setAdapter(listAdapter);

        }
        else
        {
            listView = (ListView) root.findViewById(R.id.contactTracingList);
            arrayAdapter = new ArrayAdapter<>(homePage, R.layout.customer_history_list, R.id.listCustomerHistory, alerts);
            listView.setAdapter(arrayAdapter);
        }



        return root;
    }




}