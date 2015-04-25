package com.android.gradients.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PSD on 4/22/15.
 */
public class ColorDBHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "WikiColors.db";
    private static final int DB_VERSION = 1;

    public ColorDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ColorTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        ColorTable.onUpgrade(db, oldVersion, newVersion);
    }

    public void insert(SQLiteDatabase db) {
        ColorTable.insert(db);
    }



}
