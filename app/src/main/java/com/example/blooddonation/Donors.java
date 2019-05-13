package com.example.blooddonation;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.accounttransfer.AccountTransfer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.List;

public class Donors extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Attributes> AttributesList;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    StorageReference reference;
    private CollectionReference collectionReference ;
    TextView ph;
    PostItemsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donors);

        Toolbar toolbar = findViewById(R.id.toolbar_donor);
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));



        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setHomeButtonEnabled(true);
        }

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.left);
        toolbar.setNavigationIcon(drawable);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mAuth = FirebaseAuth.getInstance();

        db = FirebaseFirestore.getInstance();

        collectionReference = db.collection("Donors");

        reference = FirebaseStorage.getInstance().getReference();

        AttributesList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerview1);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new PostItemsAdapter(getApplicationContext(), AttributesList);

        recyclerView.setAdapter(adapter);

        displayAllPosts();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.alldonors, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filter) {
            Toast.makeText(getApplicationContext(), "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void displayAllPosts() {
        Log.i("Tests","Reached");
        db.collection("Donors/O+/Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Attributes p = document.toObject(Attributes.class);

                        AttributesList.add(p);
                    }
                    adapter.notifyDataSetChanged();

                    adapter = new PostItemsAdapter(getApplicationContext(), AttributesList);

                    recyclerView.setAdapter(adapter);
                    Log.d("Tests", AttributesList.toString());
                } else {
                    Log.d("Tests", "Error getting documents: ", task.getException());
                }
            }
        });

        db.collection("Donors/O-/Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Attributes p = document.toObject(Attributes.class);

                        AttributesList.add(p);
                    }
                    adapter.notifyDataSetChanged();

                    adapter = new PostItemsAdapter(getApplicationContext(), AttributesList);

                    recyclerView.setAdapter(adapter);
                    Log.d("Tests", AttributesList.toString());
                } else {
                    Log.d("Tests", "Error getting documents: ", task.getException());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });

        db.collection("Donors/A+/Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Attributes p = document.toObject(Attributes.class);

                        AttributesList.add(p);
                    }
                    adapter.notifyDataSetChanged();

                    adapter = new PostItemsAdapter(getApplicationContext(), AttributesList);

                    recyclerView.setAdapter(adapter);
                    Log.d("Tests", AttributesList.toString());
                } else {
                    Log.d("Tests", "Error getting documents: ", task.getException());
                }
            }
        });

        db.collection("Donors/A-/Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Attributes p = document.toObject(Attributes.class);

                        AttributesList.add(p);
                    }
                    adapter.notifyDataSetChanged();

                    adapter = new PostItemsAdapter(getApplicationContext(), AttributesList);

                    recyclerView.setAdapter(adapter);
                    Log.d("Tests", AttributesList.toString());
                } else {
                    Log.d("Tests", "Error getting documents: ", task.getException());
                }
            }
        });

        db.collection("Donors/AB+/Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Attributes p = document.toObject(Attributes.class);

                        AttributesList.add(p);
                    }
                    adapter.notifyDataSetChanged();

                    adapter = new PostItemsAdapter(getApplicationContext(), AttributesList);

                    recyclerView.setAdapter(adapter);
                    Log.d("Tests", AttributesList.toString());
                } else {
                    Log.d("Tests", "Error getting documents: ", task.getException());
                }
            }
        });

        db.collection("Donors/AB-/Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Attributes p = document.toObject(Attributes.class);

                        AttributesList.add(p);
                    }
                    adapter.notifyDataSetChanged();

                    adapter = new PostItemsAdapter(getApplicationContext(), AttributesList);

                    recyclerView.setAdapter(adapter);
                    Log.d("Tests", AttributesList.toString());
                } else {
                    Log.d("Tests", "Error getting documents: ", task.getException());
                }
            }
        });

        db.collection("Donors/B+/Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Attributes p = document.toObject(Attributes.class);

                        AttributesList.add(p);
                    }
                    adapter.notifyDataSetChanged();

                    adapter = new PostItemsAdapter(getApplicationContext(), AttributesList);

                    recyclerView.setAdapter(adapter);
                    Log.d("Tests", AttributesList.toString());
                } else {
                    Log.d("Tests", "Error getting documents: ", task.getException());
                }
            }
        });

        db.collection("Donors/B-/Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Attributes p = document.toObject(Attributes.class);

                        AttributesList.add(p);
                    }
                    adapter.notifyDataSetChanged();

                    adapter = new PostItemsAdapter(getApplicationContext(), AttributesList);

                    recyclerView.setAdapter(adapter);
                    Log.d("Tests", AttributesList.toString());
                } else {
                    Log.d("Tests", "Error getting documents: ", task.getException());
                }
            }
        });
    }

}
