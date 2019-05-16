package com.blood.blooddonation;


import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class BecomeDonor extends AppCompatActivity {

    ImageView aposi, anega, bnega, bposi, oposi, onega, abposi, abnega, male, female;
    int i = 1, j = 1, k = 1, l = 1, m = 1, n = 1, o = 1, p = 1, q = 1;
    boolean ma = false, fe = false;
    EditText name, age, phone;
    TextView city;
    String gender, bloodgroup, visibility_str;
    CheckBox visibility;
    DatePickerDialog.OnDateSetListener dateSetListener;
    String dat, personalemail;
    double lat;
    double lon;
    Location location;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        setContentView(R.layout.activity_become_donor);
        aposi = findViewById(R.id.apos);
        anega = findViewById(R.id.aneg);
        bnega = findViewById(R.id.bneg);
        bposi = findViewById(R.id.bpos);
        oposi = findViewById(R.id.opos);
        onega = findViewById(R.id.oneg);
        abposi = findViewById(R.id.abpos);
        abnega = findViewById(R.id.abneg);
        male = findViewById(R.id.imageView);
        female = findViewById(R.id.imageView2);
        city = findViewById(R.id.city);
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

        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    get_city();
            }
        });

        //Edittext definition
        name = findViewById(R.id.editText);
        city = findViewById(R.id.city);
        age = findViewById(R.id.age_donor);
        phone = findViewById(R.id.phonenumber);

        //Check box
        visibility = findViewById(R.id.checkBox2);

        final TextView date;
        date = findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int date = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(BecomeDonor.this, android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth, dateSetListener, year, month, date);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;

                dat = year + "/" + month + "/" + dayOfMonth;
                date.setText(dat);
            }
        };

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 1) {
                    male.setImageResource(R.drawable.cman);
                    i = 0;
                    ma = true;
                    gender = "male";
                } else if (i == 0) {
                    male.setImageResource(R.drawable.cman);
                    ma = true;
                    female.setImageResource(R.drawable.woman);
                }

            }
        });


        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 1) {
                    female.setImageResource(R.drawable.cwomen);
                    fe = true;
                    gender = "female";
                    i = 0;
                } else if (i == 0) {
                    female.setImageResource(R.drawable.cwomen);
                    fe = true;
                    male.setImageResource(R.drawable.man1);

                }
            }
        });

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

        Button sub_button = findViewById(R.id.button);
        sub_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (visibility.isChecked())
                    visibility_str = "checked";
                else
                    visibility_str = "unchecked";

                addPost(name.getText().toString(), city.getText().toString(), gender, bloodgroup, visibility_str);
            }
        });
    }

    private void get_city() {
       lat = location.getLatitude();
       lon = location.getLongitude();
       try {
           Geocoder geocoder = new Geocoder(this);
           List<Address> addresses=null;
           addresses = geocoder.getFromLocation(lat,lon,1);
           String cit = addresses.get(0).getLocality();
           city.setText(cit);
       }catch (Exception e){
           e.printStackTrace();
       }

    }

    private void addPost(final String name, final String email, final String gender, final String bloodgroup, final String visibility_str) {


        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        final RequestParams params = new RequestParams();

        if (account != null) {

            personalemail = account.getEmail();
            Log.i("JSON", name + " " + personalemail + " " + gender + " " + bloodgroup + " " + visibility_str + " " + age.getText().toString() + " " + phone.getText().toString() + " " + dat);
            params.put("name", name);
            params.put("mobile", phone.getText().toString());
            params.put("donor", "yes");
            params.put("visibility", visibility_str);
            params.put("gender", gender);
            params.put("email", personalemail);
            params.put("age", age.getText().toString());
            params.put("city", city.getText().toString());
            params.put("bloodgrp", bloodgroup);
            params.put("area", "area");
            params.put("lastdonated", dat);

            String donor_url = "https://bloodtransfer.herokuapp.com/index.php/data/save";

            AsyncHttpClient client = new AsyncHttpClient();
            client.post(donor_url, params, new JsonHttpResponseHandler() {
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
                    Toast.makeText(getApplicationContext(), "Request Failed:(", Toast.LENGTH_SHORT).show();
                    Log.i("JSON", "Status  code" + statusCode);
                    Log.i("JSON", responseString);
                }
            });

        } else {
            GraphRequest request = GraphRequest.newMeRequest(
                    AccessToken.getCurrentAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {
                            try {

                                if (object.has("email")) {
                                    personalemail = object.getString("email");
                                    Log.i("JSON", name + " " + personalemail + " " + gender + " " + bloodgroup + " " + visibility_str + " " + age.getText().toString() + " " + phone.getText().toString() + " " + dat);
                                    params.put("name", name);
                                    params.put("mobile", phone.getText().toString());
                                    params.put("donor", "yes");
                                    params.put("visibility", visibility_str);
                                    params.put("gender", gender);
                                    params.put("email", personalemail);
                                    params.put("age", age.getText().toString());
                                    params.put("city", "Hyderabad");
                                    params.put("bloodgrp", bloodgroup);
                                    params.put("area", "area");
                                    params.put("lastdonated", dat);

                                    Log.i("JSON", "Has email" + personalemail);
                                    String donor_url = "https://bloodtransfer.herokuapp.com/index.php/data/save";

                                    AsyncHttpClient client = new AsyncHttpClient();
                                    client.post(donor_url, params, new JsonHttpResponseHandler() {
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
                                            Toast.makeText(getApplicationContext(), "Request Failed:(", Toast.LENGTH_SHORT).show();
                                            Log.i("JSON", "Status  code" + statusCode);
                                            Log.i("JSON", responseString);
                                        }
                                    });
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
        Log.i("JSON", "reached addPost method");



    }


}