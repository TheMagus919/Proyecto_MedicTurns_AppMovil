package com.softagus.medicturns.ui.pacientes.historialPaciente;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.softagus.medicturns.R;
import com.softagus.medicturns.modelo.Turno;

import java.io.Serializable;
import java.util.List;

public class HistorialPacienteAdapter extends RecyclerView.Adapter<HistorialPacienteAdapter.viewHolder>{
    private List<Turno> listaTurnos;
    private Context context;
    private LayoutInflater layoutInflater;

    public HistorialPacienteAdapter(java.util.List<Turno> listaTurnos, Context context, LayoutInflater layoutInflater) {
        this.listaTurnos = listaTurnos;
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public HistorialPacienteAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_historial_paciente,parent,false);
        return new HistorialPacienteAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistorialPacienteAdapter.viewHolder holder, int position) {
        holder.fecha.setText(listaTurnos.get(position).getFechaTurno().toString());
        holder.paciente.setText(listaTurnos.get(position).getPaciente().getNombre()+" "+listaTurnos.get(position).getPaciente().getApellido());
        holder.estudio.setText(listaTurnos.get(position).getEstudio().getNombre());
        holder.dni.setText(listaTurnos.get(position).getPaciente().getDni());
    }

    @Override
    public int getItemCount() {
        return listaTurnos.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        private TextView paciente;
        private TextView fecha;
        private TextView estudio;
        private TextView dni;
        private Button button;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            paciente=itemView.findViewById(R.id.txNombrePConsultar);
            dni=itemView.findViewById(R.id.txDniPConsultar);
            estudio=itemView.findViewById(R.id.txEstudioPConsultar);
            fecha=itemView.findViewById(R.id.txFechaPConsultar);
            button= itemView.findViewById(R.id.btConsultarHistorialPaciente);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    Turno turno = listaTurnos.get(getLayoutPosition());
                    bundle.putSerializable("turno",(Serializable)turno);
                    Navigation.findNavController(view).navigate(R.id.infoTurnoFragment, bundle);
                }
            });
        }
    }
}
