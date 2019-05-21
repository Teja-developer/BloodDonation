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
import android.widget.ImageButton;
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
    String name, number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);


        Toolbar toolbar = findViewById(R.id.toolbar_req);
//        toolbar.setTitle("Red Blood");
        toolbar.setBackgroundColor(getResources().getColor(R.color.main));

        setSupportActionBar(toolbar);

        ImageButton imageButton = findViewById(R.id.arrow);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button req = findViewById(R.id.button_req);
        req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RequestBlood.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });

        listView = findViewById(R.id.listview_request);

        count();
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

    private void count() {
        String rec_url = "https://bloodtransfer.herokuapp.com/index.php/data/count/bloodrequest";
        AsyncHttpClient client = new AsyncHttpClient();

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
                don_count2 =findViewById(R.id.don_count);

                try {
                    JSONObject obj = (JSONObject) response.get(0);
                    don_count2.setText(obj.getString("count(*)"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
            TextView photo = convertView.findViewById(R.id.photo1);

            final String don_name = getIntent().getStringExtra("don_name");
            final String don_num = getIntent().getStringExtra("number");
            final String don_blood = getIntent().getStringExtra("bloodgroup");
            final String don_city = getIntent().getStringExtra("city");

            try {
                final JSONObject object = result.getJSONObject(position);
                name.setText(object.getString("rec_name"));
                place.setText(object.getString("city"));
                blood.setText(object.getString("blood_group"));
                if(don_name == null)
                    no.setVisibility(View.INVISIBLE);
                no.setText("Select");
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), AskForHelp.class);
                        try {

                            intent.putExtra("rec_name", object.getString("rec_name"));
                            intent.putExtra("city", object.getString("city"));
                            intent.putExtra("blood_group", object.getString("blood_group"));
                            intent.putExtra("number", object.getString("contact_no"));
                            intent.putExtra("don_name", don_name);
                            intent.putExtra("don_num", don_num);
                            intent.putExtra("don_blood",don_blood);
                            intent.putExtra("don_city",don_city);


                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

                String fl = object.getString("rec_name").substring(0, 1);
                photo.setText(fl);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return convertView;
        }
    }

}
