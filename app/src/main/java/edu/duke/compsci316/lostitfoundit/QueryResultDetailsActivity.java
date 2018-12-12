package edu.duke.compsci316.lostitfoundit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class QueryResultDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_result_details);

        Intent intent = getIntent();
        FoundReport report = (FoundReport) intent.getSerializableExtra("report");

//        TextView name = (R.id.details_name);


        Toast.makeText(this, report.getName(), Toast.LENGTH_SHORT);
    }
}
