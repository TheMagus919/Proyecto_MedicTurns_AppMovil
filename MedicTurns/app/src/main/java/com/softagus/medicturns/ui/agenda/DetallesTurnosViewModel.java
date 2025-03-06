package com.softagus.medicturns.ui.agenda;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softagus.medicturns.Request.ApiClientRetrofit;
import com.softagus.medicturns.modelo.Turno;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetallesTurnosViewModel extends AndroidViewModel {
    private MutableLiveData<Turno> turnoM;
    private Context context;

    public DetallesTurnosViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Turno> getTurnoM() {
        if (turnoM == null) {
            turnoM = new MutableLiveData<>();
        }
        return turnoM;
    }

    public void actualizarTurno(Turno turno){
        String token = ApiClientRetrofit.leerToken(context);
        ApiClientRetrofit.ApiMedicTurns ap= ApiClientRetrofit.getApiMedicTurns();
        Call<Turno> llamada = ap.editarTurno(token,turno.getIdTurno(), turno.isAsistio());
        llamada.enqueue(new Callback<Turno>() {
            @Override
            public void onResponse(Call<Turno> call, Response<Turno> response) {
                Log.d("salida","Editado con exito");
                Toast.makeText(context, "Editado con exito", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Turno> call, Throwable t) {
                Log.d("salida",t.getMessage());
            }
        });
    }

    public void obtenerInformacionTurno(Bundle bundle) {
        Turno turno = (Turno) bundle.get("turno");
        String token = ApiClientRetrofit.leerToken(context);
        ApiClientRetrofit.ApiMedicTurns ap = ApiClientRetrofit.getApiMedicTurns();
        int id = turno.getIdTurno();
        Call<Turno> llamada = ap.obtenerTurnoUsuario(token, id);
        llamada.enqueue(new Callback<Turno>() {
            @Override
            public void onResponse(Call<Turno> call, Response<Turno> response) {
                if (response.isSuccessful()) {
                    turnoM.postValue(response.body());
                } else {
                    Log.d("salida", response.raw().message());
                    Toast.makeText(context, "Error cargando informacion del turno", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Turno> call, Throwable t) {
                Log.d("salida", t.getCause().toString());
                Log.d("salida", t.getLocalizedMessage());
                Toast.makeText(context, "Error cargando informacion.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
