package com.softagus.medicturns.ui.agenda;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.softagus.medicturns.databinding.FragmentAgendaBinding;
import com.softagus.medicturns.modelo.Turno;
import com.softagus.medicturns.ui.historialMedico.historial.HistorialAdapter;

import java.util.List;

public class AgendaFragment extends Fragment {

    private AgendaViewModel am;
    private Context context;
    private FragmentAgendaBinding binding;

    public static AgendaFragment newInstance() {
        return new AgendaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        am = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(AgendaViewModel.class);

        binding = FragmentAgendaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        am.getListaTurnos().observe(getViewLifecycleOwner(), new Observer<List<Turno>>() {
            @Override
            public void onChanged(List<Turno> turnos) {
                if (turnos != null && !turnos.isEmpty()) {
                    Log.d("salida", "Datos recibidos: " + turnos.size());
                    GridLayoutManager glm = new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
                    binding.rvAgenda.setLayoutManager(glm);
                    AgendaAdapter ad=new AgendaAdapter(turnos,getContext(),getLayoutInflater());
                    if (turnos != null && !turnos.isEmpty()) {
                        Log.d("salida", "Asignando turnos al Adapter: " + turnos.size());
                        binding.rvAgenda.setAdapter(ad);
                    } else {
                        Log.d("salida", "La lista de turnos está vacía en el Adapter");
                    }
                } else {
                    Log.d("salida", "La lista de turnos está vacía o es nula");
                }
            }
        });
        am.armarLista();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        am.armarLista();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}