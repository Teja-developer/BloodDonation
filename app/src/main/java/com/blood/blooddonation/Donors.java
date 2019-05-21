package com.blood.blooddonation;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class Donors extends AppCompatActivity {

    private ListView listview;
    private List<Attributes> AttributesList;
    JSONArray result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donors);

        Toolbar toolbar = findViewById(R.id.toolbar_donor);
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));

        listview = findViewById(R.id.listview1);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setHomeButtonEnabled(true);
        }

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.left);
        toolbar.setNavigationIcon(drawable);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        AttributesList = new ArrayList<>();

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        displayAllPosts();

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.alldonors, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_filter) {
//            Toast.makeText(getApplicationContext(), "Action clicked", Toast.LENGTH_LONG).show();
//            return true;
//        }
//
//
//        return super.onOptionsItemSelected(item);
//    }

    private void displayAllPosts() {
        String displayurl = "https://bloodtransfer.herokuapp.com/index.php/data/searchall";

        AsyncHttpClient client = new AsyncHttpClient();

        RequestParams params = new RequestParams();

        client.post(displayurl, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i("JSON", "JSON is " + response.toString());
                Log.i("JSON", "Status  code" + statusCode);
                Log.i("JSON", response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(getApplicationContext(), "Request Failed:(", Toast.LENGTH_SHORT).show();
                Log.i("JSON", "Status code" + statusCode);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.i("JSON", "Status  code" + statusCode);
                Log.i("JSON", responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.i("JSON", response.toString());
                Log.i("JSON", "Length " + response.length());
                if (response.length() > 0) {
                    result = response;
                }

                CustomAdapter adapter = new CustomAdapter();
                listview.setAdapter(adapter);
            }
        });
    }

    class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return result.length();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.cardview, null);

            TextView name = convertView.findViewById(R.id.name_card);
            TextView place = convertView.findViewById(R.id.place);
            TextView blood = convertView.findViewById(R.id.blood_group_card);
            TextView last_d = convertView.findViewById(R.id.last_donated_card);
            TextView image = convertView.findViewById(R.id.photo);
            Button contact = convertView.findViewById(R.id.help);

            try {
                final JSONObject object = result.getJSONObject(position);
                name.setText(object.getString("name"));
                place.setText(object.getString("city"));
                blood.setText(object.getString("bloodgroup"));
                last_d.setText("Last donated on " + object.getString("last_donated"));
                contact.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), RequestActivity.class);
                        try {
                            intent.putExtra("don_name", object.getString("name"));
                            intent.putExtra("number", object.getString("mobile"));
                            //intent.putExtra("id",object.getString("id"));
                            intent.putExtra("bloodgroup",object.getString("bloodgroup"));
                            intent.putExtra("city",object.getString("city"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        startActivity(intent);
                    }
                });
                String fl = object.getString("name").substring(0, 1);
                image.setText(fl);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return convertView;
        }
    }

}
