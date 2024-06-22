package com.softagus.medicturns.ui.pacientes.historialPaciente.info;

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

public class InformacionTurnoViewModel extends AndroidViewModel {

    private MutableLiveData<Turno> turnos;
    private Context context;

    public InformacionTurnoViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Turno> getInformacion() {
        if (turnos == null) {
            turnos = new MutableLiveData<>();
        }
        return turnos;
    }

    public void obtenerInformacion(Bundle bundle) {
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
