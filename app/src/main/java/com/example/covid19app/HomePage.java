package com.example.covid19app;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.covid19app.businessUI.ContractTracing.ContractTracingModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity
{
    private String username, type, email;
    private ArrayList<Users> usersList = new ArrayList<>();

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    private BusinessDatabase businessDatabase;
    private Business business;
    private Users users;
    private UsersDataBase usersData;
    private HealthDatabase healthDatabase;
    private Health health;
    private Organisation organisation;
    private OrganisationDatabase organisationDatabase;
    private Alert alert;
    private AlertDatabase alertDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        Intent homepage = getIntent();
        email = homepage.getStringExtra("userEmail");
        type = homepage.getStringExtra("userType");
        System.out.println(email);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        checkUserType(type);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);




    }

    private void checkCovid(String email)
    {
        ArrayList<ContactTracingModel> contactTracingModel = new ArrayList<ContactTracingModel>();
        ContactTracingDatabase contactTracingDatabase = new ContactTracingDatabase(this);
        contactTracingModel = contactTracingDatabase.getAllQRContacts();
        ArrayList<Alert> alerts = new ArrayList<Alert>();
        AlertDatabase alertDatabase = new AlertDatabase(this);

        alerts = alertDatabase.getAllAlerts();


        for(ContactTracingModel c : contactTracingModel)
        {
            for(Alert a : alerts)
            {
                if(type.matches("Public"))
                {
                    if(a.getLocation().matches(c.getAddress()))
                    {
                        if(email.matches(c.getEmail()))
                        {
                            alertDialog();
                        }
                    }
                }

            }
        }

    }
    private void alertDialog()
    {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("There is a covid case in facility or area you have visited");
        dialog.setIcon(R.drawable.warn);
        dialog.setTitle("Covid Alert");
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();

    }

    private void hideUserItem()
    {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.AdministratePatient).setVisible(false);
        nav_Menu.findItem(R.id.PatientRecords).setVisible(false);
        nav_Menu.findItem(R.id.OrganisationInfo).setVisible(false);
        nav_Menu.findItem(R.id.OrganisationHistory).setVisible(false);
        nav_Menu.findItem(R.id.Site_Info).setVisible(false);
        nav_Menu.findItem(R.id.customer_history).setVisible(false);
        nav_Menu.findItem(R.id.QRMaker).setVisible(false);
        nav_Menu.findItem(R.id.VaccineRollout).setVisible(false);


    }

    private void hideBusinessItem()
    {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.OrganisationInfo).setVisible(false);
        nav_Menu.findItem(R.id.OrganisationHistory).setVisible(false);
        nav_Menu.findItem(R.id.AdministratePatient).setVisible(false);
        nav_Menu.findItem(R.id.PatientRecords).setVisible(false);
        nav_Menu.findItem(R.id.nav_qr).setVisible(false);
        nav_Menu.findItem(R.id.vaccineCert).setVisible(false);
        nav_Menu.findItem(R.id.VaccineRollout).setVisible(false);

    }

    private void hideHealthItem()
    {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.OrganisationInfo).setVisible(false);
        nav_Menu.findItem(R.id.OrganisationHistory).setVisible(false);
        nav_Menu.findItem(R.id.Site_Info).setVisible(false);
        nav_Menu.findItem(R.id.customer_history).setVisible(false);
        nav_Menu.findItem(R.id.QRMaker).setVisible(false);
        nav_Menu.findItem(R.id.VaccineRollout).setVisible(false);

    }

    private void hideOrgItem()
    {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_qr).setVisible(false);
        nav_Menu.findItem(R.id.nav_c).setVisible(false);
        nav_Menu.findItem(R.id.AdministratePatient).setVisible(false);
        nav_Menu.findItem(R.id.Site_Info).setVisible(false);
        nav_Menu.findItem(R.id.customer_history).setVisible(false);
        nav_Menu.findItem(R.id.nav_vac).setVisible(false);
        nav_Menu.findItem(R.id.vaccineCert).setVisible(false);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }



    @Override
    public boolean onSupportNavigateUp()
    {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void checkUserType(String type)
    {
        if (type.matches("Public"))
        {
            usersData = new UsersDataBase(this);
            users = usersData.getAUsers(email);
            username = users.getFirstName() + " " + users.getLastName();

            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home, R.id.nav_qr, R.id.nav_c, R.id.nav_vac,R.id.vaccineCert ,R.id.alerts,R.id.settings)
                    .setDrawerLayout(drawer)
                    .build();
            hideUserItem();
            checkCovid(email);

        }
        if (type.matches("Business"))
        {
            businessDatabase = new BusinessDatabase(this);
            business = businessDatabase.getBusinessStuff(email);
            username = business.getBusiness_name();

            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home, R.id.nav_qr, R.id.nav_c, R.id.nav_vac,R.id.Site_Info, R.id.customer_history,R.id.QRMaker,
                    R.id.alerts,R.id.settings)
                    .setDrawerLayout(drawer)
                    .build();

            hideBusinessItem();


        }

        if (type.matches("Health"))
        {
            healthDatabase = new HealthDatabase(this);
            health = healthDatabase.getAHealth(email);
            username = health.getFirstName() + " " + health.getLastName();

            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home,R.id.AdministratePatient,R.id.PatientRecords,  R.id.nav_vac,R.id.vaccineCert,R.id.alerts,R.id.settings)
                    .setDrawerLayout(drawer)
                    .build();
            hideHealthItem();


        }

        if (type.matches("Organisation"))
        {
            organisationDatabase = new OrganisationDatabase(this);
            organisation = organisationDatabase.getOrganisationStuff(email);
            username = organisation.getOrganisation_name();

            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home,R.id.OrganisationInfo,R.id.OrganisationHistory,R.id.VaccineRollout,R.id.alerts, R.id.settings)
                    .setDrawerLayout(drawer)
                    .build();
            hideOrgItem();


        }
    }

    public Users getUsers()
    {
        return users;
    }

    public String getEmail() {
        return email;
    }

    public Business getBusiness()
    {
        return business ;
    }

    public String getUsername()
    {
        return username;
    }

    public Organisation getOrganisation(){return organisation;}

    public Health getHealth(){return health;}

    public String getUserType() {return type;}
}