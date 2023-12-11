package com.example.moonbecafe.Api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit getClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
//        Gson gson = new GsonBuilder().serializeNulls().create();
        //                   ubah ip sesuai dengan laptop
        Retrofit retrofit = new Retrofit.Builder()
//                   ubah ip sesuai dengan laptop
                .baseUrl("http://192.168.62.149/MoonBeCafe-master/public/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }

    public  static Service getUserService(){
        Service userService = getClient().create(Service.class);
        return userService;
    }


}
