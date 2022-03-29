package com.example.covid19app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ContactTracingDatabase extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "ContactTracingDatabase";
    public static final String USERS_TABLE_NAME = "users";
    public static final String USERS_COLUMN_ID = "id";
    public static final String USERS_COLUMN_FIRSTNAME = "firstName";
    public static final String USERS_COLUMN_LASTNAME = "lastName";
    public static final String USERS_COLUMN_EMAIL = "email";
    public static final String USERS_COLUMN_MOBILE = "mobile";
    public static final String USERS_COLUMN_ADDRESS = "address";
    public static final String USERS_COLUMN_ARRIVAL = "arrival";
    public static final String USERS_COLUMN_DEPARTURE = "departure";
    public static final String USERS_COLUMN_DATE = "date";




    public ContactTracingDatabase(Context context)
    {
        super(context,DATABASE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + USERS_TABLE_NAME + "("
                + USERS_COLUMN_ID + " INTEGER  PRIMARY KEY," + USERS_COLUMN_FIRSTNAME + " TEXT," + USERS_COLUMN_LASTNAME  + " TEXT,"
                + USERS_COLUMN_EMAIL + " TEXT," + USERS_COLUMN_MOBILE + " TEXT,"
                + USERS_COLUMN_ADDRESS + " TEXT," + USERS_COLUMN_ARRIVAL + " TEXT," + USERS_COLUMN_DEPARTURE + " TEXT," +
                USERS_COLUMN_DATE + " TEXT" +")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    public void addUsers(ContactTracingModel contractTracingModel )
    {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(USERS_COLUMN_ID, contractTracingModel.getId());
        contentValues.put(USERS_COLUMN_FIRSTNAME, contractTracingModel.getFirstName());
        contentValues.put(USERS_COLUMN_LASTNAME, contractTracingModel.getLastName());
        contentValues.put(USERS_COLUMN_EMAIL, contractTracingModel.getEmail());
        contentValues.put(USERS_COLUMN_MOBILE, contractTracingModel.getMobile());
        contentValues.put(USERS_COLUMN_ADDRESS, contractTracingModel.getAddress());
        contentValues.put(USERS_COLUMN_ARRIVAL, contractTracingModel.getArrival());
        contentValues.put(USERS_COLUMN_DEPARTURE, contractTracingModel.getDeparture());
        contentValues.put(USERS_COLUMN_DATE, contractTracingModel.getDate());

        database.insert(USERS_TABLE_NAME, null, contentValues);
        database.close();

    }

    public ArrayList<ContactTracingModel> getAllQRContacts()
    {
        ArrayList<ContactTracingModel> contactTracingModelList = new ArrayList<ContactTracingModel>();

        String selectQuery = "SELECT * FROM " + USERS_TABLE_NAME;

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery,null);


        while(cursor.moveToNext())
        {
            ContactTracingModel user = new ContactTracingModel();
            user.setId(Integer.parseInt(cursor.getString(0)));
            user.setFirstName(cursor.getString(1));
            user.setLastName(cursor.getString(2));
            user.setEmail(cursor.getString(3));
            user.setMobile(cursor.getString(4));
            user.setAddress(cursor.getString(5));
            user.setArrival(cursor.getString(6));
            user.setDeparture(cursor.getString(7));
            user.setDate(cursor.getString(8));
            contactTracingModelList.add(user);
        }


        return contactTracingModelList;
    }
}


