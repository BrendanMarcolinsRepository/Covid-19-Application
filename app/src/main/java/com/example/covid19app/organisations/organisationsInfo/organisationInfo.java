package com.example.covid19app.organisations.organisationsInfo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.covid19app.Business;
import com.example.covid19app.ContactTracingDatabase;
import com.example.covid19app.ContactTracingModel;
import com.example.covid19app.HomePage;
import com.example.covid19app.Organisation;
import com.example.covid19app.R;
import com.example.covid19app.businessUI.ContractTracing.ContractTracingModel;
import com.example.covid19app.businessUI.siteinfo.siteInfoModel;
import com.example.covid19app.organisations.organisationHistory.organisationHistoryModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.BreakIterator;
import java.util.ArrayList;

public class organisationInfo extends Fragment
{

    private organisationHistoryModel org;

    private ArrayList<ContractTracingModel> contacts;

    private Organisation organisation;
    private ArrayList<ContactTracingModel> customer;
    private ContactTracingDatabase contractTracingDatabase;
    private int customerCount = 0;
    private TextView orgInformation, textView;
    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList barEntries;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        org =
                new ViewModelProvider(this).get(organisationHistoryModel.class);
        View root = inflater.inflate(R.layout.fragment_organisation_info, container, false);

        HomePage homePage = (HomePage) getActivity();

        contractTracingDatabase = new ContactTracingDatabase(homePage);
        organisation = homePage.getOrganisation();
        customer = contractTracingDatabase.getAllQRContacts();
        orgInformation = root.findViewById(R.id.orgCustomerText);
        textView = root.findViewById(R.id.orgInfoGretting);
        textView.setText("Organisation Name: " + organisation.getOrganisation_name());
        barChart = root.findViewById(R.id.BarChartOrg);
        getEnteries();
        barDataSet = new BarDataSet(barEntries, "");
        barData = new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(18f);

        orgInformation.setText("Amount Of People Attending your Facility: " + customerCount);



        return root;
    }

    private void getEnteries()
    {
        for(ContactTracingModel c : customer)
        {
            if(organisation.getOrganisation_address().equals(c.getAddress()))
            {
                customerCount++;
            }
        }

        System.out.println("Amount of customers" + customerCount);
        barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(27f,customerCount));
        barEntries.add(new BarEntry(31f, null));


    }
}
