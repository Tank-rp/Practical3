package sg.edu.rp.c346.id20022678.practical3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/*
 * I declare that this code was written by me.
 * I will not copy or allow others to copy my code.
 * I understand that copying code is considered as plagiarism.
 *
 * Student Name: Tan Ke Ting
 * Student ID: 200226678
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "client.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CLIENT = "client";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SALESPOTENTIAL  = "salespotential ";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createClientTableSql = "CREATE TABLE " + TABLE_CLIENT + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT, "
                + COLUMN_SALESPOTENTIAL + " INTEGER )";
        db.execSQL(createClientTableSql);
        Log.i("info", createClientTableSql + "\ncreated tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENT);
        onCreate(db);
    }

    public void insertClient(String name, int salespotential) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_SALESPOTENTIAL, salespotential);
        db.insert(TABLE_CLIENT, null, values);
        db.close();
    }

    public ArrayList<Client> getAllClients() {
        ArrayList<Client> clientlist = new ArrayList<Client>();
        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_NAME + "," + COLUMN_SALESPOTENTIAL
                + " FROM " + TABLE_CLIENT + " ORDER BY " + COLUMN_SALESPOTENTIAL + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int salespotential = cursor.getInt(2);

                Client newClient = new Client(id, name, salespotential);
                clientlist.add(newClient);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return clientlist;
    }

    public int getAllSalespotential() {
        int allSalespotential = 0;
        ArrayList<Client> clientlist = new ArrayList<Client>();
        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_NAME + "," + COLUMN_SALESPOTENTIAL
                + " FROM " + TABLE_CLIENT;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int salespotential = cursor.getInt(2);
                allSalespotential += salespotential;
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return allSalespotential;
    }
}
