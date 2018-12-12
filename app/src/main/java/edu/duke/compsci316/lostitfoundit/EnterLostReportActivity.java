package edu.duke.compsci316.lostitfoundit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EnterLostReportActivity extends AppCompatActivity {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_lost_report);

//        final EditText itemTitle = findViewById(R.id.lost_report_editText);
//        final EditText description = findViewById(R.id.lost_report_description_editText);
//        final EditText time = findViewById(R.id.lost_report_date_editText);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference();

        /* following code from
            https://stackoverflow.com/questions/13377361/how-to-create-a-drop-down-list
         */
        //get the spinner from the xml.
        final Spinner dropdown = findViewById(R.id.lost_report_type_spinner);
        //create a list of items for the spinner.
        String[] items = new String[]{"What TYPE of item is it?", "clothing",
                "electronic", "water bottle", "school supplies", "miscellaneous"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        //LOCATION SPINNER
        final Spinner dropdownLocation = findViewById(R.id.lost_report_location_spinner);
        //create a list of items for the spinner.
        String[] places = new String[]{"Where did you lose it?", "West Union", "Bryan Center", "Divinity School", "East Campus"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, places);
        //set the spinners adapter to the previously created one.
        dropdownLocation.setAdapter(locationAdapter);

        final Button button = findViewById(R.id.lost_report_submit_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(String.valueOf(dropdown.getSelectedItem()).equals("What TYPE of item is it?")){
                    Toast.makeText(EnterLostReportActivity.this, "TYPE field required",
                            Toast.LENGTH_SHORT).show();
                }
                else if(String.valueOf(dropdownLocation.getSelectedItem()).equals("Where did you lose it?")){
                    Toast.makeText(EnterLostReportActivity.this, "Location field required",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    Report report = new LostReport(String.valueOf(dropdown.getSelectedItem()),
                            String.valueOf(dropdownLocation.getSelectedItem())
                            );

                    sendReportToFirebase(report);

                    Intent intent = new Intent(EnterLostReportActivity.this, QueryResultsActivity.class);
                    intent.putExtra("type", dropdown.getSelectedItem().toString());
                    intent.putExtra("location", dropdownLocation.getSelectedItem().toString());
                    startActivity(intent);

                }


            }
        });
    }

    private void sendReportToFirebase(Report report){

        mRef.child("lost").push().setValue(report);
        Toast.makeText(EnterLostReportActivity.this, "Locating your item...",
                Toast.LENGTH_LONG).show();
    }


}
