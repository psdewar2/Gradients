package com.android.gradients.db;

import android.annotation.TargetApi;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by PSD on 4/22/15.
 */
public class ColorTable { //SQL instructions for database

    public static final String COLOR_TABLE = "Colors";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_HUE = "Hue";
    public static final String COLUMN_SAT = "Saturation";
    public static final String COLUMN_VAL = "Value";

    public static final String SQL_CREATE_DB =
            "CREATE TABLE " + COLOR_TABLE
        +   "("
        +   COLUMN_NAME + " varchar(55), "
        +   COLUMN_HUE + " int, "
        +   COLUMN_SAT + " double, "
        +   COLUMN_VAL + " double "
        +   ");";

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_DB);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //versions can be incremented, but for this case, the data will simply be replaced
        Log.w(ColorTable.class.getName(), "Upgrading from " + oldVersion + " to " + newVersion
                + ". (Old data has been destroyed)");
        db.execSQL("DROP TABLE IF EXISTS " + COLOR_TABLE);
        onCreate(db);
    }

    @TargetApi(19)
    public static void insert(SQLiteDatabase db) {
        try (BufferedReader br = new BufferedReader(new FileReader("ColorInfo.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                // process the line.
                db.execSQL(line);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
/*
CREATE TABLE Colors
	(Name varchar(55), Hue int, Saturation double, Value double)
;
 */