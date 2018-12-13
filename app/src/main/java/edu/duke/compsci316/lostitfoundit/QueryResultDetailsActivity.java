package edu.duke.compsci316.lostitfoundit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class QueryResultDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_result_details);

        Intent intent = getIntent();
        FoundReport report = (FoundReport) intent.getSerializableExtra("report");

        ImageView image = findViewById(R.id.details_image_view);

        StorageReference ref = FirebaseStorage.getInstance()
                .getReference()
                .child(report.getImageName());

        Glide.with(this /* context */)
                .using(new FirebaseImageLoader())
                .load(ref)
                .placeholder(android.R.drawable.ic_dialog_info)
                .into(image);

        TextView name = findViewById(R.id.details_name);
        TextView type = findViewById(R.id.details_type);
        TextView location = findViewById(R.id.details_location);
        TextView description = findViewById(R.id.details_description);
        TextView contact = findViewById(R.id.details_contact);

        name.setText(report.getName());
        type.setText(report.getType());
        location.setText(report.getLocation());
        description.setText(report.getDescription());
        contact.setText("Contact: " + report.getContact());

    }
}
