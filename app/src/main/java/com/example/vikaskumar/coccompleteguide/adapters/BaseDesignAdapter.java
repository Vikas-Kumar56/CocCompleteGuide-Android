package com.example.vikaskumar.coccompleteguide.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vikaskumar.coccompleteguide.Models.BaseDesignModel;
import com.example.vikaskumar.coccompleteguide.R;

import java.util.List;

public class BaseDesignAdapter extends RecyclerView.Adapter<BaseDesignAdapter.MapViewHolder> {

    private List<BaseDesignModel> baseDesignModelList;
    class MapViewHolder extends RecyclerView.ViewHolder {


        public MapViewHolder(View view) {
            super(view);

        }
    }
   public BaseDesignAdapter(List<BaseDesignModel> baseDesignModelList){
        this.baseDesignModelList=baseDesignModelList;
   }
    @Override
    public MapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.base_design_row, parent, false);

        return new MapViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MapViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return baseDesignModelList.size();
    }


}

