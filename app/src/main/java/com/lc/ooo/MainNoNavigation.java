package com.lc.ooo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.astuetz.PagerSlidingTabStrip;
import com.lc.ooo.ui.AvailableMatches;
import com.lc.ooo.ui.CreateSport;
import com.lc.ooo.ui.MyFriends;
import com.lc.ooo.ui.MyMatches;
import com.lc.ooo.ui.ProfilePage;
import com.lc.ooo.ui.SettingsPage;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;


public class MainNoNavigation extends ActionBarActivity {
    private ViewPager pager;

    private static final String[] CONTENT = new String[] { "Browse", "My Matches", "My Friends" };
    private static final int[] ICONS = new int[] {
            R.drawable.ic_drawer,
            R.drawable.ic_drawer,
            R.drawable.ic_drawer,
            R.drawable.ic_drawer,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_no_navigation);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Initialize the ViewPager and set an adapter
        pager = (ViewPager) findViewById(R.id.pager);
        MyPagerAdapter adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapterViewPager);

        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);

        if(!isNetworkAvailable()) {
            noNetworkDialogBox();
        }

        floatingNav();
    }

    public void floatingNav(){

        ImageView iconFAB = new ImageView(this); // Create an icon
        Resources res = getResources(); // need this to fetch the drawable

        Drawable draw = res.getDrawable( R.drawable. ic_launcher);
        iconFAB.setImageDrawable(draw);


        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(iconFAB)
                .build();

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);

        ImageView iconTweet = new ImageView(this);
        iconTweet.setImageDrawable(res.getDrawable( R.drawable.ic_profile ));
        SubActionButton button_tweet = itemBuilder.setContentView(iconTweet).build();

        ImageView iconSearch = new ImageView(this);
        iconSearch.setImageDrawable(res.getDrawable( R.drawable.ic_new ));
        SubActionButton button_search = itemBuilder.setContentView(iconSearch).build();

        ImageView iconProfile = new ImageView(this);
        iconProfile.setImageDrawable(res.getDrawable(R.drawable.ic_setting));
        SubActionButton button_profile = itemBuilder.setContentView(iconProfile).build();

        button_tweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainNoNavigation.this, ProfilePage.class);
                startActivity(intent);
            }
        });

        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainNoNavigation.this, CreateSport.class);
                startActivity(intent);
            }
        });

        button_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainNoNavigation.this, SettingsPage.class);
                startActivity(intent);
            }
        });

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(button_tweet)
                .addSubActionView(button_search)
                .addSubActionView(button_profile)
                .attachTo(actionButton)
                .build();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_no_navigation, menu);
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

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new AvailableMatches();
                case 1:
                    return new MyMatches();
                case 2:
                    return new MyFriends();
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length].toUpperCase();
        }


//        @Override
//        public int getPageIconResId(int i) {
//            return ICONS[i];
//        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager  = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void noNetworkDialogBox() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title
        alertDialogBuilder.setTitle("Unable to Connect to Server");

        // set dialog message
        alertDialogBuilder
                .setMessage("Please turn on the internet connection and re-launch the app.")
                .setCancelable(false)
                .setPositiveButton("Okay!",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        finish();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
}
