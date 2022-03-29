package com.example.covid19app.healthPackage.adminPatient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.covid19app.Health;
import com.example.covid19app.HomePage;
import com.example.covid19app.R;
import com.example.covid19app.Vaccine;
import com.example.covid19app.VaccineDatabase;
import com.example.covid19app.VacinatedModel;
import com.example.covid19app.VacinationDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class administratePatient extends Fragment
{
    private administratePatientModel administrate;
    private VacinationDatabase vacinationDatabase;
    private Health health;
    private Button regPatientl;
    private EditText fn, ln, dob, email, medi,vac;
    private String stringFn, stringLn, stringDob,stringEmail, stringMedi,stringVac;
    private String kl;
    private int amountOfContacts = 0;
    private View root;
    private HomePage homePage;
    private ArrayList<VacinatedModel> vacinatedModelArrayList;
    private VaccineDatabase vaccineDatabase;
    private ArrayList<Vaccine> vaccineArrayList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {

        administrate =
                new ViewModelProvider(this).get(administratePatientModel.class);
        root = inflater.inflate(R.layout.fragment_administate, container, false);
        homePage = (HomePage) getActivity();
        health = homePage.getHealth();

        fn = root.findViewById(R.id.firstNamePatient1);
        ln = root.findViewById(R.id.lastNamePatient1);
        dob = root.findViewById(R.id.datePatient1);
        email = root.findViewById(R.id.patientEmailPatient1);
        medi = root.findViewById(R.id.medicarePatient1);
        vac = root.findViewById(R.id.vaccinePatient1);
        regPatientl = root.findViewById(R.id.register);
        vacinationDatabase = new VacinationDatabase(homePage);
        vacinatedModelArrayList = vacinationDatabase.getAllPatients();
        vaccineDatabase = new VaccineDatabase(homePage);




        for(int i = 0; i <= vacinatedModelArrayList.size();i++)
        {
            System.out.println("Contacts size  " + vacinatedModelArrayList.size());
            if(i == vacinatedModelArrayList.size() && vacinatedModelArrayList.size() > 0)
            {
                amountOfContacts = vacinatedModelArrayList.get(i - 1).getPatientID() + 1;
                System.out.println("Contacts Number:  " + amountOfContacts);
            }
            else
            {
                amountOfContacts = 1;
            }
        }

        regPatientl.setOnClickListener(regPatient);

        final TextView textView = root.findViewById(R.id.text_slideshow);
        administrate.getText().observe(getViewLifecycleOwner(), new Observer<String>()
        {
            @Override
            public void onChanged(@Nullable String s)
            {
                textView.setText(s);
            }
        });
        return root;
    }

    private View.OnClickListener regPatient = new View.OnClickListener()
    {


        @Override
        public void onClick(View v)
        {
            Vaccine vaccine = new Vaccine(1,"Pfizer","covid","vac");
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
            String str = formatter.format(date);

            stringFn = fn.getText().toString();
            stringLn = ln.getText().toString();
            stringDob = dob.getText().toString();
            stringEmail = email.getText().toString();
            stringMedi = medi.getText().toString();
            stringVac = vac.getText().toString();
            String nurseEmail = health.getEmail();
            System.out.println("Person : " + health.getEmail());
            System.out.println("Contacts Number:  " + amountOfContacts);
            System.out.println(stringFn);


            if(!vaccineDatabase.checkVaccineExist(stringVac))
            {
                vac.setError("Vaccine Number Doesn't Exist");
                vac.requestFocus();
            }
            else
            {
                VacinatedModel vacinatedModel = new VacinatedModel(amountOfContacts,nurseEmail,stringFn, health.getAddress(), stringLn,stringEmail,stringMedi,stringVac,vaccine.getVaccineName(),stringVac, str);
                vacinationDatabase.addVaccinated(vacinatedModel);
                amountOfContacts++;
            }


        }
    };
}
