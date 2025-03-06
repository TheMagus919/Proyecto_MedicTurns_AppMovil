package com.softagus.medicturns.Request;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softagus.medicturns.modelo.Estudio;
import com.softagus.medicturns.modelo.Horario;
import com.softagus.medicturns.modelo.Paciente;
import com.softagus.medicturns.modelo.Turno;
import com.softagus.medicturns.modelo.Usuario;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class ApiClientRetrofit {
    //private static final String URLBASE= "http://192.168.2.110:5000";
    private static final String URLBASE= "http://192.168.1.33:5000";
    private static ApiMedicTurns apimedicTurns;

    public static ApiMedicTurns getApiMedicTurns(){
        Gson gson= new GsonBuilder().setLenient().create();

        Retrofit retrofit=new Retrofit.Builder().baseUrl(URLBASE).addConverterFactory(GsonConverterFactory.create(gson)).build();
        apimedicTurns= retrofit.create(ApiMedicTurns.class);
        return apimedicTurns;
    }
    public static void guardarToken(Context context, String token){

        SharedPreferences sp=context.getSharedPreferences("token.xml",0);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("token",token);
        editor.commit();

    }

    public static String leerToken(Context context){

        SharedPreferences sp=context.getSharedPreferences("token.xml",0);
        return sp.getString("token","");


    }

    public static void borrarToken(Context context){

        SharedPreferences sp=context.getSharedPreferences("token.xml",0);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("token");
        editor.apply();
    }

    public interface ApiMedicTurns{
        //USUARIO
        @FormUrlEncoded
        @POST("Usuarios/login")
        Call<String> login(@Field("Usuario") String usuario, @Field("Clave") String clave);

        @GET("Usuarios/perfil")
        Call<Usuario> obtenerPerfil(@Header("Authorization") String token);

        @GET("Usuarios/logout")
        Call<ResponseBody> logout(@Header("Authorization") String token);

        //PACIENTES
        @GET("Pacientes/{Dni}")
        Call<Paciente> obtenerInfoPaciente(@Header("Authorization") String token, @Path("Dni") String Dni);

        @FormUrlEncoded
        @POST("Pacientes/crear")
        Call<Paciente> crearPaciente(
                @Header("Authorization") String token,
                @Field("Nombre") String nombre,
                @Field("Apellido") String apellido,
                @Field("Email") String email,
                @Field("Dni") String dni,
                @Field("Cuil") String cuil,
                @Field("Telefono") String telefono,
                @Field("ObraSocial") String obraSocial,
                @Field("Direccion") String direccion,
                @Field("GrupoSanguineo") String grupoSanguineo,
                @Field("Alergias") String alergia,
                @Field("IdRiesgo") int idRiesgo
        );
        @FormUrlEncoded
        @PUT("Pacientes/editar/{id}")//
        Call<Paciente>editarPaciente(@Header("Authorization") String token, @Path("id") int id,
                                     @Field("Nombre") String nombre,
                                     @Field("Apellido") String apellido,
                                     @Field("Email") String email,
                                     @Field("Dni") String dni,
                                     @Field("Cuil") String cuil,
                                     @Field("Telefono") String telefono,
                                     @Field("ObraSocial") String obraSocial,
                                     @Field("Direccion") String direccion,
                                     @Field("GrupoSanguineo") String grupoSanguineo,
                                     @Field("Alergias") String alergia,
                                     @Field("IdRiesgo") int idRiesgo);


        //TURNOS
        @GET("Turnos")
        Call<List<Turno>> obtenerTurnos(@Header("Authorization") String token);

        @GET("Turnos/{id}")
        Call<Turno> obtenerTurnoUsuario(@Header("Authorization") String token, @Path("id") int id);

        @GET("Turnos/historial/{dni}")
        Call<List<Turno>> obtenerHistorialMedico(@Header("Authorization") String token, @Path("dni") int dni);

        @PUT("Turnos/editar/{id}")
        Call<Turno> editarTurno(@Header("Authorization") String token, @Path("id") int id, @Query("asistio") boolean asistio);

        @PUT("Turnos/observacion/{id}")
        Call<Turno> agregarObservacion(@Header("Authorization") String token, @Path("id") int id, @Query("observacion") String observaciones);

    }
}

