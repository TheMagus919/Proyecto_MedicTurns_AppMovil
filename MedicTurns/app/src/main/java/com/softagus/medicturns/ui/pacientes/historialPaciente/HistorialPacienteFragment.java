package com.softagus.medicturns.ui.pacientes.historialPaciente;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.softagus.medicturns.databinding.FragmentHistorialBinding;
import com.softagus.medicturns.databinding.FragmentHistorialPacienteBinding;
import com.softagus.medicturns.modelo.Turno;
import com.softagus.medicturns.ui.historialMedico.historial.HistorialAdapter;
import com.softagus.medicturns.ui.historialMedico.historial.HistorialViewModel;

import java.util.List;

public class HistorialPacienteFragment extends Fragment {
    private HistorialPacienteViewModel hm;
    private Context context;
    private FragmentHistorialPacienteBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HistorialPacienteViewModel HistorialPacienteViewModel =
                new ViewModelProvider(this).get(HistorialPacienteViewModel.class);

        hm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(HistorialPacienteViewModel.class);
        binding = FragmentHistorialPacienteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Bundle bundle = getArguments();
        hm.getListaTurnos().observe(getViewLifecycleOwner(), new Observer<List<Turno>>() {
            @Override
            public void onChanged(List<Turno> turnos) {
                GridLayoutManager glm = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
                binding.rvHistorialPaciente.setLayoutManager(glm);
                HistorialPacienteAdapter ad=new HistorialPacienteAdapter(turnos,getContext(),getLayoutInflater());
                binding.rvHistorialPaciente.setAdapter(ad);
            }
        });
        hm.obtenerInformacionTurno(bundle);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
