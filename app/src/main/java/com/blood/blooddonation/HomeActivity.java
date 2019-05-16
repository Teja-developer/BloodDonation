package com.blood.blooddonation;

import android.content.Intent;
import android.os.Handler;
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class HomeActivity extends AppCompatActivity {

    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private TextView usrnm;
    private Button propic;
    boolean doubleBackToExitPressedOnce = false;

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


        //Code for knowing the google username i.e if the user signs up via google

        //Initialization of views in navigation bar.
        usrnm = n.findViewById(R.id.userName);
        propic = n.findViewById(R.id.profilePic);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String name = acct.getDisplayName();

            assert name != null;
            String cap = name.substring(0, 1).toUpperCase() + name.substring(1);
            Log.i("name_of_acct", name);


            propic.setText(String.valueOf(cap.charAt(0)));
            usrnm.setText(cap);
        } else {
            getFbInfo();
        }
        count();
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

    private void getFbInfo() {
        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        try {
//                            Log.d(LOG_TAG, "fb json object: " + object);
//                            Log.d(LOG_TAG, "fb graph response: " + response);

                            String id = object.getString("id");
                            String first_name = object.getString("first_name");
                            String last_name = object.getString("last_name");
//                            String gender = object.getString("gender");
//                            String birthday = object.getString("birthday");
//                            String image_url = "http://graph.facebook.com/" + id + "/picture?type=large";

                            if (first_name != null && last_name != null) {
                                usrnm.setText(String.format("%s %s", first_name, last_name));
                                propic.setText(String.valueOf(first_name.charAt(0)));
                            }
                            String email;
                            if (object.has("email")) {
                                email = object.getString("email");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "error in name", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,first_name,last_name,email,gender,birthday"); // id,first_name,last_name,email,gender,birthday,cover,picture.type(large)
        request.setParameters(parameters);
        request.executeAsync();
    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finishAffinity();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click back again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    private void count() {
        String don_url = "https://bloodtransfer.herokuapp.com/index.php/data/count/users";
        String rec_url = "https://bloodtransfer.herokuapp.com/index.php/data/count/bloodrequest";
        AsyncHttpClient client = new AsyncHttpClient();

        client.post(don_url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i("JSON", "JSON is " + response.toString());
                Log.i("JSON", response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.i("JSON", "Status code" + statusCode);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getApplicationContext(), "Request Success:)", Toast.LENGTH_SHORT).show();
                Log.i("JSON", responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.i("JSON", response.toString());
                TextView don_count, don_count2;
                don_count =findViewById(R.id.don_count_home);

                try {
                    JSONObject obj = (JSONObject) response.get(0);
                    don_count.setText(obj.getString("count(*)"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        client.post(rec_url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i("JSON", "JSON is " + response.toString());
                Log.i("JSON", response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.i("JSON", "Status code" + statusCode);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getApplicationContext(), "Request Success:)", Toast.LENGTH_SHORT).show();
                Log.i("JSON", responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.i("JSON", response.toString());
                TextView  don_count2;
                don_count2 =findViewById(R.id.don_count2_home);

                try {
                    JSONObject obj = (JSONObject) response.get(0);
                    don_count2.setText(obj.getString("count(*)"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
