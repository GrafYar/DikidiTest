package ru.dikidi.dikiditest.data.network.services;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import ru.dikidi.dikiditest.data.network.resources.UserListRes;

public interface JSONPlaceHolderApi {



    @POST("home/info/")
    @FormUrlEncoded
    Call<UserListRes> getJson(@Field("city_id") int city_id);


}
