package com.example.covid19app.businessUI.customerHistory;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.covid19app.Business;
import com.example.covid19app.BusinessDatabase;
import com.example.covid19app.ContactTracingDatabase;
import com.example.covid19app.ContactTracingModel;
import com.example.covid19app.HomePage;
import com.example.covid19app.R;
import com.example.covid19app.businessUI.ContractTracing.ContactTracing;
import com.example.covid19app.businessUI.ContractTracing.ContractTracingModel;

import java.util.ArrayList;

public class customerHistory extends Fragment {

    private customerHistoryModel _customerHistoryModel;
    private Business business;
    private ContactTracing contactTracing;
    private BusinessDatabase businessDatabase;
    private ContactTracingDatabase contractTracingDatabase;
    private ArrayList<ContactTracingModel> contactsArrayList;
    private ArrayList<ContactTracingModel> customers;
    private ArrayList<String> empty;
    private ListView listView;
    private ArrayAdapter<ContactTracingModel> arrayAdapter;
    private ArrayAdapter<String> arrayAdapter1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        _customerHistoryModel =
                new ViewModelProvider(this).get(customerHistoryModel.class);
        View root = inflater.inflate(R.layout.fragment_customer_history, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        HomePage homePage = (HomePage) getActivity();

        contractTracingDatabase = new ContactTracingDatabase(homePage);

        business = homePage.getBusiness();
        customers = new ArrayList<ContactTracingModel>();
        contactsArrayList = contractTracingDatabase.getAllQRContacts();
        empty = new ArrayList<String>();

        for(ContactTracingModel c : contactsArrayList)
        {
            if(business.getBusiness_address().matches(c.getAddress()))
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
            listView = (ListView) root.findViewById(R.id.customer_historyID);
            arrayAdapter1 = new ArrayAdapter<String>(homePage,R.layout.customer_history_list,R.id.listCustomerHistory,empty);
            listView.setAdapter(arrayAdapter1);
        }
        else
        {
            listView = (ListView) root.findViewById(R.id.customer_historyID);
            arrayAdapter = new ArrayAdapter<>(homePage,R.layout.customer_history_list,R.id.listCustomerHistory,customers);
            listView.setAdapter(arrayAdapter);
        }



        _customerHistoryModel.getText().observe(getViewLifecycleOwner(), new Observer<String>()
        {
            @Override
            public void onChanged(@Nullable String s)
            {
                textView.setText("Customer History" );
            }
        });
        return root;
    }
}