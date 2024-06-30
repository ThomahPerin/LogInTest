package com.zybooks.highapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FetchPriceExample extends AppCompatActivity{

        private static final String TAG = "inMain";

        private static final String BASE_URL = "https://www.alphavantage.co/";
        private static final String API_KEY = "MN43IT6ESG3EYKVN";
        private StockApiService stockApiService;

        private TextView stockPriceTextView;

        private Button goToRegister;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            stockPriceTextView = findViewById(R.id.stock_price);

            Retrofit retrofit = RetrofitClient.getClient(BASE_URL);
            stockApiService = retrofit.create(StockApiService.class);
            goToRegister = findViewById(R.id.registerButton);

            getStockPrice("AAPL");  // Example for Apple Inc.
        }

        private void getStockPrice(String symbol) {
            Call<StockResponse> call = stockApiService.getStockPrice("GLOBAL_QUOTE", symbol, API_KEY);
            call.enqueue(new Callback<StockResponse>() {
                @Override
                public void onResponse(Call<StockResponse> call, Response<StockResponse> response) {
                    if (response.isSuccessful()) {
                        StockResponse stockResponse = response.body();
                        if (stockResponse != null && stockResponse.getGlobalQuote() != null) {
                            String price = stockResponse.getGlobalQuote().getPrice();
                            // Update UI with the stock price
                            stockPriceTextView.setText("Stock Price: " + price);
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

        public void goToMag7(View v){
            Log.i(TAG, "Going from main to mag 7");
            Intent goToMagnificent7 = new Intent(this, Display7.class);
            startActivity(goToMagnificent7);
        }

}
