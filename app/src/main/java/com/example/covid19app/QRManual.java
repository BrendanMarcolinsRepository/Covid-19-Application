package com.example.covid19app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.covid19app.businessUI.ContractTracing.ContactTracing;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class QRManual extends AppCompatActivity
{
    String firstName;
    String lastName;
    String stringEmail;
    String stringAddress;
    String StringMobile;
    private int amountOfContacts = 0;
    private EditText fn,ln,num,email,ad;
    private Button enter,reset;
    private ConstraintLayout qrLayout;
    private ContactTracingModel contractTracingModel;
    private ArrayList<ContactTracingModel> usersArrayList;
    private ContactTracingDatabase contactTracingDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r_manual);



        fn = findViewById(R.id.firstNameQRMan);
        ln = findViewById(R.id.lastNameQRMan);
        num = findViewById(R.id.mobileQRMan);
        email = findViewById(R.id.emailQRMan);
        ad = findViewById(R.id.manualEnteredaddress);
        enter = findViewById(R.id.enterManualQR);
        reset = findViewById(R.id.resetManualQR);
        qrLayout = findViewById(R.id.qrLayout);
        contactTracingDatabase = new ContactTracingDatabase(this);
        usersArrayList = contactTracingDatabase.getAllQRContacts();

        for(int i = 0; i <= usersArrayList.size();i++)
        {
            System.out.println("Contacts size  " + usersArrayList.size());
            if(i == usersArrayList.size() && usersArrayList.size() > 0)
            {
                amountOfContacts = usersArrayList.get(i - 1).getId() + 1;
                System.out.println("Contacts Number:  " + amountOfContacts);
            }
            else
            {
                amountOfContacts = 1;
            }
        }

        enter.setOnClickListener(enteredManualQr);
        reset.setOnClickListener(enteredManualQr);
    }



    private final View.OnClickListener enteredManualQr = new View.OnClickListener()
    {

        @Override
        public void onClick(View v)
        {
            switch(v.getId())
            {
                case R.id.enterManualQR:
                    addManualQRInformation();
                    break;
                case R.id.resetManualQR:
                    clearManualInformation();
            }
        }
    };
    public void addManualQRInformation()
    {
        firstName = fn.getText().toString();
        lastName = ln.getText().toString();
        stringEmail = email.getText().toString();
        stringAddress = ad.getText().toString();
        StringMobile = num.getText().toString();

        if(firstName.isEmpty())
        {
            fn.setError("First name is requried");
            fn.requestFocus();
        }
        else if(lastName.isEmpty())
        {
            ln.setError("Last name is requried");
            ln.requestFocus();
        }
        else if(stringEmail.isEmpty())
        {
            email.setError("Email is requried");
            email.requestFocus();
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(stringEmail).matches())
        {
            email.setError("Email is Required");
            email.requestFocus();
        }
        else
        {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
            String str = formatter.format(date);
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");


            contractTracingModel = new ContactTracingModel(amountOfContacts, firstName,lastName,stringEmail, stringAddress, StringMobile,"10:30", "12:30",str);
            contactTracingDatabase.addUsers(contractTracingModel);
            Snackbar.make(qrLayout,"Contacts Have Been Saved ", Snackbar.LENGTH_LONG).show();
            amountOfContacts++;
        }
    }

    public void clearManualInformation()
    {
        fn.setText("");
        ln.setText("");
        email.setText("");
        ad.setText("");
        num.setText("");
    }
}