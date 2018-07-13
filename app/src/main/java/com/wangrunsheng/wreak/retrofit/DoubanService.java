package com.wangrunsheng.wreak.retrofit;

import com.wangrunsheng.wreak.beans.BookBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DoubanService {

    @GET("v2/book/search?fields=title,images")
    Call<BookBean> getBookCover(@Query("q") String name);

    @GET("v2/book/search?fields=title,images")
    Call<ResponseBody> getBookCovers(@Query("q") String name);
}
