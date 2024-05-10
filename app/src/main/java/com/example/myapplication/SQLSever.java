package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLSever extends SQLiteOpenHelper {
    private static final String DatabaseName = "library";

    private static final String Table_Name1 = "users";
    private static final String id = "id";
    private static final String username = "username";
    private static final String fullName = "fullname";
    private static final String password = "password";
    private static final String phone = "phone";
    private static final String role = "role";
    private static int version = 1;

    private SQLiteDatabase db;
    private Context context;
    private ContentValues values;

    public SQLSever(Context context) {
        super(context, DatabaseName, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create_Table_user = " CREATE TABLE " + Table_Name1 + " ( " +
                id + " TEXT primary key, " +
                username + " TEXT, " +
                fullName + " TEXT, " +
                phone + " TEXT, " +
                password + " TEXT, " +
                role + " TEXT)";
        db.execSQL(Create_Table_user);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name1);
        onCreate(db);
    }

    public boolean Changpass(String account, String newpass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(password, newpass);
        int a = db.update(Table_Name1, values, username + "=?", new String[]{account});
        if (a != 0) {
            return true;
        } else {
            return false;
        }
    }


}


