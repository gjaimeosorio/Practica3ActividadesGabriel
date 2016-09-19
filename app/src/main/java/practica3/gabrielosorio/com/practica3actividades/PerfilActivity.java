package practica3.gabrielosorio.com.practica3actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class PerfilActivity extends AppCompatActivity {

    String user, correo;
    TextView eUser, eCorreo;

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_perfil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.mPpal:
                Toast toast1 = Toast.makeText(getApplicationContext(), "A publicidad", Toast.LENGTH_SHORT);
                toast1.show();
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("usuario",user);
                intent.putExtra("email",correo);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
