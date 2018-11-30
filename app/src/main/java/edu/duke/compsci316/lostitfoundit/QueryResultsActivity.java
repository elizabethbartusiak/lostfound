package edu.duke.compsci316.lostitfoundit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
        rv.setAdapter(new QueryResultAdapter(this, titles, locations));
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}
