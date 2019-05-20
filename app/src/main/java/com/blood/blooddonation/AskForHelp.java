package com.blood.blooddonation;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class AskForHelp extends AppCompatActivity {

    String message;
    double lat;
    double lon;
    String loc_link, bloodgroup;
    Location location;
    private LocationManager locationManager;
    int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    TextView loc;
    Button sub;
    ImageView aposi, anega, bnega, bposi, oposi, onega, abposi, abnega;
    int i = 1, j = 1, k = 1, l = 1, m = 1, n = 1, o = 1, p = 1, q = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_for_help);

        aposi = findViewById(R.id.apos);
        anega = findViewById(R.id.aneg);
        bnega = findViewById(R.id.bneg);
        bposi = findViewById(R.id.bpos);
        oposi = findViewById(R.id.opos);
        onega = findViewById(R.id.oneg);
        abposi = findViewById(R.id.abpos);
        abnega = findViewById(R.id.abneg);

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

        final EditText editText = findViewById(R.id.rea_ask);

        final EditText r_name = findViewById(R.id.nam_ask);

        final EditText mobil_r = findViewById(R.id.ph_ask);

        final EditText hospital = findViewById(R.id.hos_ask);

        loc = findViewById(R.id.are);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(AskForHelp.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            return;
        }

        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_city();
            }
        });
        final String link1 = "blood.donate/accept/123", link2 = "blood.donate/reject/123";

        String[] bloodfor = {"Friend", "Father", "Mother", "Relative", "Others"};
        final Spinner spin1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bloodfor);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adapter1);

        final String name = getIntent().getStringExtra("don_name");
        final String number = getIntent().getStringExtra("number");


        sub = findViewById(R.id.sub_ask);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sub.setVisibility(View.GONE);
                final String reason = editText.getText().toString();
                final String r_name_str = r_name.getText().toString();
                final String mobile = mobil_r.getText().toString();
                final String hospital_str = hospital.getText().toString();

                message = "Hello " + name + ",\n\n Will you be able to donate blood for a patient who is suffering with " + reason + ".\nHere are the details:-\n\n Name: " + r_name_str + "\n Mobile number: " + mobile +
                        "\n Reason: " + reason + "\n Hospital place: " + hospital_str + "\n Blood group needed: "+bloodgroup+"\n\n Location link: " + loc_link + "\n\nClick on this link to accept this request\n" + link1 + "\n\n Click here to reject\n" + link2;

//                Log.i("JSON",message);

                sendsms(message, number);
            }
        });

    }
//https://bloodtransfer.herokuapp.com/index.php/Oauth/send_sms/msg/mobno
    private void sendsms(String message, String number) {


        String otp_url = "https://bloodtransfer.herokuapp.com/index.php/Oauth/help";
        Log.i("JSON", otp_url);

        RequestParams params = new RequestParams();
        params.put("mobile",number);
        params.put("msg",message);

        AsyncHttpClient client = new AsyncHttpClient();
        client.post(otp_url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i("JSON", "JSON is " + response.toString());
                Log.i("JSON", "Status  code" + statusCode);
                Log.i("JSON", "Response is "+response.toString());
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
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
                Log.i("JSON", "Response is "+responseString);
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
            }
        });


    }


    private void get_city() {
        lat = location.getLatitude();
        lon = location.getLongitude();
        try {
            Geocoder geocoder = new Geocoder(this);
            List<Address> addresses = null;
            addresses = geocoder.getFromLocation(lat, lon, 1);
            String cit = addresses.get(0).getLocality();
            loc.setText(cit);
            loc_link = "https://www.google.com/maps?q=" + lat + "," + lon;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
