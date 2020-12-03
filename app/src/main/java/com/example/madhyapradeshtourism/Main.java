package com.example.madhyapradeshtourism;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.madhyapradeshtourism.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Main extends AppCompatActivity {


    public static String mylocation;
   public static CityAdapterView adapter;
    public static Popularplcadapter popularplcadapter;
    public static MynearAdpater mynearAdpater;
   public static Category_adapter category_adapter;
    public  static ImageSLiderAdapter imageSliderAdapter;
    RequestQueue requestQueue,requestQueue2,requestQueue3,requestQueue4,requestQueue5;
    List<CityModel> mydata=new ArrayList<>();
    final List<HomeImagmodel> mydata2=new ArrayList<HomeImagmodel>();
    List<CityModel> mydata3=new ArrayList<>();
    final List<Imagemodel> mydata4=new ArrayList< Imagemodel>();
    final List<CategoryModel> mydata5=new ArrayList<CategoryModel>();
    String url ="https://navneetandroid.000webhostapp.com/showcity.php";
    String Url2 ="https://navneetandroid.000webhostapp.com/showplacebyrating.php";
    String Url1 = "https://navneetandroid.000webhostapp.com/showplacebycity.php";
    String Url3= "https://navneetandroid.000webhostapp.com/showimage.php";
    String Url4="https://navneetandroid.000webhostapp.com/Showaboutmp.php";
    String Url5 = "https://navneetandroid.000webhostapp.com/showcategory.php";
    String hindi,english,img;
  public static String[]data=new String[3];
  ProgressBar progressBar;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
progressBar=findViewById(R.id.progress);
        final SharedPreferences sharedPreferences = getSharedPreferences("Location", MODE_PRIVATE);
        mylocation = sharedPreferences.getString("location", "bhopal");
MainHolder mainHolder=new MainHolder();
        mainHolder.checkcon(Main.this,Main.this);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_categories)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
        cityadd();
        homedata();
        categoryshow();
        progressBar.setVisibility(View.VISIBLE);
        }


    public void categoryshow() {

        requestQueue5 = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest obj = new JsonObjectRequest(Request.Method.POST, Url5, new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                JSONArray jsonArray = null;
                try {
                    jsonArray = response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);

                        String img = obj.getString("image");
                        String name = obj.getString("name");

                        CategoryModel model = new CategoryModel();
                        model.setName(name);
                        model.setImg(img);
                        mydata5.add(model);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                category_adapter = new Category_adapter(getApplicationContext(), mydata5);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {



            }
        });
        requestQueue5.add(obj);

    }



    private void homedata() {
showplacebyrating();
shownearplace();
        aboutmp();
        imgslider();

    }
    public void imgslider() {
        requestQueue3= Volley.newRequestQueue(getApplicationContext());
        StringRequest request=new StringRequest(Request.Method.POST, Url3, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject jsonObject =new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i =0; i<jsonArray.length();i++)
                    {

                        JSONObject obj =jsonArray.getJSONObject(i);
                        String img=obj.getString("url");
                        String name=obj.getString("name");
                        Imagemodel imagemodel =new Imagemodel();

                        imagemodel.setPhoto(img);
                        imagemodel.setName(name);

                        mydata4.add(imagemodel);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

             imageSliderAdapter =new ImageSLiderAdapter(getApplicationContext(),mydata4);
                HomeFragment.viewPager.setAdapter(imageSliderAdapter);




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }


        });


        requestQueue.add(request);

    }

    private String[] aboutmp() {


        requestQueue4 = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Url4, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {


                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    JSONObject obj = jsonArray.getJSONObject(0);
                    img = obj.getString("image");
                    english = obj.getString("english");
                    hindi = obj.getString("hindi");
              progressBar.setVisibility(View.GONE);
                    data[0]=img;
                    data[1]=english;
                    data[2]=hindi;


                    Picasso.with(getApplicationContext())
                            .load(Main.data[0])
                            .placeholder(R.drawable.mpnewlogo)
                            .error(R.drawable.mpnewlogo)
                            // To fit image into imageView
                            .fit()
                            // To prevent fade animation
                            .noFade()
                            .into(HomeFragment.bnner);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }


        });
        requestQueue4.add(request);
        return data;

    }


    public void shownearplace() {
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest request=new StringRequest(Request.Method.POST, Url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject =new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
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
                        mydata3.add(model);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                mynearAdpater = new MynearAdpater(getApplicationContext(),mydata3);
                HomeFragment.recyclerView2.setAdapter(mynearAdpater);
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
                params.put("cityname",Main.mylocation);
                return params;
            }
        };


        requestQueue.add(request);

    }

    public void showplacebyrating() {
        requestQueue2= Volley.newRequestQueue(getApplicationContext());
        StringRequest request=new StringRequest(Request.Method.POST, Url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {



                try {
                    JSONObject jsonObject =new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
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

                        HomeImagmodel model =new HomeImagmodel();
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


                        mydata2.add(model);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
               popularplcadapter =new Popularplcadapter(getApplicationContext(),mydata2);
                HomeFragment.recyclerView.setAdapter(Main.popularplcadapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }


        });


        requestQueue2.add(request);

    }

    private void cityadd() {
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest obj = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray jsonArray = null;

                try {
                    jsonArray = response.getJSONArray("data");
                    for (int i =0; i<jsonArray.length();i++)
                    {
                        JSONObject obj =jsonArray.getJSONObject(i);
                        String cityname=obj.getString("name");

                        CityModel model = new CityModel();
                        model.setName(cityname);
                        mydata.add(model);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter =new CityAdapterView(Main.this,mydata);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        requestQueue.add(obj);

    }


}
