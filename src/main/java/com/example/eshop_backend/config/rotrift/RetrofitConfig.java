package com.example.eshop_backend.config.rotrift;

import com.example.eshop_backend.config.rotrift.client.KeycloakClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class RetrofitConfig {


    public Retrofit.Builder build(String baseUrl) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .baseUrl(baseUrl);
    }

    @Bean
    public KeycloakClient keycloakClient(){
        return build("http://localhost:8181")
                .build()
                .create(KeycloakClient.class); // create an implementation of Api

    }


}
