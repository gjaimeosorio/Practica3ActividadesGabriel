package practica3.gabrielosorio.com.practica3actividades;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends NavDrawerActivity {

    String user, correo, sesion;

    ContactosSQLiteHelper contactos;
    SQLiteDatabase dbContactos;
    Cursor co;

    ProductosSQLiteHelper productos;
    SQLiteDatabase dbProductos;
    ContentValues dataProdBD;
    Cursor c, c1;

    MisFavoritosSQLiteHelper favoritos;
    Cursor f;

    private Productos[] datos = new Productos[5];/*{
            new Productos(R.drawable.pmcmenu1,15000,"McMENÚ","Lunes oferta 2x1"),
            new Productos(R.drawable.pmenuensalada,12000,"MENÚ ENSALADA","Para los amantes de lo sano"),
            new Productos(R.drawable.phappymeal3,8000,"HAPPY MEAL","Para los mas pequeños"),
            new Productos(R.drawable.pmcextremehamb4,13000,"McEXTREME","Con todo el sabor de casa"),
            new Productos(R.drawable.pflurry4,0,"McHELADOS","Por la compra de 20000 pesos o más")};*/

    String[] productos_i = new String[]{"McMENÚ","MENÚ ENSALADA","HAPPY MEAL","McEXTREME","McHELADOS"};
    String[] descripcion_i = new String[]{"Lunes oferta 2x1","Para los amantes de lo sano"
            ,"Para los mas pequeños","Con todo el sabor de casa","Por la compra de 20000 pesos o más"};
    String[] precios_i = new String[]{"15000","12000","8000","13000","0"};
    int[] imagen_i = new int[]{R.drawable.pmcmenu1,R.drawable.pmenuensalada,R.drawable.phappymeal3,
            R.drawable.pmcextremehamb4,R.drawable.pflurry4};

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contenedorFrame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_main, contentFrameLayout);

        productos = new ProductosSQLiteHelper(this, "ProductosDB", null, 1);
        dbProductos = productos.getWritableDatabase();

        favoritos = new MisFavoritosSQLiteHelper(this, "FavoritosDB", null, 1);
        SQLiteDatabase dbFavoritos = favoritos.getWritableDatabase();

        contactos = new ContactosSQLiteHelper(this,"ContactosDB",null,1);
        dbContactos = contactos.getReadableDatabase();
/*
        tButton = (ToggleButton)findViewById(R.id.tFavorito);

        tButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheked) {

                if (isCheked) {
                    // The toggle is enabled
                    status = "1";
                } else {
                    // The toggle is disabled
                    status = "0";
                }

            }
        });
*/
        for(int i = 0; i < 5; i++) {
            dbProductos.execSQL("INSERT INTO Productos VALUES(null, '" + productos_i[i] + "', '" + descripcion_i[i]
                    + "', " + " '" + precios_i[i] + "')");
        }
        int aux=2;
        for (int j = 0; j < 5; j++) {
            c = dbProductos.rawQuery("SELECT * FROM Productos WHERE idproducto='"+(aux+j)+"'",null);
            if(c.moveToFirst()){
                datos[j] = new Productos(imagen_i[j], c.getString(3), c.getString(1), c.getString(2),false);
                dataProdBD = new ContentValues();
                dataProdBD.put("producto", c.getString(3));
                dataProdBD.put("descripcion", c.getString(1));
                dataProdBD.put("precio", c.getString(2));
            }
        }
        //close the cursor
        //c.close();

        Bundle extras = getIntent().getExtras();//Recibo info de perfil y correo
        user = extras.getString("usuario");
        correo = extras.getString("email");
        sesion = extras.getString("sesion");
        contrasena = extras.getString("contrasena");

        Adapter adaptador = new Adapter(this, datos);//Para el ListView

        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(adaptador);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Fragment fragment = null;//Para los fragments promociones

                Intent intent = new Intent(MainActivity.this, AmplInfo.class);
                intent.putExtra("usuario", user);
                intent.putExtra("email", correo);
                intent.putExtra("frag_i",i);
                startActivity(intent);
                finish();

                Toast.makeText(getApplicationContext(),"Opción elegida: "+(i+1),Toast.LENGTH_SHORT).show();
                Log.d("producto presionado", ((Productos)adapterView.getItemAtPosition(i)).getNombre());
            }//Para los fragments promociones
        });

    }//Close OnCreate

    class Adapter extends ArrayAdapter<Productos> {
        public Adapter(Context context, Productos[] datos) {
            super(context, R.layout.producto_item, datos);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //Position va desde 0
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.producto_item, null);

            ImageView imagen = (ImageView) item.findViewById(R.id.iImagen);
            imagen.setImageResource(datos[position].getIdImagen());
            TextView nombre = (TextView) item.findViewById(R.id.tNombre);
            nombre.setText(datos[position].getNombre());
            TextView descripcion = (TextView) item.findViewById(R.id.tDescripcion);
            descripcion.setText(datos[position].getDescripcion());
            TextView precio = (TextView) item.findViewById(R.id.tPrecio);
            precio.setText(datos[position].getPrecio());
            ToggleButton tButton = (ToggleButton) item.findViewById(R.id.tFavorito);
            tButton.setChecked(Boolean.valueOf(datos[position].getCheck(tButton)));
            co = dbContactos.rawQuery("SELECT * FROM Contactos WHERE usuario='"+user+"'",null);
            //c1 = dbProductos.rawQuery("SELECT * FROM Productos WHERE idproducto='"+position+1+"'",null);

            if(datos[0].getCheck(tButton)){
                c1 = dbProductos.rawQuery("SELECT * FROM Productos WHERE idproducto='"+1+"'",null);
                //favoritos.AddFav(co.getInt(0),c1.getInt(0));
                Toast toast1 = Toast.makeText(getApplicationContext(), "Producto agregado a favoritos", Toast.LENGTH_LONG);
                toast1.show();
            }else{

            }

            return (item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        Toast toast;
        switch (id){
            case R.id.mMiperfil:
                toast = Toast.makeText(getApplicationContext(), "A perfil", Toast.LENGTH_SHORT);
                toast.show();
                intent = new Intent(MainActivity.this, PerfilActivity.class);
                intent.putExtra("usuario",user);
                intent.putExtra("email",correo);
                startActivity(intent);
                break;
            case R.id.mClasifica:
                toast = Toast.makeText(getApplicationContext(), "A clasificacion", Toast.LENGTH_SHORT);
                toast.show();
                intent = new Intent(MainActivity.this, ClasificacionActivity.class);
                intent.putExtra("usuario",user);
                intent.putExtra("email",correo);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}


