package com.example.vikaskumar.coccompleteguide.Fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import android.widget.PopupWindow;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.vikaskumar.coccompleteguide.Models.BaseDesignModel;
import com.example.vikaskumar.coccompleteguide.Models.DescriptionModel;
import com.example.vikaskumar.coccompleteguide.R;
import com.example.vikaskumar.coccompleteguide.adapters.BaseDesignAdapter;
import com.example.vikaskumar.coccompleteguide.api.RestClient;
import com.example.vikaskumar.coccompleteguide.utility.Navigator;
import com.example.vikaskumar.coccompleteguide.utility.ObjectSerializer;
import com.example.vikaskumar.coccompleteguide.utility.Resources;
import com.google.gson.Gson;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class WarBase extends Fragment implements BaseDesignAdapter.ItemClickListener{

    private SuperRecyclerView recyclerView;
    private BaseDesignAdapter adpter;
    int page = 1;
    private List<BaseDesignModel> baseDesignModelList = new ArrayList<BaseDesignModel>();
    private PopupMenu popupMenu;
    private FloatingActionButton townhallPopMenuShow;
    public static int townhallid=10;
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
        initData(10);
        return view;
    }


    private void initView(View view) {
        townhallPopMenuShow = (FloatingActionButton) view.findViewById(R.id.townhallSelection);
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
                Log.e("WarBase", "Inside more asked" + page);
                initData(townhallid);
            }
        }, 1);
    }

    public void initData(int townhallId) {
        int id = getArguments().getInt("Id");
        switch (id) {
            case Resources.warBase:
                downlaodWarBase(townhallId, Resources.warBase);
                break;
            case Resources.farmingBase:
                downlaodWarBase(townhallId, Resources.farmingBase);
                break;
            case Resources.hybridBase:
                downlaodWarBase(townhallId, Resources.hybridBase);
                break;
            case Resources.tropiesBase:
                downlaodWarBase(townhallId, Resources.tropiesBase);

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
                try {
                    baseDesignModelList.addAll(ParseBaseDesignData(response));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                adpter.setData(baseDesignModelList);
                adpter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<BaseDesignModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }

    private List<BaseDesignModel> ParseBaseDesignData(Response<List<BaseDesignModel>> response) throws JSONException {
        List<BaseDesignModel> newBaseDesignData = new ArrayList<BaseDesignModel>();
        for (BaseDesignModel obj : response.body()) {

            // parse description JSON into DescriptionModel
            DescriptionModel descriptionModel = new DescriptionModel();
            JSONObject descObject = new JSONObject(obj.getDescription());
            descriptionModel.setName(descObject.getString("Name"));
            descriptionModel.setDescription(descObject.getString("Description"));
            descriptionModel.setSpecialFeature(descObject.getString("SpecialFeature"));
            descriptionModel.setVideoUrl(descObject.getString("VideoUrl"));
            JSONArray array = descObject.getJSONArray("AntiTroopies");
            ArrayList<String> antiTroopies = new ArrayList<>();
            for (int value = 0; value < array.length(); value++) {
                antiTroopies.add(array.getString(value));
            }
            descriptionModel.setAntiTroopies(antiTroopies);
            obj.setBaseDescription(descriptionModel);
            newBaseDesignData.add(obj);
            Log.e("desxc", obj.getBaseDescription().getName());

        }
        return newBaseDesignData;
    }

    @Override
    public void onDownloadClick(View itemView, final View parentView) {
        //downoad map image on disk
        Log.e("inside download", "a");
        Toast.makeText(getContext(), "downloading started ...", Toast.LENGTH_LONG).show();
        int position = recyclerView.getRecyclerView().getChildLayoutPosition(parentView);
        final int ImageId = baseDesignModelList.get(position).getMapId();
        String url = baseDesignModelList.get(position).getUrl();
        Glide.with(getContext())
                .load(url)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Log.e("inside download", "a" + resource);

                        saveImage(resource, ImageId);
                    }
                });

    }

    private void saveImage(Bitmap bitMapImg, int imageId) {
        Log.e("inside download", "ab");

        File filename;
        try {
            String path = Environment.getExternalStorageDirectory().toString();
            int random = (new Random()).nextInt(120000) + 100000;
            //new File(path + "/BaseMaps").mkdirs();
            filename = new File(path + "/Download/map_" + imageId + "_" + random + ".jpg");

            FileOutputStream out = new FileOutputStream(filename);

            bitMapImg.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

            MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), filename.getAbsolutePath(), filename.getName(), filename.getName());

            Toast.makeText(getContext(), "File is Saved in  " + filename, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onFavoriteClick(View itemView, View parentView) {
        int position = recyclerView.getRecyclerView().getChildLayoutPosition(parentView);

        //get mapid in shared prefrences
        ArrayList<Integer> mapIds;
        ImageButton favourite = (ImageButton) itemView;
        mapIds = getAllMapIds();
        if (mapIds != null) {
            Log.e("Inside war base", "a:" + mapIds.size());
        }
        if (mapIds == null || !mapIds.contains(baseDesignModelList.get(position).getMapId())) {
            if (mapIds == null)
                mapIds = new ArrayList<>();
            mapIds.add(baseDesignModelList.get(position).getMapId());
            storeMapIdInPrefrencces(mapIds);
            favourite.setImageResource(R.drawable.red_heart);
            Log.e("Inside war base", "b");
        } else {
            mapIds.remove((Object) baseDesignModelList.get(position).getMapId());
            storeMapIdInPrefrencces(mapIds);
            favourite.setImageResource(R.drawable.blank_heart);
            Log.e("Inside war base", "c");
        }
    }

    @Override
    public void onItemClicked(View itemView) {
        int position = recyclerView.getRecyclerView().getChildLayoutPosition(itemView);
        //navigate to Map description activity
        Gson gson = new Gson();
        String mapDescriptionModel=gson.toJson(baseDesignModelList.get(position));
        Log.e("map data",mapDescriptionModel);
        Navigator.getInstance().navigateToMapDescriptionActivity(getActivity(),mapDescriptionModel);
    }

    private ArrayList<Integer> getAllMapIds() {
        // SharedPreferences prefs = getContext().getSharedPreferences("MAP_IDS", Context.MODE_PRIVATE);
        SharedPreferences settings;
        List<Integer> mapIds;

        settings = getContext().getSharedPreferences(Resources.MAP_IDS,
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

    private void storeMapIdInPrefrencces(ArrayList<Integer> mapIds) {
        // mapIds.add(mapid);
        if (mapIds == null)
            mapIds = new ArrayList<Integer>();

        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = getActivity().getSharedPreferences(Resources.MAP_IDS,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonMapIds = gson.toJson(mapIds);

        editor.putString(Resources.MAP_IDS_KEY, jsonMapIds);

        editor.commit();

    }

    public void onRefresh(){
        baseDesignModelList.clear();
        page=1;
        if(recyclerView!=null) {
            recyclerView.setRefreshing(true);
        }
        initData(townhallid);
    }

}
