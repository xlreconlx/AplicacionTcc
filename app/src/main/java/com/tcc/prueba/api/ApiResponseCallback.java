package com.tcc.prueba.api;

public interface ApiResponseCallback<T> {
    void onSuccess(T response);

    void onError(String errorMessage);
}
