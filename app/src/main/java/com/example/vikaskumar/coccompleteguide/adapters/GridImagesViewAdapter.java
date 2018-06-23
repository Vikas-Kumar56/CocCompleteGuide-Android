package com.example.vikaskumar.coccompleteguide.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vikaskumar.coccompleteguide.R;
import com.malinskiy.superrecyclerview.swipe.BaseSwipeAdapter;

import java.util.List;

public class GridImagesViewAdapter extends BaseSwipeAdapter<BaseSwipeAdapter.BaseSwipeableViewHolder> {

    private Context context;
    private List<Integer> gridImages;

    private GridImageClickListener gridImageClickListener;

    public GridImagesViewAdapter(Context context, List<Integer> gridImages, GridImageClickListener gridImageClickListener) {
        this.context = context;
        this.gridImages = gridImages;
        this.gridImageClickListener = gridImageClickListener;
    }
    @Override
    public BaseSwipeableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_images_view, parent, false);
        return new GridImageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BaseSwipeableViewHolder holder, int position) {
        GridImageViewHolder gridImageViewHolder = (GridImageViewHolder)holder;
        gridImageViewHolder.gridImage.setImageResource(gridImages.get(position));
    }

    @Override
    public int getItemCount() {
        return gridImages.size();
    }

    class GridImageViewHolder extends BaseSwipeableViewHolder {
        private ImageView gridImage;

        public GridImageViewHolder(final View parentView) {
            super(parentView);
            gridImage = parentView.findViewById(R.id.grid_image_view);
            gridImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gridImageClickListener.onImageClick(parentView);
                }
            });
        }
    }

    public interface GridImageClickListener {
        void onImageClick(View parentView);
    }
}
