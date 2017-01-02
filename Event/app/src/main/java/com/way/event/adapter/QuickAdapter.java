package com.way.event.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.way.event.GlideCircleTransform;
import com.way.event.R;
import com.way.event.entity.Status;

import java.util.List;

/**
 * Created by pc on 2016/12/24.
 */

public class QuickAdapter extends BaseQuickAdapter<Status, BaseViewHolder> {
    public QuickAdapter(List<Status> data) {
        super( R.layout.tweet, data);
    }

//    public QuickAdapter(int dataSize) {
//        super( R.layout.tweet, DataServer.getSampleData(dataSize));
//    }

    @Override
    protected void convert(BaseViewHolder helper, Status item) {
        helper.setText(R.id.tweetName, item.getUserName())
                .setText(R.id.tweetText, item.getText())
                .setText(R.id.tweetDate, item.getCreatedAt())
                .setVisible(R.id.tweetRT, item.isRetweet())
                .addOnClickListener(R.id.tweetAvatar)
                .addOnClickListener(R.id.tweetName)
                .linkify(R.id.tweetText);

        Glide.with(mContext)
                .load(item.getUserAvatar())
                .crossFade()
                .placeholder(R.mipmap.def_head)
                .transform(new GlideCircleTransform(mContext))
                .into((ImageView) helper.getView(R.id.tweetAvatar));
    }


}

