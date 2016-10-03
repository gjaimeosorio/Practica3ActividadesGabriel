package practica3.gabrielosorio.com.practica3actividades;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

public class AmplInfo extends AppCompatActivity {

    private String[] opciones = new String[] {"Clasificación","Perfil","Promociones"};//
    private DrawerLayout drawerLayout;
    private ListView list;
    private ActionBarDrawerToggle drawerToggle;

    String user, correo;
    int j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ampl_info);
        Bundle extras = getIntent().getExtras();
        user = extras.getString("usuario");
        correo = extras.getString("email");
        j = extras.getInt("frag_i");

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        switch (j) {
            case (0):
                McmenuFragment fragment = new McmenuFragment();
                ft.replace(R.id.contenedorFrame, fragment).commit();
                break;
            case (1):
                McmenuEnsaFragment fragment1 = new McmenuEnsaFragment();
                ft.replace(R.id.contenedorFrame, fragment1).commit();
                break;
            case (2):
                McHappyFragment fragment2 = new McHappyFragment();
                ft.replace(R.id.contenedorFrame, fragment2).commit();
                break;
            case (3):
                McExtremeFragment fragment3 = new McExtremeFragment();
                ft.replace(R.id.contenedorFrame, fragment3).commit();
                break;

        }

        ActionBar actionBar = getSupportActionBar();//
        if (actionBar != null){//
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);//
            actionBar.setDisplayHomeAsUpEnabled(true);//
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.contenedorPrincipal);//
        list = (ListView) findViewById(R.id.menuIzq);//

        list.setAdapter(new ArrayAdapter<String>(getSupportActionBar().getThemedContext(),
                android.R.layout.simple_list_item_1, opciones));//

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fragment fragment = null;
                Intent intent;
                switch (i){
                    case(0):
                        intent = new Intent(AmplInfo.this, ClasificacionActivity.class);
                        intent.putExtra("usuario",user);
                        intent.putExtra("email",correo);
                        startActivity(intent);
                        finish();
                        break;
                    case(1):
                        intent = new Intent(AmplInfo.this, PerfilActivity.class);
                        intent.putExtra("usuario",user);
                        intent.putExtra("email",correo);
                        startActivity(intent);
                        finish();
                        break;
                    case(2):
                        intent = new Intent(AmplInfo.this, MainActivity.class);
                        intent.putExtra("usuario",user);
                        intent.putExtra("email",correo);
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
    }//Close OnCreate

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
