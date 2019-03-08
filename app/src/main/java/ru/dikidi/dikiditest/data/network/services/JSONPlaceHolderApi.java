package ru.dikidi.dikiditest.data.network.services;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import ru.dikidi.dikiditest.data.network.resources.MainListRes;

public interface JSONPlaceHolderApi {

    @POST("home/info/")
    @FormUrlEncoded
    Call<MainListRes> getMainJson(@Field("city_id") int city_id);

}
