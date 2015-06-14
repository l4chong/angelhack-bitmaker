package com.lc.ooo;

import android.content.Context;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignInPage extends ActionBarActivity{

    private Button LogIn;
    private EditText uName;
    private EditText pWord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);

        checkLogIn();


        uName = (EditText) findViewById(R.id.username);
        pWord = (EditText) findViewById(R.id.password);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_in_page, menu);
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

    public void checkLogIn() {
        LogIn = (Button)findViewById(R.id.buttonSign);
        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String rUsername = uName.getText().toString();
                String rPassword = uName.getText().toString();
                String logInUsername = "1";
                String logInPassword = "1";
                Context context = getApplicationContext();
                String errorMess = "Password and Username incorrect";
                int time = Toast.LENGTH_SHORT;
                uName = (EditText)findViewById(R.id.username);
                pWord= (EditText)findViewById(R.id.password);




                if (rUsername.trim().equals("") || rPassword.trim().equals("")) {
                    Log.i(errorMess,"INSIDE IF");
                    Toast toast = Toast.makeText(context, errorMess, time);
                    toast.show();

                } else if (rUsername.equals(logInUsername) && rPassword.equals(logInPassword)) {
                    Intent intent = new Intent(SignInPage.this, MainNoNavigation.class);
                    startActivity(intent);
                }
            }
        });
    }
 }



