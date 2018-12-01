package edu.duke.compsci316.lostitfoundit;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

/**
 * Created by Elizabeth on 11/8/2018.
 */

public class QueryResultAdapter extends RecyclerView.Adapter<QueryResultAdapter.ViewHolder>{

    private Context mContext;
    private String[] mTitles; // item titles
    private String[] mLocations; // item locations

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

    public QueryResultAdapter(final Context context, String[] titles, String[] locations){
        mContext = context;
        mTitles = titles;
        mLocations = locations;
    }

    @Override
    public int getItemCount(){
        return mTitles.length;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = mInflater.inflate(R.layout.query_result_holder, parent, false);
        final ViewHolder queryResultHolder = new ViewHolder(row);

        queryResultHolder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openItemDetails(mTitles[queryResultHolder.getAdapterPosition()],
                        mLocations[queryResultHolder.getAdapterPosition()]);
            }
        });

        return queryResultHolder;
    }

    private void openItemDetails(String albumName, String artistName) {
        Log.d(TAG, "TODO: implement item details page");
        Toast.makeText(mContext, "TODO: show deets", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(mContext, AlbumActivity.class);
//        intent.putExtra("album_name_key", albumName);
//        intent.putExtra("artist_name_key", artistName);
//        mContext.startActivity(intent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Drawable itemImage = mContext.getDrawable(android.R.drawable.ic_dialog_info);
//        String albumName = mTitles[position].toLowerCase().replaceAll("\\W+",
//                "");
//        int drawableId = mContext.getResources().getIdentifier(albumName,
//                "drawable", mContext.getPackageName());
//        Drawable albumArtwork = mContext.getDrawable(drawableId);

        holder.mImageView.setImageDrawable(itemImage);
        holder.mItemName.setText(mTitles[position]);
        holder.mLocation.setText(mLocations[position]);
    }

}

