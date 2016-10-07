package practica3.gabrielosorio.com.practica3actividades;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogginActivity extends AppCompatActivity implements View.OnClickListener {

    Button bRegistro, bEntrar;
    EditText eContrasena, eUsuario;
    String user = "";
    String contrasena = "";
    String correo;
    String sesion;

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);

        prefs = getPreferences(MODE_PRIVATE);//Preferencias compartidas
        editor = prefs.edit();
        refreshPrefs();

        Bundle extras = getIntent().getExtras();//Recibo info de perfil y correo
        if(extras != null) {
            user = extras.getString("usuario");
            correo = extras.getString("email");
            sesion = extras.getString("sesion");
            editor.putString("sesion",sesion);
            editor.commit();
        }
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


        if (sesion.equals("entro")) {//****ACA
            Intent intent = new Intent(LogginActivity.this,MainActivity.class);
            intent.putExtra("usuario",user);
            intent.putExtra("email",correo);
            intent.putExtra("sesion",sesion);
            intent.putExtra("contrasena",contrasena);

            startActivity(intent);
            finish();
        }

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
                if (prefs.getString(user,contrasena).length()==0) {
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Aún no se ha registrado", Toast.LENGTH_LONG);
                    toast1.show();
                }
                else if((eUsuario.getText().toString().equals(user))&&(eContrasena.getText().toString().equals(contrasena))) {
                    Intent intent1 = new Intent().setClass(this, MainActivity.class);
                    intent1.putExtra("usuario",user);
                    intent1.putExtra("email",correo);
                    intent1.putExtra("sesion","entro");
                    intent1.putExtra("contrasena",contrasena);
                    savePref(view);
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
            user = data.getExtras().getString("usuario");
            contrasena = data.getExtras().getString("contrasena");
            correo = data.getExtras().getString("email");
            Log.d("usuario",user);//Muestra lo que pasa en debug
            Log.d("contraseña",contrasena);//Ayuda a ver si se hace lo que quiero
            //Toast.makeText(this, "user: "+user+" contrasena: "+contrasena,Toast.LENGTH_SHORT).show();
        }
        if (requestCode==1234 && resultCode == RESULT_CANCELED){
            Log.d("mensaje","no se cargaron datos");
        }
    }

    public void savePref(View view){
        editor.putString("usuario",user);
        editor.putString("contrasena",contrasena);
        editor.putString("email",correo);
        editor.putString("sesion","entro");
        editor.commit();
        refreshPrefs();
    }

        public void refreshPrefs(){
            user = String.valueOf(prefs.getString("usuario","nodef"));
            correo = String.valueOf(prefs.getString("email","nodef"));
            contrasena = String.valueOf(prefs.getString("contrasena","nodef"));
            sesion = String.valueOf(prefs.getString("sesion","nodef"));
    }

    public void ClearPrefs(){
        editor.clear();
        editor.commit();
    }

}

