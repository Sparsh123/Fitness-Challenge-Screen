package com.sparsh.trackichallengescreen;
import retrofit2.Call;
import retrofit2.http.GET;


import java.util.List;

public interface Api {

    String BASE_URL =  "http://www.mocky.io/v2/";

@GET("5d1f27683100009073ebeb1b")
Call<ResponseModel> getData();

}
