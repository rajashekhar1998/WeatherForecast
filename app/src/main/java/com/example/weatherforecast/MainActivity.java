package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.weatherforecast.Retrofit.ApiClient;
import com.example.weatherforecast.Retrofit.ApiInterface;
import com.example.weatherforecast.Retrofit.Example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button search;
    private TextView temp,humidity,feel;
    private EditText city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search=findViewById(R.id.search);
        temp=findViewById(R.id.temp);
        humidity=findViewById(R.id.humidity);
        feel=findViewById(R.id.feel);
        city=findViewById(R.id.city);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    weather(city.getText().toString().trim()); 
            }
        });

    }
    //the whole logic of API calling and converting JSON to java objects is done here
    public void weather(String city){
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);

        Call<Example> call= apiInterface.getWeatherData(city);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                temp.setText("Temp"+" "+response.body().getMain().getTemp()+" C");
                feel.setText("Feels Like"+" "+response.body().getMain().getFeels_like());
                humidity.setText("Humidity"+" "+response.body().getMain().getHumidity());

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }
}
