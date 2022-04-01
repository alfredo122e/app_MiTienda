package com.example.app_mitiendita;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private ListView list;
    Adapter adapter;
    public static ArrayList<BD>bd=new ArrayList<>();

    String url ="https://zelayalainea.000webhostapp.com/crud/mostrar.php";
    BD bds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        list=findViewById(R.id.ListView1);
        adapter=new Adapter(this,bd);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogoItem = {"Ver datos.", "Editar datos.", "Eliminar datos."};
                builder.setTitle(bd.get(position).getNombre());
                builder.setItems(dialogoItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                startActivity(new Intent(getApplicationContext(),Detalles.class)
                                .putExtra("position", position));
                                break;

                            case 1:
                                startActivity(new Intent(getApplicationContext(),modificar.class)
                                        .putExtra("position", position));
                                break;

                            case 2:
                                break;
                        }
                    }
                });

                builder.create().show();
            }
    });
        mostrardatos();
    }

    public void mostrardatos() {
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                bd.clear();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String succes=jsonObject.getString("succes");


                    JSONArray jsonArray=jsonObject.getJSONArray("store");

                    if(succes.equals("1")){
                        for (int i=0;i<jsonArray.length();i++){

                            JSONObject object=jsonArray.getJSONObject(i);
                            String id=object.getString("id");
                            String codigo=object.getString("codigo");
                            String nombre=object.getString("nombre");
                            String precio=object.getString("precio");
                            String cantidad=object.getString("cantidad");
                            String cliente=object.getString("cliente");

                            bds=new BD(id,codigo,nombre,precio,cantidad,cliente);
                            bd.add(bds);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity2.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public void btnAgregar(View view){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}