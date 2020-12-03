package com.example.madhyapradeshtourism;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class VanVihar extends AppCompatActivity {
TextView overview,rate1,rate3,plcname,timing3,waittime,fee,plcadd,plcbus;
Button rate2;
ImageView imageView;
RatingBar rate;
RadioButton hin,eng;
ProgressBar progressBar;
    private ScaleGestureDetector scaleGestureDetector;
    private float mScaleFactor = 1.0f;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_van_vihar);
hin =findViewById(R.id.hindi);
eng =findViewById(R.id.english);
plcname=findViewById(R.id.place);
imageView=findViewById(R.id.vanvihar1);
timing3=findViewById(R.id.timing2);
fee=findViewById(R.id.fees2);
progressBar=findViewById(R.id.progress);
rate1=findViewById(R.id.ratingshow);
rate=findViewById(R.id.ratingive);
plcadd=findViewById(R.id.add2);
rate3=findViewById(R.id.ratingshow1);
rate2=findViewById(R.id.ratingsubmit);
waittime=findViewById(R.id.waiting2);
overview =findViewById(R.id.overview1);
plcbus=findViewById(R.id.bus2);


 final String placename = getIntent().getExtras().getString("name","");

    final String image = getIntent().getExtras().getString("image","");
    final String timing = getIntent().getExtras().getString("timing","All time");
    final String timereq = getIntent().getExtras().getString("timerequired","");
    final String entryfee = getIntent().getExtras().getString("entry","free");
    final String address = getIntent().getExtras().getString("address","");
    final String buses = getIntent().getExtras().getString("buses","No buses");
    final String rating1 = getIntent().getExtras().getString("rating","1");
    final String enoverviw = getIntent().getExtras().getString("enoverviw","");
    final String hioverviw = getIntent().getExtras().getString("hioverviw","");


    if (hioverviw.equals(""))
    {
        hin.setVisibility(View.GONE);
    }


    Picasso.with(VanVihar.this)
            .load(image)
            .placeholder(R.drawable.mpnewlogo)
            .error(R.drawable.mpnewlogo)
            // To fit image into imageView
            .fit()
            // To prevent fade animation
            .noFade()
            .into(imageView);

progressBar.setVisibility(View.GONE);
    scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

    plcname.setText(placename);
    overview.setText(enoverviw);
    timing3.setText(timing);
    fee.setText(entryfee);
    plcbus.setText(buses);
    plcadd.setText(address);
    waittime.setText(timereq);



    hin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            overview.setText(hioverviw);
        }
    });

    eng.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            overview.setText(enoverviw);
        }
    });


rate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

     rate1.setText(""+rating1);
     rate3.setText(""+rating1);
    }
});




}

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);
        return true;
    }
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 100.0f));
            imageView.setScaleX(mScaleFactor);
            imageView.setScaleY(mScaleFactor);
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu1,menu);
        return super.onCreateOptionsMenu(menu);


    }
}
