package com.softagus.medicturns.ui.historialMedico.Informacion;

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

import com.softagus.medicturns.databinding.FragmentInfoturnoBinding;
import com.softagus.medicturns.modelo.Turno;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InfoTurnoFragment extends Fragment {
    private FragmentInfoturnoBinding binding;
    private InfoTurnoViewModel dm;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        dm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InfoTurnoViewModel.class);
        binding = FragmentInfoturnoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Bundle bundle = getArguments();
        dm.getTurnoM().observe(getViewLifecycleOwner(), new Observer<Turno>() {
            @Override
            public void onChanged(Turno turno) {
                String asis;
                if(turno.isAsistio()){
                    asis="Si";
                }else{
                    asis="No";
                }
                String fechaTurno = turno.getFechaTurno();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                LocalDateTime fechaTurnoLocalDateTime = LocalDateTime.parse(fechaTurno, formatter);

                DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = fechaTurnoLocalDateTime.format(outputFormatter);
                binding.txFechaTurnoInfo.setText(formattedDate);
                binding.txAsistioTurnoInfo.setText(asis);
                binding.txNombreMedicoTurnoInfo.setText(turno.getUsuario().getNombre()+" "+turno.getUsuario().getApellido());
                if(turno.getUsuario().getEspecialidad() == null){
                    binding.txEspecialidadMedicoTurnoInfo.setText("Ninguna.");
                }else{
                    binding.txEspecialidadMedicoTurnoInfo.setText(turno.getUsuario().getEspecialidad().getNombre());
                }
                binding.txNombrePacienteTurnoInfo.setText(turno.getPaciente().getNombre()+" "+turno.getPaciente().getApellido());
                binding.txDniPacienteTurnoInfo.setText(turno.getPaciente().getDni());
                binding.txGrupoSanguineoPacienteTurnoInfo.setText(turno.getPaciente().getGrupoSanguineo());
                binding.txRiesgoPacienteTurnoInfo.setText(turno.getPaciente().getRiesgo().getNombre());
                binding.txAlergiaPacienteTurnoInfo.setText(turno.getPaciente().getAlergias());
                binding.txObraSocialPacienteTurnoInfo.setText(turno.getPaciente().getObraSocial());
                binding.txCuilPacienteTurnoInfo.setText(turno.getPaciente().getCuil());
                binding.txTelefonoPacienteTurnoInfo.setText(turno.getPaciente().getTelefono());
                binding.txNombreEstudioTurnoInfo.setText(turno.getEstudio().getNombre());
                binding.txRiesgoEstudioTurnoInfo.setText(turno.getEstudio().getRiesgo().getNombre());
                binding.txRequisitosEstudioTurnoInfo.setText(turno.getEstudio().getRequisitos());
                binding.txDescripcionEstudioTurnoInfo.setText(turno.getEstudio().getDescripcion());
            }
        });
        dm.obtenerInformacionTurno(bundle);
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

