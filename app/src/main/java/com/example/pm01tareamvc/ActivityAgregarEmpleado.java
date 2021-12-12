package com.example.pm01tareamvc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pm01tareamvc.configuracion.SQLiteConexion;
import com.example.pm01tareamvc.configuracion.transaccion;

public class ActivityAgregarEmpleado extends AppCompatActivity {
    EditText nombre, apellido, edad, direccion, puesto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_empleado);

        nombre= (EditText) findViewById(R.id.txtnombre);
        apellido= (EditText) findViewById(R.id.txtapellido);
        edad= (EditText) findViewById(R.id.txtedad);
        direccion= (EditText) findViewById(R.id.txtdireccion);
        puesto= (EditText) findViewById(R.id.txtpuesto);

        Button btnagregar = (Button) findViewById(R.id.btnagregar);

        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgregarPersona();
            }
        });
    }

    private void AgregarPersona() {
        SQLiteConexion conexion = new SQLiteConexion(this, transaccion.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(transaccion.nombre, nombre.getText().toString());
        valores.put(transaccion.apellido, apellido.getText().toString());
        valores.put(transaccion.edad, edad.getText().toString());
        valores.put(transaccion.direccion, direccion.getText().toString());
        valores.put(transaccion.puesto, puesto.getText().toString());

        if(!validarCampos()){
            Long resultado = db.insert(transaccion.tablaEmpleados, transaccion.id, valores);
            Toast.makeText(getApplicationContext(), "Empleado Ingresado: " + resultado.toString(), Toast.LENGTH_LONG).show();

            db.close();

            LimpiarPantalla();
        }else{
            Toast.makeText(getApplicationContext(), "Debe completar todos los campos", Toast.LENGTH_LONG).show();
        }



    }

    private boolean validarCampos(){
        boolean error;

        if(nombre.getText().toString().isEmpty()
            || apellido.getText().toString().isEmpty()
            ||edad.getText().toString().isEmpty()
            ||direccion.getText().toString().isEmpty()
            ||puesto.getText().toString().isEmpty()
        ){
            error = true;
        }else{
            error = false;
        }

        return error;
    }

    private void LimpiarPantalla() {
        nombre.setText("");
        apellido.setText("");
        edad.setText("");
        direccion.setText("");
        puesto.setText("");
    }
}