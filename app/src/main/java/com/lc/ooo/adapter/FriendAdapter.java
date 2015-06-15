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
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by LC on 15-06-13.
 */

public class FriendAdapter extends ArrayAdapter<SportItem> {

    public FriendAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public FriendAdapter(Context context, List<SportItem> items) {
        super(context, R.layout.rowlayout, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {

            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.friend_rowlayout, null);

        }

        SportItem item = getItem(position);

        if (item != null) {
            TextView username = (TextView) v.findViewById(R.id.username);
            TextView sport = (TextView) v.findViewById(R.id.sport);
            TextView location = (TextView) v.findViewById(R.id.location);
            TextView rating = (TextView) v.findViewById(R.id.rating);
            ImageView iv = (ImageView) v.findViewById(R.id.avatar);

            username.setText(item.getUsername());
            sport.setText(item.getSport());
            location.setText(item.getLocation());
            rating.setText(item.getRating());

            if (item.getAvatar() != null && !item.getAvatar().equals("")) {
                String url = item.getAvatar();

                Picasso.with(getContext())
                        .load(url)
                        .placeholder(R.drawable.lebron)
                        .noFade()
                        .into(iv);
            }

        }
        return v;
    }
}