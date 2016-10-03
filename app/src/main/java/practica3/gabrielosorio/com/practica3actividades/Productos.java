package practica3.gabrielosorio.com.practica3actividades;

/**
 * Created by Jaime on 02/10/2016.
 */
public class Productos {
    int idImagen, precio;
    String nombre, descripcion;

    public Productos(int idImagen, int precio, String nombre, String descripcion) {
        this.idImagen = idImagen;
        this.precio = precio;
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }
}

