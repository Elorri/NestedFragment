package com.elorri.nestedfragments.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elorri.nestedfragments.R;
import com.elorri.nestedfragments.interfaces.CustomFragment;
import com.elorri.nestedfragments.views.ViewA;
import com.elorri.nestedfragments.views.ViewB;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentA extends Fragment implements CustomFragment {

    public FragmentA() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.w("LifeCycle", "onCreateView " + this.getClass().getSimpleName());
        View layout = inflater.inflate(R.layout.view_a, container, false);
        return layout;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.w("LifeCycle", "onCreate " + this.getClass().getSimpleName());
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.w("NestedFragments", "onStart " + this.getClass().getSimpleName());
        super.onStart();
    }



    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        Log.w("NestedFragments", "onViewStateRestored " + this.getClass().getSimpleName());
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onResume() {
        Log.w("NestedFragments", "onResume " + this.getClass().getSimpleName());
        super.onResume();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.w("NestedFragments", "onResume " + this.getClass().getSimpleName());
        //super.onCreateOptionsMenu(menu, inflater);
    }




    @Override
    public void onPause() {
        Log.w("NestedFragments", "onPause " + this.getClass().getSimpleName());
        super.onPause();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.w("LifeCycle", "onSaveInstanceState " + this.getClass().getSimpleName());
        //super.onSaveInstanceState(outState);
    }



    @Override
    public void onStop() {
        Log.w("NestedFragments", "onStop " + this.getClass().getSimpleName());
        super.onStop();
    }


    @Override
    public void onDestroy() {
        Log.w("NestedFragments", "onDestroy " + this.getClass().getSimpleName());
        super.onDestroy();
    }


    @Override
    public void onAttach(Activity activity) {
        Log.w("LifeCycle", "onAttach " + this.getClass().getSimpleName());
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        Log.w("LifeCycle", "onDetach " + this.getClass().getSimpleName());
        super.onDetach();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.w("NestedFragments", "onActivityCreated " + this.getClass().getSimpleName());
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public Bundle saveState() {
        return null;
    }



    //**********************************
    // Used only for testing
    //**********************************

    public static Bundle getBundleInit(){
        return null;
    }
}
