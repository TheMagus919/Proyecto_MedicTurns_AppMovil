package com.softagus.medicturns.ui.pacientes.historialPaciente.info;

import android.content.Context;
import android.os.Bundle;
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
import com.softagus.medicturns.databinding.FragmentInfoturnoBinding;
import com.softagus.medicturns.modelo.Paciente;
import com.softagus.medicturns.modelo.Turno;
import com.softagus.medicturns.ui.pacientes.consultar.ConsultarPacienteViewModel;

import java.io.Serializable;

public class InformacionTurnoFragment extends Fragment {
    private InformacionTurnoViewModel im;
    private Context context;
    private FragmentInfoturnoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        im = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InformacionTurnoViewModel.class);
        binding = FragmentInfoturnoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Bundle bundle = getArguments();
        im.getInformacion().observe(getViewLifecycleOwner(), new Observer<Turno>() {
            @Override
            public void onChanged(Turno turno) {
                binding.txNombrePacienteTurnoInfo.setText(turno.getPaciente().getNombre()+" "+turno.getPaciente().getApellido());
                binding.txDniPacienteTurnoInfo.setText(turno.getPaciente().getDni());
                binding.txCuilPacienteTurnoInfo.setText(turno.getPaciente().getCuil());
                binding.txTelefonoPacienteTurnoInfo.setText(turno.getPaciente().getTelefono());
                binding.txAlergiaPacienteTurnoInfo.setText(turno.getPaciente().getAlergias());
                binding.txGrupoSanguineoPacienteTurnoInfo.setText(turno.getPaciente().getGrupoSanguineo());
                binding.txRiesgoPacienteTurnoInfo.setText(turno.getPaciente().getRiesgo().getNombre());
                binding.txObraSocialPacienteTurnoInfo.setText(turno.getPaciente().getObraSocial());

                binding.txNombreMedicoTurnoInfo.setText(turno.getUsuario().getNombre()+" "+turno.getUsuario().getApellido());
                binding.txEspecialidadMedicoTurnoInfo.setText(turno.getUsuario().getEspecialidad().getNombre());

                binding.txFechaTurnoInfo.setText(turno.getFechaTurno().toString());
                binding.txAsistioTurnoInfo.setText(turno.isAsistio()+"");

                binding.txEstudioTurnoNombre.setText(turno.getEstudio().getNombre());
                binding.txEstudioTurnoDescripcion.setText(turno.getEstudio().getDescripcion());
                binding.txEstudioTurnoRequisitos.setText(turno.getEstudio().getRequisitos());
                binding.txEstudioTurnoRiesgo.setText(turno.getEstudio().getRiesgo().getNombre());
            }
        });
        im.obtenerInformacion(bundle);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
