package com.example.reyanthonypaano.apitester.rest;

import android.text.TextUtils;

import com.example.reyanthonypaano.apitester.model.Data;
import com.example.reyanthonypaano.apitester.model.User;
import com.example.reyanthonypaano.apitester.rest.config.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Data.class, new APIDeserializer<Data>())
                    .create();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(Constants.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson));

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null, null);
    }

    public static <S> S createService(Class<S> serviceClass, String user, String password) {
        if(!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password)) {
            String authToken = Credentials.basic(user, password);

            return createService(serviceClass, authToken);
        }

        return createService(serviceClass, null);
    }

    public static <S> S createService(Class<S> serviceClass, final String authToken) {
        if(!TextUtils.isEmpty(authToken)) {
            AuthInterceptor interceptor = new AuthInterceptor(authToken);

            HttpLoggingInterceptor header = new HttpLoggingInterceptor();
            HttpLoggingInterceptor body = new HttpLoggingInterceptor();
            header.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            body.setLevel(HttpLoggingInterceptor.Level.BODY);

            if(!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);

                httpClient.addInterceptor(header);
                httpClient.addInterceptor(body);
                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        }

        return retrofit.create(serviceClass);
    }
}
