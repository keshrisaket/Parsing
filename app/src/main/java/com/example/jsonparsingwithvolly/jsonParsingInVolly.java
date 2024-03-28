package com.example.jsonparsingwithvolly;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class jsonParsingInVolly extends AppCompatActivity {

    ListView listView;

    ArrayList<String> arrdata=new ArrayList<>();

    String Url="https://jsonplaceholder.typicode.com/posts";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_parsing_in_volly);

        listView=findViewById(R.id.postList);

        postApiVolly();






    }

    public void postApiVolly(){

        RequestQueue requestQueue= Volley.newRequestQueue(jsonParsingInVolly.this);

        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String name=jsonObject.getString("id");
                    String id=jsonObject.getString("title");
                    String roll=jsonObject.getString("name");
                    String adress=jsonObject.getString("title");

                    arrdata.add(id);
                    arrdata.add(name);
                    arrdata.add(roll);
                    arrdata.add(adress);

                    ArrayAdapter<String> arradp=new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1,arrdata);
                    listView.setAdapter(arradp);






                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("tag"," "+error);
            }
        }){

            @Override
            protected Map<String,String> getParams(){
                HashMap<String,String> param=new HashMap<>();
                param.put("id","1");
                param.put("title","saket");
                param.put("name","danaplur");
                return  param;
            }





        };

        requestQueue.add(stringRequest);

    }
}