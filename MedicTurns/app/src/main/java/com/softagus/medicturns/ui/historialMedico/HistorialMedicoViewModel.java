package com.softagus.medicturns.ui.historialMedico;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.softagus.medicturns.R;
import com.softagus.medicturns.Request.ApiClientRetrofit;
import com.softagus.medicturns.modelo.Turno;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistorialMedicoViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<List<Turno>> listaTurnos;

    public HistorialMedicoViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<List<Turno>> getListaTurnos() {
        if (listaTurnos == null) {
            listaTurnos = new MutableLiveData<>();
        }
        return listaTurnos;
    }

    public void buscarHistorial(View view, int dni){
        Bundle bundle = new Bundle();
        int dniEnviar = dni;
        bundle.putSerializable("dni",(Serializable)dniEnviar);
        Navigation.findNavController(view).navigate(R.id.historialFragment, bundle);
    }
    /*
    public void buscarHistorial(int dni) {
        String token = ApiClientRetrofit.leerToken(context);
        ApiClientRetrofit.ApiMedicTurns ap = ApiClientRetrofit.getApiMedicTurns();
        Call<List<Turno>> llamada = ap.obtenerHistorialMedico(token, dni);
        llamada.enqueue(new Callback<List<Turno>>() {
            @Override
            public void onResponse(Call<List<Turno>> call, Response<List<Turno>> response) {
                if (response.isSuccessful()) {
                    listaTurnos.postValue(response.body());
                } else {
                    Log.d("salida", response.message());
                    Toast.makeText(context, "Error al obtener contratos vigentes", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Turno>> call, Throwable t) {
                Log.d("salida", t.getMessage());
                Toast.makeText(context, "Error al obtener contratos vigentes", Toast.LENGTH_SHORT).show();
            }
        });
    }*/
}