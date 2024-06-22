package com.softagus.medicturns.ui.agenda;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.softagus.medicturns.R;
import com.softagus.medicturns.modelo.Turno;

import java.io.Serializable;
import java.util.List;

public class AgendaAdapter extends RecyclerView.Adapter<AgendaAdapter.viewHolder>{
    private List<Turno> listaTurnos;
    private Context context;
    private LayoutInflater layoutInflater;

    public AgendaAdapter(java.util.List<Turno> listaTurnos, Context context, LayoutInflater layoutInflater) {
        this.listaTurnos = listaTurnos;
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_agenda,parent,false);
        return new AgendaAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.fecha.setText(listaTurnos.get(position).getFechaTurno().toString());
        holder.paciente.setText(listaTurnos.get(position).getPaciente().getNombre()+" "+listaTurnos.get(position).getPaciente().getApellido());
        holder.estudio.setText(listaTurnos.get(position).getEstudio().getNombre());
        holder.asistio.setChecked(listaTurnos.get(position).isAsistio());
    }

    @Override
    public int getItemCount() {
        return listaTurnos.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        private TextView paciente;
        private TextView fecha;
        private TextView estudio;
        private CheckBox asistio;
        private Button button;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            paciente=itemView.findViewById(R.id.txPacienteInfoTurno);
            estudio=itemView.findViewById(R.id.txEstudioInfoTurno);
            fecha=itemView.findViewById(R.id.txFechayInfoTurno);
            asistio=itemView.findViewById(R.id.checkBoxAsistio);
            button= itemView.findViewById(R.id.btConsultaTurno);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    Turno turno = listaTurnos.get(getLayoutPosition());
                    bundle.putSerializable("turno",(Serializable)turno);
                    Navigation.findNavController(view).navigate(R.id.detallesTurnoFragment, bundle);
                }
            });
        }
    }
}
