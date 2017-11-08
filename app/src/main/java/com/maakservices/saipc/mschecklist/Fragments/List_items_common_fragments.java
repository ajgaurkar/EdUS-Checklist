package com.maakservices.saipc.mschecklist.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.maakservices.saipc.mschecklist.Adapters.ItemListAdapter;
import com.maakservices.saipc.mschecklist.Controllers.ItemListData;
import com.maakservices.saipc.mschecklist.DatabaseHandler;
import com.maakservices.saipc.mschecklist.R;
import com.maakservices.saipc.mschecklist.SessionManager;

import java.util.ArrayList;

/**
 * Created by SAI PC on 11/23/2016.
 */
public class List_items_common_fragments extends Fragment {
    private ArrayList<ItemListData> item_arrayList;
    private ArrayList<ItemListData> tempItem_arrayList;
    private ItemListAdapter itemListAdapter;
    private ListView itemListView;
    private Bundle bundle;
    private DatabaseHandler databaseHandler;
    private SessionManager sessionManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bundle = this.getArguments();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.listview_main_xml, container, false);
        itemListView = (ListView) rootView.findViewById(R.id.listView_main);

        sessionManager = new SessionManager(getActivity());
        databaseHandler = new DatabaseHandler(getActivity());

        item_arrayList = new ArrayList<>();
        tempItem_arrayList = new ArrayList<>();

        //Get first part. standard items
        item_arrayList = databaseHandler.getItemList(bundle.getString("fragment_name"), bundle.getString("departure_type"), 0, 0);

        //Get second part. custom items
        tempItem_arrayList = databaseHandler.getItemList(bundle.getString("fragment_name"), bundle.getString("departure_type"), 0, 1);
        System.out.println("tempItem_arrayList size : " + tempItem_arrayList.size());
        if (tempItem_arrayList.size() > 0) {

            //add separator of std and custom items
            item_arrayList.add(new ItemListData("", "", "", "", "SEPARATOR", 3, 3, 3, "", 3, ""));

//            add templist to orignal list
            for (int i = 0; i < tempItem_arrayList.size(); i++) {
                item_arrayList.add(tempItem_arrayList.get(i));
            }
        }

        System.out.println("documents_list : " + item_arrayList);

        itemListAdapter = new ItemListAdapter(getActivity(), item_arrayList, new SessionManager(getActivity()));
        itemListView.setAdapter(itemListAdapter);

        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemListData selectedItemListData = item_arrayList.get(position);

                //condition to avoid click listener action on separator
                if (!selectedItemListData.getCategory().equals("SEPARATOR")) {

                    System.out.println("selectedItemListData.getItemId() : " + selectedItemListData.getItemId());
                    System.out.println("selectedItemListData.getItemName() : " + selectedItemListData.getItemName());
                    System.out.println("selectedItemListData.getMarkStatus() : " + selectedItemListData.getMarkStatus());
                    System.out.println("selectedItemListData.getMarkStatus() : " + selectedItemListData.getMarkStatus());
                    System.out.println("selectedItemListData.getPriority() : " + selectedItemListData.getPriority());


                    if (selectedItemListData.getMarkStatus() == 0) {
                        markChanges(position);
                    } else {
                        if (sessionManager.getSpecificBooleanValue(SessionManager.CONFIRM_BEFORE_UNCHECK)) {
                            confirmBeforeUncheckD(position, selectedItemListData.getItemName());
                        } else {
                            markChanges(position);
                        }
                    }
                }
            }
        });


        itemListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                ItemListData selectedItemListData = item_arrayList.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Choose action");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println("delete pressed");
                        removeListItem(position);

                    }
                }).setNegativeButton("dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println("Dismiss pressed");
                    }
                }).create().show();
                return true;
            }
        });

        return rootView;
    }

    private void confirmBeforeUncheckD(final int position, String itemName) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Please confirm");
        builder.setMessage("Are you sure to uncheck \"" + itemName + "\"");
        builder.setPositiveButton("Uncheck", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("uncheck pressed");
                markChanges(position);

            }
        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("Dismiss pressed");
            }
        }).create().show();

    }

    private void removeListItem(int position) {

        ItemListData selectedItemListData = item_arrayList.get(position);
        item_arrayList.remove(position);

        selectedItemListData.setDeletedStatus(1);
        databaseHandler.updateItem(selectedItemListData);

        int index = itemListView.getFirstVisiblePosition();
        View v = itemListView.getChildAt(0);
        int top = (v == null) ? 0 : v.getTop();

        itemListAdapter.notifyDataSetChanged();
        //attendanceListView.setAdapter(attendanceRefreshAdapter);
        itemListView.setSelectionFromTop(index, top);
        Toast.makeText(getActivity(), "ITEM DELETED. Restore it anytime from TRASH.", Toast.LENGTH_LONG).show();

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

        //updates data in database
        databaseHandler.updateItem(selectedItemListData);
    }
}




