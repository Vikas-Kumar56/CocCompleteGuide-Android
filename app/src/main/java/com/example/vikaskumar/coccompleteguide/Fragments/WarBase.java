package com.example.vikaskumar.coccompleteguide.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;

import com.example.vikaskumar.coccompleteguide.Models.BaseDesignModel;
import com.example.vikaskumar.coccompleteguide.Models.DescriptionModel;
import com.example.vikaskumar.coccompleteguide.R;
import com.example.vikaskumar.coccompleteguide.adapters.BaseDesignAdapter;
import com.example.vikaskumar.coccompleteguide.api.RestClient;
import com.example.vikaskumar.coccompleteguide.utility.ObjectSerializer;
import com.example.vikaskumar.coccompleteguide.utility.Resources;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class WarBase extends Fragment implements BaseDesignAdapter.ItemClickListener {

    private SuperRecyclerView recyclerView;
    private BaseDesignAdapter adpter;
    int page = 1;
    private List<BaseDesignModel> baseDesignModelList = new ArrayList<BaseDesignModel>();

    public WarBase() {
        // Required empty public constructor
    }

    public static WarBase getInstance(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("Id", id);
        WarBase warBase = new WarBase();
        warBase.setArguments(bundle);
        return warBase;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_war_base, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        recyclerView = (SuperRecyclerView) view.findViewById(R.id.base_design);

        adpter = new BaseDesignAdapter(baseDesignModelList, getContext(), this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adpter);

        recyclerView.setupMoreListener(new OnMoreListener() {
            @Override
            public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
                page++;
                initData();
            }
        }, 1);
    }

    private void initData() {
        int id = getArguments().getInt("Id");
        switch (id) {
            case Resources.warBase:
                downlaodWarBase(8, Resources.warBase);
                break;
            case Resources.farmingBase:
                downlaodWarBase(10, Resources.warBase);
                break;
            case Resources.hybridBase:
                downlaodWarBase(8, Resources.warBase);
                break;
            case Resources.tropiesBase:
                downlaodWarBase(8, Resources.warBase);

        }
    }

    private void downlaodWarBase(int townHallId, int typeId) {
        Log.d("e", "***********");
        Call<List<BaseDesignModel>> dataCall = RestClient.get().getAllBaseByTownhallIdAndtypeId(townHallId, typeId, page);
        dataCall.enqueue(new Callback<List<BaseDesignModel>>() {
            @Override
            public void onResponse(Call<List<BaseDesignModel>> call, Response<List<BaseDesignModel>> response) {
                Log.d("data:*********", response.body().toString());
                //Parse resposnse and update the list
                baseDesignModelList.addAll(ParseBaseDesignData(response));
                adpter.setData(baseDesignModelList);
                adpter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<BaseDesignModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }

    private List<BaseDesignModel> ParseBaseDesignData(Response<List<BaseDesignModel>> response) {
        List<BaseDesignModel> newBaseDesignData = new ArrayList<BaseDesignModel>();
        for (BaseDesignModel obj : response.body()) {
            newBaseDesignData.add(obj);
            // Log.d("desc+++++++++++++++++",obj.getDescription().getName());

        }
        return newBaseDesignData;
    }

    @Override
    public void onDownloadClick(View itemView,View parentView) {



    }

    @Override
    public void onFavoriteClick(View itemView,View parentView) {
        int position = recyclerView.getRecyclerView().getChildLayoutPosition(parentView);

        //get mapid in shared prefrences
        ArrayList<Integer> mapIds;
        SharedPreferences prefs = getContext().getSharedPreferences("MAP_IDS", Context.MODE_PRIVATE);
        ImageButton favourite=(ImageButton)itemView;
        try {
            mapIds = (ArrayList<Integer>) ObjectSerializer.deserialize(prefs.getString("mapIds", ObjectSerializer.serialize(new ArrayList<Integer>())));
            Log.e("MSG", "Inside add" + mapIds + mapIds.contains(baseDesignModelList.get(position).getMapId()));
            if(mapIds.isEmpty() || !mapIds.contains(baseDesignModelList.get(position).getMapId())){
                //mapIds.add()
                Log.e("MSG", "Inside add");
                favourite.setImageResource(R.drawable.red_heart);
                mapIds.add(baseDesignModelList.get(position).getMapId());

            }
            else{
                Log.e("MSG", "Inside remove");
                favourite.setImageResource(R.drawable.blank_heart);
                mapIds.remove(baseDesignModelList.get(position).getMapId());

            }
            SharedPreferences.Editor editor = prefs.edit();
            try {
                editor.putString("mapIds", ObjectSerializer.serialize(mapIds));
            } catch (IOException e) {
                e.printStackTrace();
            }
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
