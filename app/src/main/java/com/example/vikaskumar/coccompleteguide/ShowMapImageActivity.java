package com.example.vikaskumar.coccompleteguide;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.vikaskumar.coccompleteguide.utility.Resources;

import uk.co.senab.photoview.PhotoViewAttacher;

public class ShowMapImageActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView toolBarText;
    private ImageView mapPic;
    private PhotoViewAttacher viewAttacher;
    private String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_map_image);
        initData();
        initView();

    }

    private void initData() {
        Bundle extras = getIntent().getExtras();
         imageUrl = extras.getString("DATA");
    }

    private void initView(){
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
        toolBarText.setText(Resources.showMapImageToolBarText);
        mapPic=(ImageView)findViewById(R.id.mapPic);
        viewAttacher=new PhotoViewAttacher(mapPic);
        viewAttacher.getDisplayMatrix();
        Log.e("url",imageUrl);
        Glide.with(getApplicationContext())
                .load(imageUrl)
                .fitCenter()
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

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, ShowMapImageActivity.class);
    }
}

