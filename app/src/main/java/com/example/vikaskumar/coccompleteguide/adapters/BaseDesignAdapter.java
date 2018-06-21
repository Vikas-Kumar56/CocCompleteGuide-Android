package com.example.vikaskumar.coccompleteguide.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.vikaskumar.coccompleteguide.Models.BaseDesignModel;
import com.example.vikaskumar.coccompleteguide.R;
import com.example.vikaskumar.coccompleteguide.utility.ObjectSerializer;
import com.malinskiy.superrecyclerview.swipe.BaseSwipeAdapter;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BaseDesignAdapter extends BaseSwipeAdapter<BaseSwipeAdapter.BaseSwipeableViewHolder> {

    private List<BaseDesignModel> baseDesignModelList;
    private ImageView mapImage;
    private ImageButton downloadMap, favouriteicon;
    private TextView mapName;
    private Context context;

    private ItemClickListener itemClickListener;

    public BaseDesignAdapter(List<BaseDesignModel> baseDesignModelList, Context context, ItemClickListener itemClickListener) {
        this.baseDesignModelList = baseDesignModelList;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    public void setData(List<BaseDesignModel> baseDesignModelList) {
        this.baseDesignModelList = baseDesignModelList;
    }

    @Override
    public BaseSwipeableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.base_design_row, parent, false);

        return new MapViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BaseSwipeableViewHolder holder, int position, List<Object> payLoads) {
        BaseDesignModel model = baseDesignModelList.get(position);
        Log.d("inside", model.getUrl());
        Glide.with(context)
                .load(model.getUrl())
                .centerCrop() // scale to fill the ImageView and crop any extra
                .into(mapImage);
        ArrayList<Integer> mapIds;
        SharedPreferences prefs = context.getSharedPreferences("MAP_IDS", Context.MODE_PRIVATE);
        try {
            mapIds = (ArrayList<Integer>) ObjectSerializer.deserialize(prefs.getString("mapIds", ObjectSerializer.serialize(new ArrayList<Integer>())));
            if(mapIds.contains(baseDesignModelList.get(position).getMapId())){
                favouriteicon.setImageResource(R.drawable.red_heart);

            }

        }catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return baseDesignModelList.size();
    }

    class MapViewHolder extends BaseSwipeableViewHolder {


        public MapViewHolder(View view) {
            super(view);
            mapImage = (ImageView) view.findViewById(R.id.mapPic);
            downloadMap = (ImageButton) view.findViewById(R.id.downloadMap);
            favouriteicon = (ImageButton) view.findViewById(R.id.favouriteIcon);
            mapName = (TextView) view.findViewById(R.id.mapName);
            setEventListener(view);

        }
    }

    private void setEventListener(final View parentView) {
        mapImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        downloadMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onDownloadClick(view, parentView);
            }
        });

        favouriteicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onFavoriteClick(view, parentView);
            }
        });
    }

    public interface ItemClickListener {
        void onDownloadClick(View itemView, View parentView);
        void onFavoriteClick(View itemView, View parentView);
    }

}

