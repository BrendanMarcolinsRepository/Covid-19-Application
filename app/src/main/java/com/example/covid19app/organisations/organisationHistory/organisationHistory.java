package com.example.covid19app.organisations.organisationHistory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.covid19app.Business;
import com.example.covid19app.BusinessDatabase;
import com.example.covid19app.ContactTracingDatabase;
import com.example.covid19app.ContactTracingModel;
import com.example.covid19app.HomePage;
import com.example.covid19app.Organisation;
import com.example.covid19app.R;
import com.example.covid19app.businessUI.ContractTracing.ContactTracing;
import com.example.covid19app.businessUI.ContractTracing.ContractTracingModel;
import com.example.covid19app.businessUI.customerHistory.customerHistoryModel;
import com.example.covid19app.ui.contacttracing.ContactTracingViewModel;

import java.util.ArrayList;

public class organisationHistory extends Fragment
{

    private organisationHistoryModel organisationHistoryModel;
    private customerHistoryModel _customerHistoryModel;
    private Organisation organisation;
    private ContactTracingDatabase contractTracingDatabase;
    private ArrayList<ContactTracingModel> contactsArrayList;
    private ArrayList<ContactTracingModel> customers;
    private ArrayList<String> empty;
    private ListView listView;
    private ArrayAdapter<ContactTracingModel> arrayAdapter;
    private ArrayAdapter<String> arrayAdapter1;
    private HomePage homePage;
    private View root;

    private ArrayList<ContractTracingModel> contacts;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {

        homePage = (HomePage) getActivity();
        root = inflater.inflate(R.layout.fragment_organisation_history, container, false);
        contractTracingDatabase = new ContactTracingDatabase(homePage);

        organisation = homePage.getOrganisation();
        customers = new ArrayList<ContactTracingModel>();
        contactsArrayList = contractTracingDatabase.getAllQRContacts();
        empty = new ArrayList<String>();

        for(ContactTracingModel c : contactsArrayList)
        {
            if(organisation.getOrganisation_address().matches(c.getAddress()))
            {
                customers.add(c);
                System.out.println(c.getAddress());
            }
            else
            {
                System.out.println("NO");
            }
        }

        if(customers.isEmpty())
        {

            empty.add("No Customer Have Attended The Venue");
            listView = (ListView) root.findViewById(R.id.org_historyID);
            arrayAdapter1 = new ArrayAdapter<String>(homePage,R.layout.customer_history_list,R.id.listCustomerHistory,empty);
            listView.setAdapter(arrayAdapter1);
        }
        else
        {
            listView = (ListView) root.findViewById(R.id.org_historyID);
            arrayAdapter = new ArrayAdapter<>(homePage,R.layout.customer_history_list,R.id.listCustomerHistory,customers);
            listView.setAdapter(arrayAdapter);
        }






        return root;
    }




}