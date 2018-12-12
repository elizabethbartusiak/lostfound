package edu.duke.compsci316.lostitfoundit;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static android.content.ContentValues.TAG;

/**
 * Created by Elizabeth on 11/8/2018.
 */

public class QueryResultAdapter extends FirebaseRecyclerAdapter<FoundReport, QueryResultAdapter.ViewHolder> {

    private Context mContext;

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mLinearLayout;
        private ImageView mImageView;
        private TextView mItemName;
        private TextView mLocation;

        public ViewHolder(View itemView) {
            super(itemView);

            this.mLinearLayout = itemView.findViewById(R.id.query_result_holder_linear_layout);
            this.mImageView = itemView.findViewById(R.id.query_result_image_view);
            this.mItemName = itemView.findViewById(R.id.query_result_name_text_view);
            this.mLocation = itemView.findViewById(R.id.query_result_location_text_view);
        }
    }

    public QueryResultAdapter(final Context context,
                              FirebaseRecyclerOptions<FoundReport> options){
        super(options);
        mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = mInflater.inflate(R.layout.query_result_holder, parent, false);
        final ViewHolder queryResultHolder = new ViewHolder(row);

        queryResultHolder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openItemDetails(getItem(queryResultHolder.getAdapterPosition()));
            }
        });

        return queryResultHolder;
    }

    private void openItemDetails(FoundReport report) {
//        Log.d(TAG, "name " + report.getName() + " descrip: " + report.getDescription());
        Intent intent = new Intent(mContext, QueryResultDetailsActivity.class);
        intent.putExtra("report", report);
        mContext.startActivity(intent);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull FoundReport model) {
        Drawable itemImage = mContext.getDrawable(android.R.drawable.ic_dialog_info);

        holder.mImageView.setImageDrawable(itemImage);
        holder.mItemName.setText(model.getName());
        holder.mLocation.setText(model.getLocation());
//
//        DatabaseReference ref = FirebaseDatabase.getInstance()
//                .getReference()
//                .child("found")
//                .child(model.getType())
//                .child(model.getLocation())
//                .child(model.get);
        StorageReference ref = FirebaseStorage.getInstance()
                .getReference()
                .child(model.getImageName());

        Toast.makeText(mContext, ref.toString(), Toast.LENGTH_SHORT).show();

        Glide.with(mContext /* context */)
                .using(new FirebaseImageLoader())
                .load(ref)
                .placeholder(android.R.drawable.ic_dialog_info)
                .into(holder.mImageView);


    }

}

