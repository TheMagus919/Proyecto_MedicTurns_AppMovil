package com.softagus.medicturns.ui.historialMedico.historial;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.softagus.medicturns.Request.ApiClientRetrofit;
import com.softagus.medicturns.modelo.Turno;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistorialViewModel extends AndroidViewModel {

    private MutableLiveData<List<Turno>> listaTurnos;
    private Context context;

    public HistorialViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<List<Turno>> getListaTurnos(){
        if(listaTurnos==null){
            listaTurnos=new MutableLiveData<>();
        }
        return listaTurnos;
    }

    public void obtenerInformacionTurno(Bundle bundle) {
        int dni = (int) bundle.get("dni");
        String token = ApiClientRetrofit.leerToken(context);
        ApiClientRetrofit.ApiMedicTurns ap = ApiClientRetrofit.getApiMedicTurns();
        Call<List<Turno>> llamada = ap.obtenerHistorialMedico(token, dni);
        llamada.enqueue(new Callback<List<Turno>>() {
            @Override
            public void onResponse(Call<List<Turno>> call, Response<List<Turno>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && !response.body().isEmpty()) {
                        listaTurnos.postValue(response.body());
                    } else {
                        Log.d("salida", "La respuesta está vacía");
                    }
                    listaTurnos.postValue(response.body());
                } else {
                    Log.d("salida", response.raw().message());
                    Toast.makeText(context, "Error cargando informacion del turno", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Turno>> call, Throwable t) {
                Log.d("salida", "Error: " + t.getMessage());
                Toast.makeText(context, "Error cargando informacion.", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
