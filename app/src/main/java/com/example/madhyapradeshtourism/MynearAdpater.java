package com.example.madhyapradeshtourism;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MynearAdpater extends RecyclerView.Adapter<MynearAdpater.ViewHolder>
{

    Context context;
    List<CityModel> mydata;

    public MynearAdpater(Context context, List<CityModel> mydata) {
        this.context = context;
        this.mydata = mydata;
    }

    @NonNull
    @Override
    public MynearAdpater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_mynear_adpater,parent,false);
       ViewHolder viewHolder = new ViewHolder(v);
       return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MynearAdpater.ViewHolder holder, final int position) {
        holder.c.setText(mydata.get(position).getPlacename());
        Picasso.with(context)
                .load(mydata.get(position).getPhoto())
                .placeholder(R.drawable.mpnewlogo)
                .error(R.drawable.mpnewlogo)
                // To fit image into imageView
                .fit()
                // To prevent fade animation
                .noFade()
                .into(holder.im);

        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, VanVihar.class);
                i.putExtra("position",""+mydata.get(position).getId());
                i.putExtra("image",""+mydata.get(position).getPhoto());
                i.putExtra("timing",""+mydata.get(position).getTimings());
                i.putExtra("timerequired",""+mydata.get(position).getTimerequired());
                i.putExtra("entry",""+mydata.get(position).getEntryfees());
                i.putExtra("address",""+mydata.get(position).getAddress());
                i.putExtra("name",""+mydata.get(position).getPlacename());
                i.putExtra("buses",""+mydata.get(position).getBuses());
                i.putExtra("rating",""+mydata.get(position).getRatings());
                i.putExtra("enoverviw",""+mydata.get(position).getEnoverview());
                i.putExtra("hioverviw",""+mydata.get(position).getHinoverview());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mydata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView c;
        ImageView im;
        RelativeLayout click;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            click=itemView.findViewById(R.id.click2);
            c=itemView.findViewById(R.id.plcname);
            im=itemView.findViewById(R.id.plcimg);
        }
    }
}