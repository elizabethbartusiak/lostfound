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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_results);


        String[] titles = {"water bottle", "sweatshirt", "pen", "backpack", "laptop"};
        String[] locations = {"Gross Hall", "BioSci111", "Vondy", "BC", "WU"};
        RecyclerView rv = findViewById(R.id.activity_query_results_rv);

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("found");

        FirebaseRecyclerOptions<FoundReport> options =
                new FirebaseRecyclerOptions.Builder<FoundReport>()
                        .setQuery(query, FoundReport.class)
                        .build();

        FirebaseRecyclerAdapter adapter = new QueryResultAdapter(this, options) ;

//        rv.setAdapter(new QueryResultAdapter(this, titles, locations));
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}
