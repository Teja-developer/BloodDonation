package com.example.blooddonation;


import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BecomeDonor extends AppCompatActivity {

    ImageView aposi, anega, bnega, bposi, oposi, onega, abposi, abnega, male, female;
    int i = 1, j = 1, k = 1, l = 1, m = 1, n = 1, o = 1, p = 1, q = 1;
    boolean ma = false, fe = false;
    EditText name, email;
    String gender, bloodgroup, visibility_str;
    CheckBox visibility;

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

        //Edittext definition
        name = findViewById(R.id.name_donor);
        email = findViewById(R.id.email_donor);

        //Check box
        visibility = findViewById(R.id.visibility);

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

        Button sub_button = findViewById(R.id.submit);
        sub_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (visibility.isChecked())
                    visibility_str = "checked";
                else
                    visibility_str = "unchecked";

                addPost(name.getText().toString(), email.getText().toString(), gender, bloodgroup, visibility_str);
            }
        });
    }

    private void addPost(String name, String email, String gender, String bloodgroup, String visibility_str) {
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final FirebaseFirestore userposts = FirebaseFirestore.getInstance();

        final CollectionReference posts = db.collection("Donors");

        Date date = new Date();
        String dayOfTheWeek = (String) DateFormat.format("EEEE", date); // Thursday
        final String dateofday = (String) DateFormat.format("dd", date); // 20
        final String monthString = (String) DateFormat.format("MMM", date); // Jun
        String monthNumber = (String) DateFormat.format("MM", date); // 06
        String year = (String) DateFormat.format("yyyy", date); // 2013

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        final String currentDateTimeString = sdf.format(date);
        final FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();

        final Attributes attributes = new Attributes(gender, bloodgroup, mAuth.getUid(), posts.getId(), dateofday, monthString, year, name, email, visibility_str);

        userposts.collection("Donors/" + bloodgroup + "/Users").add(attributes).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "Working ", Toast.LENGTH_SHORT).show();
                documentReference.update("pid", documentReference.getId());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Not Working ", Toast.LENGTH_SHORT).show();
            }
        });

    }

}