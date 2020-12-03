package com.example.madhyapradeshtourism;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    View v;
    Context context;
    List<CityModel> mydata;
    public Adapter(Context context, List<CityModel> mydata) {
        this.context = context;
        this.mydata = mydata;
    }




    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adapter,parent,false);
       ViewHolder viewHolder = new ViewHolder(v);
       return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, final int position) {

        holder.tv1.setText(mydata.get(position).getPlacename());
        holder.tv2.setText(mydata.get(position).getEnoverview());
        holder.tv3.setText(mydata.get(position).getEntryfees());
        holder.tv4.setText(mydata.get(position).getTimings());

    Picasso.with(context)
            .load(mydata.get(position).getPhoto())
            .placeholder(R.drawable.mpnewlogo)
            .error(R.drawable.mpnewlogo)
            // To fit image into imageView
            .fit()
            // To prevent fade animation
            .noFade()
            .into(holder.iv);

       /* Picasso.with(context)
                .load(mydata.get(position).getPhoto())
                .into(holder.iv);*/



        holder.ck.setOnClickListener(new View.OnClickListener() {
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



        context.startActivity(i);
    }
});
    }

    @Override
    public int getItemCount() {
        return mydata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv1,tv2,tv3,tv4;
        RelativeLayout ck;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

ck=itemView.findViewById(R.id.click);

            iv=itemView.findViewById(R.id.image1);
            tv1=itemView.findViewById(R.id.vanvihar);
            tv2=itemView.findViewById(R.id.vanparagrapgh);
            tv3=itemView.findViewById(R.id.price);
            tv4=itemView.findViewById(R.id.timing);

        }
    }
}