package com.example.covid19app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class VacinationDatabase extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "VaccinationDatabase";
    public static final String USERS_TABLE_NAME = "patient";
    public static final String USERS_COLUMN_ID = "id";
    public static final String USERS_COLUMN_NURSENAME = "nurseName";
    public static final String USERS_COLUMN_ORGANISATIONNAME = "organisationName";
    public static final String USERS_COLUMN_FIRSTNAME = "firstName";
    public static final String USERS_COLUMN_LASTNAME = "lastName";
    public static final String USERS_COLUMN_EMAIL = "email";
    public static final String USERS_COLUMN_PATIENTDOB   = "patientDOB";
    public static final String USERS_COLUMN_PATIENTMEDICARE   = "patientMedicare";
    public static final String USERS_COLUMN_VACCINENAME = "vaccineName";
    public static final String USERS_COLUMN_VACCINENUMBER = "vaccineNumber";
    public static final String USERS_COLUMN_VACCINEADMINISTRATED = "vaccineAdministrated";



    public VacinationDatabase(Context context)
    {
        super(context,DATABASE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + USERS_TABLE_NAME + "("
                + USERS_COLUMN_ID + " INTEGER  PRIMARY KEY," + USERS_COLUMN_NURSENAME + " TEXT,"+ USERS_COLUMN_ORGANISATIONNAME + " TEXT," + USERS_COLUMN_FIRSTNAME + " TEXT," + USERS_COLUMN_LASTNAME  + " TEXT," + USERS_COLUMN_EMAIL  + " TEXT,"
                + USERS_COLUMN_PATIENTDOB + " TEXT,"  + USERS_COLUMN_PATIENTMEDICARE + " TEXT," + USERS_COLUMN_VACCINENAME + " TEXT,"
                + USERS_COLUMN_VACCINENUMBER + " TEXT," + USERS_COLUMN_VACCINEADMINISTRATED + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE_NAME);
        onCreate(db);

    }

    public void addVaccinated(VacinatedModel vacinatedModel)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERS_COLUMN_ID, vacinatedModel.getPatientID());
        contentValues.put(USERS_COLUMN_NURSENAME, vacinatedModel.getNurseName());
        contentValues.put(USERS_COLUMN_ORGANISATIONNAME, vacinatedModel.getOrganisationName());
        contentValues.put(USERS_COLUMN_FIRSTNAME, vacinatedModel.getPatientFirstName());
        contentValues.put(USERS_COLUMN_LASTNAME, vacinatedModel.getPatientLastName());
        contentValues.put(USERS_COLUMN_EMAIL, vacinatedModel.getPatientEmail());
        contentValues.put(USERS_COLUMN_PATIENTDOB, vacinatedModel.getPatientDOB());
        contentValues.put(USERS_COLUMN_PATIENTMEDICARE, vacinatedModel.getPatientMedicare());
        contentValues.put(USERS_COLUMN_VACCINENAME, vacinatedModel.getVaccineName());
        contentValues.put(USERS_COLUMN_VACCINENUMBER, vacinatedModel.getVaccineNumber());
        contentValues.put(USERS_COLUMN_VACCINEADMINISTRATED, vacinatedModel.getVaccineAdministrated());
        database.insert(USERS_TABLE_NAME, null, contentValues);
        database.close();

    }

    public ArrayList<VacinatedModel> getAllPatients()
    {
        ArrayList<VacinatedModel> patientList = new ArrayList<VacinatedModel>();

        String selectQuery = "SELECT * FROM " + USERS_TABLE_NAME;

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery,null);


        while(cursor.moveToNext())
        {
            VacinatedModel patient = new VacinatedModel();
            patient.setPatientID(Integer.parseInt(cursor.getString(0)));
            patient.setNurseName(cursor.getString(1));
            patient.setOrganisationName(cursor.getString(2));
            patient.setPatientFirstName(cursor.getString(3));
            patient.setPatientLastName(cursor.getString(4));
            patient.setPatientEmail(cursor.getString(5));
            patient.setPatientDOB(cursor.getString(6));
            patient.setPatientMedicare(cursor.getString(7));
            patient.setVaccineName(cursor.getString(8));
            patient.setVaccineNumber(cursor.getString(9));
            patient.setVaccineAdministrated(cursor.getString(10));
            patientList.add(patient);
        }
        cursor.close();
        database.close();
        return patientList;
    }



    public VacinatedModel getAUsers(String email)
    {
        VacinatedModel patient;
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + USERS_TABLE_NAME
                + " WHERE " + USERS_COLUMN_EMAIL + " = " + email;

        Cursor cursor = database.query(USERS_TABLE_NAME, new String[] { USERS_COLUMN_ID,USERS_COLUMN_NURSENAME,USERS_COLUMN_ORGANISATIONNAME,
                        USERS_COLUMN_FIRSTNAME,USERS_COLUMN_LASTNAME, USERS_COLUMN_EMAIL,USERS_COLUMN_PATIENTDOB
                        ,USERS_COLUMN_VACCINENAME,USERS_COLUMN_VACCINENUMBER,USERS_COLUMN_VACCINEADMINISTRATED}, USERS_COLUMN_EMAIL + "=?",
                new String[] { String.valueOf(email) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();


        patient = new VacinatedModel(Integer.parseInt(cursor.getString(0)), cursor.getString(1),cursor.getString(2), cursor.getString(3),
                cursor.getString(4), cursor.getString(5),
                cursor.getString(6),cursor.getString(7),cursor.getString(8), cursor.getString(9), cursor.getString(10));


        cursor.close();
        database.close();
        return patient;
    }


}
