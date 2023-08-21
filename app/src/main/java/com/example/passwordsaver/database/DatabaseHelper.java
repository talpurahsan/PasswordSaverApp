package com.example.passwordsaver.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "password_saver";
    private static final int DATABASE_VERSION = 1;

    //table attributes
    private static final String TABLE_PASSWORD = "password";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_ACCOUNT = "account";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_PASSWORD_TYPE = "password_type";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // CREATE TABLE password
        // (
        //  id INTEGER PRIMARY KEY AUTOINCREMENT,
        //  title TEXT,
        //  account TEXT
        //  username TEXT,
        //  password TEXT,
        //  password_type ENUM('BANKS','BANKING_APPS','SOCIAL','EMAILS','WORK')
        // )

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PASSWORD +
                "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_TITLE + " TEXT, " +
                KEY_ACCOUNT + " TEXT, " +
                KEY_USERNAME + " TEXT, " +
                KEY_PASSWORD + " TEXT, " +
                KEY_PASSWORD_TYPE + " TEXT CHECK( " + KEY_PASSWORD_TYPE + " IN ( 'Banks', 'Banking Apps', 'Social', 'Emails', 'Work' ) )NOT NULL DEFAULT 'BANKS'" +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        /*sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PASSWORD);

        onCreate(sqLiteDatabase);*/
    }

    public void addPassword(String title, String account, String username, String password, String passwordType){

        SQLiteDatabase database = this.getWritableDatabase();   //opening database

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, title);
        values.put(KEY_ACCOUNT, account);
        values.put(KEY_USERNAME, username);
        values.put(KEY_PASSWORD, password);
        values.put(KEY_PASSWORD_TYPE, passwordType);

        database.insert(TABLE_PASSWORD, null, values);
    }

    public ArrayList<Password> getAllPasswords(){

        ArrayList<Password> passwordList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_PASSWORD, null);

        while (cursor.moveToNext())
            passwordList.add(new Password(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)));

        return passwordList;
    }

    public ArrayList<Password> getPasswords(String passwordType){

        ArrayList<Password> passwordList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_PASSWORD + " WHERE " + KEY_PASSWORD_TYPE + " = '" + passwordType + "'", null);

        while (cursor.moveToNext())
            passwordList.add(new Password(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)));

        return passwordList;
    }

    public Password getPassword(int passwordID){

        ArrayList<Password> passwordList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_PASSWORD + " WHERE " + KEY_ID + " = " + passwordID, null);

        while(cursor.moveToNext())
            passwordList.add(new Password(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)));

        return passwordList.get(0);
    }

    public void updatePassword(int passwordID, String title, String account, String username, String password, String passwordType){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, title);
        values.put(KEY_ACCOUNT, account);
        values.put(KEY_USERNAME, username);
        values.put(KEY_PASSWORD, password);
        values.put(KEY_PASSWORD_TYPE, passwordType);

        database.update(TABLE_PASSWORD, values, KEY_ID + " = " + passwordID, null);
    }

    public void deletePassword(int passwordID){

        SQLiteDatabase database = this.getWritableDatabase();

        database.delete(TABLE_PASSWORD, KEY_ID + " = " + passwordID, null);
    }
}
