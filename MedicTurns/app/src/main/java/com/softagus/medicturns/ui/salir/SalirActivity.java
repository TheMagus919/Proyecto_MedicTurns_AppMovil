package com.softagus.medicturns.ui.salir;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.softagus.medicturns.Request.ApiClientRetrofit;
import com.softagus.medicturns.modelo.Paciente;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalirActivity extends Activity {
    public SalirActivity() {
    }

    public static void mostrarDialogo(Activity activity, Bundle bundle){
        new AlertDialog.Builder(activity).setTitle("Cierre de Sesión").setMessage("Está seguro de que desea cerrar la sesión?").setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ApiClientRetrofit.ApiMedicTurns ap= ApiClientRetrofit.getApiMedicTurns();
                String token = bundle.getString("token");
                Call<ResponseBody> llamada= ap.logout(token);
                llamada.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            ApiClientRetrofit.borrarToken(activity);
                            activity.finish();
                        } else {
                            Log.d("salida","error:"+response.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("salida","err: "+t.getMessage());
                    }
                });
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(activity, "Continuamos Trabajando", Toast.LENGTH_SHORT).show();
            }
        }).show();
    }
}
