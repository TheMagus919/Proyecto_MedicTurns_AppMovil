package com.softagus.medicturns;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.softagus.medicturns.Request.ApiClientRetrofit;
import com.softagus.medicturns.databinding.ActivityOpcionesBinding;
import com.softagus.medicturns.modelo.Paciente;
import com.softagus.medicturns.modelo.Usuario;
import com.softagus.medicturns.ui.salir.SalirActivity;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OpcionesActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityOpcionesBinding binding;
    private OpcionesActivityViewModel om;
    private Context context;
    private SalirActivity sl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        om= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(OpcionesActivityViewModel.class);
        binding = ActivityOpcionesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarOpciones.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_historialMedico, R.id.nav_inicio, R.id.nav_agenda, R.id.nav_cargarPaciente, R.id.nav_pacientes,R.id.nav_cargarPaciente,R.id.detallesTurnoFragment,R.id.reporteFragment,R.id.infoTurnoFragment,R.id.informacionTurnoFragment,R.id.historialFragment,R.id.historialPacienteFragment,R.id.consultarPacienteFragment , R.id.nav_salir)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_opciones);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        om.getUserM().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario user) {
                Menu menu = navigationView.getMenu();
                TextView txtName = navigationView.getHeaderView(0).findViewById(R.id.txNom);
                TextView txtEmail = navigationView.getHeaderView(0).findViewById(R.id.txMail);
                ImageView imageView  = navigationView.getHeaderView(0).findViewById(R.id.imgAvatar);

                String nombre = user.getNombre() + " " + user.getApellido();
                txtName.setText(nombre);
                txtEmail.setText(user.getEmail().toString());

                if (user.getRol().getNombre().equals("Medico")) {
                    imageView.setImageResource(R.drawable.doctor);

                    menu.findItem(R.id.nav_agenda).setVisible(true);
                    menu.findItem(R.id.nav_historialMedico).setVisible(true);
                    menu.findItem(R.id.nav_cargarPaciente).setVisible(false);
                    menu.findItem(R.id.nav_pacientes).setVisible(false);
                } else if (user.getRol().getNombre().equals("Secretaria")) {
                    imageView.setImageResource(R.drawable.secretario);
                    menu.findItem(R.id.nav_pacientes).setVisible(true);
                    menu.findItem(R.id.nav_cargarPaciente).setVisible(true);
                    menu.findItem(R.id.nav_historialMedico).setVisible(false);
                    menu.findItem(R.id.nav_agenda).setVisible(false);
                }
            }
        });
        om.setInformacion();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.opciones, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_opciones);
        om.setInformacion();
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void mostrarDialogo(MenuItem item) {
        String token = ApiClientRetrofit.leerToken(OpcionesActivity.this);
        Bundle bundle = new Bundle();
        bundle.putSerializable("token",(Serializable)token);
        sl.mostrarDialogo(OpcionesActivity.this, bundle);
    }
}