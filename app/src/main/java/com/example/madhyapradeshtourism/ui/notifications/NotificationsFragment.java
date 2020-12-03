package com.example.madhyapradeshtourism.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.madhyapradeshtourism.CityAdapterView;
import com.example.madhyapradeshtourism.CityModel;
import com.example.madhyapradeshtourism.Main;
import com.example.madhyapradeshtourism.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

RecyclerView recyclerView;
    RequestQueue requestQueue;
    ProgressBar progressBar;
    List<CityModel> mydata=new ArrayList<>();
    String url ="https://navneetandroid.000webhostapp.com/showcity.php";
    RecyclerView.LayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

recyclerView=root.findViewById(R.id.recyclercity);
progressBar=root.findViewById(R.id.progress1);
progressBar.setVisibility(View.GONE);
        layoutManager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

recyclerView.setAdapter(Main.adapter);

        return root;
    }
}