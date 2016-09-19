package practica3.gabrielosorio.com.practica3actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {

    Button bAceptar, bCancelar;
    EditText eRUsuario, eRContrasena, eRRContrasena, eCorreoE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        bAceptar = (Button) findViewById(R.id.bAceptar);
        bCancelar = (Button) findViewById(R.id.bCancelar);
        eRUsuario = (EditText) findViewById(R.id.eRUsuario);
        eRContrasena = (EditText) findViewById(R.id.eRContrasena);
        eRRContrasena = (EditText) findViewById(R.id.eRRContrasena);
        eCorreoE = (EditText) findViewById(R.id.eCorreoE);

        bAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        /*      Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.putExtra("usuario",eRUsuario.getText().toString());
                intent.putExtra("contrasena",eRContrasena.getText().toString());
                startActivity(intent);*/

                Intent intent = new Intent(RegistroActivity.this, LogginActivity.class);
                intent.putExtra("usuario",eRUsuario.getText().toString());
                intent.putExtra("contrasena",eRContrasena.getText().toString());
                intent.putExtra("email",eCorreoE.getText().toString());

                if(eRContrasena.getText().toString().equals(eRRContrasena.getText().toString())) {
                    setResult(RESULT_OK, intent);
                    finish();
                }else{
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_LONG);
                    toast1.show();
                }
            }
        });

        bCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED,intent);
                finish();
            }
        });
    }
}
