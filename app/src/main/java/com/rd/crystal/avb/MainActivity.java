package com.rd.crystal.avb;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static int NETWORK_STATE = 0, LOCATION_STATE = 0;
    public static final int CONNECTED = 1;
    public static final int NOT_CONNECTED = 2;
    private WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        WebView myWebView = (WebView) findViewById(R.id.mainview);
//        myWebView.getSettings().setJavaScriptEnabled(true);
//        myWebView.loadUrl("http://budnetdesign.in/avb/");

        mWebview = new WebView(this);

        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript

        final Activity activity = this;

        mWebview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
        });

        mWebview.loadUrl("http://budnetdesign.in/avb/");
        setContentView(mWebview);

        this.registerReceiver(this.mConnReceiver, new IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION));

    }

    public BroadcastReceiver mConnReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            if (isNetworkAvailable(context)) {
                // code here
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection. Your device is currently not connected to the internet, please try again!", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    };

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
