package edu.duke.compsci316.lostitfoundit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.provider.MediaStore;
import android.os.Environment;
import android.net.Uri;
import android.support.v4.content.FileProvider;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class EnterFoundReportActivity extends AppCompatActivity {
    private static int REQUEST_IMAGE_CAPTURE = 1;
    private static int REQUEST_TAKE_PHOTO = 1;
    private String mCurrentPhotoPath;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_found_report);

        final EditText itemTitle = findViewById(R.id.item_title_editText);
        final EditText itemDescription = findViewById(R.id.found_report_description_editText);
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

        //camera part
        final Button imgButton = findViewById(R.id.img_button);
        imgButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }});

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
                    Date currentTime = Calendar.getInstance().getTime();

                    Toast.makeText(EnterFoundReportActivity.this, "submitted",
                            Toast.LENGTH_LONG).show();
                    Intent myIntent = new Intent(EnterFoundReportActivity.this, MainActivity.class);
                    startActivity(myIntent);

                    Report foundReport = new FoundReport(itemTitle.getText().toString(), String.valueOf(dropdown.getSelectedItem()).toString(),
                                                        itemDescription.getText().toString(),
                                                         currentTime.toString(), dropdownLocation.getSelectedItem().toString());
                    sendReportToFirebase(foundReport);
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView mImageView = findViewById(R.id.img_viewer);
            mImageView.setImageBitmap(imageBitmap);
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        SimpleDateFormat timeStamp = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String imageFileName = "JPEG_" + timeStamp.format(new Date()) + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void sendReportToFirebase(Report report) {
      mDatabase = FirebaseDatabase.getInstance().getReference();

      mDatabase.child("found").push().setValue(report);
    }
}
