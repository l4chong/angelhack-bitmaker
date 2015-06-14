package com.lc.ooo.api;

import com.lc.ooo.models.SportItem;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by LC on 15-06-13.
 */
public interface SportsAPI {

    public static final String ENDPOINT = "http://oneononeapp.herokuapp.com";
    public static final String USERNAME = "username";
    public static final String ID = "id";

    @GET("/sportslist")
    public void getSportsList(Callback<List<SportItem>> callback);

    @GET("/mymatches/{username}")
    public void getMyMatches(@Path(USERNAME) String username, Callback<List<SportItem>> callback);

    @POST("/update/inprogress/{id}")
    public void updateMatchToProgress(@Path(ID) String id, Callback<String> cb);



}
