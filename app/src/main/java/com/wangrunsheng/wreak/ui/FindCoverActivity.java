package com.wangrunsheng.wreak.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.wangrunsheng.wreak.R;
import com.wangrunsheng.wreak.adapter.BookCoverAdapter;
import com.wangrunsheng.wreak.beans.BookBean;
import com.wangrunsheng.wreak.retrofit.DoubanService;
import com.wangrunsheng.wreak.util.BookCoverUtils;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;

/**
 * Created by Russell on 2018/3/19.
 */

public class FindCoverActivity extends AppCompatActivity implements View.OnClickListener, SearchView.OnQueryTextListener {

    private Toolbar mToolbar;
    private SearchView mBookCoverSearchView;
    private RecyclerView mBookCoverRv;
    private BookCoverAdapter mBookCoverAdapter;
    private String mBookCoverUrl = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_cover);
        initView();
    }

    private void initView() {
        initToolbar();
        mBookCoverSearchView = (SearchView) findViewById(R.id.book_cover_search_view);
        mBookCoverSearchView.setIconifiedByDefault(false);
        mBookCoverSearchView.setOnQueryTextListener(this);
        mBookCoverRv = (RecyclerView) findViewById(R.id.book_cover_rv);
        mBookCoverRv.setLayoutManager(new GridLayoutManager(this, 2));
        mBookCoverAdapter = new BookCoverAdapter(new ArrayList<BookBean.BooksBean>(), FindCoverActivity.this);
        mBookCoverRv.setAdapter(mBookCoverAdapter);

        mBookCoverAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mBookCoverAdapter.setSelectedPos(position);
                mBookCoverAdapter.notifyDataSetChanged();
                mBookCoverUrl = mBookCoverAdapter.getData().get(position).getImages().getSmall();
            }
        });
    }

    private void initToolbar() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.done, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.done:
                done();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void done() {
        Intent intent = new Intent();
        intent.putExtra(BookCoverUtils.BOOK_COVER_URL, mBookCoverUrl);
        setResult(BookCoverUtils.RC_BOOK_COVER, intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Logger.d("query: " + query);
     //   searchDoubanBooks(query);
     //   searchDoubanBooksOkHttp(query);
        searchDoubanBooksRetrofit(query);
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
            //    url = "https://api.douban.com/v2/book/isbn/" + name + "?fields=title,images";
            url = "https://api.douban.com/v2/book/" + name + "?fields=title,images";
        } else {
            url = "https://api.douban.com/v2/book/search?q=" + name + "&fields=title,images";
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Logger.json(response);
                BookBean bookBean = JSONObject.parseObject(response, BookBean.class);
                mBookCoverAdapter.setNewData(bookBean.getBooks());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Logger.d("some thing is wrong");
            }
        });

        queue.add(stringRequest);
    }


    private void searchDoubanBooksOkHttp(String name) {
        OkHttpClient client = new OkHttpClient();
        String url = "";
        if (isDigit(name)) {
            url = "https://api.douban.com/v2/book/" + name + "?fields=title,images";
        } else {
            url = "https://api.douban.com/v2/book/search?q=" + name + "&fields=title,images";
        }
        okhttp3.Request request = new okhttp3.Request.Builder().url(url).build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            // onResponse 执行的线程并不是 UI 线程
            @Override
            public void onResponse(@NonNull Call call, @NonNull okhttp3.Response response) throws IOException {

                ResponseBody body = response.body();
                if (body != null) {
                    String r = body.string();
                    final BookBean bookBean = JSONObject.parseObject(r, BookBean.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mBookCoverAdapter.setNewData(bookBean.getBooks());
                        }
                    });
                }
            }
        });
    }

    private void searchDoubanBooksRetrofit(String name) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DoubanService service = retrofit.create(DoubanService.class);

        retrofit2.Call<BookBean> bodyCall = service.getBookCover(name);
        bodyCall.enqueue(new retrofit2.Callback<BookBean>() {
            @Override
            public void onResponse(@NonNull retrofit2.Call<BookBean> call, @NonNull retrofit2.Response<BookBean> response) {
                BookBean bookBean = response.body();
                if (bookBean != null) {
                    mBookCoverAdapter.setNewData(bookBean.getBooks());
                    mBookCoverRv.scrollToPosition(0);
                }
            }

            @Override
            public void onFailure(@NonNull retrofit2.Call<BookBean> call, @NonNull Throwable t) {

            }
        });

    }

    // 判断一个字符串是否都为数字
    public boolean isDigit(String name) {
        return name.matches("[0-9]{1,}");
    }

}
