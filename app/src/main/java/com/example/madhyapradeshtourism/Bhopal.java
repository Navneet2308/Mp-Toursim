package com.example.madhyapradeshtourism;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.lang.UCharacter;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bhopal extends AppCompatActivity {

    RecyclerView.LayoutManager layoutManager;
    String Url1 = "https://navneetandroid.000webhostapp.com/showplacebycity.php";
    String Url2 = "https://navneetandroid.000webhostapp.com/showplacebycategory.php";
    String Url3 ="https://navneetandroid.000webhostapp.com/showplacebyrating.php";
    String cat, city,popular;
    final List<CityModel> mydata = new ArrayList<>();
    RecyclerView rc;
    ProgressBar progressBar;
    RequestQueue requestQueue;
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.bhopal);
textView=findViewById(R.id.place);
        rc = findViewById(R.id.recycler);
        final String condition = getIntent().getExtras().getString("condition", "");
        city = getIntent().getExtras().getString("cityname", "");
        cat = getIntent().getExtras().getString("catname", "");
          popular=getIntent().getExtras().getString("popular","");
progressBar=findViewById(R.id.progress);
        layoutManager = new LinearLayoutManager(Bhopal.this, LinearLayoutManager.VERTICAL, false);
        rc.setLayoutManager(layoutManager);
      MainHolder mainHolder=new MainHolder();
      mainHolder.checkcon(Bhopal.this,Bhopal.this);

        if (condition.equals("category")) {
            textView.setText(cat);
            showplcbycategory();

        }

        if (condition.equals("Near by")) {
            textView.setText("Places To Visit In "+city);
            showplcbycity();
        }

        if (condition.equals("Popular Places")) {
            textView.setText("Popular Places of Madhya Pradesh");
            showplacebyrating();
        }
    }

    private void showplacebyrating() {
        requestQueue= Volley.newRequestQueue(Bhopal.this);
        StringRequest request=new StringRequest(Request.Method.POST, Url3, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {



                try {
                    JSONObject jsonObject =new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    progressBar.setVisibility(View.GONE);
                    for (int i =0; i<jsonArray.length();i++)
                    {

                        JSONObject obj =jsonArray.getJSONObject(i);
                        String id=obj.getString("id");
                        String cityid=obj.getString("cityname");
                        String name=obj.getString("name");
                        String hioverview=obj.getString("hioverview");
                        String timings=obj.getString("timings");
                        String timerequired=obj.getString("timerequired");
                        String entryfees=obj.getString("entryfees");
                        String address=obj.getString("address");
                        String buses=obj.getString("buses");
                        String rating=obj.getString("rating");
                        String enoverview=obj.getString("enoverview");
                        String category=obj.getString("category");
                        String img=obj.getString("image");

                        CityModel model =new CityModel();
                        model.setId(id);
                        model.setAddress(address);
                        model.setBuses(buses);
                        model.setPlacecategory(category);
                        model.setCityid(cityid);
                        model.setTimings(timings);
                        model.setTimerequired(timerequired);
                        model.setRatings(rating);
                        model.setPlacename(name);
                        model.setEnoverview(enoverview);
                        model.setHinoverview(hioverview);
                        model.setPhoto(img);
                        model.setEntryfees(entryfees);
                        mydata.add(model);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Adapter adapter = new Adapter(Bhopal.this, mydata);
                rc.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }


        });


        requestQueue.add(request);

    }



    private void showplcbycity() {
        StringRequest request=new StringRequest(Request.Method.POST, Url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressBar.setVisibility(View.GONE);

                try {
                    JSONObject jsonObject =new JSONObject(response);
                   JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        String id = obj.getString("id");
                        String cityid = obj.getString("cityname");
                        String name = obj.getString("name");
                        String hioverview = obj.getString("hioverview");
                        String timings = obj.getString("timings");
                        String timerequired = obj.getString("timerequired");
                        String entryfees = obj.getString("entryfees");
                        String address = obj.getString("address");
                        String buses = obj.getString("buses");
                        String rating = obj.getString("rating");
                        String enoverview = obj.getString("enoverview");
                        String category = obj.getString("category");
                        String img = obj.getString("image");


                        CityModel model = new CityModel();
                        model.setId(id);
                        model.setAddress(address);
                        model.setBuses(buses);
                        model.setPlacecategory(category);
                        model.setCityid(cityid);
                        model.setTimings(timings);
                        model.setTimerequired(timerequired);
                        model.setRatings(rating);
                        model.setPlacename(name);
                        model.setEnoverview(enoverview);
                        model.setHinoverview(hioverview);
                        model.setPhoto(img);
                        model.setEntryfees(entryfees);
                        mydata.add(model);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Adapter adapter = new Adapter(Bhopal.this, mydata);
                rc.setAdapter(adapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<String,String>();
                params.put("cityname",city);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Bhopal.this);
        requestQueue.add(request);



}

    private void showplcbycategory() {

        StringRequest request=new StringRequest(Request.Method.POST, Url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressBar.setVisibility(View.GONE);

                try {
                    JSONObject jsonObject =new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        String id = obj.getString("id");
                        String cityid = obj.getString("cityid");
                        String name = obj.getString("name");
                        String hioverview = obj.getString("hioverview");
                        String timings = obj.getString("timings");
                        String timerequired = obj.getString("timerequired");
                        String entryfees = obj.getString("entryfees");
                        String address = obj.getString("address");
                        String buses = obj.getString("buses");
                        String rating = obj.getString("rating");
                        String enoverview = obj.getString("enoverview");
                        String category = obj.getString("category");
                        String img = obj.getString("image");


                        CityModel model = new CityModel();
                        model.setId(id);
                        model.setAddress(address);
                        model.setBuses(buses);
                        model.setPlacecategory(category);
                        model.setCityid(cityid);
                        model.setTimings(timings);
                        model.setTimerequired(timerequired);
                        model.setRatings(rating);
                        model.setPlacename(name);
                        model.setEnoverview(enoverview);
                        model.setHinoverview(hioverview);
                        model.setPhoto(img);
                        model.setEntryfees(entryfees);
                        mydata.add(model);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Adapter adapter = new Adapter(Bhopal.this, mydata);
                rc.setAdapter(adapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<String,String>();
                params.put("category",cat);
                return params;
            }
        }
       ;
         requestQueue= Volley.newRequestQueue(Bhopal.this);
        requestQueue.add(request);



    }


}


