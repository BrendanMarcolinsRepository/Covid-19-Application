package com.example.covid19app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RegisterAccount extends AppCompatActivity
{
    private String firstName, lastName, stringPW,stringEmail, stringAddress, stringMobile;
    private Button button;
    private int amountOfContacts = 0;
    private EditText fN, lN, pw, email, address,mobile;
    private FirebaseAuth mAuth;
    private ConstraintLayout registerLayout;
    private Users users;
    private ArrayList<Users> usersArrayList;
    private  UsersDataBase userDatabase;
    private TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        fN = findViewById(R.id.newFirstName);
        lN = findViewById(R.id.newLastName);
        pw = findViewById(R.id.regPassword);
        email = findViewById(R.id.regEmail);
        address = findViewById(R.id.regAddress);
        mobile = findViewById(R.id.regMobile);
        mAuth = FirebaseAuth.getInstance();
        registerLayout = findViewById(R.id.register);
        userDatabase = new UsersDataBase(this);
        usersArrayList = userDatabase.getAllUsers();
        loginText = findViewById(R.id.loginRegister);

        button = findViewById(R.id.createButton);
        button.setOnClickListener(createAccount);

        for(int i = 0; i <= usersArrayList.size();i++)
        {
            System.out.println("Contacts size  " + usersArrayList.size());
            if(i == usersArrayList.size() && usersArrayList.size() > 0)
            {
                amountOfContacts = usersArrayList.get(i - 1).getIdNumber() + 1;
                System.out.println("Contacts Number:  " + amountOfContacts);
            }
            else
            {
                amountOfContacts = 1;
            }
        }


        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

    }

    private final View.OnClickListener createAccount = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            registerUser();



        }
    };

    private void registerUser()
    {
        firstName = fN.getText().toString();
        lastName = lN.getText().toString();
        stringEmail = email.getText().toString();
        stringPW = pw.getText().toString();
        stringAddress = address.getText().toString();
        stringMobile = mobile.getText().toString();


        if(firstName.isEmpty())
        {
            fN.setError("First name is requried");
            fN.requestFocus();
        }

        if(lastName.isEmpty())
        {
            lN.setError("Last name is requried");
            lN.requestFocus();
        }

        if(stringEmail.isEmpty())
        {
            lN.setError("Email is requried");
            lN.requestFocus();
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(stringEmail).matches())
        {
            email.setError("Email is required");
            email.requestFocus();
        }

        if(stringPW.isEmpty())
        {
            lN.setError("Password is required");
            lN.requestFocus();
        }
        if(stringAddress.isEmpty())
        {
            address.setError("Address is required");
            address.requestFocus();
        }
        if(stringMobile.isEmpty())
        {
            mobile.setError("Address is required");
            mobile.requestFocus();
        }
        else
        {
            System.out.println("Contacts Number:  " + amountOfContacts);
            users = new Users(amountOfContacts,firstName,lastName,stringPW,stringEmail,stringMobile,stringAddress);
            userDatabase.addUsers(users);
            Snackbar.make(registerLayout,"Contact Has Been Saved ", Snackbar.LENGTH_LONG).show();
            amountOfContacts++;
        }
        /*
        mAuth.createUserWithEmailAndPassword(stringEmail,stringPW).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    Users user = new Users(amountOfContacts,firstName,lastName,stringPW,stringEmail,"w","Random");
                    FirebaseDatabase.getInstance().getReference("Users")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if(task.isSuccessful())
                            {
                                Snackbar.make(registerLayout,"Registration Complete", Snackbar.LENGTH_LONG).show();
                                finish();
                            }
                        }
                    });
                }
                else
                {
                    if(task.isCanceled())
                    {
                        Snackbar.make(registerLayout,"Registration Couldn't Be Complete", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });
        */

    }

}