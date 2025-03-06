package com.softagus.medicturns.ui.pacientes.editar;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.softagus.medicturns.R;
import com.softagus.medicturns.databinding.FragmentEditarPacienteBinding;
import com.softagus.medicturns.modelo.Paciente;

import java.io.Serializable;

public class EditarPacienteFragment extends Fragment {
    private EditarPacienteViewModel am;
    private FragmentEditarPacienteBinding binding;
    private Context context;

    private Paciente pacienteOriginal;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        am= ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(EditarPacienteViewModel.class);
        binding = FragmentEditarPacienteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ArrayAdapter<CharSequence> adaptadorGrupos= ArrayAdapter.createFromResource(getContext(), R.array.array_grupos,R.layout.dropdown);
        binding.acGrupoEditar.setAdapter(adaptadorGrupos);
        ArrayAdapter<CharSequence> adaptadorRiesgos= ArrayAdapter.createFromResource(getContext(),R.array.array_riesgos,
                R.layout.dropdown);
        binding.acRiesgoEditar.setAdapter(adaptadorRiesgos);
        Bundle bundle = getArguments();
        am.getInfoPaciente().observe(getViewLifecycleOwner(), new Observer<Paciente>() {
            @Override
            public void onChanged(Paciente paciente) {
                binding.edNombreEditar.setText(paciente.getNombre());
                binding.edApellidoEditar.setText(paciente.getApellido());
                binding.edDniEditar.setText(paciente.getDni());
                binding.edCuilEditar.setText(paciente.getCuil());
                binding.edEmailEditar.setText(paciente.getEmail());
                binding.edDireccionEditar.setText(paciente.getDireccion());
                binding.edAlergiaEditar.setText(paciente.getAlergias());
                binding.edTelefonoEditar.setText(paciente.getTelefono());
                binding.edObraSocial.setText(paciente.getObraSocial());
                pacienteOriginal = paciente;
                Log.d("salida",pacienteOriginal.getNombre());
            }
        });
        am.getPaciente().observe(getViewLifecycleOwner(), new Observer<Paciente>() {
            @Override
            public void onChanged(Paciente paciente) {
                Toast.makeText(getContext(),"Paciente "+ paciente.getIdPaciente()+ " editado con exito", Toast.LENGTH_LONG).show();
                Bundle bundle = new Bundle();
                int dniEnviar = Integer.parseInt(paciente.getDni());
                bundle.putSerializable("dni",(Serializable)dniEnviar);
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_opciones).navigate(R.id.consultarPacienteFragment, bundle);
            }
        });
        am.getError().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(context,s, Toast.LENGTH_LONG).show();
            }
        });
        binding.btEditarPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Paciente in = new Paciente();
                in.setNombre(binding.edNombreEditar.getText().toString());
                in.setApellido(binding.edApellidoEditar.getText().toString());
                in.setDni(binding.edDniEditar.getText().toString());
                in.setCuil(binding.edCuilEditar.getText().toString());
                in.setEmail(binding.edEmailEditar.getText().toString());
                in.setDireccion(binding.edDireccionEditar.getText().toString());
                in.setAlergias(binding.edAlergiaEditar.getText().toString());
                in.setTelefono(binding.edTelefonoEditar.getText().toString());
                String riesgos = binding.acRiesgoEditar.getText().toString();
                if(riesgos.equals("Bajo")){
                    in.setIdRiesgo(1);
                }else if(riesgos.equals("Medio")){
                    in.setIdRiesgo(2);
                }else if(riesgos.equals("Alto")){
                    in.setIdRiesgo(3);
                }
                in.setObraSocial(binding.edObraSocial.getText().toString());
                in.setGrupoSanguineo(binding.acGrupoEditar.getText().toString());

                Bundle bundle = new Bundle();
                bundle.putSerializable("paciente",(Serializable)pacienteOriginal);
                am.editarPaciente(in, bundle);
            }
        });
        am.obtenerInfo(bundle);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
