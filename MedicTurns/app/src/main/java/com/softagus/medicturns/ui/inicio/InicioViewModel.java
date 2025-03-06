package com.softagus.medicturns.ui.inicio;

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

public class InicioViewModel extends AndroidViewModel {

    private Context context;

    public InicioViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

}
