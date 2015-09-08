package com.win16.sceneanimation;

import android.app.Activity;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.Scene;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import cimi.com.easeinterpolator.EaseBackInInterpolator;
import cimi.com.easeinterpolator.EaseBounceInInterpolator;

public class MainActivity extends Activity {
    private TransitionManager mTransitionManager;
    private Scene mScene1;
    private Scene mScene2;
    private AutoTransition autoTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);

        ViewGroup container = (ViewGroup)findViewById(R.id.container);
        TransitionInflater transitionInflater = TransitionInflater.from(this);
        mTransitionManager = transitionInflater.inflateTransitionManager(R.transition.transition_manager, container);
        mScene1 = Scene.getSceneForLayout(container, R.layout.fragment_transition_scene_1, this);
        mScene2 = Scene.getSceneForLayout(container, R.layout.fragment_transition_scene_2, this);
//        autoTransition = new AutoTransition();
//        autoTransition.setInterpolator(new EaseBackInInterpolator());
//        autoTransition.setDuration(1000);
        goToScene2(null);
    }

    public void goToScene1(View view) {
        mTransitionManager.transitionTo(mScene1);
//        TransitionManager.go(mScene1, autoTransition);
    }

    public void goToScene2(View view) {
        mTransitionManager.transitionTo(mScene2);
//        TransitionManager.go(mScene2, autoTransition);
    }
}
