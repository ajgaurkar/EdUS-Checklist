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
public class Upon_Arrival_Tips_fragments extends Fragment {
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

        tips_arrayList.add(new TipsListData("T1", "Get warm clothes", "Some states have much cooler climate than India. Dont forget to take warm clothes", "M", "Clothes", false));
        tips_arrayList.add(new TipsListData("T2", "Get warm clothes", "Some states have much cooler climate than India. Dont forget to take warm clothes", "M", "Clothes", false));
        tips_arrayList.add(new TipsListData("T3", "Get warm clothes", "Some states have much cooler climate than India. Dont forget to take warm clothes", "M", "Clothes", false));
        tips_arrayList.add(new TipsListData("T4", "Get warm clothes", "Some states have much cooler climate than India. Dont forget to take warm clothes", "M", "Clothes", false));
        tips_arrayList.add(new TipsListData("T5", "Get warm clothes", "Some states have much cooler climate than India. Dont forget to take warm clothes", "M", "Clothes", false));

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




