package edu.duke.compsci316.lostitfoundit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonLost = findViewById(R.id.button_lost);
        buttonLost.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, EnterLostReportActivity.class);
                startActivity(myIntent);
            }
        });

        final Button buttonFound = findViewById(R.id.button_found);
        buttonFound.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, EnterFoundReportActivity.class);
                startActivity(myIntent);
            }
        });




    }
}
