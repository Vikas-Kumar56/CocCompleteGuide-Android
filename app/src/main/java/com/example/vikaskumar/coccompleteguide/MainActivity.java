package com.example.vikaskumar.coccompleteguide;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.vikaskumar.coccompleteguide.utility.Navigator;
import com.example.vikaskumar.coccompleteguide.utility.Resources;


public class MainActivity extends AppCompatActivity {

    ImageView homeBase;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeBase=(ImageView)findViewById(R.id.homeBase);
        context=this;
        // make imageview clckable to redirect to new activity
        homeBase.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Navigator.getInstance().navigateToHomeBaseActivity(context, Resources.homeBase+"");
            }
        });
    }
    public static Intent getCallingIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    public void onBackPressed() {
        this.onBackPressed();
    }
}
