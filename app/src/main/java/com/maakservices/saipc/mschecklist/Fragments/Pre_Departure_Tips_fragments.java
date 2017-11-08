package com.maakservices.saipc.mschecklist.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v4.app.Fragment;

import com.maakservices.saipc.mschecklist.Adapters.TipsListAdapter;
import com.maakservices.saipc.mschecklist.Controllers.TipsListData;
import com.maakservices.saipc.mschecklist.DatabaseHandler;
import com.maakservices.saipc.mschecklist.R;
import com.maakservices.saipc.mschecklist.SessionManager;

import java.util.ArrayList;

/**
 * Created by Ajinkya on 12/1/2016.
 */
public class Pre_Departure_Tips_fragments extends Fragment {
    private ArrayList<TipsListData> tips_arrayList;
    private TipsListAdapter tipsListAdapter;
    private Bundle bundle;
    private DatabaseHandler databaseHandler;
    private SessionManager sessionManager;
    private ListView tipsListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bundle = this.getArguments();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tips_main_xml, container, false);
        tipsListView = (ListView) rootView.findViewById(R.id.tips_listview_main);

//        sessionManager = new SessionManager(getActivity());
//        databaseHandler = new DatabaseHandler(getActivity());

        tips_arrayList = new ArrayList<>();

        tips_arrayList.add(new TipsListData("T1", "Packing", "Remove tags from clothes and other stuff.\n\n4 wheeled trolley bags are better to than 2 wheeled ones to carry.\n\nCheck for bags size every airline permits and purchase accordingly. Also ask for any student offer on baggage\n\nInstead of folding clothes, roll them.\n\nPack some items in empty vessels like pressure cooker and containers to save space.\n\nYou won't run out of space, but will have hard time managing weight. Look for proper bag size. Bigger ones aren't always better.\n\nWrap delicate items in bubble wrap or even clothes will do the job.\n\nAvoid liquids as much as possible.\n\nPrefer few smaller packets instead of one large packet for spices and other food items. Small ones fit easily in nooks of bags and you can use one at a time when u reach US\n\nDo not waste time arranging items in bags, bags are literally thrown in baggage processing units.\n\nTie coloured ribbons or string to your bags. Helps you identify your ones in crowd.", "", "", false));
        tips_arrayList.add(new TipsListData("T2", "Travel", "Be careful while you travel, try to keep your hands free and arrange your belongings accordingly.\n\nGet a passport bag for keeping your passport handy all the time.\n\nBoarding passes and other important documents can also fit in along with passport for easy access.\n\nIf you miss connecting flight, do not panic,contact their support desk, they will probably put you in the next flight.\n\nHave some coins handy for making phone calls when needed. Notes/bills won't work.\n\n", "", "", false));
        tips_arrayList.add(new TipsListData("T3", "Documents", "Put all your original docs in carry-on and copies of them in each of your check-in bags and leave one set at your home.", "", "", false));
        tips_arrayList.add(new TipsListData("T4", "Cooking & food", "Do not carry rice or dal. They are heavy and are easily available in US", "", "", false));
        tips_arrayList.add(new TipsListData("T5", "Can be bought in US", "Some items can be bought in US also. Its better to take necessary items from India, But if you are running out of space or are overweight, you can skip few items.\n\nToiletries are cheap there, carry minimal of these for initial days.\n\nStationery doesn't weigh more or if you are not sure what to carry, skip the optional ones you can buy them easily in US.\n\nReady to eat Indian foods are easily available in US and are not too expensive.", "", "", false));
        tips_arrayList.add(new TipsListData("T6", "Freebies", "You may come across free stuff being distributed in your university on several occasions. Try to grab them as much as possible. They include useful stuff like T-shirts, food, scarfs, pens, cups, sunglasses, moisturizers, hand sanitizers, caps, sticky notes etc. So even if you forget to take any of these items, you will probably get them for free", "", "", false));
        tips_arrayList.add(new TipsListData("T7", "Enjoy your travel", "This will probably be your first international journey, try to have fun and enjoy your travel rather than thinking about your expenses and education.", "", "", false));

        System.out.println("tips_arrayList : " + tips_arrayList);

        tipsListAdapter = new TipsListAdapter(getActivity(), tips_arrayList);
        tipsListView.setAdapter(tipsListAdapter);

        tipsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TipsListData selectedTipsListData = tips_arrayList.get(position);

                selectedTipsListData.setTipExpandStatus(!selectedTipsListData.getTipExpandStatus());
                tipsListAdapter.notifyDataSetChanged();
            }
        });


        return rootView;
    }


}




