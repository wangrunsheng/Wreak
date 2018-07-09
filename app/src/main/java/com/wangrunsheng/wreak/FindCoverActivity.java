package com.wangrunsheng.wreak;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.orhanobut.logger.Logger;
import com.wangrunsheng.wreak.adapter.BookCoverAdapter;
import com.wangrunsheng.wreak.beans.BookBean;

/**
 * Created by Russell on 2018/3/19.
 */

public class FindCoverActivity extends AppCompatActivity implements View.OnClickListener, SearchView.OnQueryTextListener {

    private Button mBackBtn;
    private SearchView mBookCoverSearchView;
    private RecyclerView mBookCoverRv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_cover);
        initView();
    }

    private void initView() {
        mBackBtn = (Button) findViewById(R.id.back_btn);
        mBackBtn.setOnClickListener(this);
        mBookCoverSearchView = (SearchView) findViewById(R.id.book_cover_search_view);
        mBookCoverSearchView.setIconifiedByDefault(false);
        mBookCoverSearchView.setOnQueryTextListener(this);
        mBookCoverRv = (RecyclerView) findViewById(R.id.book_cover_rv);
        mBookCoverRv.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back_btn:
                finish();
                break;
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Logger.d("query: " + query);
        searchDoubanBooks(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    private void searchDoubanBooks(String name) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "";
        if (isDigit(name)) {
            url = "https://api.douban.com/v2/book/isbn/" + name + "?fields=title,images";
            url = "https://api.douban.com/v2/book/" + name + "?fields=title,images";
        } else {
            url = "https://api.douban.com/v2/book/search?q=" + name + "&fields=title,images";
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Logger.json(response);
                BookBean bookBean = JSONObject.parseObject(response, BookBean.class);
                BookCoverAdapter adapter = new BookCoverAdapter(bookBean.getBooks(), FindCoverActivity.this);
                mBookCoverRv.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);
    }

    // 判断一个字符串是否都为数字
    public boolean isDigit(String name) {
        return name.matches("[0-9]{1,}");
    }
}
