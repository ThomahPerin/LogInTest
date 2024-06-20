package com.zybooks.highapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Display7 extends AppCompatActivity{

    private static final String TAG = "inDisplay7";

    private static final String BASE_URL = "https://www.alphavantage.co/";
    private static final String API_KEY = "MN43IT6ESG3EYKVN";
    private StockApiService stockApiService;

    private ArrayList<String> theStockNames;
    private ArrayList<String> thePrices;

    HashMap<String, Double> priceAndNames;

    private TextView appleBlock, microsoftBlock, nvidiaBlock, teslaBlock, metaBlock, googleBlock, amazonBlock;



    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnificentseven);
        appleBlock = findViewById(R.id.apple);
        microsoftBlock = findViewById(R.id.microsoft);
        nvidiaBlock = findViewById(R.id.nvidia);
        teslaBlock = findViewById(R.id.tesla);
        metaBlock = findViewById(R.id.meta);
        googleBlock = findViewById(R.id.google);
        amazonBlock = findViewById(R.id.amazon);

        Retrofit retrofit = RetrofitClient.getClient(BASE_URL);
        stockApiService = retrofit.create(StockApiService.class);

        String applePrice, microsoftPrice, nvidiaPrice, teslaPrice, metaPrice, googlePrice, amazonPrice;
        String[] stockNames = {"AAPL", "MSFT", "NVDA", "TSLA", "META", "GOOGL", "AMZN"};

         theStockNames = new ArrayList<>();

         thePrices = new ArrayList<>();

         priceAndNames = new HashMap<>();



        for (String name : stockNames){
            getStockPrice(name);
        }

        /**for (String name : stockNames) {
            returnPrice returnItApple = new returnPrice() {
                @Override
                public void onPriceReceived(String price) {
                    name = price;

                }

                @Override
                public void onFailure() {
                    Log.i(TAG, "returning price failed");
                }
            }
        }*/




    }


    private void getStockPrice(String symbol) {
        Log.i(TAG, "getStockPrice called");

        Call<StockResponse> call = stockApiService.getStockPrice("GLOBAL_QUOTE", symbol, API_KEY);
        call.enqueue(new Callback<StockResponse>() {
            @Override
            public void onResponse(Call<StockResponse> call, Response<StockResponse> response) {
                if (response.isSuccessful()) {
                    StockResponse stockResponse = response.body();
                    if (stockResponse != null) {
                         String price = stockResponse.getGlobalQuote().getPrice();
                         priceAndNames.put(symbol, Double.parseDouble(price));
                        Log.i(TAG, "price: " + price);
                        // Update UI with the stock price

                    }
                }
            }

            @Override
            public void onFailure(Call<StockResponse> call, Throwable t) {
                Log.i(TAG, "API call fail");
                // Handle failure
            }
        });

    }

    public void exitMag(View v){
        Intent exit = new Intent(this, MainActivity.class);
        startActivity(exit);
    }
}
