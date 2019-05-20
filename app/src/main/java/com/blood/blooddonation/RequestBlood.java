package com.blood.blooddonation;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class RequestBlood extends AppCompatActivity {

    ImageView aposi, anega, bnega, bposi, oposi, onega, abposi, abnega, male, female;
    int i = 1, j = 1, k = 1, l = 1, m = 1, n = 1, o = 1, p = 1, q = 1;
    Button submit;
    TextView location;
    String location_string;
    String google_map_link;
    String bloodgroup;
    EditText name, hospital, phonenum;
    double lat;
    double lon;
    Location location2;
    private LocationManager locationManager;
    int STORAGE_PERMISSION_CODE = 1;
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
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        else{
            requestPermissions();
        }
        location2 = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_city();
                location_string = location.getText().toString();
            }
        });


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

    private void requestPermissions() {

        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_COARSE_LOCATION)){
            new AlertDialog.Builder(this)
                    .setTitle("Permission Needed")
                    .setMessage("This permission is needed to fetch your current city")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(RequestBlood.this,new String[] {Manifest.permission.ACCESS_COARSE_LOCATION},STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }
        else{
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_COARSE_LOCATION},STORAGE_PERMISSION_CODE);
        }
    }

    private void get_city() {
        lat = location2.getLatitude();
        lon = location2.getLongitude();
        try {
            Log.i("JSON","Lat Lon"+lat+" "+lon);
            Geocoder geocoder = new Geocoder(this);
            List<Address> addresses=null;
            addresses = geocoder.getFromLocation(lat,lon,1);
            String cit = addresses.get(0).getLocality();
            location.setText(cit);
            google_map_link = "https://www.google.com/maps?q=" + lat + "," + lon;
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void request(String toString, String city, final String bloodgroup, final String name, final String location, final String hospital, final String phonenum) {

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
                intent.putExtra("rname",name);
                intent.putExtra("blood",fbloodgroup);
                intent.putExtra("hospital",hospital);
                intent.putExtra("location",location_string);
                intent.putExtra("google_loc",google_map_link);
                intent.putExtra("mobile",phonenum);

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
