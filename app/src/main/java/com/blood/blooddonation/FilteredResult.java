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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class FilteredResult extends AppCompatActivity {

    JSONArray result;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtered_result);

        String json = getIntent().getStringExtra("json");

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
                final JSONObject object = result.getJSONObject(position);
                name.setText(object.getString("rec_name"));
                place.setText(object.getString("city"));
                blood.setText(object.getString("blood_group"));
                last_d.setText("Last donated on " + object.getString("last_donated"));
                contact.setOnClickListener(new View.OnClickListener() {
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
