package com.example.vikaskumar.coccompleteguide.Fragments;


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

import com.example.vikaskumar.coccompleteguide.Models.BaseDesignModel;
import com.example.vikaskumar.coccompleteguide.R;
import com.example.vikaskumar.coccompleteguide.adapters.BaseDesignAdapter;
import com.example.vikaskumar.coccompleteguide.api.RestClient;
import com.example.vikaskumar.coccompleteguide.utility.Resources;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class WarBase extends Fragment {

   private SuperRecyclerView recyclerView;
    private BaseDesignAdapter adpter;
    int page=1;
   private List<BaseDesignModel> baseDesignModelList=new ArrayList<BaseDesignModel>();
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
        View view= inflater.inflate(R.layout.fragment_war_base, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        recyclerView = (SuperRecyclerView)view.findViewById(R.id.base_design);

        adpter = new BaseDesignAdapter(baseDesignModelList);
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
        int id=getArguments().getInt("Id");
        switch (id){
            case Resources.warBase:
                downlaodWarBase(10);
                break;
            case Resources.farmingBase:
                break;
             case  Resources.hybridBase:
                 break;
            case Resources.tropiesBase:

        }
    }

    private void downlaodWarBase(int townHallId) {
        Log.d("e","***********");
        Call<List<BaseDesignModel>> dataCall =  RestClient.get().getAllBaseByTownhallIdAndtypeId(townHallId,1,1);
        dataCall.enqueue(new Callback<List<BaseDesignModel>>() {
            @Override
            public void onResponse(Call<List<BaseDesignModel>> call, Response<List<BaseDesignModel>> response) {
              Log.d("data:*********",response.body().toString());
              //Parse resposnse and update the list
              adpter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<BaseDesignModel>> call, Throwable t) {
            }
        });


    }

}
