package com.example.madhyapradeshtourism;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
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
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.madhyapradeshtourism.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class First extends AppCompatActivity implements LocationListener {


    public double lat1, lng1;
    static public String finaladdress;

    Address finalad;


    LocationManager locationManager1;
    boolean GpsStatus1;
    boolean connected1;


    protected static final int REQUEST_CHECK_SETTINGS = 0x1;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);


        ConnectivityManager connectivityManager = (ConnectivityManager) First.this.getSystemService(First.this.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected1 = true;
        } else {
            connected1 = false;
        }

        if (!connected1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Turn on mobile data");
            builder.setMessage("To use this application you must need to data connection")
                    .setCancelable(false).setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    final Intent intent = getIntent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                   finish();
                  overridePendingTransition(0, 0);
                    startActivity(intent);
                  overridePendingTransition(0, 0);
                }
            })
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            First.this.finish();
                        }
                    });

            AlertDialog alert = builder.create();
            alert.show();
        } else {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (this.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    Activity#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.

                    ActivityCompat.requestPermissions(First.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 200);
                    return;
                } else {
                    locationManager1 = (LocationManager) First.this.getSystemService(LOCATION_SERVICE);
                    locationManager1.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 50, this);
                    CheckGpsStatus();
                    if (GpsStatus1) {
Handler handler=new Handler();
handler.postDelayed(new Runnable() {
    @Override
    public void run() {
      Intent intent=new Intent(First.this,Main.class);
      startActivity(intent);
      finish();
    }
},3000);
                    } else {
                       displayLocationSettingsRequest(this);
                    }
                }
            }
            else
            {
                locationManager1 = (LocationManager) First.this.getSystemService(LOCATION_SERVICE);
                locationManager1.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 50, this);
                CheckGpsStatus();
                if (GpsStatus1) {
                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent=new Intent(First.this,Main.class);
                            startActivity(intent);
                            finish();
                        }
                    },3000);
                } else {
                    displayLocationSettingsRequest(this);
                }
            }


        }}


        public void displayLocationSettingsRequest ( final Context first){
            GoogleApiClient googleApiClient = new GoogleApiClient.Builder(first)
                    .addApi(LocationServices.API).build();
            googleApiClient.connect();

            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(10000);
            locationRequest.setFastestInterval(10000 / 2);

            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
            builder.setAlwaysShow(true);

            PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(LocationSettingsResult result) {
                    final Status status = result.getStatus();
                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.SUCCESS:
                            // Log.i(TAG, "All location settings are satisfied.");
                            final Intent intent = getIntent();
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                            break;
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                            try {
                                // Show the dialog by calling startResolutionForResult(), and check the result
                                // in onActivityResult().
                                status.startResolutionForResult((Activity) first, REQUEST_CHECK_SETTINGS);

                            } catch (IntentSender.SendIntentException e) {
                                //  Log.i(TAG, "PendingIntent unable to execute request.");
                            }
                            Handler handler2=new Handler();
                            handler2.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent=new Intent(First.this,Main.class);
                                    startActivity(intent);
                                    finish();
                                }
                            },5000);
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            //  Log.i(TAG, "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
                            //  Log.i(TAG, "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");
                            Handler handler1=new Handler();
                            handler1.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent=new Intent(First.this,Main.class);
                                    startActivity(intent);
                                    finish();
                                }
                            },5000);
                            break;
                    }
                }
            });

        }


        public Boolean CheckGpsStatus () {

            locationManager1 = (LocationManager) First.this.getSystemService(Context.LOCATION_SERVICE);

            GpsStatus1 = locationManager1.isProviderEnabled(LocationManager.GPS_PROVIDER);
            return GpsStatus1;

        }

        @Override
        public void onLocationChanged (Location location){
            double lat = location.getLatitude();
            double lng = location.getLongitude();

            try {
                Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);

                List<Address> ad = geocoder.getFromLocation(lat, lng, 10);
                finalad = ad.get(0);
                finaladdress = finalad.getSubAdminArea();
                SharedPreferences sharedPreferences = getSharedPreferences("Location", MODE_PRIVATE);
                SharedPreferences.Editor e;
                e = sharedPreferences.edit();
                e.putString("location", finaladdress);
                e.commit();


            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onStatusChanged (String provider,int status, Bundle extras){

        }

        @Override
        public void onProviderEnabled (String provider){

        }

        @Override
        public void onProviderDisabled (String provider){

        }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        for( int x = 0; x < grantResults.length; x++ )
        {
            if( grantResults[x] == PackageManager.PERMISSION_GRANTED )
            {
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent=new Intent(First.this,Main.class);
                        startActivity(intent);
                        finish();
                    }
                },3000);
            }
            else
            {
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent=new Intent(First.this,Main.class);
                        startActivity(intent);
                        finish();
                    }
                },3000);
            }
        }
    }
}


