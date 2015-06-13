package com.lc.ooo.api;

import com.lc.ooo.models.SportItem;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by LC on 15-06-13.
 */
public interface SportsAPI {

    public static final String SKIP = "skip";
    public static final String LIMIT = "limit";

    @GET("/")
    public void getSports(@Query(SKIP) int skip, @Query(LIMIT) int limit, Callback<List<SportItem>> callback);
}
