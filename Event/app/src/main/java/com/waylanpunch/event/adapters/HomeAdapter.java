package com.waylanpunch.event.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.waylanpunch.event.R;
import com.waylanpunch.event.model.PostModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2017/3/9.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context mContext;
    private List<PostModel> dataList = new ArrayList<>();


    public void addAllData(List<PostModel> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.dataList.clear();
    }

    public HomeAdapter(Context context) {
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nickname;
        public TextView title;
        public TextView description;

        public ViewHolder(View itemView) {
            super(itemView);
            nickname = (TextView) itemView.findViewById(R.id.tv_item_event_nickname);
            title = (TextView) itemView.findViewById(R.id.tv_item_event_title);
            description = (TextView) itemView.findViewById(R.id.tv_item_event_description);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_event, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String strNickname = dataList.get(position).getNickname();
        if (!strNickname.equals("")) {
            holder.nickname.setText(strNickname);
        }
        String strTitle = dataList.get(position).getTitle();
        if (!strTitle.equals("")) {
            holder.title.setText(strTitle);
        }
        String strDescription = dataList.get(position).getDescription();
        if (!strDescription.equals("")) {
            holder.description.setText(strDescription);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}