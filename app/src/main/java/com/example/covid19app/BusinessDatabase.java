package com.example.covid19app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BusinessDatabase extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "BusinessDatabase";
    public static final String USERS_TABLE_NAME = "business";
    public static final String USERS_COLUMN_ID = "id";
    public static final String USERS_COLUMN_BUSINESSNAME = "business_name";
    public static final String USERS_COLUMN_EMAIL = "business_email";
    public static final String USERS_COLUMN_PASSWORD = "business_password";
    public static final String USERS_COLUMN_MOBILE = "business_phone";
    public static final String USERS_COLUMN_ADDRESS = "business_address";

    public BusinessDatabase(@Nullable Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + USERS_TABLE_NAME + "("
                + USERS_COLUMN_ID + " INTEGER  PRIMARY KEY," + USERS_COLUMN_BUSINESSNAME + " TEXT,"
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


    public void addBusiness(Business business )
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERS_COLUMN_ID, business.getId());
        contentValues.put(USERS_COLUMN_BUSINESSNAME, business.getBusiness_name());
        contentValues.put(USERS_COLUMN_EMAIL, business.getBusiness_email());
        contentValues.put(USERS_COLUMN_PASSWORD, business.getBusiness_password());
        contentValues.put(USERS_COLUMN_MOBILE, business.getBusiness_phone());
        contentValues.put(USERS_COLUMN_ADDRESS, business.getBusiness_address());
        database.insert(USERS_TABLE_NAME, null, contentValues);
        database.close();

    }

    public ArrayList<Business> getAllBusiness()
    {
        ArrayList<Business> businesses = new ArrayList<Business>();

        String selectQuery = "SELECT * FROM " + USERS_TABLE_NAME;

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery,null);


        while(cursor.moveToNext())
        {
            Business business = new Business();
            business.setId(Integer.parseInt(cursor.getString(0)));
            business.setBusiness_name(cursor.getString(1));
            business.setBusiness_email(cursor.getString(2));
            business.setBusiness_password(cursor.getString(3));
            business.setBusiness_phone(cursor.getString(4));
            business.setBusiness_address(cursor.getString(5));
            businesses.add(business);
        }

        return businesses;
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

    public Business getBusinessLocation(String address)
    {

        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + USERS_TABLE_NAME
                + " WHERE " + USERS_COLUMN_ADDRESS + " = " + address;

        Cursor cursor = database.query(USERS_TABLE_NAME, new String[] { USERS_COLUMN_ID,
                        USERS_COLUMN_BUSINESSNAME, USERS_COLUMN_EMAIL,USERS_COLUMN_PASSWORD,
                        USERS_COLUMN_MOBILE,USERS_COLUMN_ADDRESS}, USERS_COLUMN_ADDRESS + "=?",
                new String[] { String.valueOf(address) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Business business = new Business(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),
                cursor.getString(4),cursor.getString(5));

        cursor.close();
        database.close();
        return business;
    }

    public Business getBusinessStuff(String email)
    {
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.query(USERS_TABLE_NAME, new String[] { USERS_COLUMN_ID,
                        USERS_COLUMN_BUSINESSNAME, USERS_COLUMN_EMAIL,USERS_COLUMN_PASSWORD,
                        USERS_COLUMN_MOBILE,USERS_COLUMN_ADDRESS}, USERS_COLUMN_EMAIL + "=?",
                new String[] { String.valueOf(email) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Business business = new Business(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),
                cursor.getString(4),cursor.getString(5));

        cursor.close();
        database.close();
        return business;
    }
}
