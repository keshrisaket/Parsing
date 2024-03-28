package com.example.jsonparsingwithvolly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button button;

    ListView listview;

    ArrayList<String> arrdata=new ArrayList<>();

    String url="https://jsonplaceholder.typicode.com/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview=findViewById(R.id.list);

        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, jsonParsingInVolly.class);
                startActivity(intent);
            }
        });

//        StringRequest stringRequest=new StringRequest(Request.Method.GET,url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                try {
//                    JSONArray jsonArray = new JSONArray(response);
//                    Log.d("TAG", "onResponse: "+response);
//
//                    for (int i=0;i<jsonArray.length();i++) {
//                        JSONObject jsonObject = jsonArray.getJSONObject(i);
//                        String email=jsonObject.getString("email");
//                        arrdata.add(email);
//                    }
//
//
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(MainActivity.this, "Error Occourd" + error, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        RequestQueue requestQueue= Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//
//        ArrayAdapter<String> arradp=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrdata);
//        listview.setAdapter(arradp);
          getapi();



    }

    public  void getapi(){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.GET,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    Log.d("TAG", "onResponse: "+response);

                    for (int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String email=jsonObject.getString("email");
                        arrdata.add(email);
                    }


                    ArrayAdapter<String> arradp=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,arrdata);
                    listview.setAdapter(arradp);



                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error Occourd" + error, Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);


    }
}