package com.win16.moveanimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import cimi.com.easeinterpolator.EaseBounceOutInterpolator;
import cimi.com.easeinterpolator.EaseCircularInInterpolator;
import cimi.com.easeinterpolator.EaseCircularOutInterpolator;
import cimi.com.easeinterpolator.EaseCubicInInterpolator;
import cimi.com.easeinterpolator.EaseCubicOutInterpolator;
import cimi.com.easeinterpolator.EaseQuartOutInterpolator;

public class MainActivity extends Activity {

    private View ball1;
    private View ball2;
    private View ball3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ball1 = findViewById(R.id.ball1);
        ball2 = findViewById(R.id.ball2);
        ball3 = findViewById(R.id.ball3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
            startAnimation(id);
        return super.onOptionsItemSelected(item);
    }

    private void startAnimation(int id) {

        float distance1 = getResources().getDimensionPixelOffset(R.dimen.distance1);
        float distance2 = getResources().getDimensionPixelOffset(R.dimen.distance2);
        switch (id) {
            case R.id.action_ball1:
                AnimatorSet redBall = new AnimatorSet();
                //go
                ObjectAnimator redBall1 = ObjectAnimator.ofFloat(ball1, "TranslationY", 0, -distance1);
                redBall1.setDuration(2000);
                redBall1.setInterpolator(new EaseCircularOutInterpolator());

                //back
                ObjectAnimator redBall2 = ObjectAnimator.ofFloat(ball1, "TranslationY", -distance1,0);
                redBall2.setDuration(2000);
                redBall2.setInterpolator(new EaseBounceOutInterpolator());

                redBall.playSequentially(redBall1, redBall2);

                redBall.start();
                break;
            case R.id.action_ball2:
                AnimatorSet gBall = new AnimatorSet();
                //go
                ObjectAnimator gBall1 = ObjectAnimator.ofFloat(ball2, "TranslationY", 0, -distance2);
                gBall1.setDuration(2000);
                gBall1.setInterpolator(new EaseCircularOutInterpolator());

                //back
                ObjectAnimator gBall2 = ObjectAnimator.ofFloat(ball2, "TranslationY", -distance2,0);
                gBall2.setDuration(2000);
                gBall2.setInterpolator(new EaseCircularInInterpolator());

                gBall.playSequentially(gBall1, gBall2);

                gBall.start();
                break;

            case R.id.action_ball3:

                AnimatorSet bBall = new AnimatorSet();
                //go
                ObjectAnimator bBall1 = ObjectAnimator.ofFloat(ball3, "TranslationY", 0, -distance1);
                bBall1.setDuration(2000);
                bBall1.setInterpolator(new LinearInterpolator());

                //back
                ObjectAnimator bBall2 = ObjectAnimator.ofFloat(ball3, "TranslationY", -distance1,0);
                bBall2.setDuration(2000);
                bBall2.setInterpolator(new LinearInterpolator());

                bBall.playSequentially(bBall1, bBall2);

                bBall.start();
                break;
        }



    }
}
