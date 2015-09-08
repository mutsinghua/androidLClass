package com.win16.widthanimation;

import android.app.Activity;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Activity {

    private View mElement;

    private ViewGroup mRoot;

    private int phrase = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRoot = (ViewGroup) findViewById(R.id.root);
        mElement = findViewById(R.id.element);
    }

    public void changeSize(View v) {

        TransitionManager.beginDelayedTransition(mRoot);
        if( phrase == 0) {
            phrase++;
            setViewWidth(300);
        }
        else {
            setViewHight(300);
        }
    }

    private void setViewWidth(int x) {
        ViewGroup.LayoutParams params = mElement.getLayoutParams();
        params.width = x;
        mElement.setLayoutParams(params);
    }

    private void setViewHight(int x) {
        ViewGroup.LayoutParams params = mElement.getLayoutParams();
        params.height = x;
        mElement.setLayoutParams(params);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
