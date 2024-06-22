package com.softagus.medicturns.ui.historialMedico.historial;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.softagus.medicturns.databinding.FragmentHistorialBinding;
import com.softagus.medicturns.databinding.FragmentHistorialmedicoBinding;
import com.softagus.medicturns.modelo.Turno;
import com.softagus.medicturns.ui.agenda.AgendaAdapter;
import com.softagus.medicturns.ui.historialMedico.HistorialMedicoViewModel;

import java.util.List;


public class HistorialFragment extends Fragment{
        private HistorialViewModel hm;
        private Context context;
        private FragmentHistorialBinding binding;

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            HistorialViewModel historialViewModel =
                    new ViewModelProvider(this).get(HistorialViewModel.class);

            hm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(HistorialViewModel.class);
            binding = FragmentHistorialBinding.inflate(inflater, container, false);
            View root = binding.getRoot();
            Bundle bundle = getArguments();
            hm.getListaTurnos().observe(getViewLifecycleOwner(), new Observer<List<Turno>>() {
                @Override
                public void onChanged(List<Turno> turnos) {
                    GridLayoutManager glm = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
                    binding.rvHistorial.setLayoutManager(glm);
                    HistorialAdapter ad=new HistorialAdapter(turnos,getContext(),getLayoutInflater());
                    binding.rvHistorial.setAdapter(ad);
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

