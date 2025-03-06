package com.softagus.medicturns.ui.agenda;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softagus.medicturns.Request.ApiClientRetrofit;
import com.softagus.medicturns.modelo.Turno;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgendaViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<List<Turno>> listaTurnos;

    public AgendaViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<List<Turno>> getListaTurnos(){
        if(listaTurnos==null){
            listaTurnos=new MutableLiveData<>();
        }
        return listaTurnos;
    }

    public void armarLista(){
        String token = ApiClientRetrofit.leerToken(context);
        ApiClientRetrofit.ApiMedicTurns ap= ApiClientRetrofit.getApiMedicTurns();
        Call<List<Turno>> llamada = ap.obtenerTurnos(token);
        llamada.enqueue(new Callback<List<Turno>>() {
            @Override
            public void onResponse(Call<List<Turno>> call, Response<List<Turno>> response) {
                if(response.isSuccessful()){
                    if (response.body() != null && !response.body().isEmpty()) {
                        listaTurnos.postValue(response.body());
                    } else {
                        Log.d("salida", "La respuesta está vacía");
                    }
                }else{
                    Log.d("salida",response.message());
                    Toast.makeText(context, "No hay Turnos en la agenda de hoy.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Turno>> call, Throwable t) {
                Log.d("salida",t.getMessage());
                Toast.makeText(context, "Error al obtener Agendaa", Toast.LENGTH_SHORT).show();
            }
        });
    }
}