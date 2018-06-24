package com.example.vikaskumar.coccompleteguide.utility;

import android.content.Context;
import android.content.Intent;

import com.example.vikaskumar.coccompleteguide.ArmyActivity;
import com.example.vikaskumar.coccompleteguide.BeginnersGuideActivity;
import com.example.vikaskumar.coccompleteguide.CompleteGuideDisplayActivity;
import com.example.vikaskumar.coccompleteguide.DefensesActivity;
import com.example.vikaskumar.coccompleteguide.HeroesActivity;
import com.example.vikaskumar.coccompleteguide.HomeBaseActivity;
import com.example.vikaskumar.coccompleteguide.HomeBaseDesignActivity;
import com.example.vikaskumar.coccompleteguide.MapDescriptionActivity;

public class Navigator {

    private static Navigator instance;

    private Navigator() {
    }

    public static Navigator getInstance() {
        if (instance == null) {
            synchronized (Navigator.class) {
                if (instance == null) {
                    instance = new Navigator();
                }
            }
        }
        return instance;
    }

    public void navigateToHomeBaseActivity(Context context, String data) {
        Intent subAct = HomeBaseActivity.getCallingIntent(context);
        // This is the data where you want to send to target activity.
        subAct.putExtra("DATA", data);
        context.startActivity(subAct);
    }

    public void navigateToHomeBaseDesignActivity(Context context, String data) {
        Intent subAct = HomeBaseDesignActivity.getCallingIntent(context);
        // This is the data where you want to send to target activity.
        subAct.putExtra("DATA", data);
        context.startActivity(subAct);
    }

    public void navigateToBeginnersGuideActivity(Context context, String data) {
        Intent subAct = BeginnersGuideActivity.getCallingIntent(context);
        // This is the data where you want to send to target activity.
        subAct.putExtra("DATA", data);

        context.startActivity(subAct);
    }

    public void navigateToHeroesActivity(Context context, String data) {
        Intent subAct = HeroesActivity.getCallingIntent(context);
        // This is the data where you want to send to target activity.
        subAct.putExtra("DATA", data);
        context.startActivity(subAct);
    }

    public void navigateToCompleteGuideActivity(Context context, String source, String description) {
        Intent subAct = CompleteGuideDisplayActivity.getCallingIntent(context);
        // This is the data where you want to send to target activity.
        subAct.putExtra(Resources.HEADER_TITLE_KEY, source);
        subAct.putExtra(Resources.SOURCE_DESCRIPTION_KEY, description);
        context.startActivity(subAct);
    }

    public void navigateToArmyActivity(Context context, String data) {
        Intent subAct = ArmyActivity.getCallingIntent(context);
        // This is the data where you want to send to target activity.
        subAct.putExtra("DATA", data);
        context.startActivity(subAct);
    }

    public void navigateToDefensesActivity(Context context, String tootlbarTitle, String headerTitle) {
        Intent subAct = DefensesActivity.getCallingIntent(context);
        // This is the data where you want to send to target activity.
        subAct.putExtra(Resources.TOOLBAR_TITLE_KEY, tootlbarTitle);
        subAct.putExtra(Resources.HEADER_TITLE_KEY, headerTitle);
        context.startActivity(subAct);
    }

    public void navigateToClanWarsActivity(Context context, String tootlbarTitle, String headerTitle) {
        Intent subAct = DefensesActivity.getCallingIntent(context);
        // This is the data where you want to send to target activity.
        subAct.putExtra(Resources.TOOLBAR_TITLE_KEY, tootlbarTitle);
        subAct.putExtra(Resources.HEADER_TITLE_KEY, headerTitle);
        context.startActivity(subAct);
    }

    public void navigateToMapDescriptionActivity(Context context, String data) {
        Intent subAct = MapDescriptionActivity.getCallingIntent(context);
        // This is the data where you want to send to target activity.
        subAct.putExtra("DATA", data);
        context.startActivity(subAct);
    }
}