package com.example.ejemplo11_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.ejemplo11_listview.pojo.Contacto;

public class ContactoActivity extends AppCompatActivity {

    private TextView txtNombre, txtApellidos, txtTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        txtNombre = findViewById(R.id.txtNombreContacto);
        txtApellidos = findViewById(R.id.txtApellidoContacto);
        txtTelefono = findViewById(R.id.txtTelefonoContacto);

        Contacto c = null;

        if (getIntent().getExtras() != null) {

            if ((c = getIntent().getExtras().getParcelable("contacto")) != null) {

                txtNombre.setText(c.getNombre());
                txtApellidos.setText(c.getApellidos());
                txtTelefono.setText(c.getTelefono());

            }

        }
    }
}
