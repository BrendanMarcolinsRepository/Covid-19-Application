package com.example.covid19app.ui.vaccinecertification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
import com.example.covid19app.R;
import com.example.covid19app.Users;
import com.example.covid19app.Vaccine;
import com.example.covid19app.VaccineDatabase;
import com.example.covid19app.VacinatedModel;
import com.example.covid19app.VacinationDatabase;
import com.example.covid19app.ui.vac.VacInfoModel;

import java.util.ArrayList;

public class vaccineCertification extends Fragment
{

    private vaccineCertificationModel vaccineCertificationModel;
    private Users user;
    private VacinationDatabase vacinationDatabase;
    private VacinatedModel vacinatedModel;
    private VaccineDatabase vaccineDatabase;
    private ArrayList<VacinatedModel> vacinatedModelArrayList;
    private ImageView imageView;
    private Health health;
    private String type;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {

        vaccineCertificationModel =
                new ViewModelProvider(this).get(vaccineCertificationModel.class);
        View root = inflater.inflate(R.layout.vaccine_certification, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);

        HomePage homePage = (HomePage) getActivity();
        user = homePage.getUsers();
        health = homePage.getHealth();
        vacinationDatabase = new VacinationDatabase(homePage);
        vacinatedModelArrayList = vacinationDatabase.getAllPatients();
        imageView = root.findViewById(R.id.vacCertYayorNay);
        type = homePage.getUserType();

        if(type.matches("Public"))
        {
            setStuff();
        }
        else
        {
            setNurseStuff();
        }





        return root;
    }

    public void setStuff()
    {
        System.out.println("no");
        for(VacinatedModel c : vacinatedModelArrayList)
        {
            String s = c.getPatientEmail();
            String l = user.getEmail();
            System.out.println("Patient email " + s);
            System.out.println("Patient email " + l);
            if(l.equals(s))
            {
                System.out.println("worked ");
                imageView.setImageResource(R.drawable.vacyes);
            }
            else
            {
                System.out.println("no work");
            }
        }

    }
    public void setNurseStuff()
    {
        System.out.println("no");
        for(VacinatedModel c : vacinatedModelArrayList)
        {
            String s = c.getPatientEmail();
            String l = health.getEmail();
            System.out.println("Patient email " + s);
            System.out.println("Patient email " + l);
            if(l.equals(s))
            {
                System.out.println("worked ");
                imageView.setImageResource(R.drawable.vacyes);
            }
            else
            {
                System.out.println("no work");
            }
        }

    }
}