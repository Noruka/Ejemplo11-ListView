package com.example.ejemplo11_listview.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ejemplo11_listview.R;
import com.example.ejemplo11_listview.pojo.Contacto;

import java.util.ArrayList;
import java.util.List;

public class ContactosAdapter extends ArrayAdapter<Contacto> {

    private Context context;
    private int resource;
    private ArrayList<Contacto> listaContactos;

    public ContactosAdapter(@NonNull Context context, int resource, @NonNull List<Contacto> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.listaContactos = (ArrayList<Contacto>) objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // 1. Inflater
        LayoutInflater inflater = LayoutInflater.from(context);
        // 2. Generar el View con el inflater
        View fila = inflater.inflate(this.resource, null);
        // 3. Crear las variables de la interfaz
        TextView txtNombre = fila.findViewById(R.id.txtNombreFila);
        TextView txtApellidos = fila.findViewById(R.id.txtApellidosFila);
        // 4. Dar valores con el elemento que le corresponde(position)
        Contacto c = listaContactos.get(position);
        // 5. Devolver la fila generada
        txtNombre.setText(c.getNombre());
        txtApellidos.setText(c.getApellidos());

        return fila;
    }
}
