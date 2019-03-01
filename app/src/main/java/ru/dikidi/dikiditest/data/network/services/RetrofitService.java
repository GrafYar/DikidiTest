package ru.dikidi.dikiditest.data.network.services;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.logging.HttpLoggingInterceptor;

public class RetrofitService {

    private static RetrofitService mInstance;
    private static final String BASE_URL = "http://api.beauty.dikidi.ru/";
    private Retrofit mRetrofit;
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private RetrofitService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
        //        .addInterceptor(interceptor);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.addInterceptor(interceptor).build())
                .build();
    }


    public static RetrofitService getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitService();
        }
        return mInstance;
    }

    public JSONPlaceHolderApi getJSONApi() {
        return mRetrofit.create(JSONPlaceHolderApi.class);
    }
}


