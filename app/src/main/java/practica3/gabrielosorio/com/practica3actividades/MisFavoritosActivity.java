package practica3.gabrielosorio.com.practica3actividades;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MisFavoritosActivity extends NavDrawerActivity {

    Productos[] datos;

    ContactosSQLiteHelper contactos;
    SQLiteDatabase dbContactos;
    Cursor co;

    ProductosSQLiteHelper productos;
    SQLiteDatabase dbProductos;
    Cursor c, c1;

    MisFavoritosSQLiteHelper favoritos;
    SQLiteDatabase dbFavoritos;
    Cursor f;

    SharedPreferences prefs;

    int position1;

    Adapter adaptador;
    TextView mostrar;

    int[] imagen_i = new int[]{R.drawable.pmcmenu1, R.drawable.pmenuensalada, R.drawable.phappymeal3,
            R.drawable.pmcextremehamb4, R.drawable.pflurry4};

    ListView listView;

    private String user;
    private int idUsuario, idFavorito, countProFav = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_mis_favoritos);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contenedorFrame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_mis_favoritos, contentFrameLayout);

        productos = new ProductosSQLiteHelper(this, "ProductossDB", null, 1);
        dbProductos = productos.getReadableDatabase();

        favoritos = new MisFavoritosSQLiteHelper(this, "FavoritosDB", null, 1);
        dbFavoritos = favoritos.getWritableDatabase();

        contactos = new ContactosSQLiteHelper(this, "ContactosDB", null, 1);
        dbContactos = contactos.getReadableDatabase();

        prefs = getSharedPreferences("preferencia", Context.MODE_PRIVATE);
        user = String.valueOf(prefs.getString("usuario", "nodef"));

        co = dbContactos.rawQuery("SELECT * FROM Contactos WHERE usuario='" + user + "'", null);
        if (co.moveToFirst()) {
            idUsuario = co.getInt(0);
        }

        final ArrayList<Integer> arrayListIdsProductos = new ArrayList<Integer>();
        c = dbFavoritos.rawQuery("SELECT * FROM MisFavoritos WHERE idUsuario='" + idUsuario + "'", null);
        //c.moveToFirst();
        while (c.moveToNext()) {
            arrayListIdsProductos.add(c.getInt(2));
            countProFav++;
        }
        Collections.sort(arrayListIdsProductos);//ordena de menor a mayor los elementos del array
        datos = new Productos[countProFav];

        for (int i = 0; i < countProFav; i++) {
            c = dbProductos.rawQuery("SELECT * FROM Productoss WHERE idproducto='" + arrayListIdsProductos.get(i) + "'", null);
            if (c.moveToFirst()) {
                datos[i] = new Productos(imagen_i[c.getInt(0)-1], c.getString(3), c.getString(1), c.getString(2), c.getInt(0));
            }
        }

        adaptador = new Adapter(this, datos);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adaptador);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int n, long l) {
                //Fragment fragment = null;//Para los fragments promociones

                c1 = dbFavoritos.rawQuery("SELECT * FROM MisFavoritos WHERE idUsuario='" + idUsuario +
                        "' AND idProducto='" + arrayListIdsProductos.get(n) + "'", null);
                if (c1.moveToFirst()) {
                    Intent intent = new Intent(getApplicationContext(), AmplInfo.class);
                    intent.putExtra("usuario", user);
                    intent.putExtra("email", correo);
                    intent.putExtra("frag_i", c1.getInt(2)-1);
                    startActivity(intent);
                    finish();

                    Toast.makeText(getApplicationContext(), "Opción elegida: " + (c1.getInt(2) + 1), Toast.LENGTH_SHORT).show();
                    Log.d("producto presionado", ((Productos) adapterView.getItemAtPosition(n)).getNombre());
                }
            }//Para los fragments promociones
        });

        /*mostrar = (TextView) findViewById(R.id.eMostrar);

        Cursor c = dbFavoritos.rawQuery("SELECT * FROM MisFavoritos",null);

        if (c != null) {

            //more to the first row
            c.moveToFirst();

            //iterate over rows
            for (int i = 0; i < c.getCount(); i++) {

                //iterate over the columns
                for(int j = 0; j < c.getColumnNames().length; j++){
                    //append the column value to the string builder and delimit by a pipe symbol
                    mostrar.append(c.getString(j) + "   |   ");
                }
                //add a new line carriage return
                mostrar.append("\n");

                //move to the next row
                c.moveToNext();
            }
            //close the cursor
            c.close();
        }*/
        //mostrar.setText(toString().valueOf(datos[0].getIdproducto()));

    }//Close on create

    class Adapter extends ArrayAdapter<Productos> {
        public Adapter(Context context, Productos[] datos) {
            super(context, R.layout.producto_item, datos);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            //Position va desde 0
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.favs_item, null);

            ImageView imagen = (ImageView) item.findViewById(R.id.iImagen);
            imagen.setImageResource(datos[position].getIdImagen());
            TextView nombre = (TextView) item.findViewById(R.id.tNombre);
            nombre.setText(datos[position].getNombre());
            TextView descripcion = (TextView) item.findViewById(R.id.tDescripcion);
            descripcion.setText(datos[position].getDescripcion());
            TextView precio = (TextView) item.findViewById(R.id.tPrecio);
            precio.setText(datos[position].getPrecio());

            Button bFavorito = (Button) item.findViewById(R.id.tFavorito);
            bFavorito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    f = dbFavoritos.rawQuery("SELECT * FROM MisFavoritos WHERE idUsuario='" + idUsuario +
                          "' AND idProducto='" + datos[position].getIdproducto() + "'", null);

                    if (f.moveToFirst()) {
                        idFavorito = f.getInt(0);
                        dbFavoritos.delete("MisFavoritos", "idFavorito='" + idFavorito + "'", null);
                        Toast.makeText(getApplicationContext(), "Eliminado de favoritos", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent().setClass(getApplicationContext(),MisFavoritosActivity.class);
                        intent1.putExtra("usuario", user);
                        intent1.putExtra("email", correo);
                        intent1.putExtra("sesion", "entro");
                        intent1.putExtra("contrasena", contrasena);
                        startActivity(intent1);
                        finish();
                    } else
                        Toast.makeText(getApplicationContext(), "El producto ya ha sido eliminado", Toast.LENGTH_SHORT).show();
                }
            });

            return (item);
        }
    }
}
