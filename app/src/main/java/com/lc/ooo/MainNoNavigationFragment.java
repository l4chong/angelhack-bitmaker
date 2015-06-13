package com.lc.ooo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lc.ooo.adapter.CustomAdapter;

import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainNoNavigationFragment extends Fragment {

    ListView lv;

    Context context;

    ArrayList prgmName;
    public static int [] prgmImages={R.drawable.lebron,R.drawable.lebron,R.drawable.lebron,R.drawable.lebron,R.drawable.lebron,R.drawable.lebron,R.drawable.lebron,R.drawable.lebron,R.drawable.lebron};
    public static String [] prgmNameList={"Let Us C","c++","JAVA","Jsp","Microsoft .Net","Android","PHP","Jquery","JavaScript"};

    public MainNoNavigationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_no_navigation, container, false);

        lv=(ListView) rootView.findViewById(R.id.listView);
        lv.setAdapter(new CustomAdapter(getActivity().getApplicationContext(), prgmNameList,prgmImages));

        return rootView;
    }
}
