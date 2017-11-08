package com.maakservices.saipc.mschecklist.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.maakservices.saipc.mschecklist.Controllers.TipsListData;
import com.maakservices.saipc.mschecklist.R;
import com.maakservices.saipc.mschecklist.SessionManager;

import java.util.List;

/**
 * Created by Ajinkya on 12/1/2016.
 */
public class TipsListAdapter extends BaseAdapter {

    LayoutInflater inflater;
    Context context;
    List<TipsListData> tipsList;
    SessionManager sessionManager;

    public TipsListAdapter(Context context, List<TipsListData> tipsList) {
        this.context = context;
        this.tipsList = tipsList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return tipsList.size();
    }

    @Override
    public Object getItem(int position) {
        return tipsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TipsListAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new TipsListAdapter.ViewHolder();
            convertView = this.inflater.inflate(R.layout.tips_list_item, parent, false);

            viewHolder.tipHeaderTextView = (TextView) convertView.findViewById(R.id.tipsHeaderTextView);
            viewHolder.tipDetailTextView = (TextView) convertView.findViewById(R.id.tipsDetailTextView);
            viewHolder.tipExpandStatusImageView = (ImageView) convertView.findViewById(R.id.tipsHeaderImageView);

            convertView.setTag(viewHolder);
        } else {

            viewHolder = (TipsListAdapter.ViewHolder) convertView.getTag();

        }

        TipsListData tipsListData = tipsList.get(position);
        System.out.println("tipsListData : " + tipsListData);
        System.out.println("tipsListData.getTipName() : " + tipsListData.getTipName());

        viewHolder.tipHeaderTextView.setText(tipsListData.getTipName());

        if (tipsListData.getTipExpandStatus()) {
            viewHolder.tipExpandStatusImageView.setImageResource(R.drawable.collapse_minus_32);
            viewHolder.tipDetailTextView.setVisibility(View.VISIBLE);
        }
        if (!tipsListData.getTipExpandStatus()) {
            viewHolder.tipExpandStatusImageView.setImageResource(R.drawable.expand_plus_32);
            viewHolder.tipDetailTextView.setVisibility(View.GONE);
        }
        viewHolder.tipDetailTextView.setText(tipsListData.getTipDetail());


        return convertView;
    }

    public class ViewHolder {
        private TextView tipHeaderTextView;
        private TextView tipDetailTextView;
        private ImageView tipExpandStatusImageView;

    }
}
