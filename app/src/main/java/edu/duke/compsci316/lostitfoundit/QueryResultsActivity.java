package edu.duke.compsci316.lostitfoundit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_results);

        RecyclerView rv = findViewById(R.id.activity_query_results_rv);

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("found");

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
