package com.lc.ooo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.lc.ooo.R;
import com.lc.ooo.models.SportItem;

import java.util.List;

/**
 * Created by LC on 15-06-13.
 */

public class ListAdapter extends ArrayAdapter<SportItem> {

    public ListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ListAdapter(Context context, int resource, List<SportItem> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {

            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.rowlayout, null);

        }

        SportItem p = getItem(position);

        if (p != null) {


        }
        return v;
    }
}