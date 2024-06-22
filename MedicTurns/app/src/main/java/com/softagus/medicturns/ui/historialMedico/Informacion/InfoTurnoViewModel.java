package com.softagus.medicturns.ui.historialMedico.Informacion;

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

public class InfoTurnoViewModel extends AndroidViewModel {
    private MutableLiveData<Turno> turnoM;
    private Context context;

    public InfoTurnoViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Turno> getTurnoM() {
        if (turnoM == null) {
            turnoM = new MutableLiveData<>();
        }
        return turnoM;
    }

    public void obtenerInformacionTurno(Bundle bundle) {
        Turno turno = (Turno) bundle.get("inmueble");
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
                    Toast.makeText(context, "Error cargando informacion del contrato", Toast.LENGTH_SHORT).show();
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

