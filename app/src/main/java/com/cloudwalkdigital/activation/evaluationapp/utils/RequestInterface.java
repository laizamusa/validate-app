package com.cloudwalkdigital.activation.evaluationapp.utils;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by henry on 08/06/2016.
 */
public interface RequestInterface {

    @GET
    Call<JSONResponse> getJSON(@Url String url);
}
