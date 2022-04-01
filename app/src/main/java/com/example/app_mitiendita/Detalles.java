package com.example.app_mitiendita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Detalles extends AppCompatActivity {
    TextView tvid, tvCodigo, tvNombre, tvPrecio, tvCantidad, tvCliente;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        tvid = (TextView) findViewById(R.id.txtid);
        tvCodigo = (TextView) findViewById(R.id.txtcodigo);
        tvNombre = (TextView) findViewById(R.id.txtnombre);
        tvPrecio = (TextView) findViewById(R.id.txtprecio);
        tvCantidad = (TextView) findViewById(R.id.txtcantidad);
        tvCliente = (TextView) findViewById(R.id.txtcliente);

        Intent intent=getIntent();
        position=intent.getExtras().getInt("position");

        tvid.setText("ID"+MainActivity2.bd.get(position).getId());
        tvCodigo.setText("Codigo"+MainActivity2.bd.get(position).getCodigo());
        tvNombre.setText("Nombre"+MainActivity2.bd.get(position).getNombre());
        tvPrecio.setText("Precio"+MainActivity2.bd.get(position).getPrecio());
        tvCantidad.setText("Cantidad"+MainActivity2.bd.get(position).getCantidad());
        tvCliente.setText("Cliente"+MainActivity2.bd.get(position).getCliente());


    }
}