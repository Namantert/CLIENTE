package com.example.david.namantert;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Latin Production on 21/05/2017.
 */

public class DatosCliente {

    public static ArrayList<Cliente> traerCliente(Context contexto){
        ArrayList<Cliente> cliente = new ArrayList<>();

        SQLiteDatabase db;
        String sql, cedula, nombre, apellido, telefono;
        Cliente p;
        //Abrir conexión de lectura
        ClienteSQLiteOpenHelper aux = new ClienteSQLiteOpenHelper(contexto,"DBCliente",null,1);
        db = aux.getReadableDatabase();
        sql ="select * from Cliente";
        Cursor c = db.rawQuery(sql,null);
        if(c.moveToFirst()){
            do{

                cedula = c.getString(0);
                nombre =c.getString(1);
                apellido = c.getString(2);
                telefono = c.getString(3);

                p = new Cliente(cedula,nombre,apellido,telefono);
                cliente.add(p);

            }while (c.moveToNext());
        }

        db.close();

        return cliente;

    }
    public static Cliente buscarCliente(Context contexto, String ced){


        //Declarar variables
        SQLiteDatabase db;
        String sql, cedula, nombre, apellido, telefono;
        Cliente p=null;
        //Abrir conexión de lectura
        ClienteSQLiteOpenHelper aux = new ClienteSQLiteOpenHelper(contexto,"DBCliente",null,1);
        db = aux.getReadableDatabase();

        //Cursor
        sql ="select * from Cliente where cedula ='"+ced+"'";
        Cursor c = db.rawQuery(sql,null);

        //Recorrido del cursor
        if(c.moveToFirst()){
            cedula = c.getString(0);
            nombre =c.getString(1);
            apellido = c.getString(2);
            telefono = c.getString(3);
            p = new Cliente(cedula,nombre,apellido,telefono);
        }

        db.close();
        return p;
    }
}
