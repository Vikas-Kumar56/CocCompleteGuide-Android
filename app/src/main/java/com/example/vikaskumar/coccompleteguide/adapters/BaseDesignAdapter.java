package com.example.vikaskumar.coccompleteguide.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
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
import com.example.vikaskumar.coccompleteguide.utility.Resources;
import com.google.gson.Gson;
import com.malinskiy.superrecyclerview.swipe.BaseSwipeAdapter;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.os.Build.VERSION_CODES.M;

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
        int endIndex=model.getBaseDescription().getName().length()>20?20:model.getBaseDescription().getName().length();
        mapName.setText(model.getBaseDescription().getName().substring(0,endIndex)+"...");
        favouriteicon=holder.itemView.findViewById(R.id.favouriteIcon);
        if(!model.getUrl().isEmpty()) {
            mapImage=holder.itemView.findViewById(R.id.mapPic);
            Glide.with(context)
                    .load(model.getUrl())
                    .centerCrop() // scale to fill the ImageView and crop any extra
                    .into(mapImage);
        }
        ArrayList<Integer> mapIds=getAllMapIds();;

            if(mapIds!=null && mapIds.contains(model.getMapId())){
                Log.e("maoids:",model.getMapId()+"");
                favouriteicon.setImageResource(R.drawable.red_heart);

            }
    }
    private ArrayList<Integer> getAllMapIds() {
        // SharedPreferences prefs = getContext().getSharedPreferences("MAP_IDS", Context.MODE_PRIVATE);
        SharedPreferences settings;
        List<Integer> mapIds;

        settings = context.getSharedPreferences(Resources.MAP_IDS,
                Context.MODE_PRIVATE);

        if (settings.contains(Resources.MAP_IDS_KEY)) {
            String jsonMapIds = settings.getString(Resources.MAP_IDS_KEY, null);
            Gson gson = new Gson();
            Integer[] favoriteItems = gson.fromJson(jsonMapIds,
                    Integer[].class);

            mapIds = Arrays.asList(favoriteItems);
            mapIds = new ArrayList<Integer>(mapIds);
        } else
            return null;

        return (ArrayList<Integer>) mapIds;

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
                itemClickListener.onItemClicked(parentView);
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
        parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClicked(parentView);
            }
        });
    }

    public interface ItemClickListener {
        void onDownloadClick(View itemView, View parentView);
        void onFavoriteClick(View itemView, View parentView);
        void onItemClicked(View itemView);
    }

}

