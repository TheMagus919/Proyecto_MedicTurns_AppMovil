package com.softagus.medicturns.ui.pacientes.cargar;

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
import com.softagus.medicturns.databinding.FragmentAgregarPacienteBinding;
import com.softagus.medicturns.modelo.Paciente;

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
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_opciones).navigate(R.id.nav_pacientes);
            }
        });
        am.getError().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(),s, Toast.LENGTH_LONG).show();
            }
        });
        binding.btGuardarPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Paciente in = new Paciente();
                if(!binding.edNombreGuardar.getText().toString().equals("")){
                    in.setNombre(binding.edNombreGuardar.getText().toString());
                }else{
                    in.setNombre("");
                }
                if(!binding.edApellidoGuardar.getText().toString().equals("")){
                    in.setApellido(binding.edApellidoGuardar.getText().toString());
                }else{
                    in.setApellido("");
                }
                if(!binding.edDniGuardar.getText().toString().equals("")){
                    in.setDni(binding.edDniGuardar.getText().toString());
                }else{
                    in.setDni("");
                }
                if(!binding.edCuilGuardar.getText().toString().equals("")){
                    in.setCuil(binding.edCuilGuardar.getText().toString());
                }else{
                    in.setCuil("");
                }
                if(!binding.edEmailGuardar.getText().toString().equals("")){
                    in.setEmail(binding.edEmailGuardar.getText().toString());
                }else{
                    in.setEmail("");
                }
                if(!binding.edDireccionGuardar.getText().equals("")){
                    in.setDireccion(binding.edDireccionGuardar.getText().toString());
                }else{
                    in.setDireccion("");
                }
                if(!binding.edAlergiaGuardar.getText().toString().equals("")){
                    in.setAlergias(binding.edAlergiaGuardar.getText().toString());
                }else{
                    in.setAlergias("");
                }
                if(!binding.edTelefonoGuardar.getText().toString().equals("")){
                    in.setTelefono(binding.edTelefonoGuardar.getText().toString());
                }else{
                    in.setTelefono("");
                }
                String riesgos = binding.acRiesgoGuardar.getText().toString();
                if(!riesgos.equals("") || !riesgos.isEmpty()) {
                    if (riesgos.equals("Bajo")) {
                        in.setIdRiesgo(1);
                    } else if (riesgos.equals("Medio")) {
                        in.setIdRiesgo(2);
                    } else if (riesgos.equals("Alto")) {
                        in.setIdRiesgo(3);
                    } else{
                        in.setIdRiesgo(0);
                    }
                }
                if(!binding.edObraSocialGuardar.getText().toString().equals("")){
                    in.setObraSocial(binding.edObraSocialGuardar.getText().toString());
                }else{
                    in.setObraSocial("");
                }
                if(!binding.acGrupoGuardar.getText().toString().equals("")){
                    in.setGrupoSanguineo(binding.acGrupoGuardar.getText().toString());
                }else{
                    in.setGrupoSanguineo("Seleccione");
                }

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
