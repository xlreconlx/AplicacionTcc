package com.tcc.prueba;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.tcc.prueba.adapter.ClienteAdapter;
import com.tcc.prueba.api.ApiResponseCallback;
import com.tcc.prueba.api.ClienteApi;
import com.tcc.prueba.cliente.Cliente;
import com.tcc.prueba.cliente.CrearClienteActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ClienteAdapter clienteAdapter;
    private List<Cliente> clientes;
    private ClienteApi clienteApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        clienteApi = new ClienteApi(this);

        clientes = new ArrayList<>();
        clienteAdapter = new ClienteAdapter(clientes, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(clienteAdapter);

        obtenerClientes();

        FloatingActionButton fab = findViewById(R.id.fabNuevoCliente);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CrearClienteActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    private void obtenerClientes() {
        Gson gson = new Gson();
        clienteApi.obtenerClientes(new ApiResponseCallback<JSONArray>() {
            @Override
            public void onSuccess(JSONArray response) {
                try {
                    clientes.clear();
                    JsonArray jsonArray = JsonParser.parseString(response.toString()).getAsJsonArray();
                    for (JsonElement jsonElement : jsonArray) {
                        Cliente cliente = gson.fromJson(jsonElement, Cliente.class);
                        clientes.add(cliente);
                    }
                    clienteAdapter.notifyDataSetChanged();
                } catch (JsonParseException jsonParseException) {
                    Toast.makeText(MainActivity.this, "Error al obtener clientes: " + jsonParseException, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(MainActivity.this, "Error al obtener clientes: " + errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }

}