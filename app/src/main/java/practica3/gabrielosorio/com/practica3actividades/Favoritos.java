package practica3.gabrielosorio.com.practica3actividades;

/**
 * Created by Jaime on 25/10/2016.
 */
public class Favoritos {
    private int id;
    private int idus;
    private int idpr;
    // Constructor de un objeto Contactos
    public Favoritos(int id, int idus, int idpr) {
        this.id = id;
        this.idus = idus;
        this.idpr = idpr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdus() {
        return idus;
    }

    public void setIdus(int idus) {
        this.idus = idus;
    }

    public int getIdpr() {
        return idpr;
    }

    public void setIdpr(int idpr) {
        this.idpr = idpr;
    }
}
