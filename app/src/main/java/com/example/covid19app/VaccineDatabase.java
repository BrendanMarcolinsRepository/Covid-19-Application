package com.example.covid19app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VaccineDatabase extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "VaccineDatabase";
    public static final String USERS_TABLE_NAME = "Vaccine";
    public static final String USERS_COLUMN_ID = "id";
    public static final String USERS_COLUMN_BRANDNAME = "brandName";
    public static final String USERS_COLUMN_VACCINENAME = "vaccineName";
    public static final String USERS_COLUMN_VACCINENUMBER = "vaccineNumber";

    public VaccineDatabase(Context context)
    {
        super(context,DATABASE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + USERS_TABLE_NAME + "("
                + USERS_COLUMN_ID + " INTEGER  PRIMARY KEY," + USERS_COLUMN_BRANDNAME + " TEXT," + USERS_COLUMN_VACCINENAME + " TEXT,"
                + USERS_COLUMN_VACCINENUMBER + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE_NAME);
        onCreate(db);

    }

    public void addVaccinated(Vaccine vaccine)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERS_COLUMN_ID, vaccine.getID());
        contentValues.put(USERS_COLUMN_BRANDNAME, vaccine.getBrand());
        contentValues.put(USERS_COLUMN_VACCINENAME, vaccine.getVaccineName());
        contentValues.put(USERS_COLUMN_VACCINENUMBER, vaccine.getVaccineNumber());
        database.insert(USERS_TABLE_NAME, null, contentValues);
        database.close();

    }

    public Vaccine getAVaccine(String vaccineSerial)
    {
        Vaccine vaccine;
        SQLiteDatabase database = this.getReadableDatabase();


        Cursor cursor = database.query(USERS_TABLE_NAME, new String[] { USERS_COLUMN_ID,
                        USERS_COLUMN_BRANDNAME,USERS_COLUMN_VACCINENAME, USERS_COLUMN_VACCINENUMBER}, USERS_COLUMN_VACCINENUMBER + "=?",
                new String[] { String.valueOf(vaccineSerial) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
        vaccine = new Vaccine(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3));


        cursor.close();
        database.close();
        return vaccine;
    }

    public boolean checkVaccineExist(String vaccine)
    {
        String[] columns = {USERS_COLUMN_ID};
        SQLiteDatabase db = getReadableDatabase();
        String selection = USERS_COLUMN_VACCINENUMBER + "=?";
        String[] selectionArgs = {vaccine};
        Cursor cursor = db.query(USERS_TABLE_NAME, columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return true;
        else
            return false;
    }

}
