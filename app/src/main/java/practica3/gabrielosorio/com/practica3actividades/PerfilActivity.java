package practica3.gabrielosorio.com.practica3actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PerfilActivity extends NavDrawerActivity {

    String user, correo, contrasena, sesion;
    TextView eUser, eCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_perfil);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contenedorFrame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_perfil, contentFrameLayout);

        Bundle extras = getIntent().getExtras();
        user = extras.getString("usuario");
        correo = extras.getString("email");
        sesion = extras.getString("sesion");
        contrasena = extras.getString("contrasena");

        eUser = (TextView) findViewById(R.id.eName);
        eCorreo = (TextView) findViewById(R.id.eMail);
        eUser.setText(user);
        eCorreo.setText(correo);

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
        }
        return super.onOptionsItemSelected(item);
    }
}
