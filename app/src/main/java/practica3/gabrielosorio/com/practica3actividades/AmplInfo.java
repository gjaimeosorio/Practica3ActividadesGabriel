package practica3.gabrielosorio.com.practica3actividades;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;

public class AmplInfo extends NavDrawerActivity {

    String user, correo, sesion, contrasena;
    int j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_ampl_info);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contenedorFrame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_ampl_info, contentFrameLayout);

        Bundle extras = getIntent().getExtras();
        user = extras.getString("usuario");
        correo = extras.getString("email");
        j = extras.getInt("frag_i");
        sesion = extras.getString("sesion");
        contrasena = extras.getString("contrasena");

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
            case (4):
                FlurryFragment fragment4 = new FlurryFragment();
                ft.replace(R.id.contenedorFrame, fragment4).commit();
                break;
        }

    }//Close OnCreate
}
