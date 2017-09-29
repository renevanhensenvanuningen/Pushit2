package com.example.reneu.pushit2;

/**
 * Created by reneu on 29-9-2017.
 */

import static android.provider.BaseColumns._ID;
import static com.example.reneu.pushit2.Constants.TABLE_NAME;
import static com.example.reneu.pushit2.Constants.TIME;
import static com.example.reneu.pushit2.Constants.VALUE;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PushupData extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "pushup.db";
    private static final int DATABASE_VERSION = 1;

    public PushupData(Context ctx)
    {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("Create table " + TABLE_NAME + " (" + _ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TIME
                    + " INTEGER, " + VALUE +" INT NOT NULL);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }



}
