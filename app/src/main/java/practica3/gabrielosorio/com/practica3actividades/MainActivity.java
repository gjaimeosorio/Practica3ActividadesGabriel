package practica3.gabrielosorio.com.practica3actividades;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String user, correo;
    private String[] opciones = new String[] {"Clasificación","Perfil","Promociones","Cerrar sesión"};//
    private DrawerLayout drawerLayout;//
    private ListView list;//
    private ActionBarDrawerToggle drawerToggle;//
    private Productos[] datos = new Productos[]{
            new Productos(R.drawable.pmcmenu1,15000,"McMENÚ","Lunes oferta 2x1"),
            new Productos(R.drawable.pmenuensalada,12000,"MENÚ ENSALADA","Para los amantes de lo sano"),
            new Productos(R.drawable.phappymeal3,15000,"HAPPY MEAL","Para los mas pequeños"),
            new Productos(R.drawable.pmcextremehamb4,6000,"McEXTREME","Con todo el sabor de casa")};

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();//Recibo info de perfil y correo
        user = extras.getString("usuario");
        correo = extras.getString("email");

        ActionBar actionBar = getSupportActionBar();//
        if (actionBar != null){//
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);//
            actionBar.setDisplayHomeAsUpEnabled(true);//
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.contenedorPrincipal);//
        list = (ListView) findViewById(R.id.menuIzq);//

        list.setAdapter(new ArrayAdapter<String>(getSupportActionBar().getThemedContext(),
                android.R.layout.simple_list_item_1, opciones));//

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {//
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {//
                //Fragment fragment = null;//
                Intent intent;
                switch (i){
                    case(0):
                        intent = new Intent(MainActivity.this, ClasificacionActivity.class);
                        intent.putExtra("usuario",user);
                        intent.putExtra("email",correo);
                        startActivity(intent);
                        finish();
                        break;
                    case(1):
                        intent = new Intent(MainActivity.this, PerfilActivity.class);
                        intent.putExtra("usuario",user);
                        intent.putExtra("email",correo);
                        startActivity(intent);
                        finish();
                        break;
                    case(2):
                        intent = new Intent(MainActivity.this, MainActivity.class);
                        intent.putExtra("usuario",user);
                        intent.putExtra("email",correo);
                        startActivity(intent);
                        finish();
                        break;
                    case(3):
                        intent = new Intent(MainActivity.this, LogginActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
                //if (i != 3) {//Solo para fragments
                //    FragmentManager fragmentManager = getSupportFragmentManager();
                //   fragmentManager.beginTransaction().replace(R.id.contenedorFrame, fragment).commit();
                //}
                list.setItemChecked(i,true);
                drawerLayout.closeDrawer(list);
            }
        });

        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.abierto, R.string.cerrado);

        drawerLayout.setDrawerListener(drawerToggle);//

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
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.LEFT);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


