package com.softagus.medicturns.ui.pacientes.buscar;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.softagus.medicturns.R;
import com.softagus.medicturns.modelo.Paciente;

import java.io.Serializable;

public class BuscarPacienteViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Paciente> paciente;

    public BuscarPacienteViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Paciente> getPaciente() {
        if (paciente == null) {
            paciente = new MutableLiveData<>();
        }
        return paciente;
    }

    public void buscarPaciente(View view, int dni){
        Bundle bundle = new Bundle();
        int dniEnviar = dni;
        bundle.putSerializable("dni",(Serializable)dniEnviar);
        Navigation.findNavController(view).navigate(R.id.consultarPacienteFragment, bundle);
    }
}
