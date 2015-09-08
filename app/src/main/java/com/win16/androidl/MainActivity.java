package com.win16.androidl;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

public class MainActivity extends AppCompatActivity {

    private View mRect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRect = findViewById(R.id.retangle);
        mRect.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        hideView();
                        break;
                }
                return true;
            }


        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;


    }

    private void showView() {
        View myView = mRect;

// get the center for the clipping circle
        int cx = 0;
        int cy = 0;

// get the final radius for the clipping circle
        int finalRadius = (int) Math.ceil(Math.sqrt( myView.getWidth()*myView.getWidth() + myView.getHeight()*myView.getHeight()));

// create the animator for this view (the start radius is zero)
        Animator anim =
                ViewAnimationUtils.createCircularReveal(myView, 0, 0, 0, finalRadius);

// make the view visible and start the animation
        myView.setVisibility(View.VISIBLE);
        anim.start();
    }

    private void hideView() {
// previously visible view
        final View myView = mRect;

// get the center for the clipping circle
        int cx = 0;
        int cy = 0;

// get the final radius for the clipping circle
        int initialRadius = (int) Math.ceil(Math.sqrt( myView.getWidth()*myView.getWidth() + myView.getHeight()*myView.getHeight()));

// create the animation (the final radius is zero)
        Animator anim =
                ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, 0);

// make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                myView.setVisibility(View.INVISIBLE);
            }
        });

// start the animation
        anim.start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if( id == R.id.start) {
            showView();
        }

        return super.onOptionsItemSelected(item);
    }

    private void startAnimation() {
        ObjectAnimator up = ObjectAnimator.ofFloat(mRect, "translationY", 100, -400);
        up.setInterpolator(new DecelerateInterpolator());
        up.setDuration(800);

        ObjectAnimator down = ObjectAnimator.ofFloat(mRect, "translationY", -400, 100);
        down.setInterpolator(new AccelerateInterpolator());
        down.setDuration(800);

        AnimatorSet set = new AnimatorSet();
        set.playSequentially(up,down);
        set.start();
    }
}
