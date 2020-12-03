package com.example.madhyapradeshtourism.ui.dashboard;

import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.madhyapradeshtourism.CategoryModel;
import com.example.madhyapradeshtourism.Category_adapter;
import com.example.madhyapradeshtourism.Fragment_categories;
import com.example.madhyapradeshtourism.MainHolder;
import com.example.madhyapradeshtourism.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class DashboardFragment extends Fragment {

EditText name1;
ImageView search;
ListView listView;
ProgressBar progressBar;
RequestQueue requestQueue;

String url="https://navneetandroid.000webhostapp.com/searchplace.php";
MainHolder mainHolder =new MainHolder();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        name1=root.findViewById(R.id.searchplc);
        search=root.findViewById(R.id.search);
        progressBar=root.findViewById(R.id.progress);
        listView=root.findViewById(R.id.list);

        progressBar.setVisibility(View.GONE);
        name1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            private Timer timer = new Timer();
            private final long DELAY = 500;

            @Override
            public void afterTextChanged(Editable s) {
progressBar.setVisibility(View.VISIBLE);
                timer.cancel();
                timer = new Timer();
                timer.schedule(
                        new TimerTask() {
                            @Override
                            public void run() {
                                // refresh your list

                                mainHolder.checkcon(getContext(),getActivity());
                                mainHolder.searchdata(name1,getContext(),progressBar,listView);
                            }
                        },
                        DELAY
                );
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                  mainHolder.checkcon(getContext(),getActivity());
                mainHolder.searchdata(name1,getContext(),progressBar,listView);

            }
        });




        return root;
    }


}