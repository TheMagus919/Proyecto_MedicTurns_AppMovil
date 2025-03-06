package com.softagus.medicturns.ui.historialMedico.historial;

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
import com.softagus.medicturns.ui.agenda.AgendaAdapter;

import java.io.Serializable;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HistorialAdapter extends RecyclerView.Adapter<HistorialAdapter.viewHolder>{
    private List<Turno> listaTurnos;
    private Context context;
    private LayoutInflater layoutInflater;

    public HistorialAdapter(java.util.List<Turno> listaTurnos, Context context, LayoutInflater layoutInflater) {
        this.listaTurnos = listaTurnos;
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public HistorialAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_historial,parent,false);
        return new HistorialAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistorialAdapter.viewHolder holder, int position) {
        String fechaTurno = listaTurnos.get(position).getFechaTurno();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime fechaTurnoLocalDateTime = LocalDateTime.parse(fechaTurno, formatter);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = fechaTurnoLocalDateTime.format(outputFormatter);
        holder.fecha.setText(formattedDate);
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
            paciente=itemView.findViewById(R.id.txPacienteInfoTurno);
            dni=itemView.findViewById(R.id.txDniPacienteTurno);
            estudio=itemView.findViewById(R.id.txEstudioInfoTurno);
            fecha=itemView.findViewById(R.id.txFechayInfoTurno);
            button= itemView.findViewById(R.id.btConsultaTurno);
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

