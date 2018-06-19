package com.example.vikaskumar.coccompleteguide.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.vikaskumar.coccompleteguide.Models.BaseDesignModel;
import com.example.vikaskumar.coccompleteguide.R;
import com.example.vikaskumar.coccompleteguide.adapters.BaseDesignAdapter;
import com.example.vikaskumar.coccompleteguide.utility.Resources;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 */
public class WarBase extends Fragment {

   private RecyclerView recyclerView;
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
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);

        BaseDesignAdapter adpter = new BaseDesignAdapter(baseDesignModelList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adpter);
    }

    private void initData() {
        int id=getArguments().getInt("Id");
        switch (id){
            case Resources.warBase:
                break;
            case Resources.farmingBase:
                break;
             case  Resources.hybridBase:
                 break;
            case Resources.tropiesBase:

        }
    }

}
