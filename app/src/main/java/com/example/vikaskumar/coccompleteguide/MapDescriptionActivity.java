package com.example.vikaskumar.coccompleteguide;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.example.vikaskumar.coccompleteguide.Models.BaseDesignModel;
import com.example.vikaskumar.coccompleteguide.Models.DescriptionModel;
import com.example.vikaskumar.coccompleteguide.utility.Resources;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import uk.co.senab.photoview.PhotoViewAttacher;

public class MapDescriptionActivity extends AppCompatActivity {

    private BaseDesignModel mapDescriptionModel=new BaseDesignModel();

    private Toolbar toolbar;
    private TextView toolBarText;
    private TextView mapName,mapDesc,antiTroopies,specialFeature;
    private ImageView mapPic;
    private PhotoViewAttacher viewAttacher;
    private ImageButton downloadMap, favouriteicon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_description);
        initView();
        initData();

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        toolBarText = (TextView) findViewById(R.id.toolbarText);
        toolBarText.setText(Resources.homeBaseDesignToolBarText);

        mapName=(TextView)findViewById(R.id.mapName);
        mapDesc=(TextView)findViewById(R.id.mapDesc);
        antiTroopies=(TextView)findViewById(R.id.antiTroopies);
        mapPic=(ImageView)findViewById(R.id.mapPic);
        specialFeature=(TextView)findViewById(R.id.specialfeature);
        favouriteicon=(ImageButton)findViewById(R.id.favouriteIcon);
        downloadMap=(ImageButton)findViewById(R.id.downloadMap);
        ArrayList<Integer> mapIds=getAllMapIds();;

        if(mapIds!=null && mapIds.contains(mapDescriptionModel.getMapId())){

            favouriteicon.setImageResource(R.drawable.red_heart);

        }

        favouriteicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFavoriteClick(view);
            }
        });

        downloadMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDownloadClick(view);
            }
        });
    }
    private void initData(){
        // get data passed from calling activity
        Bundle extras = getIntent().getExtras();
        String model = extras.getString("DATA");
        Gson gson=new Gson();
        mapDescriptionModel=gson.fromJson(model,BaseDesignModel.class);
        viewAttacher=new PhotoViewAttacher(mapPic);
        viewAttacher.getDisplayMatrix();

        mapName.setText(mapDescriptionModel.getBaseDescription().getName());
        mapDesc.setText(mapDescriptionModel.getBaseDescription().getDescription());
        specialFeature.setText(mapDescriptionModel.getBaseDescription().getSpecialFeature());
        String antiTroop="";
        for(String str : mapDescriptionModel.getBaseDescription().getAntiTroopies()){
            if(!str.equals(mapDescriptionModel.getBaseDescription().getAntiTroopies().get(mapDescriptionModel.getBaseDescription().getAntiTroopies().size()-1)))
                antiTroop+=str+",";
            else
                antiTroop+=str;
        }
        antiTroopies.setText(antiTroop);
        Glide.with(this)
                .load(mapDescriptionModel.getUrl())
                .fitCenter()
               // .centerCrop()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        viewAttacher.update();
                        return false;
                    }
                })
                .into(mapPic);

    }

    public void onDownloadClick(View itemView) {
        //downoad map image on disk
        Log.e("inside download", "a");
        Toast.makeText(getApplicationContext(), "downloading started ...", Toast.LENGTH_LONG).show();
        final int ImageId = mapDescriptionModel.getMapId();
        String url = mapDescriptionModel.getUrl();
        Glide.with(getApplicationContext())
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

            MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), filename.getAbsolutePath(), filename.getName(), filename.getName());

            Toast.makeText(getApplicationContext(), "File is Saved in  " + filename, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void onFavoriteClick(View itemView) {
        ArrayList<Integer> mapIds;
        ImageButton favourite = (ImageButton) itemView;
        mapIds = getAllMapIds();
        if (mapIds != null) {
            Log.e("Inside war base", "a:" + mapIds.size());
        }
        if (mapIds == null || !mapIds.contains(mapDescriptionModel.getMapId())) {
            if (mapIds == null)
                mapIds = new ArrayList<>();
            mapIds.add(mapDescriptionModel.getMapId());
            storeMapIdInPrefrencces(mapIds);
            favourite.setImageResource(R.drawable.red_heart);
            Log.e("Inside war base", "b");
        } else {
            mapIds.remove((Object) mapDescriptionModel.getMapId());
            storeMapIdInPrefrencces(mapIds);
            favourite.setImageResource(R.drawable.blank_heart);
            Log.e("Inside war base", "c");
        }
    }
    private ArrayList<Integer> getAllMapIds() {
        // SharedPreferences prefs = getContext().getSharedPreferences("MAP_IDS", Context.MODE_PRIVATE);
        SharedPreferences settings;
        List<Integer> mapIds;

        settings = getApplicationContext().getSharedPreferences(Resources.MAP_IDS,
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

        settings = getApplicationContext().getSharedPreferences(Resources.MAP_IDS,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonMapIds = gson.toJson(mapIds);

        editor.putString(Resources.MAP_IDS_KEY, jsonMapIds);

        editor.commit();

    }
    public static Intent getCallingIntent(Context context) {
        return new Intent(context, MapDescriptionActivity.class);
    }
}
