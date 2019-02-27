package ru.dikidi.dikiditest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JSONPlaceHolderApi {

    /*@POST("home/info/")
    public Call<Post> getPostWithID(@Path("id") int id);*/

 //   @POST("home/info/")
 //   Call<ResponseData> getJson(@Body Post post);

    @POST("home/info/")
    @FormUrlEncoded
    Call<ResponseBody> getJson(@Field("city_id") int city_id);

   // @POST("/posts")
   // public Call<Post> postData(@Body Post data);

}
