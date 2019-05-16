package com.blood.blooddonation;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import org.w3c.dom.Text;

import cz.msebera.android.httpclient.Header;

public class RequestActivity extends AppCompatActivity {

    JSONArray result;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        Toolbar toolbar = findViewById(R.id.toolbar_req);
//        toolbar.setTitle("Red Blood");
        toolbar.setBackgroundColor(getResources().getColor(R.color.main));

        setSupportActionBar(toolbar);

        Button req = findViewById(R.id.button_req);
        req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RequestBlood.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });

        listView = findViewById(R.id.listview_request);


        displayAllRecievers();
    }

    private void displayAllRecievers() {
        String displayurl = "https://bloodtransfer.herokuapp.com/index.php/data/allrequests";

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

                CustomAdapter2 adapter = new CustomAdapter2();
                listView.setAdapter(adapter);
            }
        });
    }


    class CustomAdapter2 extends BaseAdapter {
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
            convertView = getLayoutInflater().inflate(R.layout.cardview_request, null);

            TextView name = convertView.findViewById(R.id.name_card_1);
            TextView place = convertView.findViewById(R.id.place_1);
            TextView blood = convertView.findViewById(R.id.blood_group_card_1);
            Button no = convertView.findViewById(R.id.help_1);

            try {
                final JSONObject object = result.getJSONObject(position);
                name.setText(object.getString("rec_name"));
                place.setText(object.getString("city"));
                blood.setText(object.getString("blood_group"));
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("JSON", "Calling");
                        Toast.makeText(getApplicationContext(), "Calling..", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        try {
                            intent.setData(Uri.parse("tel:" + object.getString("contact_no")));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        startActivity(intent);
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return convertView;
        }
    }

}
