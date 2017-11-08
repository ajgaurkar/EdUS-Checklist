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

public class Trash extends AppCompatActivity {


    private SessionManager mainManager;
    private ListView trashListView;
    private ArrayList<ItemListData> item_arrayList;
    private ArrayList<String> trash_List;

    ArrayList<String> trashList;
    private DatabaseHandler databaseHandler;
    private Cursor trashCursor;
    private AdView mAdView;
    private ArrayAdapter<String> adapter;
    private TextView noTrashTextView;
    private AdRequest adRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trash_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.trashToolbar);
        setSupportActionBar(toolbar);
//        toolbar.setTitle("Trash");
//        toolbar.setTitleTextColor(Color.parseColor("#00ffffff"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseHandler = new DatabaseHandler(getApplicationContext());
        mainManager = new SessionManager(getApplicationContext());
        trashListView = (ListView) findViewById(R.id.trash_List_View);
        noTrashTextView = (TextView) findViewById(R.id.noTrashTextView);
        mAdView = (AdView) findViewById(R.id.trash_adView);
        item_arrayList = new ArrayList<>();
        trash_List = new ArrayList<>();

//        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Get first part. standard items
        item_arrayList = databaseHandler.getItemListForTrash(1);

        for (int i = 0; i < item_arrayList.size(); i++) {
            trash_List.add(item_arrayList.get(i).getItemName());
        }

        adapter = new ArrayAdapter<String>(Trash.this, R.layout.trash_list_item, trash_List);
        trashListView.setAdapter(adapter);

        if (trash_List.size() == 0) {
            noTrashTextView.setVisibility(View.VISIBLE);
        } else {
            noTrashTextView.setVisibility(View.GONE);
        }

        trashListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                popDialog(position);


            }
        });

        new Handler().postDelayed(new Runnable() {
            // Using handler with postDelayed called runnable run method
            @Override
            public void run() {
                loadBannerAd();
            }
        }, 1000); // wait for n seconds

        //ad listener to set visibility of banner
        mAdView.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                mAdView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdFailedToLoad(int error) {
            }

        });
    }

    private void loadBannerAd() {
//        adRequest = new AdRequest.Builder().addTestDevice("CD8AB9C199B862A741CDCEDB0C9AB3CC").build();
        adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void popDialog(final int position) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Restore");
        alertDialog.setCancelable(false);
        alertDialog.setMessage("Do you want to retore " + item_arrayList.get(position).getItemName());
        alertDialog.setPositiveButton("Restore", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                restoreItem(position);
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.create().show();
    }

    private void restoreItem(int position) {

        ItemListData itemToRestore = item_arrayList.get(position);
        itemToRestore.setDeletedStatus(0);
        databaseHandler.updateItem(itemToRestore);

        trash_List.remove(position);

        int index = trashListView.getFirstVisiblePosition();
        View v = trashListView.getChildAt(0);
        int top = (v == null) ? 0 : v.getTop();

        adapter.notifyDataSetChanged();
        //attendanceListView.setAdapter(attendanceRefreshAdapter);
        trashListView.setSelectionFromTop(index, top);

        if (trash_List.size() == 0) {
            noTrashTextView.setVisibility(View.VISIBLE);
        } else {
            noTrashTextView.setVisibility(View.GONE);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    //    @Override
//    public void onPause() {
//        if (mAdView != null) {
//            mAdView.pause();
//        }
//        super.onPause();
//    }
//
//    @Override
//    public void onDestroy() {
//        if (mAdView != null) {
//            mAdView.destroy();
//        }
//        super.onDestroy();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        System.out.println("ONRESUME CALLED");
//        checkNotificationStatusToSetMsg();
//
//    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
