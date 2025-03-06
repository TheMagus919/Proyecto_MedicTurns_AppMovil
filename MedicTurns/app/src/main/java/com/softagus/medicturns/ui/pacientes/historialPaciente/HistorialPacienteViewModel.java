package com.softagus.medicturns.ui.pacientes.historialPaciente;

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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistorialPacienteViewModel extends AndroidViewModel {
    private MutableLiveData<List<Turno>> listaTurnos;
    private Context context;

    public HistorialPacienteViewModel(@NonNull Application application) {
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
        String dn = bundle.get("dni").toString();
        int dni = Integer.parseInt(dn);
        String token = ApiClientRetrofit.leerToken(context);
        ApiClientRetrofit.ApiMedicTurns ap = ApiClientRetrofit.getApiMedicTurns();
        Call<List<Turno>> llamada = ap.obtenerHistorialMedico(token, dni);
        llamada.enqueue(new Callback<List<Turno>>() {
            @Override
            public void onResponse(Call<List<Turno>> call, Response<List<Turno>> response) {
                if (response.isSuccessful()) {
                    listaTurnos.postValue(response.body());
                } else {
                    Log.d("salida", response.raw().message());
                    Toast.makeText(context, "No hay Turnos asociados a esta persona.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Turno>> call, Throwable t) {
                Log.d("salida", t.getCause().toString());
                Log.d("salida", t.getLocalizedMessage());
                Toast.makeText(context, "Error cargando informacion.", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
