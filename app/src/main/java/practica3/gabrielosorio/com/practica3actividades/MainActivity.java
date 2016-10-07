package practica3.gabrielosorio.com.practica3actividades;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends NavDrawerActivity {

    String user, correo, sesion, contrasena;

    private Productos[] datos = new Productos[]{
            new Productos(R.drawable.pmcmenu1,15000,"McMENÚ","Lunes oferta 2x1"),
            new Productos(R.drawable.pmenuensalada,12000,"MENÚ ENSALADA","Para los amantes de lo sano"),
            new Productos(R.drawable.phappymeal3,8000,"HAPPY MEAL","Para los mas pequeños"),
            new Productos(R.drawable.pmcextremehamb4,13000,"McEXTREME","Con todo el sabor de casa"),
            new Productos(R.drawable.pflurry4,0,"McHELADOS","Por la compra de 20000 pesos o más")};

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contenedorFrame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_main, contentFrameLayout);

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

            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.producto_item, null);

            ImageView imagen = (ImageView) item.findViewById(R.id.iImagen);
            imagen.setImageResource(datos[position].getIdImagen());

            TextView nombre = (TextView) item.findViewById(R.id.tNombre);
            nombre.setText(datos[position].getNombre());
            TextView descripcion = (TextView) item.findViewById(R.id.tDescripcion);
            descripcion.setText(datos[position].getDescripcion());
            TextView precio = (TextView) item.findViewById(R.id.tPrecio);
            precio.setText(String.valueOf(datos[position].getPrecio()));
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


