package com.android.gradients.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * Created by PSD on 4/22/15.
 */
public class ColorContentProvider extends ContentProvider {
    private ColorDBHelper db;

    //used so that other applications have access
    private static final String AUTHORITY = "com.android.gradients.db.colorprovider"; //name of content provider

    // colors is the virtual directory in the provider
    private static final String BASE_PATH = "colors";

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

    //set up the URIMatcher and set up columns to wrap database
    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int NAME = 1;
    private static final int HUE = 2;
    private static final int SATURATION = 3;
    private static final int VALUE = 4;

    //static initializer is a block of code that runs when the class is initialized...
    //...in addition to the code in the constructor when a new instance of the object is created
    static {
        sURIMatcher.addURI(AUTHORITY, BASE_PATH, NAME);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", HUE); //maybe add /# to base path?
        sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", SATURATION);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", VALUE);
    }

    /**
     *  CRUD operations
     */
    @Override
    public boolean onCreate() {
        db = new ColorDBHelper(getContext());
        return false;
    }

    /* Returns a cursor that
     * Uri: Links to the table in the provider (FROM)
     * projection: an array of columns to retrieve with each row
     * selection: (WHERE)
     * selectionArgs: The argument part of WHERE (WHERE name = black)
     * sortOrder: (ORDER)
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        //creates query
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        //checking for null columns not necessary at this point

        queryBuilder.setTables(ColorTable.COLOR_TABLE);

        //decode the uri
        int uriType = sURIMatcher.match(uri);

        switch(uriType) {
            case NAME:
                break;
            case HUE:
                queryBuilder.appendWhere("The hue of " + ColorTable.COLUMN_NAME + "=" + uri.getLastPathSegment());
                break;
            case SATURATION:
                queryBuilder.appendWhere("The saturation of " + ColorTable.COLUMN_NAME + "=" + uri.getLastPathSegment());
                break;
            case VALUE:
                queryBuilder.appendWhere("The value of " + ColorTable.COLUMN_NAME + "=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);

        }

        SQLiteDatabase wdb = db.getWritableDatabase();

        //provides read and write access to the results of the query
        Cursor cursor = queryBuilder.query(wdb, projection, selection, selectionArgs, null, null, sortOrder);

        //have the cursor watch for uri changes
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
