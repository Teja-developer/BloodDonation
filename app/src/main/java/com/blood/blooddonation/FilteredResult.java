package com.blood.blooddonation;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class FilteredResult extends AppCompatActivity {

    JSONArray result;
    ListView listView;
    String rname, blood_grp, hospital, location, goog_loc, mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtered_result);

        String json = getIntent().getStringExtra("json");
        rname = getIntent().getStringExtra("rname");
        blood_grp = getIntent().getStringExtra("blood");
        hospital = getIntent().getStringExtra("hospital");
        location = getIntent().getStringExtra("location");
        goog_loc = getIntent().getStringExtra("google_loc");
        mobile = getIntent().getStringExtra("mobile");

        try {
            JSONObject object = new JSONObject(json);
            result = object.getJSONArray("message");
            Log.i("JSON", String.valueOf(result.length()));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.i("JSON", "In filtered " + json);

        listView = findViewById(R.id.listview_filter);
        CustomAdapter adapter = new CustomAdapter();
        listView.setAdapter(adapter);

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
            Button contact = convertView.findViewById(R.id.help);

            try {
                final String[] mobile = new String[1];
                final JSONObject object = result.getJSONObject(position);
                name.setText(object.getString("name"));
                place.setText(object.getString("city"));
                blood.setText(object.getString("bloodgroup"));
                last_d.setText("Last donated on " + object.getString("last_donated"));
                contact.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("JSON", "Clicked");
                        String message = null;
                        try {
                            final String link1 = "blood.donate/accept/123", link2 = "blood.donate/reject/123";
                            message = "Hello " + object.getString("name") + ",\n\n Will you be able to donate blood for a patient who needs blood" + ".\nHere are the details:-\n\n Name: " + rname + "\n Mobile number: " + mobile[0] +
                                    "\n Hospital place: " + hospital + "\n Blood group needed: " + blood_grp + "\n\n Location link: " + goog_loc + "\n\nClick on this link to accept this request\n" + link1 + "\n\n Click here to reject\n" + link2;

                            mobile[0] = object.getString("mobile");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        sendsms(message, mobile[0]);
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return convertView;
        }

        private void sendsms(String message, String number) {

//            String urlEncoded = message.replaceAll(" ", "%20");
//            urlEncoded = urlEncoded.replaceAll(",", "%2C");
//            urlEncoded = urlEncoded.replaceAll(".", "%2E");
//            urlEncoded = urlEncoded.replaceAll("-", "%2D");
//            urlEncoded = urlEncoded.replaceAll("&", "%26");
//            urlEncoded = urlEncoded.replaceAll("/", "%2F");
//            urlEncoded = urlEncoded.replaceAll(":", "%3A");


            String otp_url = "https://bloodtransfer.herokuapp.com/index.php/Oauth/help";
            Log.i("JSON", otp_url);

            RequestParams params = new RequestParams();
            params.put("mobile", number);
            params.put("msg", message);
            Log.i("JSON", message);

            AsyncHttpClient client = new AsyncHttpClient();
            client.post(otp_url, params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Log.i("JSON", "JSON is " + response.toString());
                    Log.i("JSON", "Status  code" + statusCode);
                    Log.i("JSON", "Response is " + response.toString());
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    Toast.makeText(getApplicationContext(), "Request Failed:(", Toast.LENGTH_SHORT).show();
                    Log.i("JSON", "Status code" + statusCode);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Toast.makeText(getApplicationContext(), "Request Success :)", Toast.LENGTH_SHORT).show();
                    Log.i("JSON", "Status  code" + statusCode);
                    Log.i("JSON", "Response is " + responseString);
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                }
            });

        }

    }

}
