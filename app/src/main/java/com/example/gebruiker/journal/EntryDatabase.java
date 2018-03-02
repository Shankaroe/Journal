package com.example.gebruiker.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shankara on 27-2-2018.
 */

public class EntryDatabase extends SQLiteOpenHelper {
    private static EntryDatabase instance;

    /** Constructor. */
    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /** Returns the database if available, otherwise calls the constructor. */
    public static EntryDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new EntryDatabase(context, "entries", null, 1);
        }
        return instance;
    }

    /** Returns all data from entries table. */
    public Cursor selectAll() {
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery("SELECT _id,* FROM entries", new String[] {});
    }

    /** Inserts a new Journal Entry to the database. */
    public void insert(JournalEntry je) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", je.getTitle());
        values.put("description", je.getDescription());
        values.put("rating", je.getRating());
        db.insert("entries", null, values);
    }

    /** Deletes a Journal Entry from the database. */
    public void delete(long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("entries", "_id = " + id, null);
    }

    /** Creates the entries data table. */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table entries (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT, rating INTEGER, timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP);");
    }

    /** Deletes the database and creates a new one. */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "entries");
        onCreate(sqLiteDatabase);
    }
}
