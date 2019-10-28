package com.example.ejemplo11_listview.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Contacto implements Parcelable {

    private String nombre;
    private String apellidos;
    private String telefono;

    public Contacto(String nombre, String apellidos, String telefono) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
    }

    protected Contacto(Parcel in) {
        nombre = in.readString();
        apellidos = in.readString();
        telefono = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(apellidos);
        dest.writeString(telefono);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Contacto> CREATOR = new Creator<Contacto>() {
        @Override
        public Contacto createFromParcel(Parcel in) {
            return new Contacto(in);
        }

        @Override
        public Contacto[] newArray(int size) {
            return new Contacto[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getTelefono() {
        return telefono;
    }
}
