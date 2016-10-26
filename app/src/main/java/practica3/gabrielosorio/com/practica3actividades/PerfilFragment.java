package practica3.gabrielosorio.com.practica3actividades;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {


    public PerfilFragment() {
        // Required empty public constructor
    }

    String user, correo, contrasena, sesion;
    TextView eUser, eCorreo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle extras) {

        eUser = (TextView) container.findViewById(R.id.eName);
        eCorreo = (TextView) container.findViewById(R.id.eMail);
        eUser.setText(user);
        eCorreo.setText(correo);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }
}
