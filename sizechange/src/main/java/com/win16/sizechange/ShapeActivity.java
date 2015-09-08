package com.win16.sizechange;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.transition.AutoTransition;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;

import java.security.acl.Group;

import cimi.com.easeinterpolator.EaseBackInOutInterpolator;


/**
 * Created by Rex on 9/3/2015.
 * powered by Win16.com
 */
public class ShapeActivity extends Activity {

    private View shape;
    private ViewGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shape_layout);

        shape = findViewById(R.id.shape);
        group = (ViewGroup) findViewById(R.id.root);


    }

    public void clickEvent(View view) {
        ChangeBounds transition = new ChangeBounds();
        transition.setDuration(2000);
        transition.setInterpolator(new EaseBackInOutInterpolator());
        TransitionManager.beginDelayedTransition(group,transition);
        setViewSize(400);
    }

    private void setViewSize(int x) {
        ViewGroup.LayoutParams params = shape.getLayoutParams();
        params.height = x;
        params.width = x;
        shape.setLayoutParams(params);
    }
}
