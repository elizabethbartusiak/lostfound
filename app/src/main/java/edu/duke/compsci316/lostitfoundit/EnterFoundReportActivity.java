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
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.provider.MediaStore;
import android.os.Environment;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import java.io.IOException;

public class EnterFoundReportActivity extends AppCompatActivity {
    private static int REQUEST_IMAGE_CAPTURE = 1;
    private static int REQUEST_TAKE_PHOTO = 1;

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
                    Toast.makeText(EnterFoundReportActivity.this, "Successfully submitted",
                            Toast.LENGTH_LONG).show();
                    Intent myIntent = new Intent(EnterFoundReportActivity.this, MainActivity.class);
                    startActivity(myIntent);
                }

            }
        });


    }

    public void dispatchTakePictureIntent() {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        // Ensure that there's a camera activity to handle the intent
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            // Create the File where the photo should go
//            File photoFile = null;
//            try {
//                photoFile = createImageFile();
//            } catch (IOException ex) {
//                // Error occurred while creating the File
//                return;
//            }
//            // Continue only if the File was successfully created
//            if (photoFile != null) {
//                Uri photoURI = FileProvider.getUriForFile(this,
//                        "com.example.android.fileprovider",
//                        photoFile);
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
//            }
//        }
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

    String mCurrentPhotoPath;

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
}
