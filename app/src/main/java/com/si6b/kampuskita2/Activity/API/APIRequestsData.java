package com.si6b.kampuskita2.Activity.API;

import com.si6b.kampuskita2.Activity.Model.ModelResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestsData {
    @GET("retrieve.php")
    Call<ModelResponse> ardRetrieve();

    @FormUrlEncoded
    @POST("create.php")
    Call<ModelResponse> ardCreate(
            @Field("nama") String nama,
            @Field("kota") String kota,
            @Field("alamat") String alamat
    );

    @FormUrlEncoded
    @POST("update.php")
    Call<ModelResponse> ardUpdate(
            @Field("id") String id,
            @Field("nama") String nama,
            @Field("kota") String kota,
            @Field("alamat") String alamat
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<ModelResponse> ardDelete(
            @Field("id") String id
    );
}
