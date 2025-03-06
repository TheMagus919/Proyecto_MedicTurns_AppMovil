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
import androidx.navigation.fragment.DialogFragmentNavigatorDestinationBuilder;

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

    public void buscarHistorial(View view, int dni) {
        Bundle bundle = new Bundle();
        int dniEnviar = dni;
        bundle.putSerializable("dni",(Serializable)dniEnviar);
        Navigation.findNavController(view).navigate(R.id.historialFragment, bundle);
    }
}