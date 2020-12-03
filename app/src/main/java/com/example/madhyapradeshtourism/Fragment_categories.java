package com.example.madhyapradeshtourism;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Fragment_categories extends Fragment
{
  public static   RecyclerView rc;
    RecyclerView.LayoutManager layoutManager;
    ProgressBar progressBar;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.activity_fragment_categories, container, false);

        rc = root.findViewById(R.id.gridrecycler);
        progressBar=root.findViewById(R.id.progress);
        progressBar.setVisibility(View.GONE);
        layoutManager =new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        rc.setLayoutManager(layoutManager);

          rc.setAdapter(Main.category_adapter);

        return root;



    }


}