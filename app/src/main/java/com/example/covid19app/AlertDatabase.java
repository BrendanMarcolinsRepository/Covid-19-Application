package com.example.covid19app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AlertDatabase extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "AlertDatabase";
    public static final String USERS_TABLE_NAME = "alert";
    public static final String USERS_COLUMN_ID = "id";
    public static final String USERS_COLUMN_FIRSTNAME = "firstName";
    public static final String USERS_COLUMN_LASTNAME = "lastName";
    public static final String USERS_COLUMN_EMAIL = "address";
    public static final String USERS_COLUMN_LOCATION = "location";
    public static final String USERS_COLUMN_DATE = "date";

    public AlertDatabase(@Nullable Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + USERS_TABLE_NAME + "("
                + USERS_COLUMN_ID + " INTEGER  PRIMARY KEY," + USERS_COLUMN_FIRSTNAME + " TEXT,"
                + USERS_COLUMN_LASTNAME + " TEXT," + USERS_COLUMN_EMAIL + " TEXT," + USERS_COLUMN_LOCATION + " TEXT," + USERS_COLUMN_DATE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS business");
        onCreate(db);
    }


    public void addBusiness(Alert alert )
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERS_COLUMN_ID, alert.getId());
        contentValues.put(USERS_COLUMN_FIRSTNAME, alert.getFirstName());
        contentValues.put(USERS_COLUMN_LASTNAME, alert.getLastName());
        contentValues.put(USERS_COLUMN_EMAIL, alert.getEmail());
        contentValues.put(USERS_COLUMN_LOCATION, alert.getLocation());
        contentValues.put(USERS_COLUMN_DATE, alert.getDate());
        database.insert(USERS_TABLE_NAME, null, contentValues);
        database.close();

    }

    public ArrayList<Alert> getAllAlerts()
    {
        ArrayList<Alert> alertArrayList = new ArrayList<Alert>();

        String selectQuery = "SELECT * FROM " + USERS_TABLE_NAME;

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery,null);


        while(cursor.moveToNext())
        {
            Alert alert = new Alert();
            alert.setId(Integer.parseInt(cursor.getString(0)));
            alert.setFirstName(cursor.getString(1));
            alert.setLastName(cursor.getString(2));
            alert.setEmail(cursor.getString(3));
            alert.setLocation(cursor.getString(4));
            alert.setDate(cursor.getString(5));
            alertArrayList.add(alert);
        }

        return alertArrayList;
    }


}
