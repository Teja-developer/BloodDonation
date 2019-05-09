package com.example.blooddonation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class RequestBlood extends AppCompatActivity {

    ImageView aposi,anega,bnega,bposi,oposi,onega,abposi,abnega,male,female;
    int i=1,j=1,k=1,l=1,m=1,n=1,o=1,p=1,q=1;

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


        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        String[] bloodfor = { "Friend", "Father", "Mother", "Relative", "Others" };
        Spinner spin1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bloodfor);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adapter1);

        String[] citypref = { "Mumbai", "Chennai", "Visakhapatnam", "Hyderabad", "Bangalore" };
        Spinner spin2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, citypref);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(adapter2);

        aposi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (j==1) {
                    aposi.setImageResource(R.drawable.cgroup310);
                    j=0;
                }
                else{
                    aposi.setImageResource(R.drawable.group310);
                    j=1;
                }
            }
        });

        anega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anega.setImageResource(R.drawable.cgroup311);
                if (k==1) {
                    anega.setImageResource(R.drawable.cgroup311);
                    k=0;
                }
                else{
                    anega.setImageResource(R.drawable.group311);
                    k=1;
                }
            }
        });

        bnega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bnega.setImageResource(R.drawable.cgroup312);
                if (l==1) {
                    bnega.setImageResource(R.drawable.cgroup312);
                    l=0;
                }
                else{
                    bnega.setImageResource(R.drawable.group312);
                    l=1;
                }
            }
        });

        bposi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bposi.setImageResource(R.drawable.cgroup319);
                if (m==1) {
                    bposi.setImageResource(R.drawable.cgroup319);
                    m=0;
                }
                else{
                    bposi.setImageResource(R.drawable.group319);
                    m=1;
                }
            }
        });

        oposi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oposi.setImageResource(R.drawable.cgroup313);
                if (n==1) {
                    oposi.setImageResource(R.drawable.cgroup313);
                    n=0;
                }
                else{
                    oposi.setImageResource(R.drawable.group313);
                    n=1;
                }
            }
        });

        onega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onega.setImageResource(R.drawable.cgroup318);
                if (o==1) {
                    onega.setImageResource(R.drawable.cgroup318);
                    o=0;
                }
                else{
                    onega.setImageResource(R.drawable.group318);
                    o=1;
                }
            }
        });

        abposi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abposi.setImageResource(R.drawable.cgroup317);
                if (p==1) {
                    abposi.setImageResource(R.drawable.cgroup317);
                    p=0;
                }
                else{
                    abposi.setImageResource(R.drawable.group317);
                    p=1;
                }
            }
        });

        abnega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abnega.setImageResource(R.drawable.cgroup316);
                if (q==1) {
                    abnega.setImageResource(R.drawable.cgroup316);
                    q=0;
                }
                else{
                    abnega.setImageResource(R.drawable.group316);
                    q=1;
                }
            }
        });
    }
}
