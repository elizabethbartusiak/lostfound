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

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import java.io.IOException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.storage.StorageMetadata;
import com.google.android.gms.tasks.OnFailureListener;
import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnSuccessListener;
import android.util.Log;
import android.graphics.Color;

public class EnterFoundReportActivity extends AppCompatActivity {
    private static int REQUEST_IMAGE_CAPTURE = 1;
    private static int REQUEST_TAKE_PHOTO = 1;
    private FirebaseStorage mFirebaseStorage;
    private String mCurrentPhotoPath;
    private String mFileName;
    private static final String TAG = "MyActivity";

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
//                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//                }
                dispatchTakePictureIntent();
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
                    uploadImgToFirebaseStorage();
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            ImageView mImageView = findViewById(R.id.img_viewer);
            mImageView.setColorFilter(Color.argb(255, 0, 255, 75));

        }
    }

    private void uploadImgToFirebaseStorage() {
        mFirebaseStorage = FirebaseStorage.getInstance();
        // Create a storage reference from our app
        StorageReference storageRef = mFirebaseStorage.getReference();

//        try {
//            createImageFile();
//        }
//        catch (IOException ex){
//            Log.d(TAG, "exception thrown on createImageFile(): " + ex);
//        }

        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        Log.d(TAG, "here is uri: " + contentUri);

        // Create file metadata including the content type
        StorageMetadata metadata = new StorageMetadata.Builder()
                .setContentType("image/jpg")
                .build();

        StorageReference imagesRef = storageRef.child(mFileName);

        UploadTask uploadTask = imagesRef.putFile(contentUri, metadata);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Log.d(TAG, "uh oh: upload task failed with exception: " + exception);
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.d(TAG, "upload to storage sucessful");
                 taskSnapshot.getMetadata();
            }
        });
    }

    //takes in a Bitmap, and produces an input stream to send to storage
    private ByteArrayInputStream generateStream(Bitmap bm) {
        int byteSize = bm.getRowBytes() * bm.getHeight();
        ByteBuffer byteBuffer = ByteBuffer.allocate(byteSize);
        bm.copyPixelsToBuffer(byteBuffer);
        byte[] byteArray = byteBuffer.array();
        ByteArrayInputStream bs = new ByteArrayInputStream(byteArray);
        return bs;
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        SimpleDateFormat timeStamp = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String imageFileName = "JPEG_" + timeStamp.format(new Date()) + "_";
        mFileName = imageFileName;
        Log.d(TAG, "filename is " + imageFileName);
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        Log.d(TAG, "path is " + mCurrentPhotoPath);
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.d(TAG, "error creating file with exception: " + ex);
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }
}
