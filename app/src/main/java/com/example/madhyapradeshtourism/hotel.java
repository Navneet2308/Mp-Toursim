package com.example.madhyapradeshtourism;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class hotel extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        webView=findViewById(R.id.web);
        webView.loadUrl("https://www.booking.com/city/in/bhopal.en.html?aid=306395;label=bhopal-FiZwxM_ghgio2oqr6TDRYwS392972671109:pl:ta:p190:p2:ac:ap1t2:neg:fi:tikwd-1698815709:lp1007792:li:dec:dm;ws=" +
                "&gclid=CjwKCAiAuqHwBRAQEiwAD-zr3XAXrY9WUMbW5C_Fd_QRuNW0M5Y22ew7QogjI2DmjDadsS6IHfBGwBoCc2MQAvD_BwE");

    }
}
