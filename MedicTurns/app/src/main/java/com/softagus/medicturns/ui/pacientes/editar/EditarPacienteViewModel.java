package com.softagus.medicturns.ui.pacientes.editar;

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

public class EditarPacienteViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Paciente> paciente;
    private MutableLiveData<String> error;
    public EditarPacienteViewModel(@NonNull Application application) {
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

    public void editarPaciente(Paciente paciente1, Bundle bundle){
        String riesgo = (String)bundle.get("riesgo");
        int idRiesgoP = 0;
        if(riesgo.equals("Bajo")){
            idRiesgoP = 1;
        }else if(riesgo.equals("Medio")){
            idRiesgoP = 2;
        } else if (riesgo.equals("Alto")) {
            idRiesgoP = 3;
        }
        if(paciente1.getDireccion().equals("") || paciente1.getDireccion().isEmpty()){
            error.setValue("Ingrese un domicilio");
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
        }else if (paciente1.getGrupoSanguineo().equals("") || paciente1.getGrupoSanguineo().isEmpty()){
            error.setValue("Seleccione el Grupo Sanguineo");
        }else if (paciente1.getObraSocial().equals("") || paciente1.getObraSocial().isEmpty()){
            error.setValue("Ingrese la Obra Social");
        }else if (paciente1.getTelefono().equals("") || paciente1.getTelefono().isEmpty()){
            error.setValue("Ingrese el Telefono");
        }else if (idRiesgoP == 0){
            error.setValue("Seleccione el Factor de Riesgo");
        }else {
            String token = ApiClientRetrofit.leerToken(context);
            ApiClientRetrofit.ApiMedicTurns ap= ApiClientRetrofit.getApiMedicTurns();
            int id = paciente1.getIdPaciente();
            RequestBody direccion = RequestBody.create(MediaType.parse("application/json"),paciente1.getDireccion());
            RequestBody apellido = RequestBody.create(MediaType.parse("application/json"),paciente1.getApellido());
            RequestBody dni = RequestBody.create(MediaType.parse("application/json"),paciente1.getEmail());
            RequestBody email = RequestBody.create(MediaType.parse("application/json"),paciente1.getDni());
            RequestBody cuil = RequestBody.create(MediaType.parse("application/json"),paciente1.getCuil());
            RequestBody grupoSanguineo = RequestBody.create(MediaType.parse("application/json"),paciente1.getGrupoSanguineo());
            RequestBody alergia = RequestBody.create(MediaType.parse("application/json"),paciente1.getAlergias());
            RequestBody telefono = RequestBody.create(MediaType.parse("application/json"),paciente1.getTelefono());
            RequestBody obraSocial = RequestBody.create(MediaType.parse("application/json"),paciente1.getObraSocial());
            RequestBody nombre = RequestBody.create(MediaType.parse("application/json"),paciente1.getNombre());
            RequestBody idRiesgo = RequestBody.create(MediaType.parse("application/json"),idRiesgoP+"");
            Call<Paciente> llamada= ap.editarPaciente(id, token, nombre, apellido, dni, cuil, email, telefono, obraSocial, alergia, grupoSanguineo, direccion, idRiesgo);
            llamada.enqueue(new Callback<Paciente>() {
                @Override
                public void onResponse(Call<Paciente> call, Response<Paciente> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(context, response.message().toString(), Toast.LENGTH_SHORT).show();
                        Log.d("salida","Inmueble: "+response.message());
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
