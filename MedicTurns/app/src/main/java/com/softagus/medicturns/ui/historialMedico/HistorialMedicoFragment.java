package com.softagus.medicturns.ui.historialMedico;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.softagus.medicturns.R;
import com.softagus.medicturns.databinding.FragmentHistorialmedicoBinding;
import com.softagus.medicturns.modelo.Turno;
import com.softagus.medicturns.ui.agenda.AgendaViewModel;

import java.io.Serializable;

public class HistorialMedicoFragment extends Fragment {

    private HistorialMedicoViewModel hm;
    private Context context;
    private FragmentHistorialmedicoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HistorialMedicoViewModel historialMedicoViewModel =
                new ViewModelProvider(this).get(HistorialMedicoViewModel.class);

        hm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(HistorialMedicoViewModel.class);
        binding = FragmentHistorialmedicoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        int dni = Integer.parseInt(binding.edDni.getText().toString());
        binding.btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hm.buscarHistorial(view, dni);
                //hm.buscarHistorial(dni);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}