package com.elorri.nestedfragments.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.ViewGroup;

import com.elorri.nestedfragments.R;
import com.elorri.nestedfragments.interfaces.CustomFragment;
import com.elorri.nestedfragments.views.ViewC;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentC extends Fragment implements CustomFragment {


    ViewC layout;

    //Bundle will contain a Bundle view_c_state:
    // - Bundle of ViewC. = layout.saveState();
    private static final String FRAGMENT_C_STATE = "fragment_c_state";


    private static FragmentC instance;
    public static FragmentC getInstance() {
//        if (instance == null)
//            instance = new MainFragment();
        return instance;
    }

    public FragmentC() {
        // Required empty public constructor
    }


    private void initState() {
        Log.e("NestedFragments", "initState" + this.getClass().getSimpleName());
        layout.initState();
    }

    public Bundle saveState() {
        Log.e("NestedFragments", "saveState " + this.getClass().getSimpleName());
        return layout.saveState();
    }

    private void restoreState(Bundle savedInstanceState) {
        layout.restoreState(savedInstanceState);
    }

    public String getUser_name_holder() {
        return layout.getUser_name_holder();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.w("LifeCycle", "onCreate " + this.getClass().getSimpleName());
        super.onCreate(savedInstanceState);
        instance=this;
        setRetainInstance(true);

    }

    @Override
    public ViewC onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        Log.w("LifeCycle", "onCreateView " + this.getClass().getSimpleName());
        layout = (ViewC) inflater.inflate(R.layout.view_c, container, false);
        if (getArguments() == null)
            initState();
        else
            restoreState(getArguments());
        return layout;
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
//        outState.putBundle(FRAGMENT_C_STATE, saveState());
//        super.onSaveInstanceState(outState);
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
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.w("NestedFragments", "onActivityCreated " + this.getClass().getSimpleName());
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDetach() {
        Log.w("LifeCycle", "onDetach " + this.getClass().getSimpleName());
        super.onDetach();
    }


    //**********************************
    // Used only for testing
    //**********************************

    public static Bundle getBundleInit(){
        Bundle state = new Bundle();
        state.putString(ViewC.USER_NAME_HOLDER, ViewC.USER_NAME_HOLDER_DEFAULT);
        return state;
    }

    public static Bundle getBundleSample(){
        Bundle state = new Bundle();
        state.putString(ViewC.USER_NAME_HOLDER, "Un Sample name Paul");
        return state;
    }


}
