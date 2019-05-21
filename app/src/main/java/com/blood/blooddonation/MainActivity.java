package com.blood.blooddonation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.goodiebag.pinview.Pinview;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    Button getotp, verify;
    EditText phone;
    TextView resen;
    Pinview pinv;
    CardView cardView;
    private String otp_pin = "";
    ImageView blur;
    boolean otp_ver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        otp_ver=false;
        phone = findViewById(R.id.phonenm);
        verify = findViewById(R.id.verifysub);
        pinv = findViewById(R.id.pincode);
        cardView = findViewById(R.id.card);
        blur = findViewById(R.id.imageView4);
        getotp = findViewById(R.id.getotp);
        resen = findViewById(R.id.resend);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        phone.requestFocus();
        ImageButton imageButton = findViewById(R.id.main_back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"You cannot go back at this stage",Toast.LENGTH_SHORT).show();
            }
        });

        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerificationCode();
                cardView.setVisibility(View.VISIBLE);
                blur.setVisibility(View.VISIBLE);
                getotp.requestFocus();
                phone.clearFocus();
                getotp.setCursorVisible(true);
                getotp.setVisibility(View.GONE);
            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifySignInCode();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(pinv.getWindowToken(), 0);
            }
        });

        resen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resendVerificationCode(phone.getText().toString());
            }
        });

    }


    private void resendVerificationCode(String number) {
        RequestParams params = new RequestParams();
        params.put("mobile", number);

        Log.i("JSON", "no:" + number);
        String otp_url = "https://bloodtransfer.herokuapp.com/index.php/Oauth/resend";

        AsyncHttpClient client = new AsyncHttpClient();
        client.post(otp_url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i("JSON", "JSON is " + response.toString());
                Log.i("JSON", "Status  code" + statusCode);
                Log.i("JSON", response.toString());
                otp_pin = response.toString().substring(0, 6);
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
                otp_pin = responseString.substring(0, 6);
            }
        });

    }

    public void verifySignInCode() {
        String code = pinv.getValue();

        if (otp_pin.equals(code)) {
            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Wrong otp :(", Toast.LENGTH_SHORT).show();
        }
    }


    private void sendVerificationCode() {
        String number = phone.getText().toString();

        RequestParams params = new RequestParams();
        params.put("mobile", number);

        Log.i("JSON", "no:" + number);
        // Oauth/sendsms/(message)/(phno)/
        String otp_url = "https://bloodtransfer.herokuapp.com/index.php/Oauth/otp";

        AsyncHttpClient client = new AsyncHttpClient();
        client.post(otp_url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i("JSON", "JSON is " + response.toString());
                Log.i("JSON", "Status  code" + statusCode);
                Log.i("JSON", response.toString());
                otp_pin = response.toString().substring(0, 6);
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
                Log.i("JSON", responseString);
                otp_pin = responseString.substring(0, 6);
            }
        });
    }



    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"You cant go back at this stage",Toast.LENGTH_SHORT).show();
    }
}
