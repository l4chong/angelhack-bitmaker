package com.lc.ooo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

            TextView eventName = (TextView) v.findViewById(R.id.eventName);
            TextView eventDate = (TextView) v.findViewById(R.id.date);
            ImageView iv = (ImageView) v.findViewById(R.id.imageView);

            if (p.getEventName() != null) {
                eventName.setText(p.getEventName());
            }
            if (p.getDateBeginShow() != null) {
                String beginDate = p.getDateBeginShow().substring(0, p.getDateBeginShow().length() - 24);
                if(p.getDateEndShow() != null && !p.getDateBeginShow().equals(p.getDateEndShow())) {
                    String endDate = p.getDateEndShow().substring(0, p.getDateEndShow().length() - 24);
                    eventDate.setText(beginDate + " to " + endDate);
                }else {
                    eventDate.setText(beginDate);
                }
            }

            if (p.getImageURL() != null && p.getImageURL().contains("http")) {
                String url = p.getImageURL().replaceAll(" ", "%20");
                p.setImageURL(url);
                Picasso.with(getContext())
                        .load(url)
                        .placeholder(R.drawable.loading)
                        .noFade()
                        .into(iv);
            } else {
                iv.setImageResource(R.drawable.defaultimage);
            }
        }
        return v;