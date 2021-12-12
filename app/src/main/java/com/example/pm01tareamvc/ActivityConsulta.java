package com.example.pm01tareamvc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pm01tareamvc.configuracion.SQLiteConexion;
import com.example.pm01tareamvc.configuracion.transaccion;

public class ActivityConsulta extends AppCompatActivity {
    SQLiteConexion conexion;
    EditText idEmple, nombre, apellido, edad, direccion, puesto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        conexion = new SQLiteConexion(this, transaccion.NameDatabase, null, 1);

        // Botones
        Button btnconsulta = (Button) findViewById(R.id.btnbuscar);
        Button btneliminar = (Button) findViewById(R.id.btneliminar);
        Button btnactualizar = (Button) findViewById(R.id.btnactualizar);

        idEmple = (EditText) findViewById(R.id.txtcid);
        nombre = (EditText) findViewById(R.id.txtcnombres);
        apellido = (EditText) findViewById(R.id.txtcapellidos);
        edad = (EditText) findViewById(R.id.txtcedad);
        direccion = (EditText) findViewById(R.id.txtcdireccion);
        puesto = (EditText) findViewById(R.id.txtcpuesto);

        btnconsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Buscar();
            }
        });

        btnactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Actualizar();
            }
        });

        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eliminar();
            }
        });


    }

    private void Actualizar() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {idEmple.getText().toString()};

        ContentValues valores = new ContentValues();
        valores.put(transaccion.nombre, nombre.getText().toString());
        valores.put(transaccion.apellido, apellido.getText().toString());
        valores.put(transaccion.edad, edad.getText().toString());
        valores.put(transaccion.direccion, direccion.getText().toString());
        valores.put(transaccion.puesto, puesto.getText().toString());

        if(!validarCampos()){
            db.update(transaccion.tablaEmpleados, valores, transaccion.id + "=?", params);
            Toast.makeText(getApplicationContext(), "Dato actualizado", Toast.LENGTH_LONG).show();
            ClearScreen();
        }else{
            Toast.makeText(getApplicationContext(), "Debe completar todos los campos", Toast.LENGTH_LONG).show();
        }
    }

    private void Eliminar() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {idEmple.getText().toString()};
        String wherecond = transaccion.id + "=?";
        if(!idEmple.getText().toString().isEmpty()){
            db.delete(transaccion.tablaEmpleados, wherecond, params);
            Toast.makeText(getApplicationContext(), "Dato eliminado", Toast.LENGTH_LONG).show();
            ClearScreen();
        }else{
            Toast.makeText(getApplicationContext(), "Campo de Id Vacio", Toast.LENGTH_LONG).show();
        }

    }

    private void Buscar() {
        SQLiteDatabase db = conexion.getWritableDatabase();

        /* Parametros de configuracion de la sentencia SELECT */
        String [] params = {idEmple.getText().toString()}; // parametro de la busqueda
        String [] fields = {transaccion.nombre,
                transaccion.apellido,
                transaccion.edad,
                transaccion.direccion,
                transaccion.puesto
        };
        String wherecond = transaccion.id + "=?";

        try{
            Cursor cdata = db.query(transaccion.tablaEmpleados, fields, wherecond, params, null,null, null );
            cdata.moveToFirst();
            nombre.setText(cdata.getString(0));
            apellido.setText(cdata.getString(1));
            edad.setText(cdata.getString(2));
            direccion.setText(cdata.getString(3));
            puesto.setText(cdata.getString(3));

            Toast.makeText(getApplicationContext(), "Empleado encontrado con exito",Toast.LENGTH_LONG).show();
        } catch (Exception ex){
            ClearScreen();
            Toast.makeText(getApplicationContext(), "Empleado no encontrado",Toast.LENGTH_LONG).show();
        }
    }

    private void ClearScreen() {
        nombre.setText("");
        apellido.setText("");
        edad.setText("");
        direccion.setText("");
        puesto.setText("");
    }

    private boolean validarCampos() {
        boolean error;

        if(nombre.getText().toString().isEmpty()
                || apellido.getText().toString().isEmpty()
                ||edad.getText().toString().isEmpty()
                ||direccion.getText().toString().isEmpty()
                ||puesto.getText().toString().isEmpty()
                ||idEmple.getText().toString().isEmpty()
        ){
            error = true;
        }else{
            error = false;
        }

        return error;
    }

}