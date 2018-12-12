package edu.duke.compsci316.lostitfoundit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * Shows results for queries
 * @author Elizabeth Bartusiak
 * @version 11/06/2018
 */

public class QueryResultsActivity extends AppCompatActivity {

    FirebaseRecyclerAdapter mAdapter;
    private TextView matchesFound;
    private TextView noMatches;

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
