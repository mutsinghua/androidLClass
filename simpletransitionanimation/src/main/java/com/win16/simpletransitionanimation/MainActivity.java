package com.win16.simpletransitionanimation;

import android.app.Activity;
import android.os.Bundle;
import android.transition.ArcMotion;
import android.transition.AutoTransition;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Activity {

    private ViewGroup root;
    private Scene scene1;
    private Scene scene2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        root = (ViewGroup) findViewById(R.id.root);

        scene1 = new Scene(root, getLayoutInflater().inflate(R.layout.scence1, root,false));
        scene2 = new Scene(root, getLayoutInflater().inflate(R.layout.scence2, root,false));
        TransitionManager.go(scene1);
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
        if (id == R.id.action_settings) {
            ChangeBounds changeBounds = new ChangeBounds();
            TransitionManager.go(scene2, changeBounds);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
