package com.example.covid19app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class HealthDatabase extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "HealthDatabase";
    public static final String USERS_TABLE_NAME = "health";
    public static final String USERS_COLUMN_ID = "id";
    public static final String USERS_COLUMN_FIRSTNAME = "firstName";
    public static final String USERS_COLUMN_LASTNAME = "lastName";
    public static final String USERS_COLUMN_PASSWORD = "password";
    public static final String USERS_COLUMN_EMAIL = "email";
    public static final String USERS_COLUMN_MOBILE = "mobile";
    public static final String USERS_COLUMN_ADDRESS = "address";

    public HealthDatabase(Context context)
    {
        super(context,DATABASE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + USERS_TABLE_NAME + "("
                + USERS_COLUMN_ID + " INTEGER  PRIMARY KEY," + USERS_COLUMN_FIRSTNAME + " TEXT," + USERS_COLUMN_LASTNAME  + " TEXT,"
                + USERS_COLUMN_PASSWORD + " TEXT," + USERS_COLUMN_EMAIL + " TEXT," + USERS_COLUMN_MOBILE + " TEXT,"
                + USERS_COLUMN_ADDRESS + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE_NAME);
        onCreate(db);

    }

    public void addHealths(Health health)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERS_COLUMN_ID, health.getIdNumber());
        contentValues.put(USERS_COLUMN_FIRSTNAME, health.getFirstName());
        contentValues.put(USERS_COLUMN_LASTNAME, health.getLastName());
        contentValues.put(USERS_COLUMN_PASSWORD, health.getPassword());
        contentValues.put(USERS_COLUMN_EMAIL, health.getEmail());
        contentValues.put(USERS_COLUMN_MOBILE, health.getMobile());
        contentValues.put(USERS_COLUMN_ADDRESS, health.getAddress());
        database.insert(USERS_TABLE_NAME, null, contentValues);
        database.close();

    }

    public ArrayList<Health> getAllHealth()
    {
        ArrayList<Health> healthArrayList = new ArrayList<Health>();

        String selectQuery = "SELECT * FROM " + USERS_TABLE_NAME;

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery,null);


        while(cursor.moveToNext())
        {
            Health health = new Health();
            health.setId(Integer.parseInt(cursor.getString(0)));
            health.setFirstName(cursor.getString(1));
            health.setLastName(cursor.getString(2));
            health.setPassword(cursor.getString(3));
            health.setEmail(cursor.getString(4));
            health.setMobile(cursor.getString(5));
            health.setAddress(cursor.getString(6));
            healthArrayList.add(health);
        }

        cursor.close();
        database.close();
        return healthArrayList;
    }

    public boolean checkHealth(String email, String password)
    {
        String[] columns = {USERS_COLUMN_ID};
        SQLiteDatabase db = getReadableDatabase();
        String selection = USERS_COLUMN_EMAIL + "=?" + " and " + USERS_COLUMN_PASSWORD + "=?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(USERS_TABLE_NAME, columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        //db.close();

        if(count>0)
            return true;
        else
            return false;
    }

    public Health getAHealth(String email)
    {
        Health health;
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + USERS_TABLE_NAME
                + " WHERE " + USERS_COLUMN_EMAIL + " = " + email;

        Cursor cursor = database.query(USERS_TABLE_NAME, new String[] { USERS_COLUMN_ID,
                        USERS_COLUMN_FIRSTNAME,USERS_COLUMN_LASTNAME, USERS_COLUMN_PASSWORD,USERS_COLUMN_EMAIL,
                        USERS_COLUMN_MOBILE,USERS_COLUMN_ADDRESS}, USERS_COLUMN_EMAIL + "=?",
                new String[] { String.valueOf(email) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
        health = new Health(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),
                cursor.getString(4),cursor.getString(5),cursor.getString(6));


        cursor.close();
        database.close();
        return health;
    }

    public ArrayList<Health> getHealthAddressQR(String address)
    {
        ArrayList<Health> healthArrayList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + USERS_TABLE_NAME
                + " WHERE " + USERS_COLUMN_ADDRESS + " = " + address;

        Cursor cursor = database.query(USERS_TABLE_NAME, new String[] { USERS_COLUMN_ID,
                        USERS_COLUMN_FIRSTNAME,USERS_COLUMN_LASTNAME, USERS_COLUMN_PASSWORD,USERS_COLUMN_EMAIL,
                        USERS_COLUMN_MOBILE,USERS_COLUMN_ADDRESS}, USERS_COLUMN_EMAIL + "=?",
                new String[] { String.valueOf(address) }, null, null, null, null);

        while(cursor.moveToNext())
        {
            Health health = new Health(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),
                    cursor.getString(4),cursor.getString(5),cursor.getString(6));
            healthArrayList.add(health);
        }

        cursor.close();
        database.close();
        return healthArrayList;
    }
}
