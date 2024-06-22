package com.softagus.medicturns.ui.agenda.reporte;

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

public class ReporteViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Turno> turnos;

    public ReporteViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Turno> getTurno(){
        if(turnos==null){
            turnos=new MutableLiveData<>();
        }
        return turnos;
    }

    public void armarLista(Bundle bundle) {
        Turno turno = (Turno) bundle.get("turno");
        String token = ApiClientRetrofit.leerToken(context);
        ApiClientRetrofit.ApiMedicTurns ap = ApiClientRetrofit.getApiMedicTurns();
        Call<Turno> llamada = ap.obtenerTurnoUsuario(token, turno.getIdTurno());
        llamada.enqueue(new Callback<Turno>() {
            @Override
            public void onResponse(Call<Turno> call, Response<Turno> response) {
                if (response.isSuccessful()) {
                    turnos.postValue(response.body());
                } else {
                    Log.d("salida", response.message());
                    Toast.makeText(context, "Error al obtener contratos vigentes", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Turno> call, Throwable t) {
                Log.d("salida", t.getMessage());
                Toast.makeText(context, "Error al obtener contratos vigentes", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void agregarReporte(Turno turno) {
        String token = ApiClientRetrofit.leerToken(context);
        ApiClientRetrofit.ApiMedicTurns ap = ApiClientRetrofit.getApiMedicTurns();
        Call<Turno> llamada = ap.agregarObservacion(token, turno.getIdTurno(), turno.getObservaciones());
        llamada.enqueue(new Callback<Turno>() {
            @Override
            public void onResponse(Call<Turno> call, Response<Turno> response) {
                if (response.isSuccessful()) {
                    turnos.postValue(response.body());
                } else {
                    Log.d("salida", response.message());
                    Toast.makeText(context, "Error al obtener contratos vigentes", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Turno> call, Throwable t) {
                Log.d("salida", t.getMessage());
                Toast.makeText(context, "Error al obtener contratos vigentes", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
