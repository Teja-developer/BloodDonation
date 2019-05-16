package com.blood.blooddonation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class RequestBlood extends AppCompatActivity {

    ImageView aposi, anega, bnega, bposi, oposi, onega, abposi, abnega, male, female;
    int i = 1, j = 1, k = 1, l = 1, m = 1, n = 1, o = 1, p = 1, q = 1;
    Button submit;
    String bloodgroup;
    EditText name, location, hospital, phonenum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_blood);


        aposi = findViewById(R.id.apos);
        anega = findViewById(R.id.aneg);
        bnega = findViewById(R.id.bneg);
        bposi = findViewById(R.id.bpos);
        oposi = findViewById(R.id.opos);
        onega = findViewById(R.id.oneg);
        abposi = findViewById(R.id.abpos);
        abnega = findViewById(R.id.abneg);

        name = findViewById(R.id.nam);
        location = findViewById(R.id.are);
        hospital = findViewById(R.id.hos);
        phonenum = findViewById(R.id.ph);


        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        String[] bloodfor = {"Friend", "Father", "Mother", "Relative", "Others"};
        final Spinner spin1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bloodfor);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adapter1);

//        String[] citypref = {"Mumbai", "Chennai", "Visakhapatnam", "Hyderabad", "Bangalore"};
//        Spinner spin2 = (Spinner) findViewById(R.id.spinner2);
//        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, citypref);
//        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spin2.setAdapter(adapter2);

        aposi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (j == 1) {
                    aposi.setImageResource(R.drawable.cgroup310);
                    j = 0;
                    bloodgroup = "A+";
                } else {
                    aposi.setImageResource(R.drawable.group310);
                    j = 1;
                }
            }
        });

        anega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anega.setImageResource(R.drawable.cgroup311);
                if (k == 1) {
                    anega.setImageResource(R.drawable.cgroup311);
                    k = 0;
                    bloodgroup = "A-";
                } else {
                    anega.setImageResource(R.drawable.group311);
                    k = 1;
                }
            }
        });

        bnega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bnega.setImageResource(R.drawable.cgroup312);
                if (l == 1) {
                    bnega.setImageResource(R.drawable.cgroup312);
                    l = 0;
                    bloodgroup = "B-";
                } else {
                    bnega.setImageResource(R.drawable.group312);
                    l = 1;
                }
            }
        });

        bposi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bposi.setImageResource(R.drawable.cgroup319);
                if (m == 1) {
                    bposi.setImageResource(R.drawable.cgroup319);
                    m = 0;
                    bloodgroup = "B+";
                } else {
                    bposi.setImageResource(R.drawable.group319);
                    m = 1;
                }
            }
        });

        oposi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oposi.setImageResource(R.drawable.cgroup313);
                if (n == 1) {
                    oposi.setImageResource(R.drawable.cgroup313);
                    n = 0;
                    bloodgroup = "O+";
                } else {
                    oposi.setImageResource(R.drawable.group313);
                    n = 1;
                }
            }
        });

        onega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onega.setImageResource(R.drawable.cgroup318);
                if (o == 1) {
                    onega.setImageResource(R.drawable.cgroup318);
                    o = 0;
                    bloodgroup = "O-";
                } else {
                    onega.setImageResource(R.drawable.group318);
                    o = 1;
                }
            }
        });

        abposi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abposi.setImageResource(R.drawable.cgroup317);
                if (p == 1) {
                    abposi.setImageResource(R.drawable.cgroup317);
                    p = 0;
                    bloodgroup = "AB+";
                } else {
                    abposi.setImageResource(R.drawable.group317);
                    p = 1;
                }
            }
        });

        abnega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abnega.setImageResource(R.drawable.cgroup316);
                if (q == 1) {
                    abnega.setImageResource(R.drawable.cgroup316);
                    q = 0;
                    bloodgroup = "AB-";
                } else {
                    abnega.setImageResource(R.drawable.group316);
                    q = 1;
                }
            }
        });

        submit = findViewById(R.id.sub);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request(spin1.getSelectedItem().toString(), "Hyderabad", bloodgroup, name.getText().toString(), location.getText().toString(), hospital.getText().toString(), phonenum.getText().toString());
            }
        });
    }

    private void request(String toString, String city, String bloodgroup, String name, String location, String hospital, String phonenum) {

        final RequestParams params = new RequestParams();

        params.put("name", name);
        params.put("whatsapp", "9704800766");
        params.put("mobile", phonenum);
        params.put("relation", toString);
        params.put("needed_by", "skdafjhk");
        params.put("bloodgrp", bloodgroup);
        params.put("city", city);
        params.put("area", location);
        params.put("google_loc", "fsdfjsd");
        params.put("status", false);
        params.put("hospital", hospital);

        final String fcity = city;
        final String fbloodgroup = bloodgroup;

        String request_url = "https://bloodtransfer.herokuapp.com/index.php/data/reciever";
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(request_url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i("JSON", "JSON is " + response.toString());
                Log.i("JSON", "Status  code" + statusCode);
                Log.i("JSON", response.toString());

                Toast.makeText(getApplicationContext(), "Request Success :)", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), FilteredResult.class);
                intent.putExtra("json", response.toString());
                startActivity(intent);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(getApplicationContext(), "Request Failed:(", Toast.LENGTH_SHORT).show();
                Log.i("JSON", "Status code" + statusCode);
                Log.i("JSON", errorResponse.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getApplicationContext(), "Request Success :)", Toast.LENGTH_SHORT).show();
                Log.i("JSON", "Status  code" + statusCode);
                Log.i("JSON", responseString);


                Intent intent = new Intent(getApplicationContext(), FilteredResult.class);
                intent.putExtra("city", fcity);
                intent.putExtra("blood", fbloodgroup);
                startActivity(intent);
            }
        });

        donors();
    }

    private void donors() {
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

            }
        });
    }
}
