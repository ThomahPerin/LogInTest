package com.zybooks.highapp;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
   // private static Retrofit retrofit = null;
    public static String TAGgen = "general tag";
    //http://10.0.2.2:3000/
    private static Map<String, Retrofit> usedURLs= new HashMap<>();

    public static Retrofit getClient(String baseUrl) {
        Log.i(TAGgen, "Retrofit called with baseUrl: " + baseUrl);

        if (usedURLs.containsKey(baseUrl)){
            Retrofit retrofit = usedURLs.get(baseUrl);
            Log.i(TAGgen, baseUrl + " used before");
            return retrofit;
        }
        else {
            Log.i(TAGgen, baseUrl + " is not used before");

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            usedURLs.put(baseUrl, retrofit);
            return retrofit;
        }

    }
}
