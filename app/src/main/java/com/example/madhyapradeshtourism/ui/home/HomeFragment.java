package com.example.madhyapradeshtourism.ui.home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.madhyapradeshtourism.Adapter;
import com.example.madhyapradeshtourism.Bhopal;
import com.example.madhyapradeshtourism.CityModel;
import com.example.madhyapradeshtourism.First;
import com.example.madhyapradeshtourism.HomeImagmodel;
import com.example.madhyapradeshtourism.ImageSLiderAdapter;
import com.example.madhyapradeshtourism.Main;
import com.example.madhyapradeshtourism.MainHolder;
import com.example.madhyapradeshtourism.MynearAdpater;
import com.example.madhyapradeshtourism.Popularplcadapter;
import com.example.madhyapradeshtourism.R;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.LOCATION_SERVICE;

public class HomeFragment extends Fragment  {

public  static  RecyclerView recyclerView,recyclerView2;
RecyclerView.LayoutManager layoutManager,layoutManager2;
    Address finalad;
    String finaladdress;
RequestQueue requestQueue;
CardView cardView;
    String Url5="https://navneetandroid.000webhostapp.com/Showaboutmp.php";
   public static ViewPager viewPager;
    LinearLayout sliderDotspanel,viewall1,viewall;
   public static ProgressBar progressBar1,progressBar2;
    private int dotscount;
    TextView bnrtext;
    TextView textView;
    private ImageView[] dots;
    String hindi,english,img;

public static  ImageView bnner;
    MainHolder mainHolder =new MainHolder();
    public    Handler mHandler1 = new Handler();
    String[] result;

    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=root.findViewById(R.id.pplrrecycler);
      progressBar1=root.findViewById(R.id.progress1);
      viewall1=root.findViewById(R.id.viewallview1);
cardView=root.findViewById(R.id.bnnercardview);
        recyclerView2=root.findViewById(R.id.nearmerecyclerview);
        bnrtext=root.findViewById(R.id.bnrtext);
        layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView2.setLayoutManager(layoutManager);
        viewall=root.findViewById(R.id.viewallview);
textView=root.findViewById(R.id.plcname1);





textView.setText("Popular Places of "+Main.mylocation);


        bnrtext.setText("The Heart of India");


        viewall1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),Bhopal.class);
                intent.putExtra("condition","Popular Places");
                intent.putExtra("popular","Popular Places of Madhya Pradesh");
                startActivity(intent);
            }
        });

        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),Bhopal.class);
                intent.putExtra("condition","Near by");
                intent.putExtra("cityname",Main.mylocation);
                startActivity(intent);
            }
        });

bnner=root.findViewById(R.id.bannrimg);

        viewPager = root.findViewById(R.id.viewPager);
        sliderDotspanel = root.findViewById(R.id.SliderDots);



timer();
timer1();






        viewPager.setAdapter(Main.imageSliderAdapter);
        recyclerView.setAdapter(Main.popularplcadapter);
        recyclerView2.setAdapter(Main.mynearAdpater);

        Picasso.with(getContext())
                .load(Main.data[0])
                .placeholder(R.drawable.mpnewlogo)
                .error(R.drawable.mpnewlogo)
                // To fit image into imageView
                .fit()
                // To prevent fade animation
                .noFade()
                .into(bnner);

progressBar1.setVisibility(View.GONE);
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == 5-1) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);



cardView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        mainHolder.vibrate(getContext());
        showpopup();
    }
});


        return root;
    }

    private void timer() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (true) {
                    try {
                        Thread.sleep(6000);


                        mHandler1.post(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                // Write your code here to update the UI.

                                bnrtext.setText("Click here ");


                            }
                        });
                    }

                    catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        }).start();

    }


    private void timer1() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (true) {
                    try {
                        Thread.sleep(5000);


                        mHandler1.post(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                // Write your code here to update the UI.

                                bnrtext.setText("The Heart of India");



                            }
                        });
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        }).start();

    }



    @SuppressLint("ResourceAsColor")
    public void showpopup() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext(),R.style.DialogTheme);
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.mppopup, null);
        final TextView about;

        final ImageView myimg;
        myimg=dialogView.findViewById(R.id.mpimg);
        dialog.setView(dialogView);
        TabLayout tabLayout;
        about=dialogView.findViewById(R.id.about);
        tabLayout=dialogView.findViewById(R.id.tabs);
        progressBar2=dialogView.findViewById(R.id.progress);
        tabLayout.addTab(tabLayout.newTab().setText("English"));
        tabLayout.addTab(tabLayout.newTab().setText("Hindi"));
        progressBar2.setVisibility(View.GONE);
        Picasso.with(getContext())
                .load(Main.data[0])
                .placeholder(R.drawable.mpnewlogo)
                .error(R.drawable.mpnewlogo)
                // To fit image into imageView
                .fit()
                // To prevent fade animation
                .noFade()
                .into(myimg);
        about.setText(Main.data[1]);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int pos =tab.getPosition();
                if (pos==1)

                {
                    about.setText(Main.data[2]);
                }
                if (pos==0)

                {
                    about.setText(Main.data[1]);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {


            }
        });

        final AlertDialog alertDialog = dialog.create();
        alertDialog.setCancelable(true);

        /*Picasso.with(context)
                .load(mydata.get(position1).getPhoto())
                .placeholder(R.drawable.mpnewlogo)
                .error(R.drawable.mpnewlogo)
                // To fit image into imageView
                .fit()
                // To prevent fade animation
                .noFade()
                .into(myimg);*/
        alertDialog.show();



    }



    }