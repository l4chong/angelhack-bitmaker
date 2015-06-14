package com.lc.ooo.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lc.ooo.R;
import com.lc.ooo.adapter.FriendAdapter;
import com.lc.ooo.dummy.DummyData;
import com.lc.ooo.models.SportItem;

import java.util.ArrayList;
import java.util.List;


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
        DummyData.populateDummy();
        sportItemList = DummyData.getFriends();
        lv=(ListView) view.findViewById(R.id.listView);
        lv.setAdapter(new FriendAdapter(getActivity().getApplicationContext(), sportItemList));
    }

}
