package practica3.gabrielosorio.com.practica3actividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MisFavoritosActivity extends NavDrawerActivity {

    TextView mostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_mis_favoritos);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contenedorFrame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_mis_favoritos, contentFrameLayout);

        mostrar = (TextView)findViewById(R.id.mostrarFav);

    }
}
