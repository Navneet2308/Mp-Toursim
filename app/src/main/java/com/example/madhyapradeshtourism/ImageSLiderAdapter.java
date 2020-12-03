package com.example.madhyapradeshtourism;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageSLiderAdapter extends PagerAdapter
{


    private LayoutInflater layoutInflater;
    ImageView imageView;

    Context context;
    List<Imagemodel> mydata;

    public ImageSLiderAdapter(Context context, List<Imagemodel> mydata) {
        this.context = context;
        this.mydata = mydata;
    }



    @Override
    public int getCount() {
        return mydata.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return  view ==object;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = layoutInflater.inflate(R.layout.activity_image_slider_adapter, null);
      imageView =view.findViewById(R.id.img2);



        Picasso.with(context)
                .load(mydata.get(position).getPhoto())
                .placeholder(R.drawable.mpnewlogo)
                .error(R.drawable.mpnewlogo)
                // To fit image into imageView
                .fit()
                // To prevent fade animation
                .noFade()
                .into(imageView);

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);


        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }


}