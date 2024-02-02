package com.javabasics;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;

import java.util.List;

public interface JSONPlaceholderApiInterface {
    // what is retrofit ?? is a REST client in java to fetch data from other api's
    // similar to Axios in js, which is a REST client in js to fetch data from api
    // it is a adapter interface which calls thord party api's

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("photos")
    Call<List<Photo>> getPhotos();

    public static JSONPlaceholderApiInterface getJsonPlaceholderBaseUrl() {
        JSONPlaceholderApiInterface jpApi = new Retrofit.Builder()
                    .addConverterFactory(JacksonConverterFactory.create())
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .build()
                    .create(JSONPlaceholderApiInterface.class);
        return jpApi;
    }
}
