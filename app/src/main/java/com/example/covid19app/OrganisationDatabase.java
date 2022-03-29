package com.example.covid19app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class OrganisationDatabase extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "OrganisationDatabase";
    public static final String USERS_TABLE_NAME = "organisation";
    public static final String USERS_COLUMN_ID = "id";
    public static final String USERS_COLUMN_ORGANISATIONNAME = "organisation_name";
    public static final String USERS_COLUMN_EMAIL = "organisation_email";
    public static final String USERS_COLUMN_PASSWORD = "organisation_password";
    public static final String USERS_COLUMN_MOBILE = "organisation_phone";
    public static final String USERS_COLUMN_ADDRESS = "organisation_address";

    public OrganisationDatabase(@Nullable Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + USERS_TABLE_NAME + "("
                + USERS_COLUMN_ID + " INTEGER  PRIMARY KEY," + USERS_COLUMN_ORGANISATIONNAME + " TEXT,"
                + USERS_COLUMN_EMAIL + " TEXT," + USERS_COLUMN_PASSWORD + " TEXT," + USERS_COLUMN_MOBILE + " TEXT,"
                + USERS_COLUMN_ADDRESS + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS business");
        onCreate(db);
    }

    public void addOrganisation(Organisation organisation )
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERS_COLUMN_ID, organisation.getId());
        contentValues.put(USERS_COLUMN_ORGANISATIONNAME, organisation.getOrganisation_name());
        contentValues.put(USERS_COLUMN_EMAIL, organisation.getOrganisation_email());
        contentValues.put(USERS_COLUMN_PASSWORD, organisation.getOrganisation_password());
        contentValues.put(USERS_COLUMN_MOBILE, organisation.getOrganisation_phone());
        contentValues.put(USERS_COLUMN_ADDRESS, organisation.getOrganisation_address());
        database.insert(USERS_TABLE_NAME, null, contentValues);
        database.close();

    }

    public ArrayList<Organisation> getAllOrganisation()
    {
        ArrayList<Organisation> organisationList = new ArrayList<Organisation>();

        String selectQuery = "SELECT * FROM " + USERS_TABLE_NAME;

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery,null);


        while(cursor.moveToNext())
        {
            Organisation organisation = new Organisation();
            organisation.setId(Integer.parseInt(cursor.getString(0)));
            organisation.setOrganisation_name(cursor.getString(1));
            organisation.setOrganisation_email(cursor.getString(2));
            organisation.setOrganisation_password(cursor.getString(3));
            organisation.setOrganisation_phone(cursor.getString(4));
            organisation.setOrganisation_address(cursor.getString(5));
            organisationList.add(organisation);
        }

        return organisationList;
    }

    public boolean checkUser(String email, String password)
    {
        String[] columns = {USERS_COLUMN_ID};
        SQLiteDatabase db = getReadableDatabase();
        String selection = USERS_COLUMN_EMAIL + "=?" + " and " + USERS_COLUMN_PASSWORD + "=?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(USERS_TABLE_NAME, columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return true;
        else
            return false;
    }

    public Organisation getOrganisationLocation(String address)
    {

        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + USERS_TABLE_NAME
                + " WHERE " + USERS_COLUMN_ADDRESS + " = " + address;

        Cursor cursor = database.query(USERS_TABLE_NAME, new String[] { USERS_COLUMN_ID,
                        USERS_COLUMN_ORGANISATIONNAME, USERS_COLUMN_EMAIL,USERS_COLUMN_PASSWORD,
                        USERS_COLUMN_MOBILE,USERS_COLUMN_ADDRESS}, USERS_COLUMN_ADDRESS + "=?",
                new String[] { String.valueOf(address) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Organisation organisation = new Organisation(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),
                cursor.getString(4),cursor.getString(5));

        cursor.close();
        database.close();
        return organisation;
    }

    public  Organisation getOrganisationStuff(String email)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + USERS_TABLE_NAME
                + " WHERE " + USERS_COLUMN_EMAIL + " = " + email;

        Cursor cursor = database.query(USERS_TABLE_NAME, new String[] { USERS_COLUMN_ID,
                        USERS_COLUMN_ORGANISATIONNAME, USERS_COLUMN_EMAIL,USERS_COLUMN_PASSWORD,
                        USERS_COLUMN_MOBILE,USERS_COLUMN_ADDRESS}, USERS_COLUMN_EMAIL + "=?",
                new String[] { String.valueOf(email) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Organisation organisation = new Organisation(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),
                cursor.getString(4),cursor.getString(5));

        cursor.close();
        database.close();
        return organisation;
    }
}
