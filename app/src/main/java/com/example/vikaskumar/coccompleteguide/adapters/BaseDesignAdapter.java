package com.example.vikaskumar.coccompleteguide.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vikaskumar.coccompleteguide.Models.BaseDesignModel;
import com.example.vikaskumar.coccompleteguide.R;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.malinskiy.superrecyclerview.swipe.BaseSwipeAdapter;

import java.util.List;

public class BaseDesignAdapter extends BaseSwipeAdapter<BaseSwipeAdapter.BaseSwipeableViewHolder> {

    private List<BaseDesignModel> baseDesignModelList;

    public BaseDesignAdapter(List<BaseDesignModel> baseDesignModelList){
        this.baseDesignModelList=baseDesignModelList;
    }
    public void setData(List<BaseDesignModel> baseDesignModelList) {
        this.baseDesignModelList=baseDesignModelList;
    }
    @Override
    public BaseSwipeableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BaseSwipeableViewHolder holder, int position, List<Object> payLoads) {
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MapViewHolder extends BaseSwipeableViewHolder {


        public MapViewHolder(View view) {
            super(view);

        }
    }



}

