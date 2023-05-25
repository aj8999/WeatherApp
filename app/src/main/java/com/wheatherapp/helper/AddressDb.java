package com.wheatherapp.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AddressDb extends SQLiteOpenHelper {
    public static final String DBName="WeatherApp";
    public static final Integer DBVersion=1;
    public AddressDb(@Nullable Context context) {
        super(context, DBName,null,DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tbl_loc(id integer Primary Key AUTOINCREMENT,LocName text,Lat text,Long text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("");
    }

    public boolean insert(String LocName,String Lat,String Long) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("LocName", LocName);
            cv.put("Lat", Lat);
            cv.put("Long", Long);

            SQLiteDatabase db = getWritableDatabase();
            long res = db.insert("tbl_loc", "", cv);
            Log.i("mytag", "data inserted" + res);

            if (res > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            Log.i("mytag", "something went wrong" + e);
            return false;
        }
    }

    public boolean delete(int id) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            long res = db.delete("tbl_loc","id=?",new String[]{""+id});
            Log.i("mytag", "data delete" + res);

            if (res > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            Log.i("mytag", "something went wrong" + e);
            return false;
        }
    }

public boolean deleteAll() {
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("delete from "+ "tbl_loc");
        } catch (Exception e) {
            Log.i("mytag", "something went wrong" + e);
            return false;
        }
    return false;
    }


    public ArrayList<AddreesData> listData () {

        String sql = "select * from " + "tbl_loc";
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<AddreesData> storeData = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String LocName = cursor.getString(1);
                String Lat = cursor.getString(2);
                String Long = cursor.getString(2);
                storeData.add(new AddreesData(id,LocName, Lat, Long));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return storeData;
    }
}
