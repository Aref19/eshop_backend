package com.example.eshop_backend.config.rotrift.client;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.Map;

public interface KeycloakClient {

    @FormUrlEncoded
    @POST("/auth/realms/eshop_backend/protocol/openid-connect/token")
    public Call<Object> token(@FieldMap Map<String, String> map);


}
