package com.example.david.namantert;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Latin Production on 21/05/2017.
 */

public class ClienteSQLiteOpenHelper extends SQLiteOpenHelper {

    private String sql = "CREATE TABLE Cliente(cedula text, nombre text, apellido text, telefono text)";
    public ClienteSQLiteOpenHelper(Context contexto, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(contexto, name, factory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Cliente");
        db.execSQL(sql);
    }

}
