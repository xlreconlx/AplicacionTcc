package com.tcc.prueba.api;

import static com.tcc.prueba.api.JsonKeys.BASE_URL;
import static com.tcc.prueba.api.JsonKeys.CLIENT_API;
import static com.tcc.prueba.api.JsonKeys.CREATE_CLIENT;
import static com.tcc.prueba.api.JsonKeys.DELETE_CLIENT;
import static com.tcc.prueba.api.JsonKeys.UPDATE_CLIENT;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;


public class ClienteApi {

    private String apiUrl;
    private RequestQueue requestQueue;

    public ClienteApi(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public void obtenerClientes(final ApiResponseCallback<JSONArray> callback) {
        String url = BASE_URL + CLIENT_API;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error.getMessage());
                    }
                });

        requestQueue.add(request);
    }

    public void registrarCliente(JSONObject cliente, final ApiResponseCallback<JSONObject> callback) {
        String url = BASE_URL + CLIENT_API + CREATE_CLIENT;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, cliente,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error.getMessage());
                    }
                });

        requestQueue.add(request);
    }

    public void actualizarCliente(JSONObject cliente, final ApiResponseCallback<JSONObject> callback) {
        String url = BASE_URL + CLIENT_API + UPDATE_CLIENT;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, cliente,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error.getMessage());
                    }
                });

        requestQueue.add(request);
    }

    public void eliminarCliente(int idCliente, final ApiResponseCallback<JSONObject> callback) {
        String url = BASE_URL + CLIENT_API + DELETE_CLIENT + idCliente;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error.getMessage());
                    }
                });

        requestQueue.add(request);
    }


}
