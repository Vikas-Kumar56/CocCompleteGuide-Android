package com.example.vikaskumar.coccompleteguide;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.vikaskumar.coccompleteguide.Utility.Navigator;
import com.example.vikaskumar.coccompleteguide.Utility.Resources;

public class HomeBaseActivity extends AppCompatActivity {

    private int homeBaseId;
    private ImageView baseDesignPicture;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_base);

        // get data passed from calling activity
        Bundle extras = getIntent().getExtras();
        homeBaseId = Integer.parseInt(extras.getString("DATA"));
        baseDesignPicture=(ImageView)findViewById(R.id.baseDesignPicture);
        baseDesignPicture.setOnClickListener(clickListener);
        context=this;
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {

        public void onClick(View v) {
              if(v.getId()==R.id.baseDesignPicture){
                  Navigator.getInstance().navigateToHomeBaseDesignActivity(context, Resources.homeBaseDesign+"");
              }
        }
    };

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, HomeBaseActivity.class);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }


}
