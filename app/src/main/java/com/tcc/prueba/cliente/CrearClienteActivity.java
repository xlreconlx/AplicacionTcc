package com.tcc.prueba.cliente;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.tcc.prueba.MainActivity;
import com.tcc.prueba.api.ApiResponseCallback;
import com.tcc.prueba.api.ClienteApi;
import com.tcc.prueba.databinding.ActivityCrearClienteBinding;

import com.tcc.prueba.R;

import org.json.JSONObject;

public class CrearClienteActivity extends AppCompatActivity {

    private EditText tipoIdentificacionEditText;
    private EditText numeroIdentificacionEditText;
    private EditText nombreEditText;
    private EditText primerApellidoEditText;
    private EditText segundoApellidoEditText;
    private EditText generoEditText;
    private ClienteApi clienteApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cliente);

        tipoIdentificacionEditText = findViewById(R.id.tipoIdentificacionEditText);
        numeroIdentificacionEditText = findViewById(R.id.identificacionEditText);
        nombreEditText = findViewById(R.id.nombreEditText);
        primerApellidoEditText = findViewById(R.id.primerApellidoEditText);
        segundoApellidoEditText = findViewById(R.id.segundoApellidoEditText);
        generoEditText = findViewById(R.id.generoEditText);

        clienteApi = new ClienteApi(this);


        Button registrarButton = findViewById(R.id.registrarButton);
        registrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tipoIdentificacion = tipoIdentificacionEditText.getText().toString();
                String numeroIdentificacion = numeroIdentificacionEditText.getText().toString();
                String nombre = nombreEditText.getText().toString();
                String primerApellido = primerApellidoEditText.getText().toString();
                String segundoApellido = segundoApellidoEditText.getText().toString();
                String genero = generoEditText.getText().toString();

                Cliente cliente = new Cliente(tipoIdentificacion, numeroIdentificacion, nombre, primerApellido, segundoApellido, genero);

                registrarCliente(cliente);
            }
        });
    }

    private void registrarCliente(Cliente cliente) {
        clienteApi.registrarCliente(cliente.toJson(), new ApiResponseCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject response) {
                Toast.makeText(CrearClienteActivity.this, "Cliente registrado correctamente", Toast.LENGTH_SHORT).show();
                tipoIdentificacionEditText.setText("");
                numeroIdentificacionEditText.setText("");
                nombreEditText.setText("");
                primerApellidoEditText.setText("");
                segundoApellidoEditText.setText("");
                generoEditText.setText("");

                Intent intent = new Intent(CrearClienteActivity.this, MainActivity.class);
                CrearClienteActivity.this.startActivity(intent);
            }

            @Override
            public void onError(String mensaje) {
                Toast.makeText(CrearClienteActivity.this, "Error al registrar cliente: " + mensaje, Toast.LENGTH_SHORT).show();
            }
        });
    }


}