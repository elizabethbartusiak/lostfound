package edu.duke.compsci316.lostitfoundit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Shows results for queries
 * @author Elizabeth Bartusiak
 * @version 11/06/2018
 */

public class QueryResultsActivity extends AppCompatActivity {

    FirebaseRecyclerAdapter mAdapter;
    private TextView matchesFound;
    private TextView noMatches;
    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_results);

        matchesFound = findViewById(R.id.items_found);
        noMatches = findViewById(R.id.no_matches);
        noMatches.setVisibility(View.GONE);

        RecyclerView rv = findViewById(R.id.activity_query_results_rv);

        Intent i = getIntent();
        String location = i.getStringExtra("location");
        String type = i.getStringExtra("type");

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("found")
                .child(type)
                .child(location);

        FirebaseRecyclerOptions<FoundReport> options =
                new FirebaseRecyclerOptions.Builder<FoundReport>()
                        .setQuery(query, FoundReport.class)
                        .build();

        ValueEventListener vel = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()) {
                    matchesFound.setVisibility(View.GONE);
                    noMatches.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        query.addValueEventListener(vel);
        mAdapter = new QueryResultAdapter(this, options) ;

        rv.setAdapter(mAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }
}
