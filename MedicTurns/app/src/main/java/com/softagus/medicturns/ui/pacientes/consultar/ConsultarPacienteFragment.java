package com.softagus.medicturns.ui.pacientes.consultar;

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
import com.softagus.medicturns.databinding.FragmentInfoPacienteBinding;
import com.softagus.medicturns.modelo.Paciente;
import com.softagus.medicturns.modelo.Turno;

import java.io.Serializable;


public class ConsultarPacienteFragment extends Fragment {
    private ConsultarPacienteViewModel cm;
    private Context context;
    private FragmentInfoPacienteBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        cm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(ConsultarPacienteViewModel.class);
        binding = FragmentInfoPacienteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Bundle bundle = getArguments();
        cm.getPaciente().observe(getViewLifecycleOwner(), new Observer<Paciente>() {
            @Override
            public void onChanged(Paciente paciente) {
                binding.txNombreP.setText(paciente.getNombre()+" "+paciente.getApellido());
                binding.txDniP.setText(paciente.getDni());
                binding.txCuilP.setText(paciente.getCuil());
                binding.txEmailP.setText(paciente.getEmail());
                binding.txTelefonoP.setText(paciente.getTelefono());
                binding.txDireccionP.setText(paciente.getDireccion());
                binding.txAlergiaP.setText(paciente.getAlergias());
                binding.txGrupoSanguineoP.setText(paciente.getGrupoSanguineo());
                binding.txRiesgoP.setText(paciente.getRiesgo().getNombre());
                binding.txObraSocialP.setText(paciente.getObraSocial());
                binding.btConsultarHistorial.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        String dniPaciente = paciente.getDni();
                        bundle.putSerializable("dni",(Serializable)dniPaciente);
                        Navigation.findNavController(view).navigate(R.id.historialPacienteFragment, bundle);
                    }
                });
                binding.btModificar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        int idPaciente = paciente.getIdPaciente();
                        bundle.putSerializable("idPaciente",(Serializable)idPaciente);
                        Navigation.findNavController(view).navigate(R.id.editarPacienteFragment, bundle);
                    }
                });
            }
        });
        cm.obtenerInformacionPaciente(bundle);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
