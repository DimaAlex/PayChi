package com.paychi.dima.paychi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiClient {
    private static final  String BASE_SERVER_URL = "http://fastflow.team:8080/paychi/";

    private static RestApiClient instance;
    private PayChiService payChiService;

    private RestApiClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = httpClient.addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        payChiService = retrofit.create(PayChiService.class);
    }

    public static RestApiClient getInstance() {
        if (instance == null) {
            instance = new RestApiClient();
        }

        return instance;
    }

    public PayChiService getPayChiService() {
        return payChiService;
    }

}
