package com.maakservices.saipc.mschecklist.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.maakservices.saipc.mschecklist.Adapters.ItemListAdapter;
import com.maakservices.saipc.mschecklist.Controllers.ItemListData;
import com.maakservices.saipc.mschecklist.DatabaseHandler;
import com.maakservices.saipc.mschecklist.R;
import com.maakservices.saipc.mschecklist.SessionManager;

import java.util.ArrayList;

/**
 * Created by SAI PC on 11/22/2016.
 */
public class Clothes_fragments extends Fragment {
    private ArrayList<ItemListData> item_arrayList;
    private ItemListAdapter itemListAdapter;
    private ListView itemListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.listview_main_xml, container, false);
        itemListView = (ListView) rootView.findViewById(R.id.listView_main);

        SessionManager sessionManager = new SessionManager(getActivity());
        DatabaseHandler databaseHandler = new DatabaseHandler(getActivity());

        item_arrayList = new ArrayList<>();
        item_arrayList = databaseHandler.getItemList("Clothes", "Pre", 0,  0);

        System.out.println("documents_list : " + item_arrayList);

        itemListAdapter = new ItemListAdapter(getActivity(), item_arrayList, new SessionManager(getActivity()));
        itemListView.setAdapter(itemListAdapter);

        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemListData selectedItemListData = item_arrayList.get(position);
                System.out.println("selectedItemListData.getItemId() : " + selectedItemListData.getItemId());
                System.out.println("selectedItemListData.getItemName() : " + selectedItemListData.getItemName());
                System.out.println("selectedItemListData.getMarkStatus() : " + selectedItemListData.getMarkStatus());
                System.out.println("selectedItemListData.getPriority() : " + selectedItemListData.getPriority());

                markChanges(position);
            }
        });
        return rootView;
    }

    private void markChanges(int position) {
        ItemListData selectedItemListData = item_arrayList.get(position);

        int checkStatus = selectedItemListData.getMarkStatus();
        if (checkStatus == 1) {
            selectedItemListData.setMarkStatus(0);
        } else {
            selectedItemListData.setMarkStatus(1);
        }
        int index = itemListView.getFirstVisiblePosition();
        View v = itemListView.getChildAt(0);
        int top = (v == null) ? 0 : v.getTop();

        itemListAdapter.notifyDataSetChanged();
        //attendanceListView.setAdapter(attendanceRefreshAdapter);
        itemListView.setSelectionFromTop(index, top);

    }
}