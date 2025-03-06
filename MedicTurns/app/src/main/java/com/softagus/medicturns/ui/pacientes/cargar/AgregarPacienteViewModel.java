package com.softagus.medicturns.ui.pacientes.cargar;

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

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarPacienteViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Paciente> paciente;
    private MutableLiveData<String> error;
    public AgregarPacienteViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Paciente> getPaciente(){
        if(paciente== null){
            paciente= new MutableLiveData<>();
        }
        return paciente;
    }

    public LiveData<String> getError() {
        if(error== null){
            error= new MutableLiveData<>();
        }
        return error;
    }

    public void crearPaciente(Paciente paciente1, Bundle bundle){
        Log.d("salida",paciente1.getDireccion()+" "+paciente1.getNombre()+" "+paciente1.getDni());
        if(paciente1.getDireccion().equals("") || paciente1.getDireccion().isEmpty()){
            error.setValue("Ingrese una Direccion valida.");
        } else if (paciente1.getNombre().equals("") || paciente1.getNombre().isEmpty()) {
            error.setValue("Ingrese el Nombre") ;
        } else if (paciente1.getApellido().equals("") || paciente1.getApellido().isEmpty()) {
            error.setValue("Ingrese el Apellido");
        } else if (paciente1.getDni().equals("") || paciente1.getDni().isEmpty()) {
            error.setValue("Ingrese el DNI");
        } else if (paciente1.getCuil().equals("") || paciente1.getCuil().isEmpty()) {
            error.setValue("Ingrese el Cuil");
        } else if (paciente1.getEmail().equals("") || paciente1.getEmail().isEmpty()){
            error.setValue("Ingrese el Email");
        } else if (paciente1.getAlergias().equals("") || paciente1.getAlergias().isEmpty()){
            error.setValue("Ingrese las Alergias");
        }else if (paciente1.getGrupoSanguineo().equals("Seleccione") || paciente1.getGrupoSanguineo().equals("")){
            error.setValue("Seleccione el Grupo Sanguineo");
        }else if (paciente1.getObraSocial().equals("") || paciente1.getObraSocial().isEmpty()){
            error.setValue("Ingrese la Obra Social");
        }else if (paciente1.getTelefono().equals("") || paciente1.getTelefono().isEmpty()){
            error.setValue("Ingrese el Telefono");
        }else if (paciente1.getIdRiesgo() == 0){
            error.setValue("Seleccione el Riesgo");
        }
        else {
            String token = ApiClientRetrofit.leerToken(context);
            ApiClientRetrofit.ApiMedicTurns ap= ApiClientRetrofit.getApiMedicTurns();
            Call<Paciente> llamada= ap.crearPaciente(token, paciente1.getNombre(),
                    paciente1.getApellido(),
                    paciente1.getEmail(),
                    paciente1.getDni(),
                    paciente1.getCuil(),
                    paciente1.getTelefono(),
                    paciente1.getObraSocial(),
                    paciente1.getDireccion(),
                    paciente1.getGrupoSanguineo(),
                    paciente1.getAlergias(),
                    paciente1.getIdRiesgo());
            llamada.enqueue(new Callback<Paciente>() {
                @Override
                public void onResponse(Call<Paciente> call, Response<Paciente> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(context, response.message().toString(), Toast.LENGTH_SHORT).show();
                        Log.d("salida","Paciente: "+response.message());
                        paciente.postValue(response.body());
                    } else {
                        Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("salida","error:"+response.toString());
                    }
                }

                @Override
                public void onFailure(Call<Paciente> call, Throwable t) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("salida","err: "+t.getMessage());
                    error.setValue("Verifique extencion de la foto: "+ t.getMessage());
                }
            });
        }
    }
}
