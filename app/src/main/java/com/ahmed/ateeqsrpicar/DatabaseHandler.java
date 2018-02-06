package com.ahmed.ateeqsrpicar;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "SettingsManager";

    // Contacts table name
    private static final String TABLE_SETTINGS = "Settings";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_PORT = "port";
    private static final String KEY_IP = "ip";
    private static final String KEY_LPORT = "lport";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_SETTINGS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_IP + " TEXT," + KEY_PORT + " TEXT," + KEY_LPORT + " TEXT"  + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETTINGS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    void addData(DataClass data) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETTINGS);
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_SETTINGS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_IP + " TEXT," + KEY_PORT + " TEXT," + KEY_LPORT + " TEXT"  + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        ContentValues values = new ContentValues();
        values.put(KEY_IP, data.getIpHost());
        values.put(KEY_PORT, data.getPort());
        values.put(KEY_LPORT, data.getlPorta());
        // Inserting Row
        db.insert(TABLE_SETTINGS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    DataClass getSettings(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SETTINGS, new String[]{KEY_ID,
                        KEY_IP, KEY_PORT, KEY_LPORT}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        String ip = cursor.getString(1);
        int port = Integer.parseInt(cursor.getString(2));
        int lport = Integer.parseInt(cursor.getString(3));

        DataClass data = new DataClass(ip,port,lport);
        // return contact
        return data;
    }

    // Getting All Contacts
    public List<DataClass> getAllData() {
        List<DataClass> dataList = new ArrayList<DataClass>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SETTINGS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DataClass data = new DataClass();
                data.setIpHost(cursor.getString(1));
                data.setPort(Integer.parseInt(cursor.getString(2)));
                data.setlPorta(Integer.parseInt(cursor.getString(3)));
                // Adding contact to list
                dataList.add(data);
            } while (cursor.moveToNext());
        }

        // return contact list
        return dataList;
    }

    // Updating single contact
    public int updateData(DataClass dataClass) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IP, dataClass.getIpHost());
        values.put(KEY_PORT, dataClass.getPort());
        values.put(KEY_LPORT, dataClass.getlPorta());

        // updating row
        return db.update(TABLE_SETTINGS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(dataClass.getID()) });
    }

    // Deleting single contact
    public void deleteData(DataClass dataClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SETTINGS, KEY_ID + " = ?",
                new String[] { String.valueOf(dataClass.getID()) });
        db.close();
    }


    // Getting contacts Count
    public int getDataCount() {
        String countQuery = "SELECT  * FROM " + TABLE_SETTINGS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}