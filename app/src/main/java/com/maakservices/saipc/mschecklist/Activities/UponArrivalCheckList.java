package com.maakservices.saipc.mschecklist.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.maakservices.saipc.mschecklist.Controllers.ItemListData;
import com.maakservices.saipc.mschecklist.DatabaseHandler;
import com.maakservices.saipc.mschecklist.Fragments.List_items_common_fragments;
import com.maakservices.saipc.mschecklist.R;
import com.maakservices.saipc.mschecklist.SessionManager;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.List;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

/**
 * Created by SAI PC on 11/23/2016.
 */
public class UponArrivalCheckList extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private EditText item_name_edit_text;
    private EditText item_detail_edit_text;
    private RadioGroup qualification_radio_group;
    private int currentTab = 0;
    ArrayList<String> fragmentNameIndexList;
    private DatabaseHandler databaseHandler;
    private TabLayout tabLayout;
    private AdView mAdview;
    SessionManager sessionManager;
    private View addItemDialogView;
    private long back_pressed;
    private AdRequest adRequest;
    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.setSpecificStringDetail(SessionManager.CURRENT_PAGE, "Post");

        setContentView(R.layout.uponarrival_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.postDeparturetoolbar);
        setSupportActionBar(toolbar);
        databaseHandler = new DatabaseHandler(getApplicationContext());

        mAdview = (AdView) findViewById(R.id.postdeparture_adView);
        adRequest = new AdRequest.Builder().build();

        fragmentNameIndexList = new ArrayList<>();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTabs();


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                invalidateOptionsMenu();

            }

            @Override
            public void onPageSelected(int position) {
                invalidateOptionsMenu();

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                invalidateOptionsMenu();

            }
        });
        new Handler().postDelayed(new Runnable() {
            // Using handler with postDelayed called runnable run method
            @Override
            public void run() {
                loadBannerAd();
            }
        }, 1000); // wait for n seconds

        mAdview.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                System.out.println(" INSIDE AD VISIBLE");
                mAdview.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdFailedToLoad(int error) {
                System.out.println(" INSIDE AD GONE");
                mAdview.setVisibility(View.GONE);
            }

        });

        loadInterstitialAd();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showShowcase();
            }
        }, 2500);
    }

    private void showShowcase() {
        // sequence example
        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(400); // half second between each showcase view
//        config.setMaskColor(Color.argb(150, 0, 0, 0));
        config.setMaskColor(Color.parseColor("#e774717c"));
        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this);

        sequence.setConfig(config);
        sequence.singleUse("MSUS_SHOWCASE_SEQUENCE");

        sequence.addSequenceItem(findViewById(R.id.tab_add_item),
                "ADD your own items to the list", "NEXT");
        sequence.addSequenceItem(findViewById(R.id.tab_share_list),
                "SHARE your list with your companions", "NEXT");
        if (sessionManager.getSpecificBooleanValue(SessionManager.LIST_ITEM_EXPAND_STATUS)) {
            sequence.addSequenceItem(findViewById(R.id.tab_list_detail_collapse),
                    "EXPAND or COLLAPSE item details anytime", "DONE");
        } else {
            sequence.addSequenceItem(findViewById(R.id.tab_list_detail_expand),
                    "EXPAND or COLLAPSE item details anytime", "DONE");
        }
        sequence.start();
    }


    private void loadBannerAd() {
//        adRequest = new AdRequest.Builder().addTestDevice("CD8AB9C199B862A741CDCEDB0C9AB3CC").build();
        adRequest = new AdRequest.Builder().build();
        mAdview.loadAd(adRequest);

    }

    //AD functions...load AND show
    private void loadInterstitialAd() {
        mInterstitialAd = new InterstitialAd(this);

        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.edusinterstitial));

//        AdRequest adRequest = new AdRequest.Builder().addTestDevice("CD8AB9C199B862A741CDCEDB0C9AB3CC").build();
        AdRequest adRequest = new AdRequest.Builder().build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {

                System.out.println("mInterstitialAd value : " + mInterstitialAd);
                System.out.println("INTER LOADED");

            }
        });
    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            System.out.println("SHOW INTER CALLED");
            mInterstitialAd.show();
            loadInterstitialAd();

        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    private void setUpViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());

        Fragment fragment;
        Bundle bundle;

        bundle = new Bundle();
        bundle.putString("fragment_name", "Personal");
        bundle.putString("departure_type", "Post");
        fragment = new List_items_common_fragments();
        fragment.setArguments(bundle);
        adapter.addFragment(fragment, "Personal");
        fragmentNameIndexList.add("Personal");

        bundle = new Bundle();
        bundle.putString("fragment_name", "College");
        bundle.putString("departure_type", "Post");
        fragment = new List_items_common_fragments();
        fragment.setArguments(bundle);
        adapter.addFragment(fragment, "College");
        fragmentNameIndexList.add("College");

//        fragment = new Pre_Departure_Tips_fragments();
//        adapter.addFragment(fragment, "Tips");
//        fragmentNameIndexList.add("Tips");

        viewPager.setAdapter(adapter);

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);

        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                sessionManager.setSpecificStringDetail(SessionManager.CURRENT_PAGE, "Main");
                finish();
                return true;
            case R.id.tab_add_item:
                System.out.println("add pressed");
                addNewItem();
                return true;
            case R.id.tab_share_list:
                System.out.println("SHARE pressed");
                shareList();
                return true;
            case R.id.tab_list_detail_expand:
                System.out.println("tab_list_detail_expand");
                sessionManager.setSpecificBooleanDetail(SessionManager.LIST_ITEM_EXPAND_STATUS, true);
                invalidateOptionsMenu();
                currentTab = mViewPager.getCurrentItem();
                setTabs();
                return true;
            case R.id.tab_list_detail_collapse:
                System.out.println("tab_list_detail_collapse");
                sessionManager.setSpecificBooleanDetail(SessionManager.LIST_ITEM_EXPAND_STATUS, false);
                invalidateOptionsMenu();
                currentTab = mViewPager.getCurrentItem();
                setTabs();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void shareList() {
        String payload = "";

        ArrayList<ItemListData> payload_item_arrayList = new ArrayList<>();

        //Get first part. standard items
        payload_item_arrayList = databaseHandler.getItemList(fragmentNameIndexList.get(mViewPager.getCurrentItem()), "Pre", 0, 0);
        System.out.println("default payload_item_arrayList  size : " + payload_item_arrayList.size());

        for (int i = 0; i < payload_item_arrayList.size(); i++) {

            if (payload_item_arrayList.get(i).getMarkStatus() == 1) {
                payload = payload + "[x] " + payload_item_arrayList.get(i).getItemName() + "\n";
            } else {
                payload = payload + "[  ] " + payload_item_arrayList.get(i).getItemName() + "\n";
            }

        }

        //Get second part. custom items
        payload_item_arrayList = new ArrayList<>();
        payload_item_arrayList = databaseHandler.getItemList(fragmentNameIndexList.get(mViewPager.getCurrentItem()), "Pre", 0, 1);
        System.out.println("custom payload_item_arrayList  size : " + payload_item_arrayList.size());
        if (payload_item_arrayList.size() > 0) {
            payload = payload + "\n -- My additions --\n";
            for (int i = 0; i < payload_item_arrayList.size(); i++) {

                if (payload_item_arrayList.get(i).getMarkStatus() == 1) {
                    payload = payload + "[x] " + payload_item_arrayList.get(i).getItemName() + "\n";
                } else {
                    payload = payload + "[ ] " + payload_item_arrayList.get(i).getItemName() + "\n";
                }

            }
        }

        payload = "Check out my " + fragmentNameIndexList.get(mViewPager.getCurrentItem()) + " list \n\n" + payload;

        Intent nav_share_Intent = new Intent(android.content.Intent.ACTION_SEND);
        nav_share_Intent.setType("text/plain");
        nav_share_Intent.putExtra(android.content.Intent.EXTRA_TEXT, payload);
        startActivity(Intent.createChooser(nav_share_Intent, "Share via :"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //condition to enable/disable options depending on current page
        if (!fragmentNameIndexList.get(mViewPager.getCurrentItem()).equals("Tips")) {
            if (sessionManager.getSpecificBooleanValue(SessionManager.LIST_ITEM_EXPAND_STATUS)) {
                getMenuInflater().inflate(R.menu.tab_main_details_collapse_menu, menu);
            } else {
                getMenuInflater().inflate(R.menu.tab_main_details_expand_menu, menu);
            }
        }

        return true;
    }

    private void addNewItem() {
        LayoutInflater flater = this.getLayoutInflater();
        addItemDialogView = flater.inflate(R.layout.item_add_layout, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(addItemDialogView);
        builder.setCancelable(false);
        item_name_edit_text = (EditText) addItemDialogView.findViewById(R.id.new_item_header_EditText);
        item_detail_edit_text = (EditText) addItemDialogView.findViewById(R.id.new_item_detail_EditText);
        qualification_radio_group = (RadioGroup) addItemDialogView.findViewById(R.id.qualification_radio_group);

        builder.setTitle("New item");
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addItemTodatabse();
            }
        });
        builder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showInterstitial();

            }
        });
        builder.show();
    }

    private void addItemTodatabse() {
        String itemName = item_name_edit_text.getText().toString();
        String qualification;
        String alarmTime = null;

        int selectedId = qualification_radio_group.getCheckedRadioButtonId();
        RadioButton btnvalue = (RadioButton) addItemDialogView.findViewById(selectedId);
        System.out.println("btnvalue : " + btnvalue);
        System.out.println("btnvalue : " + btnvalue.getText());
        qualification = btnvalue.getText().toString();
        qualification = qualification.substring(0, 1);
        System.out.println("qualification : " + qualification);

        if (!itemName.equals("")) {

            currentTab = mViewPager.getCurrentItem();
            System.out.println("mViewPager.getCurrentItem() : " + mViewPager.getCurrentItem());
            System.out.println("mViewPager.getCurrentItem() NAME : " + fragmentNameIndexList.get(mViewPager.getCurrentItem()));
            System.out.println("item_name_edit_text.getText().toString(); : " + item_name_edit_text.getText().toString());
            System.out.println("item_detail_edit_text.getText().toString(); : " + item_detail_edit_text.getText().toString());
            System.out.println("qualify : " + qualification);
            System.out.println("System.currentTimeMillis() : " + System.currentTimeMillis());

            databaseHandler.addCheckListItem(new ItemListData(
                    fragmentNameIndexList.get(mViewPager.getCurrentItem()) + System.currentTimeMillis(),
                    itemName, item_detail_edit_text.getText().toString(),
                    qualification, fragmentNameIndexList.get(mViewPager.getCurrentItem()),
                    0, 1, 0, "Post", 0, alarmTime));

//            developmentMainDialog = new ProgressDialog(this);


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
//                    developmentMainDialog.dismiss();
//                    gotoChartFAB.show();

                    setTabs();
                }
            }, 500);


            Toast.makeText(getApplicationContext(), "ITEM ADDED.", Toast.LENGTH_SHORT).show();
//            setTabs();
            showInterstitial();

        } else {
            Toast.makeText(getApplicationContext(), "Invalid input! Try again", Toast.LENGTH_SHORT).show();
        }
    }

    private void setTabs() {
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.postDeparture_container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        setUpViewPager(mViewPager);

        tabLayout = (TabLayout) findViewById(R.id.postDeparture_tabs);
        tabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(currentTab);

    }

    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()) {
            Intent intentfinish = new Intent(Intent.ACTION_MAIN);
            intentfinish.addCategory(Intent.CATEGORY_HOME);
            intentfinish.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intentfinish);
//                finish();
            System.exit(1);
        } else {
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
            back_pressed = System.currentTimeMillis();
        }
    }
}
