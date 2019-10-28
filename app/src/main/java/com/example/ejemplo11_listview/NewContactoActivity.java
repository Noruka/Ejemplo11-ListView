package com.example.ejemplo11_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejemplo11_listview.pojo.Contacto;

public class NewContactoActivity extends AppCompatActivity {

    private EditText txtNombre, txtApellidos, txtTelefono;
    private Button btnCancelar, btnCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contacto);

        txtNombre = findViewById(R.id.txtNombreNew);
        txtApellidos = findViewById(R.id.txtApellidosNew);
        txtTelefono = findViewById(R.id.txtTelefonoNew);

        btnCancelar = findViewById(R.id.btnCancelarNew);
        btnCrear = findViewById(R.id.btnCrearNew);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtNombre.getText().toString().isEmpty() && !txtApellidos.getText().toString().isEmpty() && !txtTelefono.getText().toString().isEmpty()){
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("contacto", new Contacto(txtNombre.getText().toString(), txtApellidos.getText().toString(), txtTelefono.getText().toString()));
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }else{
                    Toast.makeText(NewContactoActivity.this, "Faltan datos, inútil", Toast.LENGTH_SHORT).show();
                    
                }
            }
        });

    }
}
