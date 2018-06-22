package com.example.vikaskumar.coccompleteguide.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vikaskumar.coccompleteguide.R;

import java.util.List;

public class GridViewAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<String> gridTexts;
    private List<Integer> gridImages;

    private ItemClickListener itemClickListener;

    public GridViewAdapter(Context context, List<Integer> gridImages, List<String> gridTexts, ItemClickListener itemClickListener) {
        this.context = context;
        this.gridImages = gridImages;
        this.gridTexts = gridTexts;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_layout, parent, false);

        GridViewItemHolder gridViewItemHolder = new GridViewItemHolder(view);
        return gridViewItemHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GridViewItemHolder viewHolder = (GridViewItemHolder) holder;
        viewHolder.gridImage.setImageResource(gridImages.get(position));
        viewHolder.gridText.setText(gridTexts.get(position));
    }

    @Override
    public int getItemCount() {
        return gridImages.size();
    }

    public class GridViewItemHolder extends RecyclerView.ViewHolder {
        private ImageView gridImage;
        private TextView gridText;

        public GridViewItemHolder(final View itemView) {
            super(itemView);
            initViews(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(view);
                }
            });
        }

        private void initViews(View itemView) {
            gridImage = itemView.findViewById(R.id.grid_image);
            gridText = itemView.findViewById(R.id.grid_text);

        }
    }

    public interface ItemClickListener {
        void onItemClick(View parentView);
    }
}
