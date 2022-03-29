package com.example.covid19app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompatSideChannelService;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity
{
    private TextView username,password,reset,newAccount, forgotpassword;
    private EditText usernameInput, userpasswordInput;
    private String userString,passWord,typeOfUser,type;
    private HashMap<String,String>loginHash = new HashMap<>();
    private Button login, userButton,businessButton,healthButton,orgnisationButton ;
    private ConstraintLayout main;
    private DatabaseReference databaseReference;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private UsersDataBase usersDataBase;
    private BusinessDatabase businessDatabase;
    private HealthDatabase healthDatabase;
    private DatabaseCreator databaseCreator;
    private OrganisationDatabase organisationDatabase;

    private Business businessImport;
    private Users user;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        databaseCreator = new DatabaseCreator();
        if(!doesDatabaseExist(this,"HealthDatabase") && !doesDatabaseExist(this,"UsersDataBase")
                &&!doesDatabaseExist(this,"OrganisationDatabase") && !doesDatabaseExist(this,"BusinessDatabase") && !doesDatabaseExist(this,"AlertDatabase")
        && !doesDatabaseExist(this,"VaccineDatabase"))
        {
            healthDatabase = databaseCreator.createThatHealthDatabase(this);
            businessDatabase = databaseCreator.createThatBusinessDatabase(this);
            usersDataBase = databaseCreator.createThatUserDatabase(this);
            organisationDatabase = databaseCreator.createThatOrgDatabase(this);
            databaseCreator.createThatAlertDatabase(this);
            databaseCreator.createThoseVaccine(this);
        }
        else
        {
            healthDatabase = new HealthDatabase(this);
            businessDatabase = new BusinessDatabase(this);
            usersDataBase = new UsersDataBase(this);
            organisationDatabase = new OrganisationDatabase(this);
        }

        username = findViewById(R.id.editTextTextPersonName);

        usernameInput = findViewById(R.id.editTextTextPersonName);
        userpasswordInput = findViewById(R.id.editTextTextPassword);
        radioGroup = findViewById(R.id.userTypeGroup);
        login = findViewById(R.id.loginButton);
        login.setOnClickListener(loginAccount);
        reset = findViewById(R.id.reset);
        main = findViewById(R.id.mainScreen);
        newAccount = findViewById(R.id.newAccount);
        newAccount.setOnClickListener(newaccount);
        reset.setOnClickListener(resetInputs);
        forgotpassword = findViewById(R.id.forgotPassword);

        user = new Users();

        getAndroidVersion();

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ForgotPassword.class));
            }
        });


    }


    private final View.OnClickListener loginAccount = new View.OnClickListener()
    {

        public void onClick(View v)
        {
            userString = username.getText().toString();
            passWord = userpasswordInput.getText().toString();
            int selectedID = radioGroup.getCheckedRadioButtonId();

            if (selectedID <= 0)
            {
                Snackbar.make(main, "Please Select A User Type ", Snackbar.LENGTH_LONG).show();
            }
            else
            {

                radioButton = findViewById(selectedID);
                if (userString.isEmpty())
                {
                    username.setError("Please enter a valid email");
                    username.requestFocus();
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(userString).matches())
                {
                    username.setError("Please enter a valid email");
                    username.requestFocus();
                }

                if (passWord.isEmpty())
                {
                    userpasswordInput.setError("Please enter a valid email");
                    userpasswordInput.requestFocus();
                }
                else
                    {
                    if (radioButton.getText().equals("Public"))
                    {
                        if (usersDataBase.checkUser(userString, passWord))
                        {
                            Intent loggedIn = new Intent(MainActivity.this, HomePage.class);
                            loggedIn.putExtra("userEmail", userString);
                            loggedIn.putExtra("userType", radioButton.getText());
                            startActivity(loggedIn);
                        }
                        else
                            {
                            Snackbar.make(main, "Public User Doesnt Exist ", Snackbar.LENGTH_LONG).show();
                        }
                    }
                    if (radioButton.getText().equals("Business"))
                    {
                        if (businessDatabase.checkUser(userString, passWord))
                        {
                            Intent loggedIn = new Intent(MainActivity.this, HomePage.class);
                            loggedIn.putExtra("userEmail", userString);
                            loggedIn.putExtra("userType", radioButton.getText());
                            System.out.println("worked");
                            startActivity(loggedIn);
                        }
                        else
                            {
                            Snackbar.make(main, "Business User Doesnt Exist ", Snackbar.LENGTH_LONG).show();
                        }
                    }
                    if (radioButton.getText().equals("Health"))
                    {
                        if (healthDatabase.checkHealth(userString, passWord))
                        {
                            Intent loggedIn = new Intent(MainActivity.this, HomePage.class);
                            loggedIn.putExtra("userEmail", userString);
                            loggedIn.putExtra("userType", radioButton.getText());
                            startActivity(loggedIn);
                        }
                        else
                            {
                            Snackbar.make(main, "Health User Doesnt Exist ", Snackbar.LENGTH_LONG).show();
                        }
                    }
                    if (radioButton.getText().equals("Organisation"))
                    {
                        if (organisationDatabase.checkUser(userString, passWord))
                        {
                            Intent loggedIn = new Intent(MainActivity.this, HomePage.class);
                            loggedIn.putExtra("userEmail", userString);
                            loggedIn.putExtra("userType", radioButton.getText());
                            startActivity(loggedIn);
                        }
                        else
                            {
                            Snackbar.make(main, "Organisation User Doesnt Exist ", Snackbar.LENGTH_LONG).show();
                        }
                    }

                }
            }
        }
            /*
            mAuth.signInWithEmailAndPassword(userString,passWord).addOnCompleteListener(new OnCompleteListener<AuthResult>()
            {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if(task.isSuccessful())
                    {


                        databaseReference.orderByChild("email").equalTo(userString).addListenerForSingleValueEvent(new ValueEventListener()
                        {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot)
                            {

                                for(DataSnapshot data : snapshot.getChildren())
                                {
                                    String type = data.child("id").getValue().toString();

                                    if(type.matches("Public User"))
                                    {

                                        Intent login = new Intent(MainActivity.this,HomePage.class);
                                        login.putExtra("email", userString);
                                        login.putExtra("type", type);
                                        startActivity(login);
                                    }

                                    if(type.matches("Business User"))
                                    {

                                        Intent login = new Intent(MainActivity.this,HomePage.class);
                                        login.putExtra("email", userString);
                                        login.putExtra("type", type);
                                        startActivity(login);
                                    }

                                }


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error)
                            {

                            }
                        });

                    }
                    else
                    {
                        Snackbar.make(main,"Login Failed", Snackbar.LENGTH_LONG).show();
                    }
                }
            });*/


    };
    private final View.OnClickListener newaccount = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            Intent register = new Intent(MainActivity.this,RegisterAccount.class);
            startActivity(register);
        }
    };

    private View.OnClickListener resetInputs = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
             username.setText("");
             userpasswordInput.setText("");
        }
    };

    public void  getAndroidVersion() {
        String release = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;
        System.out.println("Android SDK: " + sdkVersion + " (" + release +")");
    }

    private boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }





}