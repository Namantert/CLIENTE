package com.example.david.namantert;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RegistrarCliente extends AppCompatActivity {
    private EditText cajaCedula, cajaNombre, cajaApellido, cajaTelefono;
    private Resources res;
    private TableLayout tabla;
    private ArrayList<Cliente> cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cliente);

        cajaCedula = (EditText) findViewById(R.id.txtCedulaCliente);
        cajaNombre = (EditText) findViewById(R.id.txtNombreCliente);
        cajaApellido = (EditText) findViewById(R.id.txtApellidoCliente);
        cajaTelefono = (EditText) findViewById(R.id.txtTelefonoCliente);
        Cliente p;
        if (cajaCedula.getText().toString().isEmpty()) {

        } else {
            p = DatosCliente.buscarCliente(getApplicationContext(), cajaCedula.getText().toString());
            if (p != null) {
                cajaNombre.setText(p.getNombre());
                cajaApellido.setText(p.getApellido());
                cajaTelefono.setText(p.getTelefono());
            }

            tabla = (TableLayout) findViewById(R.id.tblCliente);
            cliente = DatosCliente.traerCliente(getApplicationContext());

            for (int i = 0; i < cliente.size(); i++) {
                TableRow fila = new TableRow(this);
                TextView c1 = new TextView(this);
                TextView c2 = new TextView(this);
                TextView c3 = new TextView(this);
                TextView c4 = new TextView(this);
                TextView c5 = new TextView(this);


                c1.setText(cliente.get(i).getCedula());
                c2.setText(cliente.get(i).getNombre());
                c3.setText(cliente.get(i).getApellido());
                c4.setText(cliente.get(i).getTelefono());


                fila.addView(c1);
                fila.addView(c2);
                fila.addView(c3);
                fila.addView(c4);


                tabla.addView(fila);
            }
        }
    }


    public boolean validarTodo(){
        if(cajaCedula.getText().toString().isEmpty()){
            cajaCedula.setError(res.getString(R.string.error_cajacedula));
            cajaCedula.requestFocus();
            return false;
        }
        if(cajaNombre.getText().toString().isEmpty()){
            cajaNombre.setError(res.getString(R.string.error_cajanombre));
            cajaNombre.requestFocus();
            return false;
        }
        if(cajaApellido.getText().toString().isEmpty()){
            cajaApellido.setError(res.getString(R.string.error_cajaapellido));
            cajaApellido.requestFocus();
            return false;
        }
        if(cajaTelefono.getText().toString().isEmpty()){
            cajaTelefono.setError(res.getString(R.string.error_telefono));
            cajaTelefono.requestFocus();
            return false;

        }
        return true;
    }
    public void guardar(View v){
        String cedula,nombre,apellido,telefono;
        Cliente p;

        if(validarTodo()){
            cedula = cajaCedula.getText().toString();
            nombre = cajaNombre.getText().toString();
            apellido=cajaApellido.getText().toString();
            telefono=cajaTelefono.getText().toString();
            p = new Cliente(cedula,nombre,apellido,telefono);
            p.guardar(getApplicationContext());

            Toast.makeText(getApplicationContext(),"Guardado",
                    Toast.LENGTH_SHORT).show();



            limpiar();

        }
    }
    public boolean validarCedula() {

        if (cajaCedula.getText().toString().isEmpty()) {
            cajaCedula.setError("Digite la cédula");
            cajaCedula.requestFocus();
            return false;
        }
        return true;
    }
    public void limpiar(){
        cajaCedula.setText("");
        cajaNombre.setText("");
        cajaApellido.setText("");
        cajaTelefono.setText("");

        cajaCedula.requestFocus();

    }
    public void buscar(View v){
        Cliente p;

        if(validarCedula()) {
            p = DatosCliente.buscarCliente(getApplicationContext(), cajaCedula.getText().toString());
            if(p!=null){
                cajaNombre.setText(p.getNombre());
                cajaApellido.setText(p.getApellido());
                cajaTelefono.setText(p.getTelefono());
            }
        }
    }

    public void modificar(View v){
        Cliente p,p2;
        String nombre,apellido,telefono;
        if(validarCedula()) {
            p = DatosCliente.buscarCliente(getApplicationContext(), cajaCedula.getText().toString());
            if(p!=null){

                nombre = cajaNombre.getText().toString();
                apellido=cajaApellido.getText().toString();
                telefono=cajaTelefono.getText().toString();
                p2 = new Cliente(p.getCedula(),nombre,apellido,telefono);
                p2.modificar(getApplicationContext());

                Toast.makeText(getApplicationContext(), "Modificado",
                        Toast.LENGTH_SHORT).show();
                limpiar();
            }
        }
    }
    public void eliminar(View v){
        Cliente p;
        if(validarCedula()) {
            p = DatosCliente.buscarCliente(getApplicationContext(), cajaCedula.getText().toString());
            if(p!=null){
                AlertDialog.Builder ventana = new AlertDialog.Builder(this);
                ventana.setTitle("Confirmación");
                ventana.setMessage("¿Está seguro que desea eliminar esta persona?");
                ventana.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Cliente p;
                        p = DatosCliente.buscarCliente(getApplicationContext(), cajaCedula.getText().toString());

                        p.eliminar(getApplicationContext());
                        limpiar();
                        Toast.makeText(getApplicationContext(), "Eliminado",
                                Toast.LENGTH_SHORT).show();

                    }
                });

                ventana.setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cajaCedula.requestFocus();
                    }
                });

                ventana.show();
            }
        }
    }
    }


