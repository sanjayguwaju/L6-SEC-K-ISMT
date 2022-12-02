package com.example.dbwithlandr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class database extends SQLiteOpenHelper {

    public static final String DB_NAME ="babybuy.db";
    public static final String TABLE = "auth";
    public static final String COL_1 = "firstname";
    public static final String COL_2 = "lastname";
    public static final String COL_3 = "email";
    public static final String COL_4 = "phonenumber";
    public static final String COL_5 = "Country";
    public static final String COL_6 = "password";
    public static final String COL_7 = "gender";

    public database(Context context) {
        super(context, DB_NAME, null, 1);
        SQLiteDatabase db = this.getReadableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String q = "create table " + TABLE + "(id INTEGER PRIMARY " +
                "KEY AUTOINCREMENT, " + COL_1 + " varchar(100), " + COL_2 +
                " varchar(100), " + COL_3 + " varchar(100), "
                + COL_4 + " varchar(50), " + COL_5 + " varchar(50), " +
        COL_6 + " varchar(100), " + COL_7 + " varchar(50))";
        db.execSQL(q);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE );
            onCreate(db);
    }

    public boolean insert(String firstname, String lastname, String email, String phone, String country, String password, String gender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(COL_1, firstname);
        v.put(COL_2, lastname);
        v.put(COL_3, email);
        v.put(COL_4, phone);
        v.put(COL_5, country);
        v.put(COL_6, password);
        v.put(COL_7, gender);
        long res = db.insert(TABLE, null,v);
        if(res == -1)
            return false;
        else
            return true;
    }
public Boolean checkemail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor getlistemail  = db.rawQuery("select * from auth where " +
            "email = ?", new String[]{email});
    if (getlistemail.getCount() > 0)
        return false;
    else
        return true;
}

    public Boolean checkemailandpassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor  = db.rawQuery("select * from auth where" +
                " email = ? and password = ?",
                new String[]{email, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }



   public ArrayList<datamodel> fetchdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor getdata = db.rawQuery("select * from auth", null);
        ArrayList<datamodel> arrarl = new ArrayList<>();

        while (getdata.moveToNext()){

            datamodel md = new datamodel();
            md.firstname = getdata.getString(1);
            md.dbmemail = getdata.getString(3);

            arrarl.add(md);
        }
        return arrarl;
   }





}
