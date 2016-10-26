package practica3.gabrielosorio.com.practica3actividades;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {

    Button bAceptar, bCancelar, bMostrar;
    EditText eRUsuario, eRContrasena, eRRContrasena, eCorreoE;
    ContactosSQLiteHelper contactos;
    SQLiteDatabase dbContactos;
    ContentValues dataBD;
    String user, contrasena, correo;
    TextView mostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        contactos = new ContactosSQLiteHelper(this, "ContactosDB", null, 1);
        dbContactos = contactos.getWritableDatabase();

        bAceptar = (Button) findViewById(R.id.bAceptar);
        bCancelar = (Button) findViewById(R.id.bCancelar);
        bMostrar = (Button) findViewById(R.id.bMostrar);
        eRUsuario = (EditText) findViewById(R.id.eRUsuario);
        eRContrasena = (EditText) findViewById(R.id.eRContrasena);
        eRRContrasena = (EditText) findViewById(R.id.eRRContrasena);
        eCorreoE = (EditText) findViewById(R.id.eCorreoE);
        mostrar = (TextView) findViewById(R.id.eMostrar);

        bAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user = eRUsuario.getText().toString();
                contrasena = eRContrasena.getText().toString();
                correo = eCorreoE.getText().toString();

                dataBD = new ContentValues();
                dataBD.put("usuario", user);
                dataBD.put("contrasena", contrasena);
                dataBD.put("email", correo);

                if (user.equals("")) {
                    Toast toast2 = Toast.makeText(getApplicationContext(), "Ingrese un usuario", Toast.LENGTH_LONG);
                    toast2.show();
                } else if (contrasena.equals("")) {
                    Toast toast3 = Toast.makeText(getApplicationContext(), "Ingrese una contraseña", Toast.LENGTH_LONG);
                    toast3.show();
                } else {
                    dbContactos.execSQL("INSERT INTO Contactos VALUES(null, '" + user + "', '" + contrasena
                            + "', " + " '" + correo + "')");

        /*      Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.putExtra("usuario",eRUsuario.getText().toString());
                intent.putExtra("contrasena",eRContrasena.getText().toString());
                startActivity(intent);*/

                    Intent intent = new Intent(RegistroActivity.this, LogginActivity.class);
                /*intent.putExtra("usuario",eRUsuario.getText().toString());
                intent.putExtra("contrasena",eRContrasena.getText().toString());
                intent.putExtra("email",eCorreoE.getText().toString());*/

                    if (eRContrasena.getText().toString().equals(eRRContrasena.getText().toString())) {
                        setResult(RESULT_OK, intent);
                        Toast toast = Toast.makeText(getApplicationContext(), "Usuario registrado", Toast.LENGTH_LONG);
                        toast.show();
                        finish();
                    } else {
                        Toast toast1 = Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_LONG);
                        toast1.show();
                    }
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

        bMostrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Cursor c = dbContactos.rawQuery("SELECT * FROM Contactos",null);

                if (c != null) {

                    //more to the first row
                    c.moveToFirst();

                    //iterate over rows
                    for (int i = 0; i < c.getCount(); i++) {

                        //iterate over the columns
                        for(int j = 0; j < c.getColumnNames().length; j++){
                            if(j==2)
                                j = 3;
                            //append the column value to the string builder and delimit by a pipe symbol
                            mostrar.append(c.getString(j) + "   |   ");
                        }
                        //add a new line carriage return
                        mostrar.append("\n");

                        //move to the next row
                        c.moveToNext();
                    }
                    //close the cursor
                    c.close();
                }

            }
        });
    }
}
