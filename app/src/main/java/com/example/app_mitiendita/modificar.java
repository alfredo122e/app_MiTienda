package com.example.app_mitiendita;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class modificar extends AppCompatActivity {

    EditText tvid, tvCodigo, tvNombre, tvPrecio, tvCantidad, tvCliente;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        tvid = findViewById(R.id.tvid);
        tvCodigo = findViewById(R.id.tvcodigo);
        tvNombre = findViewById(R.id.tvnombre);
        tvPrecio = findViewById(R.id.tvprecio);
        tvCantidad = findViewById(R.id.tvcantidad);
        tvCliente = findViewById(R.id.tvcliente);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");
        tvid.setText("Id" + MainActivity2.bd.get(position).getId());
        tvCodigo.setText("Codigo" + MainActivity2.bd.get(position).getCodigo());
        tvNombre.setText("Nombre" + MainActivity2.bd.get(position).getNombre());
        tvPrecio.setText("Precio" + MainActivity2.bd.get(position).getPrecio());
        tvCantidad.setText("Cantidad" + MainActivity2.bd.get(position).getCantidad());
        tvCliente.setText("Cliente" + MainActivity2.bd.get(position).getCliente());

    }

    public void actualizar(View view) {

        final String id = tvid.getText().toString();
        final String codigo = tvCodigo.getText().toString();
        final String nombre = tvNombre.getText().toString();
        final String precio = tvPrecio.getText().toString();
        final String cantidad = tvCantidad.getText().toString();
        final String cliente = tvCliente.getText().toString();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Actualizando....");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, "https://zelayalainea.000webhostapp.com/crud/actualizar.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(modificar.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(modificar.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("id", id);
                params.put("codigo", codigo);
                params.put("nombre", nombre);
                params.put("precio", precio);
                params.put("cantidad", cantidad);
                params.put("cliente", cliente);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(modificar.this);
        requestQueue.add(request);
    }
}
