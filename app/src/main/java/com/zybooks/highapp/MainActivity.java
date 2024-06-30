package com.zybooks.highapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import  retrofit2.Callback;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "inMain";

    private TextView username;
    private TextView password;
    private ApiService apiService;

    public static String TAGgen = "general tag";

    private Button createAccount;
    private String inputUsername;
    private String inputPassword;
    private AlertDialog.Builder builder;
    private Button loginButton;

    private static final String BASE_URL = "https://www.alphavantage.co/";
    private static final String API_KEY = "MN43IT6ESG3EYKVN";
    private Button goToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAGgen, "Main Activity on create called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        goToRegister = findViewById(R.id.registerButton);

        Retrofit getLocalUrl = RetrofitClientServer.callGetClient();
        Log.i(TAGgen, "Login retrofit created");
        apiService = getLocalUrl.create(ApiService.class);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

    }

    public void goToRegister(View v){
        Log.i(TAG, "Going from main to register");
        Intent goToRegister = new Intent(this, RegisterActivity.class);
        startActivity(goToRegister);
    }


    private void loginUser() {
        String user = username.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (!user.isEmpty() && !pass.isEmpty()) {
            User loginUser = new User(user, pass);
            Call<ServerResponse> call = apiService.loginUser(loginUser);
            call.enqueue(new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if (response.isSuccessful()) {
                        ServerResponse serverResponse = response.body();
                        if (serverResponse.getStatus().equals("success")) {
                            Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                            // Navigate to another activity
                        } else {
                            Toast.makeText(MainActivity.this, serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Login failed!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ServerResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }
    }


}