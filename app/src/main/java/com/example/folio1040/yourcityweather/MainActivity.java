package com.example.folio1040.yourcityweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> mEntries;
    private final String BASE_URL= "https://samples.openweathermap.org/data/2.5/forecast?lat=41&lon=28&appid=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    // Fetch method
    private void fetch(RequestQueue requestQueue) {
        JsonArrayRequest request = new JsonArrayRequest(BASE_URL + APIs.API_KEY,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        for(int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                mEntries.add(jsonObject.toString());
                            }
                            catch(JSONException e) {
                                mEntries.add("Error: " + e.getLocalizedMessage());
                            }
                        }

                     }
                 },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(MainActivity.this, "Unable to fetch data: " + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        mEntries = new ArrayList<>();
        requestQueue.add(request);
    }
}
