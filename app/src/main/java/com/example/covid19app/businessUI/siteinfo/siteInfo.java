package com.example.covid19app.businessUI.siteinfo;

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
import com.example.covid19app.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class siteInfo extends Fragment
{

    private siteInfoModel siteInfoModel;
    private Business business;
    private ArrayList<ContactTracingModel> customer;
    private ContactTracingDatabase contractTracingDatabase;
    private int customerCount = 0;
    private TextView customers;
    private TextView businessInformation;
    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList barEntries;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        siteInfoModel =
                new ViewModelProvider(this).get(siteInfoModel.class);
        View root = inflater.inflate(R.layout.fragment_site_info, container, false);
        final TextView textView = root.findViewById(R.id.siteInfoGrettingBus);
        customers = root.findViewById(R.id.businessCustomersText);


        HomePage homePage = (HomePage) getActivity();

        contractTracingDatabase = new ContactTracingDatabase(homePage);
        business = homePage.getBusiness();
        customer = contractTracingDatabase.getAllQRContacts();
        textView.setText("Business Name: " + business.getBusiness_name());

        barChart = root.findViewById(R.id.BarChartBusiness);
        getEnteries();
        barDataSet = new BarDataSet(barEntries, "");
        barData = new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(18f);

        customers.setText("Amount Of Customers This Month: " + customerCount);


        siteInfoModel.getText().observe(getViewLifecycleOwner(), new Observer<String>()
        {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    private void getEnteries()
    {
        for(ContactTracingModel c : customer)
        {
            System.out.println("business address"+ business.getBusiness_address() );
            System.out.println("business address"+ c.getAddress() );
            if(business.getBusiness_address().equals(c.getAddress()))
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