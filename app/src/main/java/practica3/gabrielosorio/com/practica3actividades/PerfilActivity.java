package practica3.gabrielosorio.com.practica3actividades;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PerfilActivity extends AppCompatActivity {

    String user, correo;
    TextView eUser, eCorreo;
    private String[] opciones = new String[] {"Clasificación", "Perfil","Promociones"};//
    private DrawerLayout drawerLayout;//
    private ListView list;//
    private ActionBarDrawerToggle drawerToggle;//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        Bundle extras = getIntent().getExtras();
        user = extras.getString("usuario");
        correo = extras.getString("email");
        eUser = (TextView) findViewById(R.id.eName);
        eCorreo = (TextView) findViewById(R.id.eMail);
        eUser.setText(user);
        eCorreo.setText(correo);

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
                        intent = new Intent(PerfilActivity.this, ClasificacionActivity.class);
                        intent.putExtra("usuario",user);
                        intent.putExtra("email",correo);
                        startActivity(intent);
                        finish();
                        break;
                    case(1):
                        intent = new Intent(PerfilActivity.this, PerfilActivity.class);
                        intent.putExtra("usuario",user);
                        intent.putExtra("email",correo);
                        startActivity(intent);
                        finish();
                        break;
                    case(2):
                        intent = new Intent(PerfilActivity.this, MainActivity.class);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_perfil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Toast toast;
        Intent intent;
        switch (id){
            case R.id.mPpal:
                toast = Toast.makeText(getApplicationContext(), "A promociones", Toast.LENGTH_SHORT);
                toast.show();
                intent = new Intent(this, MainActivity.class);
                intent.putExtra("usuario",user);
                intent.putExtra("email",correo);
                startActivity(intent);
                finish();
                break;
            case R.id.mClasifica:
                toast = Toast.makeText(getApplicationContext(), "A clasificación", Toast.LENGTH_SHORT);
                toast.show();
                intent = new Intent(PerfilActivity.this, ClasificacionActivity.class);
                intent.putExtra("usuario",user);
                intent.putExtra("email",correo);
                startActivity(intent);
                finish();
                break;
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.LEFT);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
