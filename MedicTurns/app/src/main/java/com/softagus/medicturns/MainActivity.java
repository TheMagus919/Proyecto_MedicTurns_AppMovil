package com.softagus.medicturns;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.softagus.medicturns.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private LoginViewModel lg;
    private boolean as;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //solicitarPermisos();
        lg = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);
        lg.getResultado().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean bool) {
                if(bool){
                    binding.edEmail.setText("");
                    binding.edPassword.setText("");
                    binding.txErrorEmail.setVisibility(View.INVISIBLE);
                }else{
                    binding.txErrorEmail.setVisibility(View.VISIBLE);
                }

            }
        });
        binding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lg.login(binding.edEmail.getText().toString(),binding.edPassword.getText().toString());
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
/*
    public void solicitarPermisos(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && (checkSelfPermission(ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) ||
                (checkSelfPermission(ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED)){
            requestPermissions(new String[]{ACCESS_FINE_LOCATION,ACCESS_COARSE_LOCATION},1000);
        }
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && checkSelfPermission(android.Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},1000);
        }
    }
*/
    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}