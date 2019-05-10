package com.example.blooddonation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private TextView usrnm, emaild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        //Toolbar ..
        Toolbar toolbar = findViewById(R.id.toolbar);
//        toolbar.setTitle("Red Blood");
        toolbar.setBackgroundColor(getResources().getColor(R.color.main));

        //Code for removing default title in toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setHomeButtonEnabled(true);
        }
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = findViewById(R.id.nv);

        Button alldonors = findViewById(R.id.find_donor_button);
        alldonors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Donors.class);
                startActivity(intent);
            }
        });

        Button request = findViewById(R.id.see_request_button);
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RequestActivity.class);
                startActivity(intent);
            }
        });

        //Code for listening to navigation items in navigation bar.
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.donors:
                        Intent donor_intent = new Intent(getApplicationContext(), Donors.class);
                        startActivity(donor_intent);
                        break;
                    case R.id.See_Requests:
                        Intent see_request = new Intent(getApplicationContext(), RequestActivity.class);
                        startActivity(see_request);
                        break;
                    case R.id.Make_Requests:
                        Intent req_blood = new Intent(getApplicationContext(), RequestBlood.class);
                        startActivity(req_blood);
                        break;

                    case R.id.Become_donor:
                        Intent bec_don = new Intent(getApplicationContext(), BecomeDonor.class);
                        startActivity(bec_don);
                        break;
                    default:
                        return true;
                }
                return false;
            }
        });

        DrawerLayout dl = findViewById(R.id.drawer_layout);
        t = new ActionBarDrawerToggle(this, dl, toolbar, R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        //code for changing the hamburger
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ham);
        View n = nv.getHeaderView(0);


        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String name = acct.getDisplayName();

            assert name != null;
            String cap = name.substring(0, 1).toUpperCase() + name.substring(1);
            Log.i("name_of_acct", name);
            usrnm = n.findViewById(R.id.userName);

            usrnm.setText(cap);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_search, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_search) {
            Toast.makeText(getApplicationContext(), "Search", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
