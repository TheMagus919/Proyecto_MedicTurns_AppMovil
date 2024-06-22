package com.softagus.medicturns.ui.pacientes.consultar;

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
import com.softagus.medicturns.modelo.Paciente;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConsultarPacienteViewModel extends AndroidViewModel {

    private MutableLiveData<Paciente> paciente;
    private Context context;

    public ConsultarPacienteViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Paciente> getPaciente() {
        if (paciente == null) {
            paciente = new MutableLiveData<>();
        }
        return paciente;
    }

    public void obtenerInformacionPaciente(Bundle bundle) {
        int dni = (int) bundle.get("dni");
        String token = ApiClientRetrofit.leerToken(context);
        ApiClientRetrofit.ApiMedicTurns ap = ApiClientRetrofit.getApiMedicTurns();
        Call<Paciente> llamada = ap.obtenerInfoPaciente(token, dni);
        llamada.enqueue(new Callback<Paciente>() {
            @Override
            public void onResponse(Call<Paciente> call, Response<Paciente> response) {
                if (response.isSuccessful()) {
                    paciente.postValue(response.body());
                } else {
                    Log.d("salida", response.raw().message());
                    Toast.makeText(context, "Error cargando informacion del turno", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Paciente> call, Throwable t) {
                Log.d("salida", t.getCause().toString());
                Log.d("salida", t.getLocalizedMessage());
                Toast.makeText(context, "Error cargando informacion.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
