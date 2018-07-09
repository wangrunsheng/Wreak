package com.wangrunsheng.wreak.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wangrunsheng.wreak.AddActivity;
import com.wangrunsheng.wreak.R;

/**
 * 首页列表页面，展示当前在读书籍.
 *
 * Created by Russell on 2018/3/17.
 */

public class TableFragment extends Fragment implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private Button mAddBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_table, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mAddBtn = view.findViewById(R.id.add_btn);
        mAddBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_btn) {
            Intent intent = new Intent(getContext(), AddActivity.class);
            startActivity(intent);
        }
    }
}
