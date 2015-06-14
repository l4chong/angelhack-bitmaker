package com.lc.ooo.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lc.ooo.R;
import com.lc.ooo.api.SportsAPI;
import com.squareup.okhttp.OkHttpClient;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

public class CreateSport extends ActionBarActivity {

    private EditText sport;
    private EditText location;
    private EditText description;
    private EditText date;
    private Button post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sport);

        sport = (EditText) findViewById(R.id.edit_text_sport);
        location = (EditText) findViewById(R.id.edit_text_location);
        description = (EditText) findViewById(R.id.edit_text_description);
        date = (EditText) findViewById(R.id.edit_text_date);
        post = (Button) findViewById(R.id.post);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateSport.this);
                builder.setMessage("Do you want to post?");
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

//        public void createGame(@Query(AVATAR) String avatar,
//                @Query(USERNAME) String username, -
//                @Query(SPORT) String sport, yes
//                @Query(LOCATION) String location, yes
//                @Query(RATING) String rating, -
//                @Query(DESCRIPTION) String description,  yes
//                @Query(STATUS) String status, -
//                Callback<String> callback);

                        api.createGame(
                                "http://cache.pakistantoday.com.pk/Roger-Federer-pumps-his-f-010.jpg",
                                "joe",
                                sport.getText().toString(),
                                location.getText().toString(),
                                "99",
                                description.getText().toString(),
                                "active",
                                callback);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_sport, menu);
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
