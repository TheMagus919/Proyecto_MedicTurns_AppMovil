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
    private MutableLiveData<Paciente> pacienteInfo;
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

    public LiveData<Paciente> getInfoPaciente(){
        if(pacienteInfo== null){
            pacienteInfo= new MutableLiveData<>();
        }
        return pacienteInfo;
    }

    public void obtenerInfo(Bundle bundle){
        int dni = Integer.parseInt((String) bundle.get("dni"));
        String token = ApiClientRetrofit.leerToken(context);
        ApiClientRetrofit.ApiMedicTurns ap = ApiClientRetrofit.getApiMedicTurns();
        String Dni = dni+"";
        Call<Paciente> llamada = ap.obtenerInfoPaciente(token, Dni);
        llamada.enqueue(new Callback<Paciente>() {
            @Override
            public void onResponse(Call<Paciente> call, Response<Paciente> response) {
                if (response.isSuccessful()) {
                    pacienteInfo.postValue(response.body());
                } else {
                    Log.d("salida", response.raw().message());
                    Toast.makeText(context, "Error cargando informacion del paciente", Toast.LENGTH_SHORT).show();
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
    public void editarPaciente(Paciente paciente1, Bundle bundle){
        Paciente original = (Paciente)bundle.get("paciente");
        if(!paciente1.getDireccion().equals("") || !paciente1.getDireccion().isEmpty()){
            original.setDireccion(paciente1.getDireccion());
        }
        if (!paciente1.getNombre().equals("") || !paciente1.getNombre().isEmpty()) {
            original.setNombre(paciente1.getNombre());
        }
        if (!paciente1.getApellido().equals("") || !paciente1.getApellido().isEmpty()) {
            original.setApellido(paciente1.getApellido());
        }
        if (!paciente1.getDni().equals("") || !paciente1.getDni().isEmpty()) {
            original.setDni(paciente1.getDni());
        }
        if (!paciente1.getCuil().equals("") || !paciente1.getCuil().isEmpty()) {
            original.setCuil(paciente1.getCuil());
        }
        if (!paciente1.getEmail().equals("") || !paciente1.getEmail().isEmpty()) {
            original.setEmail(paciente1.getEmail());
        }
        if (!paciente1.getAlergias().equals("") || !paciente1.getAlergias().isEmpty()){
            original.setAlergias(paciente1.getAlergias());
        }
        if (!paciente1.getGrupoSanguineo().equals("") && !paciente1.getGrupoSanguineo().equals("Seleccione") && !paciente1.getGrupoSanguineo().isEmpty()){
            original.setGrupoSanguineo(paciente1.getGrupoSanguineo());
        }
        if (!paciente1.getObraSocial().equals("") || !paciente1.getObraSocial().isEmpty()){
            original.setObraSocial(paciente1.getObraSocial());
        }
        if (!paciente1.getTelefono().equals("") || !paciente1.getTelefono().isEmpty()){
            original.setTelefono(paciente1.getTelefono());
        }
            String token = ApiClientRetrofit.leerToken(context);
            ApiClientRetrofit.ApiMedicTurns ap= ApiClientRetrofit.getApiMedicTurns();
            int id = original.getIdPaciente();
        String direccion = original.getDireccion();
        String apellido = original.getApellido();
        String dni = original.getDni();
        String email = original.getEmail();
        String cuil = original.getCuil();
        String grupoSanguineo = original.getGrupoSanguineo();
        String alergia = original.getAlergias();
        String telefono = original.getTelefono();
        String obraSocial = original.getObraSocial();
        String nombre = original.getNombre();
            int idRiesgo = original.getIdRiesgo();

            Call<Paciente> llamada= ap.editarPaciente(token, id, nombre, apellido, email, dni, cuil, telefono, obraSocial, direccion, grupoSanguineo, alergia, idRiesgo);
            llamada.enqueue(new Callback<Paciente>() {
                @Override
                public void onResponse(Call<Paciente> call, Response<Paciente> response) {
                    if(response.isSuccessful()){
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
