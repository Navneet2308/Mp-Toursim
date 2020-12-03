package com.example.madhyapradeshtourism;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Category_adapter extends RecyclerView.Adapter<Category_adapter.Category_ViewHolder> {


    Context context;
MainHolder mainHolder;
    List<CategoryModel> mydata;

    public Category_adapter(Context context, List<CategoryModel> mydata) {
        this.context = context;
        this.mydata = mydata;
    }


    public Category_adapter(Fragment_categories fragment_categories) {

    }


    @NonNull
    @Override
    public Category_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_category_adapter, parent, false);
        Category_ViewHolder category_viewHolder = new Category_ViewHolder(view);
        return category_viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull Category_ViewHolder holder, final int position) {

        holder.tv.setText(mydata.get(position).getName());
        Picasso.with(context)
                .load(mydata.get(position).getImg())
                .placeholder(R.drawable.mpnewlogo)
                .error(R.drawable.mpnewlogo)
                // To fit image into imageView
                .fit()
                // To prevent fade animation
                .noFade()
                .into(holder.imageView);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Bhopal.class);
               Vibrator vibrator= (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(100);
                if (position<4) {
                    intent.putExtra("condition", "category");
                    intent.putExtra("catname", mydata.get(position).getName());
                }
                if (position==4)
                {
                    intent.putExtra("condition", "Popular Places");
                    intent.putExtra("catname", mydata.get(position).getName());
                }
                if(position==5)
                {
                    intent.putExtra("condition", "Near by");
                    intent.putExtra("cityname",Main.mylocation);
                }
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }




                @Override
                public int getItemCount () {
                    return mydata.size();
                }

                public class Category_ViewHolder extends RecyclerView.ViewHolder {


LinearLayout linearLayout;
                    ImageView imageView;
                    TextView tv;

                    public Category_ViewHolder(@NonNull View itemView) {
                        super(itemView);
                        imageView = itemView.findViewById(R.id.image);
                        tv = itemView.findViewById(R.id.text);
                        linearLayout=itemView.findViewById(R.id.relative);
                    }
                }
            }