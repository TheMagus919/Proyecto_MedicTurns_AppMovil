package com.softagus.medicturns.ui.pacientes.cargar;

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
import com.softagus.medicturns.databinding.FragmentAgregarPacienteBinding;
import com.softagus.medicturns.modelo.Paciente;
import com.softagus.medicturns.modelo.Riesgo;

import java.io.Serializable;

public class AgregarPacienteFragment extends Fragment {
    private AgregarPacienteViewModel am;
    private FragmentAgregarPacienteBinding binding;
    private Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        am= ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(AgregarPacienteViewModel.class);
        binding = FragmentAgregarPacienteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ArrayAdapter<CharSequence> adaptadorGrupos= ArrayAdapter.createFromResource(getContext(), R.array.array_grupos,R.layout.dropdown);
        binding.acGrupoGuardar.setAdapter(adaptadorGrupos);
        ArrayAdapter<CharSequence> adaptadorRiesgos= ArrayAdapter.createFromResource(getContext(),R.array.array_riesgos,
                R.layout.dropdown);
        binding.acRiesgoGuardar.setAdapter(adaptadorRiesgos);
        am.getPaciente().observe(getViewLifecycleOwner(), new Observer<Paciente>() {
            @Override
            public void onChanged(Paciente paciente) {
                Toast.makeText(getContext(),"Paciente "+ paciente.getIdPaciente()+ " creado con exito", Toast.LENGTH_LONG).show();
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_opciones).navigate(R.id.buscarPacienteFragment);
            }
        });
        am.getError().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(context,s, Toast.LENGTH_LONG).show();
            }
        });
        binding.btGuardarPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Paciente in = new Paciente();
                in.setNombre(binding.edNombreGuardar.getText().toString());
                in.setApellido(binding.edApellidoGuardar.getText().toString());
                in.setDni(binding.edDniGuardar.getText().toString());
                in.setCuil(binding.edCuilGuardar.getText().toString());
                in.setEmail(binding.edEmailGuardar.getText().toString());
                in.setDireccion(binding.edDireccionGuardar.getText().toString());
                in.setAlergias(binding.edAlergiaGuardar.getText().toString());
                in.setTelefono(binding.edTelefonoGuardar.getText().toString());
                in.setIdRiesgo(0);
                in.setObraSocial(binding.edObraSocialGuardar.getText().toString());
                in.setGrupoSanguineo(binding.acGrupoGuardar.getText().toString());

                Bundle bundle = new Bundle();
                String riesgo = binding.acRiesgoGuardar.getText().toString();
                bundle.putSerializable("riesgo",(Serializable)riesgo);
                am.crearPaciente(in, bundle);
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
