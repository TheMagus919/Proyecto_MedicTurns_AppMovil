package com.softagus.medicturns.ui.pacientes.buscar;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.softagus.medicturns.databinding.FragmentBuscarPacienteBinding;
import com.softagus.medicturns.ui.historialMedico.HistorialMedicoViewModel;

public class BuscarPacienteFragment extends Fragment {

    private BuscarPacienteViewModel bm;
    private Context context;
    private FragmentBuscarPacienteBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        bm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(BuscarPacienteViewModel.class);
        binding = FragmentBuscarPacienteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.btBuscarPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int dni = Integer.parseInt(binding.edDniBuscar.getText().toString());
                bm.buscarPaciente(view, dni);
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
