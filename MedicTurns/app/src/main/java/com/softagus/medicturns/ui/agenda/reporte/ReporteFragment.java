package com.softagus.medicturns.ui.agenda.reporte;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.softagus.medicturns.R;
import com.softagus.medicturns.databinding.FragmentAgendaBinding;
import com.softagus.medicturns.databinding.FragmentReporteBinding;
import com.softagus.medicturns.modelo.Turno;
import com.softagus.medicturns.ui.agenda.AgendaAdapter;
import com.softagus.medicturns.ui.agenda.AgendaFragment;
import com.softagus.medicturns.ui.agenda.AgendaViewModel;

import java.io.Serializable;
import java.util.List;

public class ReporteFragment extends Fragment {
    private ReporteViewModel rm;
    private Context context;
    private FragmentReporteBinding binding;
    private Turno turnoActual=null;

    public static ReporteFragment newInstance() {
        return new ReporteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(ReporteViewModel.class);

        binding = FragmentReporteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Bundle bundle = getArguments();
        rm.getTurno().observe(getViewLifecycleOwner(), new Observer<Turno>() {
            @Override
            public void onChanged(Turno turno) {
                binding.edReporte.setText(turno.getObservaciones());
                turnoActual = turno;
            }
        });
        binding.btReporteEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turnoActual.setObservaciones(binding.edReporte.getText().toString());
                rm.agregarReporte(turnoActual,requireActivity());
            }
        });
        rm.armarLista(bundle);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
