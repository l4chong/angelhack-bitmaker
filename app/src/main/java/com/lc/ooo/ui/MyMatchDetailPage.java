package com.lc.ooo.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lc.ooo.R;
import com.lc.ooo.api.SportsAPI;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Picasso;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

public class MyMatchDetailPage extends ActionBarActivity {

    private ImageView avatar;
    private TextView username;
    private TextView sport;
    private TextView location;
    private TextView rating;
    private TextView description;
    private Button result;
    private Button share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_match_detail_page);

        avatar = (ImageView) findViewById(R.id.avatar);
        username = (TextView) findViewById(R.id.username);
        sport = (TextView) findViewById(R.id.sport);
        location = (TextView) findViewById(R.id.location);
        rating = (TextView) findViewById(R.id.rating);
        description = (TextView) findViewById(R.id.description);
        result = (Button) findViewById(R.id.result);
        share = (Button) findViewById(R.id.share);


        final Bundle extras = getIntent().getExtras();

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

            if(extras.getString("status").equals("completed")) {
               result.setText(extras.getString("winner"));
            }
            result.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MyMatchDetailPage.this);
                    builder.setMessage("Did you Win the game? ");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, int id) {
                            //do a post here
                            OkHttpClient okHttpClient = new OkHttpClient();
                            okHttpClient.setFollowSslRedirects(true);

                            RestAdapter adapter = new RestAdapter.Builder()
                                    .setClient(new OkClient(okHttpClient))
                                    .setEndpoint(SportsAPI.ENDPOINT)
                                    .build();

                            SportsAPI api = adapter.create(SportsAPI.class);

                            Callback callback = new Callback<String>() {

                                @Override
                                public void success(String s, Response response) {
                                    finish();
                                }

                                @Override
                                public void failure(RetrofitError error) {
//                                Toast.makeText(getApplicationContext(), error.toString(),
//                                        Toast.LENGTH_LONG).show();
                                    finish();
                                }
                            };

                            api.updateMatchToComplete(extras.getString("id"), getString(R.string.username), "1", callback);
                            finish();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                              OkHttpClient okHttpClient = new OkHttpClient();
                            okHttpClient.setFollowSslRedirects(true);

                            RestAdapter adapter = new RestAdapter.Builder()
                                    .setClient(new OkClient(okHttpClient))
                                    .setEndpoint(SportsAPI.ENDPOINT)
                                    .build();

                            SportsAPI api = adapter.create(SportsAPI.class);

                            Callback callback = new Callback<String>() {

                                @Override
                                public void success(String s, Response response) {
                                    finish();
                                }

                                @Override
                                public void failure(RetrofitError error) {
//                                Toast.makeText(getApplicationContext(), error.toString(),
//                                        Toast.LENGTH_LONG).show();
                                    finish();
                                }
                            };

                            api.updateMatchToComplete(extras.getString("id"), getString(R.string.username), "0", callback);
                            finish();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        }

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_match_detail_page, menu);
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
