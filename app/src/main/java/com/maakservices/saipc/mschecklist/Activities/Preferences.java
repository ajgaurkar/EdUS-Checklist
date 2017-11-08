package com.maakservices.saipc.mschecklist.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.maakservices.saipc.mschecklist.R;
import com.maakservices.saipc.mschecklist.SessionManager;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by Ajinkya on 11/24/2016.
 */
public class Preferences extends AppCompatActivity {

    private AdView mAdView;
    private AdRequest adRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.preferencesToolbar);
        setSupportActionBar(toolbar);
//        toolbar.setTitle("Preferences");
//        toolbar.setTitleTextColor(Color.parseColor("#00ffffff"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SwitchCompat qualificationSwitch = (SwitchCompat) findViewById(R.id.showQualificationSwitchCompat);
        SwitchCompat confirmUncheckSwitch = (SwitchCompat) findViewById(R.id.confirmBeforeUncheckSwitchCompat);
        RelativeLayout trashPanel = (RelativeLayout) findViewById(R.id.trash_panel);
//        RelativeLayout sharePanel = (RelativeLayout) findViewById(R.id.share_app_by_panel);
        RelativeLayout feedbackPanel = (RelativeLayout) findViewById(R.id.feedback_panel);
        RelativeLayout aboutPanel = (RelativeLayout) findViewById(R.id.about_by_panel);
        RelativeLayout privacy_policy_panel = (RelativeLayout) findViewById(R.id.privacy_policy_panel);
        mAdView = (AdView) findViewById(R.id.preferences_adView);


        final SessionManager sessionManager = new SessionManager(getApplicationContext());

        if (sessionManager.getSpecificBooleanValue(SessionManager.SHOW_PRIORITY)) {
            qualificationSwitch.setChecked(true);
        } else {
            qualificationSwitch.setChecked(false);
        }

        if (sessionManager.getSpecificBooleanValue(SessionManager.CONFIRM_BEFORE_UNCHECK)) {
            confirmUncheckSwitch.setChecked(true);
        } else {
            confirmUncheckSwitch.setChecked(false);
        }

        qualificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sessionManager.setSpecificBooleanDetail(SessionManager.SHOW_PRIORITY, isChecked);
            }
        });
        confirmUncheckSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sessionManager.setSpecificBooleanDetail(SessionManager.CONFIRM_BEFORE_UNCHECK, isChecked);
            }
        });

        trashPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Trash pressed");
                Intent intent = new Intent(Preferences.this, Trash.class);
                startActivity(intent);
            }
        });


        privacy_policy_panel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Trash pressed");
                Intent intent = new Intent(Preferences.this, privacy_policy.class);
                startActivity(intent);
            }
        });


        feedbackPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("feedback pressed");
                Intent email = new Intent(Intent.ACTION_SENDTO);
                email.setType("message/rfc822");
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"msuschecklist@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Msus feedback");
                email.setData(Uri.parse("mailto:"));
                startActivity(Intent.createChooser(email, "Send Feedback :"));
            }
        });



//        sharePanel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("share pressed");
//
////                String payload = "Hey! checkout this app.\nMsus checklist V 1.0(Beta).\nUseful checklist for students going to USA";
//                Intent nav_share_Intent = new Intent(android.content.Intent.ACTION_SEND);
//                nav_share_Intent.setType("text/plain");
//                nav_share_Intent.putExtra(android.content.Intent.EXTRA_TEXT, R.string.app_share_payload);
//                startActivity(Intent.createChooser(nav_share_Intent, "Share via :"));
//            }
//        });
        aboutPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("about pressed");
                showAboutDialog();
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
//                mAdView.setVisibility(View.GONE);
            }

        });
    }

    private void loadBannerAd() {
        adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void showAboutDialog() {
        Dialog alertDialog = new Dialog(this);
        LayoutInflater flater = this.getLayoutInflater();
        View view = flater.inflate(R.layout.about_dialog, null);

        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(view);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

//        TextView headerTextView = (TextView) view.findViewById(R.id.notificationDialogHeaderTextView);
//        TextView descriptionTextView = (TextView) view.findViewById(R.id.notificationDialogDescriptionTextView);
//        ImageView typeImageView = (ImageView) view.findViewById(R.id.notificationDialogImageView);

//        headerTextView.setText(selectedNotificationListData.getNotificationHeader());
//        descriptionTextView.setText(selectedNotificationListData.getNotificationDescription());
//        typeImageView.setImageResource(selectedNotificationListData.getImgId());

        //line to prevent dimming effect behind dialog
        //alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        alertDialog.show();
    }

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
