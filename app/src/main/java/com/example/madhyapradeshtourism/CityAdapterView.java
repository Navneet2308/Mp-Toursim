package com.example.madhyapradeshtourism;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class CityAdapterView extends RecyclerView.Adapter<CityAdapterView.ViewHolder>

{


View v;
Context context;
    List<CityModel> mydata;

    public CityAdapterView(Context context, List<CityModel> mydata) {
        this.context = context;
        this.mydata = mydata;
    }

    @NonNull
    @Override
    public CityAdapterView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_city_adapter_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapterView.ViewHolder holder, final int position) {
        holder.c.setText(mydata.get(position).getName());
holder.click.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


            Intent i = new Intent(v.getContext(),Bhopal.class);
        i.putExtra("condition","Near by");
        i.putExtra("cityname",mydata.get(position).getName());
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
            c=itemView.findViewById(R.id.cityname);
            im=itemView.findViewById(R.id.next);
            click=itemView.findViewById(R.id.click);
        }
    }
}