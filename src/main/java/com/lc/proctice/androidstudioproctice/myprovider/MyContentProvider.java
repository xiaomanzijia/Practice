package com.lc.proctice.androidstudioproctice.myprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by licheng on 15/10/15.
 */
public class MyContentProvider extends ContentProvider {
    private SQLiteDatabase database;
    public static final Uri URI = Uri.parse("content://com.content.myprovider");
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SEX = "sex";
    public static final String COLUMN_COMPANYNAME = "companyname";
    public static final String COLUMN_ACCOUNT = "account";
    public static final String COLUMN_CREATETIME = "createtime";

    @Override
    public boolean onCreate() {
        database = getContext().openOrCreateDatabase("myproviderdb", Context.MODE_PRIVATE,null);
        database.execSQL("create table if not exists tabnew(_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL,sex TEXT NOT NULL," +
                "companyname TEXT NOT NULL,account TEXT NOT NULL,createtime TEXT NOT NULL)");
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = database.query("tabnew",null,null,null,null,null,null);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        database.insert("tabnew","_id",values);
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        database.delete("tabnew",selection,selectionArgs);
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        database.update("tabnew",values,selection,selectionArgs);
        return 0;
    }
}
