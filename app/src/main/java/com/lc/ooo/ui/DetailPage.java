package com.lc.ooo.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lc.ooo.R;
import com.squareup.picasso.Picasso;

public class DetailPage extends ActionBarActivity {

    private ImageView avatar;
    private TextView username;
    private TextView sport;
    private TextView location;
    private TextView rating;
    private TextView description;
    private Button accept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        avatar = (ImageView) findViewById(R.id.avatar);
        username = (TextView) findViewById(R.id.username);
        sport = (TextView) findViewById(R.id.sport);
        location = (TextView) findViewById(R.id.location);
        rating = (TextView) findViewById(R.id.rating);
        description = (TextView) findViewById(R.id.description);
        accept = (Button) findViewById(R.id.accept);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            if(extras.getString("avatar") != null){
                Log.i("test", extras.getString("avatar"));
                Picasso.with(getApplicationContext())
                        .load(extras.getString("avatar"))
                        .placeholder(R.drawable.ic_drawer)
                        .noFade()
                        .into(avatar);
            }
            username.setText(extras.getString("username"));
            sport.setText(extras.getString("sport"));
            location.setText(extras.getString("location"));
            rating.setText(extras.getString("rating"));
            description.setText(extras.getString("description"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
