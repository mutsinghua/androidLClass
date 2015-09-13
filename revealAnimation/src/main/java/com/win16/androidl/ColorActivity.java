package com.win16.androidl;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;


public class ColorActivity extends Activity {

    private SharedPreferences sharedpreferences;
    private View revealView;
    private View header;
    private int primaryColor;
    private int cx;
    private int cy;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_color);
        primaryColor = getResources().getColor(R.color.color_set_1_primary);
        header = findViewById(R.id.header);
        header.setBackgroundColor(primaryColor);
        getWindow().setStatusBarColor(primaryColor);
        revealView = findViewById(R.id.reveal_view);

    }


    Animator.AnimatorListener revealAnimationListener = new Animator.AnimatorListener() {

        @Override
        public void onAnimationStart(Animator animation) {}

        @Override
        public void onAnimationEnd(Animator animation) {

            GUIUtils.hideRevealEffect(revealView, cx, cy, 1920);
            header.setBackgroundColor(primaryColor);
            getWindow().setStatusBarColor(primaryColor);
        }

        @Override
        public void onAnimationCancel(Animator animation) {}

        @Override
        public void onAnimationRepeat(Animator animation) {}
    };


    public void onColorClick(View view) {

        primaryColor = 0;

        switch (view.getId()) {
            case R.id.circle1:

                primaryColor = getResources().getColor(R.color.color_set_1_primary);
                break;

            case R.id.circle2:

                primaryColor = getResources().getColor(R.color.color_set_2_primary);
                break;

            case R.id.circle3:

                primaryColor = getResources().getColor(R.color.color_set_3_primary);
                break;

            case R.id.circle4:

                primaryColor = getResources().getColor(R.color.color_set_4_primary);
                break;
        }

        int [] location = new int[2];
        revealView.setBackgroundColor(primaryColor);

        view.getLocationOnScreen(location);

        cx = (location[0] + (view.getWidth() / 2));
        cy = location[1] + (GUIUtils.getStatusBarHeight(this) / 2);

        GUIUtils.showRevealEffect(revealView, cx, cy, revealAnimationListener);
    }
}
