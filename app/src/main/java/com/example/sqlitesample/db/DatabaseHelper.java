package com.example.sqlitesample.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sqlitesample.model.Contact;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "contacts.db";
    public static final String TABLE_NAME = "contacts_table";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String EMAIL = "email";
    public static final String ADDRESS = "address";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME + " (" +
                        ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                        NAME + " VARCHAR, " +
                        PHONE + " VARCHAR," +
                        EMAIL + " VARCHAR," +
                        ADDRESS + " VARCHAR" + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE " + TABLE_NAME);
//        db.execSQL(
//                "CREATE TABLE " + TABLE_NAME + " (" +
//                        ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
//                        NAME + " VARCHAR, " +
//                        PHONE + " VARCHAR," +
//                        EMAIL + " VARCHAR," +
//                        ADDRESS + " VARCHAR" + ")"
//        );

        if (oldVersion <  2) {
            upgradeVersion2(db);

        }
//        if (oldVersion <  3) {
//            upgradeVersion3(db);
//
//        }
//        if (oldVersion <  4) {
//            upgradeVersion4(db);
//
//        }
    }

    private void upgradeVersion2(SQLiteDatabase db) {

        String query="Select * from "+TABLE_NAME;

        Cursor cursor= db.rawQuery(query,null);

        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(NAME));
            String phone = cursor.getString(cursor.getColumnIndex(PHONE));
            String email = cursor.getString(cursor.getColumnIndex(EMAIL));
            String address = cursor.getString(cursor.getColumnIndex(ADDRESS));
            int id = cursor.getInt(cursor.getColumnIndex(ID));

            ContentValues values=new ContentValues();
            values.put(NAME,name);
            values.put(PHONE,phone);
            values.put(EMAIL,email);
            values.put(ADDRESS,address);

            db.update(TABLE_NAME,values,ID+"=?",new String[]{id+""});

        }
    }

    public boolean addContact(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, contact.getName());
        values.put(PHONE, contact.getPhoneNumber());
        values.put(EMAIL, contact.getEmail());
        values.put(ADDRESS, contact.getAddress());

        long insert = db.insert(TABLE_NAME, null, values);

        return insert > 0;
    }

    public ArrayList<Contact> getContacts(){
        ArrayList<Contact> contacts=new ArrayList<>();
        String query="Select * from "+TABLE_NAME;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor= db.rawQuery(query,null);

        while (cursor.moveToNext()){
            Contact contact=new Contact(
                    cursor.getString(cursor.getColumnIndex(NAME)),
                    cursor.getString(cursor.getColumnIndex(PHONE)),
                    cursor.getString(cursor.getColumnIndex(EMAIL)),
                    cursor.getString(cursor.getColumnIndex(ADDRESS))
            );
            contact.setId(cursor.getInt(cursor.getColumnIndex(ID)));
            contacts.add(contact);
        }

        return contacts;
    }
}
