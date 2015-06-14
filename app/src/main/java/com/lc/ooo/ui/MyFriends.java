package com.lc.ooo.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lc.ooo.R;
import com.lc.ooo.adapter.ListAdapter;
import com.lc.ooo.api.SportsAPI;
import com.lc.ooo.models.SportItem;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

public class MyFriends extends android.support.v4.app.Fragment {
    private static List<SportItem> sportItemList = new ArrayList<SportItem>();
    ListView lv;
    private static String TAG = "AvailableMatches";

    public MyFriends() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_friends, container, false);

        getEvents(rootView);

        return rootView;
    }

    public void  getEvents(final View view) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setFollowSslRedirects(true);

        RestAdapter adapter = new RestAdapter.Builder()
                .setClient(new OkClient(okHttpClient))
                .setEndpoint(SportsAPI.ENDPOINT)
                .build();

        SportsAPI api = adapter.create(SportsAPI.class);

        Callback callback = new Callback<List<SportItem>>() {
            @Override
            public void success(List<SportItem> arg0, Response response) {
                Log.i(TAG, "Success");
                sportItemList = arg0;
                lv=(ListView) view.findViewById(R.id.listView);
                lv.setAdapter(new ListAdapter(getActivity().getApplicationContext(), sportItemList));
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.i(TAG, retrofitError.toString());
            }
        };

        api.getMyMatches("joe", callback);
    }

}
