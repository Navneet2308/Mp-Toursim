package com.example.madhyapradeshtourism;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Vibrator;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainHolder {


    RecyclerView recyclerView,recyclerView2;
    RecyclerView.LayoutManager layoutManager,layoutManager2;
    Address finalad;
    String finaladdress;
    Vibrator vibrator;
    RequestQueue requestQueue;
    String Url1 = "";
    String Url2 ="";
    String Url3= "";
    String Url4="";


    final List<HomeImagmodel> mydata=new ArrayList<HomeImagmodel>();
    final List<CityModel> mydata1=new ArrayList<CityModel>();
    final List<Imagemodel> mydata2=new ArrayList< Imagemodel>();

    ArrayList<String> arrayList = new ArrayList<>();

    ArrayAdapter<String> adapter ;
String[]name;
    LinearLayout sliderDotspanel;
    ProgressBar progressBar1,progressBar2,progressBar3;
    private int dotscount;
    private ImageView[] dots;

    protected static final int REQUEST_CHECK_SETTINGS = 0x1;
    boolean GpsStatus1;
    public String deviceid1;
    public String time1,date1;
    public   double lat1,lng1;
    public boolean connected1 = false;
    String finaladd;
    LocationManager locationManager1;
    ProgressDialog progressDialog;



    public void checkcon(Context context, final Activity activity)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected1 = true;
        } else {
            connected1 = false;
        }

        if (!connected1) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Turn on mobile data");
            builder.setMessage("To use this application you must need to data connection")
                    .setCancelable(false).setNegativeButton("Back", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                   activity.finish();
                }
            })
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                                final Intent intent = activity.getIntent();
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                activity.finish();
                                activity.overridePendingTransition(0, 0);
                                activity.startActivity(intent);
                                activity.overridePendingTransition(0, 0);

                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    public void vibrate(Context context)

    {
       vibrator= (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(100);
    }









    public void searchdata(final EditText editText, final Context context, final ProgressBar progressBar, final ListView listView) {

        requestQueue= Volley.newRequestQueue(context);
        arrayList.clear();
        StringRequest request=new StringRequest(Request.Method.POST,Url4, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =new JSONObject(response);
                   JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i =0; i<jsonArray.length();i++)
                    {
                        progressBar.setVisibility(View.GONE);
                        JSONObject obj =jsonArray.getJSONObject(i);
                        String id=obj.getString("id");
                        String cityid=obj.getString("cityid");
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
                        arrayList.add(name);


                    }

                    if (arrayList.isEmpty())
                    {
                        progressBar.setVisibility(View.GONE);
                      arrayList.add("No data found");
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1, arrayList);
                        listView.setAdapter(arrayAdapter);

                    }

                   else  {
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1, arrayList);
                        listView.setAdapter(arrayAdapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String selectedItem = (String) parent.getItemAtPosition(position);
                                if (selectedItem.equals("No data found"))
                                {
                                    Snackbar snackbar = Snackbar.make(view, "data not found", Snackbar.LENGTH_LONG);
                                    snackbar.show();

                                }
                                else {
                                    showsearchdata(context, progressBar, selectedItem);
                                }
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }
        )
        {@Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<String,String>();
                params.put("name",editText.getText().toString().trim());
                return params;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);



    }




    public void showsearchdata( final Context context, final ProgressBar progressBar, final String name) {
        requestQueue= Volley.newRequestQueue(context);
        progressBar.setVisibility(View.VISIBLE);
        StringRequest request=new StringRequest(Request.Method.POST,Url4, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i =0; i<jsonArray.length();i++)
                    {
                        progressBar.setVisibility(View.GONE);
                        JSONObject obj =jsonArray.getJSONObject(i);
                        String id=obj.getString("id");
                        String cityid=obj.getString("cityid");
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

                        Intent i2 =new Intent(context,VanVihar.class);
                        i2.putExtra("image",img);
                        i2.putExtra("timing",timings);
                        i2.putExtra("timerequired",timerequired);
                        i2.putExtra("entry",entryfees);
                        i2.putExtra("address",address);
                        i2.putExtra("name",name);
                        i2.putExtra("buses",buses);
                        i2.putExtra("rating",rating);
                        i2.putExtra("enoverviw",enoverview);
                        i2.putExtra("hioverviw",hioverview);
                       context.startActivity(i2);


                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }
        )
        {@Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String>params=new HashMap<String,String>();
            params.put("name",name);
            return params;
        }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);



    }



}
