package com.softagus.medicturns.ui.agenda;

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

import com.softagus.medicturns.R;
import com.softagus.medicturns.databinding.FragmentDetallesTurnoBinding;
import com.softagus.medicturns.modelo.Turno;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DetallesTurnoFragment extends Fragment {
    private DetallesTurnosViewModel tm;
    private FragmentDetallesTurnoBinding binding;
    private Turno turnoActual=null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        tm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(DetallesTurnosViewModel.class);
        binding = FragmentDetallesTurnoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Bundle bundle = getArguments();
        tm.getTurnoM().observe(getViewLifecycleOwner(), new Observer<Turno>() {
            @Override
            public void onChanged(Turno turno) {
                String fechaTurno = turno.getFechaTurno();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                LocalDateTime fechaTurnoLocalDateTime = LocalDateTime.parse(fechaTurno, formatter);

                DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = fechaTurnoLocalDateTime.format(outputFormatter);
                binding.txFechaTurnoInfo.setText(formattedDate);
                binding.txNombrePacienteTurnoInfo.setText(turno.getPaciente().getNombre()+" "+turno.getPaciente().getApellido());
                binding.txDniPacienteTurnoInfo.setText(turno.getPaciente().getDni());
                binding.txCuilPacienteTurnoInfo.setText(turno.getPaciente().getCuil());
                binding.txTelefonoPacienteTurnoInfo.setText(turno.getPaciente().getTelefono());
                binding.txGrupoSanguineoPacienteTurnoInfo.setText(turno.getPaciente().getGrupoSanguineo());
                binding.txAlergiaPacienteTurnoInfo.setText(turno.getPaciente().getAlergias());
                binding.txRiesgoPacienteTurnoInfo.setText(turno.getPaciente().getRiesgo().getNombre());
                binding.txObraSocialPacienteTurnoInfo.setText(turno.getPaciente().getObraSocial());
                binding.txNombreEstudioTurnoInfo.setText(turno.getEstudio().getNombre());
                binding.txDescripcionEstudioTurnoInfo.setText(turno.getEstudio().getDescripcion());
                binding.txRequisitosEstudioTurnoInfo.setText(turno.getEstudio().getRequisitos());
                binding.txRiesgoEstudioTurnoInfo.setText(turno.getEstudio().getRiesgo().getNombre());
                binding.cbAsistioTurno.setChecked(turno.isAsistio());
                turnoActual = turno;
            }
        });
        binding.cbAsistioTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean disp = binding.cbAsistioTurno.isChecked();
                turnoActual.setAsistio(disp);
                tm.actualizarTurno(turnoActual);
            }
        });
        binding.btReporteEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                Turno turno = turnoActual;
                bundle.putSerializable("turno",(Serializable)turno);
                Navigation.findNavController(view).navigate(R.id.reporteFragment, bundle);
            }
        });
        tm.obtenerInformacionTurno(bundle);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
