package practica3.gabrielosorio.com.practica3actividades;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NavDrawerActivity extends AppCompatActivity {

    private String[] opciones = new String[] {"Clasificación","Perfil","Promociones","Cerrar sesión"};//
    private DrawerLayout drawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle drawerToggle;
    String user, correo, sesion, contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);
        Bundle extras = getIntent().getExtras();
        user = extras.getString("usuario");
        correo = extras.getString("email");
        sesion = extras.getString("sesion");
        contrasena = extras.getString("contrasena");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.contenedorPrincipal);
        listView = (ListView) findViewById(R.id.menuIzq);

        listView.setAdapter(new ArrayAdapter<String>(getSupportActionBar().getThemedContext(),
                android.R.layout.simple_list_item_1, opciones));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Fragment fragment = null;
                Intent intent;
                switch (i){
                    case(0):
                        intent = new Intent(NavDrawerActivity.this, ClasificacionActivity.class);
                        intent.putExtra("usuario",user);
                        intent.putExtra("email",correo);
                        intent.putExtra("contrasena",contrasena);
                        intent.putExtra("sesion",sesion);
                        startActivity(intent);
                        finish();
                        break;
                    case(1):
                        intent = new Intent(NavDrawerActivity.this, PerfilActivity.class);
                        intent.putExtra("usuario",user);
                        intent.putExtra("email",correo);
                        intent.putExtra("contrasena",contrasena);
                        intent.putExtra("sesion",sesion);
                        startActivity(intent);
                        finish();
                        break;
                    case(2):
                        intent = new Intent(NavDrawerActivity.this, MainActivity.class);
                        intent.putExtra("usuario",user);
                        intent.putExtra("email",correo);
                        intent.putExtra("contrasena",contrasena);
                        intent.putExtra("sesion",sesion);
                        startActivity(intent);
                        finish();
                        break;
                    case(3):
                        intent = new Intent(NavDrawerActivity.this, LogginActivity.class);
                        intent.putExtra("usuario",user);
                        intent.putExtra("email",correo);
                        intent.putExtra("contrasena",contrasena);
                        intent.putExtra("sesion","nada");
                        startActivity(intent);
                        finish();
                        break;

                }
/*                if (i != 3) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.contenedorFrame, fragment).commit();
                }*/
                listView.setItemChecked(i,true);
                drawerLayout.closeDrawer(listView);
            }
        });

        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.abierto, R.string.cerrado);

        drawerLayout.setDrawerListener(drawerToggle);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.LEFT);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}