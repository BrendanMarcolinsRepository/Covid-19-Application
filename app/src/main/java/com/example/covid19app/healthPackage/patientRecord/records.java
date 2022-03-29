package com.example.covid19app.healthPackage.patientRecord;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.covid19app.ContactTracingModel;
import com.example.covid19app.Health;
import com.example.covid19app.HomePage;
import com.example.covid19app.Organisation;
import com.example.covid19app.R;
import com.example.covid19app.Users;
import com.example.covid19app.VaccineDatabase;
import com.example.covid19app.VacinatedModel;
import com.example.covid19app.VacinationDatabase;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class records extends Fragment
{
    private recordsModel recordsmodel;
    private VacinationDatabase vaccineDatabase;
    private ArrayList<VacinatedModel> vacinatedModelArrayList;
    private ArrayList<VacinatedModel> searchvacinatedModelArrayList;
    private  ArrayList<String> empty;
    private ListView listView;
    private Button button;
    private EditText search;
    private View root;
    private Health health;
    private Organisation organisation;
    private String patientLooking, type;
    private ArrayAdapter<VacinatedModel> arrayAdapter;
    private ArrayAdapter<String> arrayAdapter1;
    private HomePage homePage;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        recordsmodel =
                new ViewModelProvider(this).get(recordsModel.class);
        root = inflater.inflate(R.layout.fragment_health_record, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        button = root.findViewById(R.id.patientSeachButton);
        search = root.findViewById(R.id.patientSeach);
        button.setOnClickListener(searchPatient);


        homePage = (HomePage) getActivity();
        type = homePage.getUserType();
        vaccineDatabase = new VacinationDatabase(homePage);
        vacinatedModelArrayList = new ArrayList<VacinatedModel>();
        vacinatedModelArrayList = vaccineDatabase.getAllPatients();
        empty = new ArrayList<String>();

        if(vacinatedModelArrayList.isEmpty())
        {

            empty.add("No Patients Have Received Vaccination");
            listView = (ListView) root.findViewById(R.id.patientListview);
            arrayAdapter1 = new ArrayAdapter<String>(homePage,R.layout.customer_history_list,R.id.listCustomerHistory,empty);
            listView.setAdapter(arrayAdapter1);
        }
        else
        {

            for(VacinatedModel v : vacinatedModelArrayList)
            {


                if(type.matches("Health"))
                {
                    health = homePage.getHealth();
                    System.out.println(health.getEmail());
                    System.out.println(v.getNurseName());
                    if(health.getEmail().matches(v.getNurseName()))
                    {
                        listView = (ListView) root.findViewById(R.id.patientListview);
                        arrayAdapter = new ArrayAdapter<>(homePage,R.layout.customer_history_list,R.id.listCustomerHistory,vacinatedModelArrayList);
                        listView.setAdapter(arrayAdapter);
                    }
                }
                else
                {
                    organisation = homePage.getOrganisation();
                    System.out.println(v.getOrganisationName());
                    System.out.println(organisation.getOrganisation_email());
                    if(organisation.getOrganisation_address().matches(v.getOrganisationName()))
                    {
                        organisation = homePage.getOrganisation();
                        listView = (ListView) root.findViewById(R.id.patientListview);
                        arrayAdapter = new ArrayAdapter<>(homePage,R.layout.customer_history_list,R.id.listCustomerHistory,vacinatedModelArrayList);
                        listView.setAdapter(arrayAdapter);
                    }

                }


            }
        }

        recordsmodel.getText().observe(getViewLifecycleOwner(), new Observer<String>()
        {
            @Override
            public void onChanged(@Nullable String s)
            {
                textView.setText(s);
            }
        });
        return root;
    }

    private View.OnClickListener searchPatient = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {

            searchvacinatedModelArrayList = new ArrayList<VacinatedModel>();
            patientLooking = search.getText().toString();

            if(patientLooking.isEmpty())
            {
                search.setError("Please Enter A Name");
                search.requestFocus();
            }

            for(VacinatedModel c : vacinatedModelArrayList)
            {
                if(health.getEmail().matches(c.getNurseName()))
                {
                    searchvacinatedModelArrayList.add(c);
                }
            }

            if(!searchvacinatedModelArrayList.isEmpty())
            {

                arrayAdapter = new ArrayAdapter<>(homePage,R.layout.customer_history_list,R.id.listCustomerHistory,searchvacinatedModelArrayList);
                listView.setAdapter(arrayAdapter);
            }
            else
            {
                search.setError("Name Does Not Exist In Database");
                search.requestFocus();
            }

        }
    };
}
