package com.example.ejemplo11_listview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.ejemplo11_listview.adapters.ContactosAdapter;
import com.example.ejemplo11_listview.pojo.Contacto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Contacto> listaContactos;
    private final int NEWCONTACTO = 7;
    private ContactosAdapter contactosAdapter;

    // ALMACENAMIENTO PERSISTENTE

    private SharedPreferences sharedPreferences;

    // Datos insertaré -> ArrayList en 1 sharedpreferences 1 unico registro
    // Serialización el ArrayList<Contactos> -> Cadena de texto -> Json
    private Gson gson;


    // --------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.contenedorMain);
        listaContactos = new ArrayList<>();

        sharedPreferences = getSharedPreferences("contactos", MODE_PRIVATE);
        gson = new Gson();

        String contactosCodificados = sharedPreferences.getString("contactos", null);
        if (contactosCodificados != null) {
            listaContactos = gson.fromJson(contactosCodificados, new TypeToken<ArrayList<Contacto>>() {
            }.getType());
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewContactoActivity.class);
                startActivityForResult(intent, NEWCONTACTO);
            }
        });


        //inicializarContactos();

        //1. Crear un adapter
        contactosAdapter = new ContactosAdapter(this, R.layout.fila_resource, listaContactos);
        //2. Asignar el adapter al listview
        listView.setAdapter(contactosAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Atacar al arrayList
                Contacto c1 = listaContactos.get(i);
                // Atraves del adapterView
                Contacto c2 = (Contacto) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(MainActivity.this, ContactoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("contacto", c2);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    private void inicializarContactos() {
        for (int i = 0; i < 20; i++) {
            listaContactos.add(new Contacto("nombre" + i, "apellidos" + i, "telefono" + i));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == NEWCONTACTO) {

                if (data != null) {

                    listaContactos.add((Contacto) data.getExtras().getParcelable("contacto"));
                    contactosAdapter.notifyDataSetChanged(); // Forzar al adapter a actualizarse

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("contactos", gson.toJson(listaContactos));
                    editor.commit();

                    Log.d("SHARED", gson.toJson(listaContactos));
                }

            }

        }

    }
}
