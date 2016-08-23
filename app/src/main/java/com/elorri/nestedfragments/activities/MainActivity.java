package com.elorri.nestedfragments.activities;

import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.elorri.nestedfragments.R;
import com.elorri.nestedfragments.fragments.FragmentA;
import com.elorri.nestedfragments.fragments.FragmentB;
import com.elorri.nestedfragments.fragments.FragmentC;
import com.elorri.nestedfragments.fragments.MainFragment;
import com.elorri.nestedfragments.interfaces.CustomFragment;
import com.elorri.nestedfragments.pojo.Screen;


import java.util.Stack;


public class MainActivity extends ActionBarActivity {

    private static final String MAIN_ACTIVITY_STATE = "main_activity_state";


    //Bundle will contain :
    // - mainFragmentHolderName
    private static final String MAIN_FRAGMENT_HOLDER_NAME = "main_fragment_holder_name";
    private static final int MAIN_FRAGMENT = 0;
    private static final int FRAGMENT_C = 1;
    private int mainFragmentHolderName;

    //The Bundle value of mainFragmentHolderName will allow to deduce the value of :
    // - mainFragmentHolderTag
    // - mainFragmentHolder
    // The container mainFragmentContainer must be fill with a Fragment mainFragmentHolder
    // mainFragmentHolder will receive either
    // - MainFragment instance
    // - FragmentC instance
    private static final String MAIN_FRAGMENT_HOLDER_STATE = "main_fragment_holder_state";
    private static final String MAIN_FRAGMENT_TAG = "main_fragment";
    private static final String FRAGMENT_C_TAG = "fragment_c";
    private String mainFragmentHolderTag;
    private Screen mainFragmentHolder;
    private LinearLayout mainFragmentContainer;


    private static MainActivity instance;
    private Bundle savedInstanceState;

    public static MainActivity getInstance() {
        return instance;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("LifeCycle", "onCreate  deb " + getClass().getSimpleName());


        instance = this;
        this.savedInstanceState = savedInstanceState;

        //inflate constant view elements. Elements that doesn't change with currentState
        setContentView(R.layout.activity_main);

        //get view containers references
        mainFragmentContainer = (LinearLayout) findViewById(R.id.mainFragmentContainer);

        tests();


//        if (savedInstanceState == null) {
//            initState();
//            setContainers(null);
//        } else {
//            restoreState(savedInstanceState.getBundle(MAIN_ACTIVITY_STATE));
//            setContainers(savedInstanceState.getBundle(MAIN_ACTIVITY_STATE));
//        }
        Log.e("LifeCycle", "onCreate  fin " + getClass().getSimpleName());
    }


    private void initState() {
        Log.e("NestedFragments", "initState" + this.getClass().getSimpleName());
        mainFragmentHolderName = MAIN_FRAGMENT; //if mainFragmentHolderName has not been set by an onCliked method it will be anyway equals to 0 eg MAIN_FRAGMENT because all int are initialize to 0.
//        Bundle state = new Bundle();
//        state.putInt(MAIN_FRAGMENT_HOLDER_NAME, mainFragmentHolderName);
//        currentState = state;
    }

    private Bundle saveState() {
        Log.e("NestedFragments", "saveState " + this.getClass().getSimpleName());
        Bundle state = new Bundle();
        state.putInt(MAIN_FRAGMENT_HOLDER_NAME, mainFragmentHolderName);
        Log.e("NestedFragments", "mainFragmentHolder " + mainFragmentHolder);
        Log.e("NestedFragments", "mainFragmentHolder.getFragment() " + mainFragmentHolder.getFragment());
        state.putBundle(MAIN_FRAGMENT_HOLDER_STATE, ((CustomFragment) mainFragmentHolder.getFragment()).saveState());
        return state;
    }

    private void restoreState(Bundle state) {
        Log.e("NestedFragments", "restoreState " + this.getClass().getSimpleName());
        mainFragmentHolderName = state.getInt(MAIN_FRAGMENT_HOLDER_NAME);
    }


    private void setContainers(Bundle saveInstanceState) {
        setMainFragmentContainer(saveInstanceState);
    }

    private void setMainFragmentContainer(Bundle saveInstanceState) {
        if (saveInstanceState != null) {
            switch (mainFragmentHolderName) {
                case MAIN_FRAGMENT:
                    mainFragmentHolder = new Screen(Screen.CENTER_CONTAINER_FRAGMENT_A_INIT_NAME).setContainer(mainFragmentContainer, getSupportFragmentManager(), new MainFragment(), saveInstanceState.getBundle(MAIN_FRAGMENT_HOLDER_STATE));
                    break;
                case FRAGMENT_C:
                    mainFragmentHolder = new Screen(Screen.MAIN_CONTAINER_FRAGMENT_C_INIT_NAME).setContainer(mainFragmentContainer, getSupportFragmentManager(), new FragmentC(), saveInstanceState.getBundle(MAIN_FRAGMENT_HOLDER_STATE));
                    break;
                default:
                    break;
            }
        } else
            switch (mainFragmentHolderName) {
                case MAIN_FRAGMENT:
                    mainFragmentHolder = new Screen(Screen.CENTER_CONTAINER_FRAGMENT_A_INIT_NAME).setContainer(mainFragmentContainer, getSupportFragmentManager(), new MainFragment(), null);
                    break;
                case FRAGMENT_C:
                    mainFragmentHolder = new Screen(Screen.MAIN_CONTAINER_FRAGMENT_C_INIT_NAME).setContainer(mainFragmentContainer, getSupportFragmentManager(), new FragmentC(), null);
                    break;
                default:
                    break;
            }
    }


    //*********************
    // LifeCycle Methods
    //*********************

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


    @Override
    protected void onRestart() {
        Log.e("NestedFragments", "onRestart " + this.getClass().getSimpleName());
        super.onRestart();
    }

    @Override
    protected void onStart() {
        Log.e("NestedFragments", "onStart " + this.getClass().getSimpleName());
        super.onStart();
    }


    @Override
    protected void onResume() {
        Log.e("NestedFragments", "onResume " + this.getClass().getSimpleName());
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.e("NestedFragments", "onPause " + this.getClass().getSimpleName());
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.e("NestedFragments", "onStop " + this.getClass().getSimpleName());
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.e("NestedFragments", "onDestroy " + this.getClass().getSimpleName());
        super.onDestroy();
    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        Log.e("NestedFragments", "public onSaveInstanceState " + this.getClass().getSimpleName());
        super.onSaveInstanceState(outState, outPersistentState);

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        Log.e("NestedFragments", "public onRestoreInstanceState " + this.getClass().getSimpleName());
        super.onRestoreInstanceState(savedInstanceState, persistentState);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("LifeCycle", "protected onSaveInstanceState " + this.getClass().getSimpleName());
        outState.putBundle(MAIN_ACTIVITY_STATE, saveState());
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.e("NestedFragments", "protected onRestoreInstanceState " + this.getClass().getSimpleName());
    }



    //**********************************
    // Getters and Setters
    //**********************************

    public LinearLayout getMainFragmentContainer() {
        return mainFragmentContainer;
    }


    //*********************
    // Events
    //*********************

    public void onSetFragmentAClicked(View view) {
        mainFragmentHolderName = MAIN_FRAGMENT;
        ((MainFragment) mainFragmentHolder.getFragment()).onSetFragmentAClicked(view);
    }


    public void onSetFragmentBClicked(View view) {
        mainFragmentHolderName = MAIN_FRAGMENT;
        ((MainFragment) mainFragmentHolder.getFragment()).onSetFragmentBClicked(view);
    }

    public void onSetFragmentCClicked(View view) {
        mainFragmentHolderName = FRAGMENT_C;
        mainFragmentHolder = new Screen(Screen.MAIN_CONTAINER_FRAGMENT_C_INIT_NAME).navigate(mainFragmentContainer, getSupportFragmentManager(), new FragmentC(), null);
    }


    public void onBackButtonClicked(View view) {
        mainFragmentHolderName = MAIN_FRAGMENT;
        mainFragmentHolder = new Screen(Screen.CENTER_CONTAINER_FRAGMENT_B_INIT_NAME).navigate(mainFragmentContainer, getSupportFragmentManager(), new FragmentB(), null);

    }


  /*  @Override
    public void onBackPressed() {
    }*/


    //**********************************
    // Used only for testing
    //**********************************
    private void tests() {
        //       setContainerWOBS(mainFragmentContainer, new FragmentA(), FragmentA.getBundleInit(), "FragmentA");
//        setContainer(mainFragmentContainer, new FragmentA(), null, "FragmentA");
//        setContainer(mainFragmentContainer, new FragmentB(), FragmentB.getBundleInit(), "FragmentB");
//        setContainer(mainFragmentContainer, new FragmentB(), null, "FragmentB");
//        setContainer(mainFragmentContainer, new FragmentB(), FragmentB.getBundleSample(), "FragmentB");
//        setContainer(mainFragmentContainer, new FragmentC(), FragmentC.getBundleInit(), "FragmentC");
//        setContainer(mainFragmentContainer, new FragmentC(), null, "FragmentC");
//        setContainer(mainFragmentContainer, new FragmentC(), FragmentC.getBundleSample(), "FragmentC");
//        setContainer(mainFragmentContainer, new MainFragment(), MainFragment.getBundleInit(), "MainFragment");
//        setContainer(mainFragmentContainer, new MainFragment(), null, "MainFragment");
//        setContainer(mainFragmentContainer, new MainFragment(), MainFragment.getBundleFragmentBInit(), "MainFragment");
//        setContainer(mainFragmentContainer, new MainFragment(), MainFragment.getBundleFragmentBSample(), "MainFragment");
//        printBackStack();


        mainFragmentHolder = new Screen(Screen.MAIN_CONTAINER_FRAGMENT_C_INIT_NAME).navigate(mainFragmentContainer, getSupportFragmentManager(), new FragmentC(), null);
        //mainFragmentHolder = new Screen(Screen.MAIN_CONTAINER_FRAGMENT_C_INIT_NAME).navigate(mainFragmentContainer, getSupportFragmentManager(), new FragmentC(), null);

//        if (savedInstanceState == null) {
//            Log.e("BackStack", "onSaveInstanceState == null " + this.getClass().getSimpleName());
//            mainFragmentHolder = new Screen(Screen.CENTER_CONTAINER_FRAGMENT_A_INIT_NAME).navigate(mainFragmentContainer, getSupportFragmentManager(), new MainFragment(), null);
//            mainFragmentHolder = new Screen(Screen.CENTER_CONTAINER_FRAGMENT_B_INIT_NAME).navigate(mainFragmentContainer, getSupportFragmentManager(), new MainFragment(), MainFragment.getBundleFragmentBInit());
//            mainFragmentHolder = new Screen(Screen.MAIN_CONTAINER_FRAGMENT_C_INIT_NAME).navigate(mainFragmentContainer, getSupportFragmentManager(), new FragmentC(), null);
//            mainFragmentHolder = new Screen(Screen.MAIN_CONTAINER_FRAGMENT_C_LAST_NAME).navigate(mainFragmentContainer, getSupportFragmentManager(), new FragmentC(), FragmentC.getBundleSample());
//            mainFragmentHolder = new Screen(Screen.CENTER_CONTAINER_FRAGMENT_B_LAST_NAME).navigate(mainFragmentContainer, getSupportFragmentManager(), new MainFragment(), MainFragment.getBundleFragmentBSample());
//        } else {
//            Log.e("BackStack", "onSaveInstanceState != null " + this.getClass().getSimpleName());
//            mainFragmentHolder = new Screen(Screen.CENTER_CONTAINER_FRAGMENT_A_INIT_NAME).setContainer(mainFragmentContainer, getSupportFragmentManager(), new MainFragment(), null);
//            mainFragmentHolder = new Screen(Screen.CENTER_CONTAINER_FRAGMENT_B_INIT_NAME).setContainer(mainFragmentContainer, getSupportFragmentManager(), new MainFragment(), MainFragment.getBundleFragmentBInit());
//            mainFragmentHolder = new Screen(Screen.MAIN_CONTAINER_FRAGMENT_C_INIT_NAME).setContainer(mainFragmentContainer, getSupportFragmentManager(), new FragmentC(), null);
//            mainFragmentHolder = new Screen(Screen.MAIN_CONTAINER_FRAGMENT_C_LAST_NAME).setContainer(mainFragmentContainer, getSupportFragmentManager(), new FragmentC(), FragmentC.getBundleSample());
//            mainFragmentHolder = new Screen(Screen.CENTER_CONTAINER_FRAGMENT_B_LAST_NAME).setContainer(mainFragmentContainer, getSupportFragmentManager(), new MainFragment(), MainFragment.getBundleFragmentBSample());
//
//        }
//
    }



}
