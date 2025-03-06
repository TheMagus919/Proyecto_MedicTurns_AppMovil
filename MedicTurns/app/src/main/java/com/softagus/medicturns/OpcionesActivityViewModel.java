package com.softagus.medicturns;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softagus.medicturns.Request.ApiClientRetrofit;
import com.softagus.medicturns.modelo.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OpcionesActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> userM;

    private Usuario user = null;
    public OpcionesActivityViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
    }
    public void setInformacion(){
        String token = ApiClientRetrofit.leerToken(context);
        ApiClientRetrofit.ApiMedicTurns ap= ApiClientRetrofit.getApiMedicTurns();
        Call<Usuario> llamada = ap.obtenerPerfil(token);
        llamada.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){
                    Log.d("salida",response.message());
                    userM.postValue(response.body());
                }else{
                    Log.d("salida",response.message());
                    Toast.makeText(context, "Error Cargando Perfil", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.d("salida",t.getMessage());
                Toast.makeText(context, "Error Cargando Perfil", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public LiveData<Usuario> getUserM(){
        if(userM==null){
            userM = new MutableLiveData<>();
        }
        return userM;
    }
}
