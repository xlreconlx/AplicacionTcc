package com.tcc.prueba.cliente;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tcc.prueba.MainActivity;
import com.tcc.prueba.R;
import com.tcc.prueba.api.ApiResponseCallback;
import com.tcc.prueba.api.ClienteApi;

import org.json.JSONObject;

public class EditarClienteActivity extends AppCompatActivity {
    private EditText tipoIdentificacionEditText;
    private EditText numeroIdentificacionEditText;
    private EditText nombreEditText;
    private EditText primerApellidoEditText;
    private EditText segundoApellidoEditText;
    private EditText generoEditText;

    private Cliente cliente;
    private ClienteApi clienteApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cliente);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("cliente")) {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                cliente = getIntent().getParcelableExtra("cliente", Cliente.class);
            } else {
                cliente = getIntent().getParcelableExtra("cliente");
            }
        }
        clienteApi = new ClienteApi(this);

        tipoIdentificacionEditText = findViewById(R.id.tipoIdentificacionEditText);
        numeroIdentificacionEditText = findViewById(R.id.identificacionEditText);
        nombreEditText = findViewById(R.id.nombreEditText);
        primerApellidoEditText = findViewById(R.id.primerApellidoEditText);
        segundoApellidoEditText = findViewById(R.id.segundoApellidoEditText);
        generoEditText = findViewById(R.id.generoEditText);

        if (cliente != null) {
            tipoIdentificacionEditText.setText(cliente.getTipoIdentificacion());
            numeroIdentificacionEditText.setText(cliente.getNumeroIdentificacion());
            nombreEditText.setText(cliente.getNombre());
            primerApellidoEditText.setText(cliente.getPrimerApellido());
            segundoApellidoEditText.setText(cliente.getSegundoApellido());
            generoEditText.setText(cliente.getGenero());
        }

        Button actualizarButton = findViewById(R.id.actualizarButton);
        actualizarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarCliente();
            }
        });

        Button eliminarButton = findViewById(R.id.eliminarButton);
        eliminarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarCliente();
            }
        });
    }

    private void actualizarCliente() {
        cliente.setTipoIdentificacion(tipoIdentificacionEditText.getText().toString());
        cliente.setNombre(nombreEditText.getText().toString());
        cliente.setNumeroIdentificacion(numeroIdentificacionEditText.getText().toString());
        cliente.setPrimerApellido(primerApellidoEditText.getText().toString());
        cliente.setSegundoApellido(segundoApellidoEditText.getText().toString());
        cliente.setGenero(generoEditText.getText().toString());

        clienteApi.actualizarCliente(cliente.toJson(), new ApiResponseCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject response) {
                Toast.makeText(EditarClienteActivity.this, "Cambios guardados", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(EditarClienteActivity.this, MainActivity.class);
                EditarClienteActivity.this.startActivity(intent);
            }
            @Override
            public void onError(String errorMessage) {
                Toast.makeText(EditarClienteActivity.this, "Ocurrio un error Actualizando al Cliente" + errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void eliminarCliente() {
        clienteApi.eliminarCliente(cliente.getIdCliente(), new ApiResponseCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject response) {
                Toast.makeText(EditarClienteActivity.this, "Cliente eliminado correctamente", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(EditarClienteActivity.this, MainActivity.class);
                EditarClienteActivity.this.startActivity(intent);
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(EditarClienteActivity.this, "Ocurrio un error Eliminando al Cliente" + errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }
}
