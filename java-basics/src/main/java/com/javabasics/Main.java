package com.javabasics;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            System.out.println("Hello world! " + Greeting.greet());
        } else if (args[0].equals("exampledotcom")) {
            HttpLibraryClient httpClient = new HttpLibraryClient();
            System.out.println("getExampleDotCom :: \n" + httpClient.getExampleDotCom());

        } else if(args[0].equals("photos")) {
//            JSONPlaceholderApiInterface jpApi = new Retrofit.Builder()
//                    .addConverterFactory(JacksonConverterFactory.create())
//                    .baseUrl("https://jsonplaceholder.typicode.com/")
//                    .build()
//                    .create(JSONPlaceholderApiInterface.class);
            var jpApi = JSONPlaceholderApiInterface.getJsonPlaceholderBaseUrl();
            jpApi.getPhotos().execute().body().forEach(photo -> {
                System.out.println(photo.getTitle());
            });
        } else if(args[0].equals("posts")) {
            var jpApi = JSONPlaceholderApiInterface.getJsonPlaceholderBaseUrl();
            jpApi.getPosts().execute().body().forEach(photo -> {
                System.out.println(photo.getTitle());
            });
        }

    }
}