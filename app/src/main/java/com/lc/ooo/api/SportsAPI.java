package com.lc.ooo.api;

import com.lc.ooo.models.SportItem;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by LC on 15-06-13.
 */
public interface SportsAPI {

    public static final String ENDPOINT = "http://oneononeapp.herokuapp.com";
    public static final String USERNAME = "username";
    public static final String ID = "id";
    public static final String AVATAR = "avatar";
    public static final String SPORT = "sport";
    public static final String RATING = "rating";
    public static final String LOCATION = "location";
    public static final String DESCRIPTION = "description";
    public static final String STATUS = "status";
    public static final String RESULTS = "result";


    @GET("/sportslist")
    public void getSportsList(Callback<List<SportItem>> callback);

    @GET("/mymatches/{username}")
    public void getMyMatches(@Path(USERNAME) String username, Callback<List<SportItem>> callback);

    @POST("/update/inprogress/{id}")
    public void updateMatchToProgress(@Path(ID) String id, @Query(USERNAME) String username, Callback<String> cb);

    @POST("/update/completed/{id}")
    public void updateMatchToComplete(@Path(ID) String id, @Query(USERNAME) String username, @Query(RESULTS) String result, Callback<String> cb);

    @POST("/sportslist")
    public void createGame(@Query(AVATAR) String avatar,
                           @Query(USERNAME) String username,
                           @Query(SPORT) String sport,
                           @Query(LOCATION) String location,
                           @Query(RATING) String rating,
                           @Query(DESCRIPTION) String description,
                           @Query(STATUS) String status,
                           Callback<String> callback);

}
