package practica3.gabrielosorio.com.practica3actividades;

import android.widget.CompoundButton;
import android.widget.ToggleButton;

/**
 * Created by Jaime on 02/10/2016.
 */
public class Productos {
    int idImagen;
    String nombre, descripcion, precio;
    boolean check;
    boolean status = false;

    public Productos(int idImagen, String precio, String nombre, String descripcion, boolean check) {
        this.idImagen = idImagen;
        this.precio = precio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.check = check;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }

    public boolean getCheck(ToggleButton tButton) {

        tButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheked) {

                if (isCheked) {
                    // The toggle is enabled
                    status = true;
                } else {
                    // The toggle is disabled
                    status = false;
                }
            }
        });
        return status;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}

