package com.example.david.namantert;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Latin Production on 21/05/2017.
 */

public class Cliente {
    String cedula,nombre,apellido,telefono;

    public Cliente(String cedula, String nombre, String apellido, String telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void guardar(Context contexto){
        //declarar las variables
        SQLiteDatabase db;
        String sql;

        //Abrir la conexion de base datos en modo escritura
        ClienteSQLiteOpenHelper  aux = new ClienteSQLiteOpenHelper(contexto,"DBCliente",null,1);
        db = aux.getWritableDatabase();

        //insertar forma 1
        sql = "INSERT INTO Cliente values('"
                +this.getCedula()+"','"
                +this.getNombre()+"','"
                +this.getApellido()+"','"
                +this.getTelefono()+"')";

        db.execSQL(sql);

        //insert forma 2

      /*  ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("foto",this.getFoto());
        nuevoRegistro.put("cedula",this.getCedula());
        nuevoRegistro.put("nombre",this.getNombre());
        nuevoRegistro.put("apellido",this.getApellido());
        nuevoRegistro.put("sexo",this.getSexo());
        nuevoRegistro.put("pasatiempo",this.getPasatiempo());

        db.insert("Personas",null,nuevoRegistro);
*/
        //cerrar conexion
        db.close();

    }

    public void eliminar(Context contexto){
        //declarar las variables
        SQLiteDatabase db;
        String sql;

        //Abrir la conexion de base datos en modo escritura
        ClienteSQLiteOpenHelper  aux = new ClienteSQLiteOpenHelper(contexto,"DBCliente",null,1);
        db = aux.getWritableDatabase();

        sql = "DELETE FROM Cliente where cedula='"+this.getCedula()+"'";
        db.execSQL(sql);
        db.close();

    }
    public void modificar(Context contexto){
        //declarar las variables
        SQLiteDatabase db;
        String sql;

        //Abrir la conexion de base datos en modo escritura
        ClienteSQLiteOpenHelper  aux = new ClienteSQLiteOpenHelper(contexto,"DBCliente",null,1);
        db = aux.getWritableDatabase();

        //insertar forma 1
        sql = "UPDATE Cliente SET nombre='"+this.getNombre()+"', apellido='"+this.getApellido()+"', telefono='"+this.getTelefono()+"' "+ "where cedula ='"+this.getCedula()+"'";

        db.execSQL(sql);

        //cerrar conexion
        db.close();

    }
}
