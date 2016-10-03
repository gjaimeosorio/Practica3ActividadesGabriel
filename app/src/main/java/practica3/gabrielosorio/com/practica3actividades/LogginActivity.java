package practica3.gabrielosorio.com.practica3actividades;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogginActivity extends AppCompatActivity implements View.OnClickListener {

    Button bRegistro, bEntrar;
    EditText eContrasena, eUsuario;
    String user = "";
    String contrasena = "";
    String correo;
    boolean flag = false;

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);

        bRegistro = (Button) findViewById(R.id.bRegistro);
        bEntrar = (Button) findViewById(R.id.bEntrar);
        eContrasena = (EditText)findViewById(R.id.eContrasena);
        eUsuario = (EditText) findViewById(R.id.eUsuario);
        bRegistro.setOnClickListener(this);
        bEntrar.setOnClickListener(this);
        /*    Bundle extras = getIntent().getExtras();
    String user = extras.getString("usuario");
    String contrasena = extras.getString("contrasena");
    ********************/
        prefs = getPreferences(MODE_PRIVATE);
        editor = prefs.edit();
        //refreshPrefs();

    }//Close OnCreate

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.bRegistro:
                Intent intent = new Intent(this, RegistroActivity.class);
                startActivityForResult(intent,1234);
                break;
            case R.id.bEntrar:
                if (!flag) {
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Aún no se ha registrado", Toast.LENGTH_LONG);
                    toast1.show();
                }
                else if((eUsuario.getText().toString().equals(user))&&(eContrasena.getText().toString().equals(contrasena))) {
                    Intent intent1 = new Intent().setClass(this, MainActivity.class);
                    intent1.putExtra("usuario",eUsuario.getText().toString());
                    intent1.putExtra("email",correo);
                    startActivity(intent1);
                    finish();
                }
                else{
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_LONG);
                    toast1.show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1234 && resultCode == RESULT_OK){
            flag = true;
            user = data.getExtras().getString("usuario");
            contrasena = data.getExtras().getString("contrasena");
            correo = data.getExtras().getString("email");
            Log.d("user",user);//Muestra lo que pasa en debug
            Log.d("contraseña",contrasena);//Ayuda a ver si se hace lo que quiero
            //Toast.makeText(this, "user: "+user+" contrasena: "+contrasena,Toast.LENGTH_SHORT).show();
        }
        if (requestCode==1234 && resultCode == RESULT_CANCELED){
            Log.d("mensaje","no se cargaron datos");
        }
    }

    public void savePref(View view){
        EditText campo = (EditText) findViewById(R.id.eUsuario);
        String usuario = campo.getText().toString();
        editor.putString("user", usuario);
        editor.commit();
        //refreshPrefs();
    }



}

