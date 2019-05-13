package com.example.blooddonation;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class PostItemsAdapter  extends  RecyclerView.Adapter<PostItemsAdapter.PostItemsViewHolder>{

    private Context context;
    private List<Attributes> items;
    private FirebaseFirestore db;

    PostItemsAdapter(Context context, List<Attributes> items) {
        this.context = context;
        this.items = items;
    }


    @Override
    public PostItemsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.i("app","View created");
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview,viewGroup,false);

        return new PostItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PostItemsViewHolder postItemsViewHolder, int i) {
        final Attributes post = items.get(i);

        if(haveNetworkConnection()) {

            final FirebaseAuth mAuth = FirebaseAuth.getInstance();
            db = FirebaseFirestore.getInstance();

            Log.i("Tests",post.getName()+" "+post.getName()+" "+post.getBloodgroup());
            postItemsViewHolder.blood.setText(post.getBloodgroup());
            postItemsViewHolder.name.setText(post.getName());
        }

        else {
            Toast.makeText(context,"Network required",Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    class PostItemsViewHolder extends RecyclerView.ViewHolder{
        View mView;
        TextView name,blood;

        PostItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            blood = mView.findViewById(R.id.blood_group_card);
            name = mView.findViewById(R.id.name_card);
        }

    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
}
