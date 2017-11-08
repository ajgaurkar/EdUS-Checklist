package com.maakservices.saipc.mschecklist.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.maakservices.saipc.mschecklist.Controllers.ItemListData;
import com.maakservices.saipc.mschecklist.DatabaseHandler;
import com.maakservices.saipc.mschecklist.R;
import com.maakservices.saipc.mschecklist.SessionManager;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

/**
 * Created by Ajinkya on 12/12/2016.
 */

public class privacy_policy extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacy_policy_main);


//        Toolbar toolbar = (Toolbar) findViewById(R.id.trashToolbar);
//        setSupportActionBar(toolbar);
////        toolbar.setTitle("Trash");
////        toolbar.setTitleTextColor(Color.parseColor("#00ffffff"));
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        WebView webView = (WebView) findViewById(R.id.privacy_policy_web_view);
        webView.loadUrl("https://sites.google.com/view/edusprivacypolicy/home");

    }



}
