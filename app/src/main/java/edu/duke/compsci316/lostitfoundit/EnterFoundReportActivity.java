package edu.duke.compsci316.lostitfoundit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EnterFoundReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_found_report);

        final EditText itemTitle = findViewById(R.id.item_title_editText);

        /* following code from
            https://stackoverflow.com/questions/13377361/how-to-create-a-drop-down-list
         */
        //get the spinner from the xml.
        final Spinner dropdown = findViewById(R.id.type_spinner);
        //create a list of items for the spinner.
        String[] items = new String[]{"What TYPE of item is it?", "water bottle", "clothing", "electronic", "DukeID"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        //LOCATION SPINNER
        final Spinner dropdownLocation = findViewById(R.id.location_spinner);
        //create a list of items for the spinner.
        String[] places = new String[]{"Where did you find it?", "West Union", "Bryan Center", "Divinity School", "East Campus"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, places);
        //set the spinners adapter to the previously created one.
        dropdownLocation.setAdapter(locationAdapter);

        final Button button = findViewById(R.id.found_submit_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(itemTitle.getText().toString().equals("")){
                    Toast.makeText(EnterFoundReportActivity.this, "Item Title required",
                            Toast.LENGTH_SHORT).show();
                }
                else if(String.valueOf(dropdown.getSelectedItem()).equals("What TYPE of item is it?")){
                    Toast.makeText(EnterFoundReportActivity.this, "TYPE field required",
                            Toast.LENGTH_SHORT).show();
                }
                else if(String.valueOf(dropdownLocation.getSelectedItem()).equals("Where did you find it?")){
                    Toast.makeText(EnterFoundReportActivity.this, "Location field required",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(EnterFoundReportActivity.this, "Successfully submitted",
                            Toast.LENGTH_LONG).show();
                    Intent myIntent = new Intent(EnterFoundReportActivity.this, MainActivity.class);
                    startActivity(myIntent);
                }

            }
        });


    }
}
