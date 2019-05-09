package com.example.blooddonation;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class BecomeDonor extends AppCompatActivity {

    ImageView aposi,anega,bnega,bposi,oposi,onega,abposi,abnega,male,female;
    int i=1,j=1,k=1,l=1,m=1,n=1,o=1,p=1,q=1;
    boolean ma=false,fe=false;
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

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==1){
                    male.setImageResource(R.drawable.cman);
                    i=0;
                    ma=true;
                }
                else if(i==0) {
                    male.setImageResource(R.drawable.cman);
                    ma=true;
                    female.setImageResource(R.drawable.woman);
                }

            }
        });


        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==1){
                    female.setImageResource(R.drawable.cwomen);
                    fe=true;
                    i=0;
                }
                else if (i==0){
                    female.setImageResource(R.drawable.cwomen);
                    fe=true;
                    male.setImageResource(R.drawable.man1);

                }
            }
        });

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