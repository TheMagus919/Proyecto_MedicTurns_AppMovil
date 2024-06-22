package com.softagus.medicturns;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorEvent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.softagus.medicturns.Request.ApiClientRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {
    private Context context;
    private int mov=0;
    private MutableLiveData<Boolean> resultado = new MutableLiveData<>();
    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public MutableLiveData<Boolean> getResultado() {
        if(resultado == null){
            resultado = new MutableLiveData<>();
        }
        return resultado;
    }

    public void login(String usuario, String contraseña) {
        ApiClientRetrofit.ApiMedicTurns ap= ApiClientRetrofit.getApiMedicTurns();
        Call<String> llamada = ap.login(usuario,contraseña);
        llamada.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    ApiClientRetrofit.guardarToken(context,"Bearer "+response.body());
                    Intent intent = new Intent(context, OpcionesActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    resultado.postValue(true);
                }else{
                    Log.d("salida",response.message());
                    Toast.makeText(context, "Error al iniciar sesion", Toast.LENGTH_SHORT).show();
                    resultado.postValue(false);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("salida",t.getMessage());
                Toast.makeText(context, "Error al iniciar sesion", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
