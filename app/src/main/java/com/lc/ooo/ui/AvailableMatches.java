package com.lc.ooo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

public class AvailableMatches extends android.support.v4.app.Fragment {
    private static List<SportItem> sportItemList = new ArrayList<SportItem>();
    ListView lv;
    private static String TAG = "AvailableMatches";

    public AvailableMatches() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_available_matches, container, false);

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

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        SportItem item = sportItemList.get(position);
                        Intent intent = new Intent(getActivity(), DetailPage.class);
                        intent.putExtra("avatar", item.getAvatar());
                        intent.putExtra("sport", item.getSport());
                        intent.putExtra("location", item.getLocation());
                        intent.putExtra("rating", item.getRating());
                        intent.putExtra("description", item.getDescription());
                        intent.putExtra("username", item.getUsername());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.i(TAG, retrofitError.toString());
            }
        };

        api.getSportsList(callback);
    }


}
