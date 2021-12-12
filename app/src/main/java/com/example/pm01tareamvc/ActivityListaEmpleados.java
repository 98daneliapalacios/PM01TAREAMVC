package com.example.pm01tareamvc;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pm01tareamvc.configuracion.SQLiteConexion;
import com.example.pm01tareamvc.configuracion.transaccion;
import com.example.pm01tareamvc.tablas.Empleados;

import java.util.ArrayList;

public class ActivityListaEmpleados extends AppCompatActivity {
    SQLiteConexion conexion;
    ListView listaEmpleados;
    ArrayList<Empleados> lista;
    ArrayList<String> ArregloEmpleado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_empleados);

        conexion = new SQLiteConexion(this, transaccion.NameDatabase, null, 1);
        listaEmpleados = (ListView) findViewById(R.id.listaEmpleados);

        ObtenerListaEmpleados();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ArregloEmpleado);
        listaEmpleados.setAdapter(adp);

    }

    private void ObtenerListaEmpleados() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Empleados list_Empleados = null;
        lista = new ArrayList<Empleados>();

        //cursor de  bd: nos apoya a recorrer la informacion de la tabla a la cual consltamos
        Cursor cursor = db.rawQuery("SELECT * FROM " + transaccion.tablaEmpleados , null);

        //recorrer la informacion del cursor
        while (cursor.moveToNext()){
            list_Empleados = new Empleados();
            list_Empleados.setId(cursor.getInt( 0));
            list_Empleados.setNombre(cursor.getString( 1));
            list_Empleados.setApellido(cursor.getString(2));
            list_Empleados.setEdad(cursor.getInt( 3));
            list_Empleados.setDireccion(cursor.getString( 4));
            list_Empleados.setPuesto(cursor.getString( 5));

            lista.add(list_Empleados);
        }

        cursor.close();

        filllist();
    }
    private void filllist(){
        ArregloEmpleado = new ArrayList<String>();
        for (int i = 0; i < lista.size(); i++){

            ArregloEmpleado.add(
                    "\n" + "Id: " + lista.get(i).getId() + "\n" +
                    "Nombre Completo: " +lista.get(i).getNombre() + " " + lista.get(i).getApellido()  + "\n" +
                    "Edad: " +lista.get(i).getEdad()  + "\n" +
                    "Direccion: " + lista.get(i).getDireccion() + "\n" +
                    "Puesto: " + lista.get(i).getPuesto() + "\n"
            );
        }

    }
}