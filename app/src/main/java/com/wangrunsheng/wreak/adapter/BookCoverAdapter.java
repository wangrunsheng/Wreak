package com.wangrunsheng.wreak.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.orhanobut.logger.Logger;
import com.wangrunsheng.wreak.R;
import com.wangrunsheng.wreak.beans.BookBean;

import java.util.List;

/**
 * Created by Russell on 2018/3/19.
 */

public class BookCoverAdapter extends BaseQuickAdapter<BookBean.BooksBean, BaseViewHolder> {

    private Context mContext;

    private int mSelectedPos = -1;

    public BookCoverAdapter(@Nullable List<BookBean.BooksBean> data, Context context) {
        super(R.layout.item_book_cover, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BookBean.BooksBean item) {
        helper.setText(R.id.item_book_cover_tv, item.getTitle());
        Logger.i("pos: " + mSelectedPos + " " + helper.getAdapterPosition());

        if (helper.getAdapterPosition() == mSelectedPos) {
            setSelected(helper);
        } else {
            invertSelected(helper);
        }
        Glide.with(mContext).load(item.getImages().getSmall()).into((ImageView) helper.getView(R.id.item_book_cover_iv));
    }

    private void invertSelected(BaseViewHolder helper) {
        helper.setGone(R.id.item_book_cover_selected_rl, false)
                .setBackgroundColor(R.id.item_book_cover_tv, mContext.getResources().getColor(R.color.white))
                .setTextColor(R.id.item_book_cover_tv, mContext.getResources().getColor(R.color.grey));

    }

    private void setSelected(BaseViewHolder helper) {

        helper.setGone(R.id.item_book_cover_selected_rl, true)
                .setBackgroundColor(R.id.item_book_cover_tv, mContext.getResources().getColor(R.color.colorAccent))
                .setTextColor(R.id.item_book_cover_tv, mContext.getResources().getColor(R.color.white));
    }

    public void setSelectedPos(int pos) {
        mSelectedPos = pos;
    }

}
