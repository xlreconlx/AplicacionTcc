package com.tcc.prueba.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.tcc.prueba.R;
import com.tcc.prueba.cliente.Cliente;
import com.tcc.prueba.cliente.EditarClienteActivity;

import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder> {

    private List<Cliente> clientes;
    private Context context;

    public ClienteAdapter(List<Cliente> clientes, Context context) {
        this.clientes = clientes;
        this.context = context;
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cliente, parent, false);
        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {
        Cliente cliente = clientes.get(position);
        holder.bind(cliente);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditarClienteActivity.class);
                intent.putExtra("cliente", cliente);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }

    public class ClienteViewHolder extends RecyclerView.ViewHolder {

        private TextView tipoIdentificacionTextView;
        private TextView identificacionTextView;
        private TextView nombreTextView;
        private TextView primerApellidoTextView;
        private TextView segundoApellidoTextView;
        private TextView generoTextView;
        private CardView cardView;

        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);
            tipoIdentificacionTextView = itemView.findViewById(R.id.tipoIdentificacionTextView);
            identificacionTextView = itemView.findViewById(R.id.identificacionTextView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
            primerApellidoTextView = itemView.findViewById(R.id.primerApellidoTextView);
            segundoApellidoTextView = itemView.findViewById(R.id.segundoApellidoTextView);
            generoTextView = itemView.findViewById(R.id.generoTextView);
            cardView = itemView.findViewById(R.id.cardView);
        }

        public void bind(Cliente cliente) {
            tipoIdentificacionTextView.setText(cliente.getTipoIdentificacion());
            identificacionTextView.setText(cliente.getNumeroIdentificacion());
            nombreTextView.setText(cliente.getNombre());
            primerApellidoTextView.setText(cliente.getPrimerApellido());
            segundoApellidoTextView.setText(cliente.getSegundoApellido());
            generoTextView.setText(cliente.getGenero());
        }
    }
}
