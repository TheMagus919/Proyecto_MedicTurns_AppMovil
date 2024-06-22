package com.softagus.medicturns.ui.pacientes.editar;

import android.content.Context;
import android.os.Bundle;
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
        am.getPaciente().observe(getViewLifecycleOwner(), new Observer<Paciente>() {
            @Override
            public void onChanged(Paciente paciente) {
                Toast.makeText(getContext(),"Paciente "+ paciente.getIdPaciente()+ " creado con exito", Toast.LENGTH_LONG).show();
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_opciones).navigate(R.id.consultarPacienteFragment);
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
                in.setIdRiesgo(0);
                in.setObraSocial(binding.edObraSocial.getText().toString());
                in.setGrupoSanguineo(binding.acGrupoEditar.getText().toString());

                Bundle bundle = new Bundle();
                String riesgo = binding.acRiesgoEditar.getText().toString();
                bundle.putSerializable("riesgo",(Serializable)riesgo);
                am.editarPaciente(in, bundle);
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
