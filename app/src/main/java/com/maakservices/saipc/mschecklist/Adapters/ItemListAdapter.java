package com.maakservices.saipc.mschecklist.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maakservices.saipc.mschecklist.Controllers.ItemListData;
import com.maakservices.saipc.mschecklist.R;
import com.maakservices.saipc.mschecklist.SessionManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by SAI PC on 11/22/2016.
 */
public class ItemListAdapter extends BaseAdapter {

    LayoutInflater inflater;
    Context context;
    List<ItemListData> itemsList;
    SessionManager sessionManager;

    public ItemListAdapter(Context context, List<ItemListData> itemsList, SessionManager sessionManager) {
        this.context = context;
        this.itemsList = itemsList;
        this.inflater = LayoutInflater.from(context);
        this.sessionManager = sessionManager;
    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = this.inflater.inflate(R.layout.items_list_item, parent, false);

            viewHolder.itemNameTextView = (TextView) convertView.findViewById(R.id.itemNameTextView);
            viewHolder.itemDetailTextView = (TextView) convertView.findViewById(R.id.listItemDetailTextView);
            viewHolder.itemAlarmTextView = (TextView) convertView.findViewById(R.id.listItemAlarmTextView);
            viewHolder.priorityTextView = (TextView) convertView.findViewById(R.id.priorityTextView);
            viewHolder.statusCheckBox = (ImageView) convertView.findViewById(R.id.statusCheckBoxImageView);
            viewHolder.rel_layout = (RelativeLayout) convertView.findViewById(R.id.list_item_relative_layout);
            viewHolder.bottom_rel_layout = (RelativeLayout) convertView.findViewById(R.id.listItemBottomLayout);

            convertView.setTag(viewHolder);
        } else {

            viewHolder = (ViewHolder) convertView.getTag();

        }

        ItemListData itemListData = itemsList.get(position);

        //condition for separator : FALSE
        if (!itemListData.getCategory().equals("SEPARATOR")) {

            viewHolder.itemNameTextView.setVisibility(View.VISIBLE);
            viewHolder.priorityTextView.setVisibility(View.VISIBLE);
            viewHolder.statusCheckBox.setVisibility(View.VISIBLE);
            viewHolder.rel_layout.setBackgroundColor(Color.parseColor("#00ffffff"));

            viewHolder.itemNameTextView.setText(itemListData.getItemName());

            System.out.println("sessionManager.getSpecificBooleanValue(SessionManager.SHOW_PRIORITY) : " + sessionManager.getSpecificBooleanValue(SessionManager.SHOW_PRIORITY));

            //show prioroty condition : TRUE
            if (sessionManager.getSpecificBooleanValue(SessionManager.SHOW_PRIORITY)) {
//                System.out.println("inside SHOW_PRIORITY TRUE");


                viewHolder.priorityTextView.setVisibility(View.VISIBLE);
                viewHolder.priorityTextView.setText(itemListData.getPriority());

                if (itemListData.getPriority().equals("M")) {
                    viewHolder.priorityTextView.setBackgroundResource(R.drawable.priority_high_text_bg);
                    viewHolder.priorityTextView.setTextColor(Color.parseColor("#ff7b59"));
//                    System.out.println("inside M");
                }
//            if (itemListData.getPriority().equals("M")) {
//                viewHolder.priorityTextView.setBackgroundResource(R.drawable.priority_medium_text_bg);
//                viewHolder.priorityTextView.setTextColor(Color.parseColor("#636bff"));
//            }
                if (itemListData.getPriority().equals("O")) {
                    viewHolder.priorityTextView.setBackgroundResource(R.drawable.priority_low_text_bg);
                    viewHolder.priorityTextView.setTextColor(Color.parseColor("#24999A"));
//                    System.out.println("inside O");
                }
                if (itemListData.getPriority().equals("0")) {
                    System.out.println("inside zero");
                }
            }
            //show prioroty condition : FALSE
            if (!sessionManager.getSpecificBooleanValue(SessionManager.SHOW_PRIORITY)) {
                System.out.println("inside SHOW_PRIORITY FALSE");
                viewHolder.priorityTextView.setVisibility(View.INVISIBLE);
            }

            //SHOW EXPAND STATUS CONDITIONS
            if (!sessionManager.getSpecificBooleanValue(SessionManager.LIST_ITEM_EXPAND_STATUS)) {
                System.out.println("inside LIST_ITEM_EXPAND_STATUS FALSE");
                viewHolder.bottom_rel_layout.setVisibility(View.GONE);

            } else {
                if (itemListData.getItemDetail() == null && itemListData.getReminderFlag() == 0) {
                    viewHolder.bottom_rel_layout.setVisibility(View.GONE);
                    System.out.println("INSIDE BOTH NULL 0");
                } else {
                    System.out.println("INSIDE SOME ONE NOT NULL 0");

                    viewHolder.bottom_rel_layout.setVisibility(View.VISIBLE);

                    //condition for item detail
                    if (itemListData.getItemDetail() == null) {
                        viewHolder.itemDetailTextView.setVisibility(View.GONE);
                    } else {
                        viewHolder.itemDetailTextView.setVisibility(View.VISIBLE);
                        viewHolder.itemDetailTextView.setText(itemListData.getItemDetail());
                    }

                    //condition for alarm time
                    if (itemListData.getReminderFlag() == 1) {
                        viewHolder.itemAlarmTextView.setVisibility(View.VISIBLE);
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(Long.parseLong(itemListData.getReminderTime()));

                        Date date = calendar.getTime();

                        DateFormat format = new SimpleDateFormat("dd MMM yy", Locale.getDefault());
                        String alarmTimeString = format.format(date);


                        viewHolder.itemAlarmTextView.setText(alarmTimeString + "\n" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
                    } else {
                        viewHolder.itemAlarmTextView.setVisibility(View.GONE);
                    }


                }
            }

            //checkbox condition : TRUE
            if (itemListData.getMarkStatus() == 1) {
                viewHolder.statusCheckBox.setImageResource(R.drawable.checked_64);
            }
            //checkbox condition : FALSE
            if (itemListData.getMarkStatus() == 0) {
                viewHolder.statusCheckBox.setImageResource(R.drawable.unchecked_64);
            }
        }

        //condition for separator : TRUE
        if (itemListData.getCategory().equals("SEPARATOR")) {

            viewHolder.rel_layout.setBackgroundColor(Color.parseColor("#FF4081"));
            viewHolder.itemNameTextView.setVisibility(View.GONE);
            viewHolder.priorityTextView.setVisibility(View.GONE);
            viewHolder.statusCheckBox.setVisibility(View.GONE);
            viewHolder.bottom_rel_layout.setVisibility(View.GONE);
        }

        return convertView;
    }

    public class ViewHolder {
        private TextView itemNameTextView;
        private TextView itemDetailTextView;
        private TextView priorityTextView;
        private TextView itemAlarmTextView;
        private ImageView statusCheckBox;
        private RelativeLayout rel_layout;
        private RelativeLayout bottom_rel_layout;

    }
}
