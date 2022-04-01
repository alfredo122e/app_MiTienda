package com.example.app_mitiendita;

import static com.android.volley.Request.Method.POST;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
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

public class MainActivity extends AppCompatActivity {

    EditText edtCodigo, edtNombre, edtPrecio, edtCantidad, edtCliente;
    Button btnAgregar;
    RequestQueue requestQueue;
    String codigo, nombre, precio, cantidad, cliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtCodigo = (EditText) findViewById(R.id.tvcodigo);
        edtNombre = (EditText) findViewById(R.id.tvnombre);
        edtPrecio = (EditText) findViewById(R.id.tvprecio);
        edtCantidad = (EditText) findViewById(R.id.tvcantidad);
        edtCliente = (EditText) findViewById(R.id.tvcliente);
        btnAgregar = (Button) findViewById(R.id.btn_agregar);


        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertar();

            }
        });
    }

    // Creating method to get value from EditText.

    public void insertar() {

        codigo = edtCodigo.getText().toString().trim();
        nombre = edtNombre.getText().toString().trim();
        precio = edtPrecio.getText().toString().trim();
        cantidad = edtCantidad.getText().toString().trim();
        cliente = edtCliente.getText().toString().trim();

        ProgressDialog progressDialog = new ProgressDialog(this);
        if (nombre.isEmpty()) {
            edtNombre.setError("Complete los campos.");
        } else if (precio.isEmpty()) {
            edtPrecio.setError("Complete los campos.");
        } else if (cantidad.isEmpty()) {
            edtCantidad.setError("Complete los campos.");
        } else if (cliente.isEmpty()) {
            edtCliente.setError("Complete los campos.");
        } else {
            progressDialog.show();
            StringRequest request =new StringRequest(Request.Method.POST,"https://zelayalainea.000webhostapp.com/crud/connection.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("Los datos han sido ingresados correctamente.")) {
                        Toast.makeText(MainActivity.this, "Los datos han sido ingresados correctamente", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    } else {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<String, String>();

                    params.put("codigo", codigo);
                    params.put("nombre", nombre);
                    params.put("precio", precio);
                    params.put("cantidad", cantidad);
                    params.put("cliente", cliente);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(request);
        }
    }
}