package com.elorri.nestedfragments.fragments;


import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;

import com.elorri.nestedfragments.R;
import com.elorri.nestedfragments.activities.MainActivity;
import com.elorri.nestedfragments.interfaces.CustomFragment;
import com.elorri.nestedfragments.interfaces.CustomView;
import com.elorri.nestedfragments.pojo.Screen;
import com.elorri.nestedfragments.views.MainView;
import com.elorri.nestedfragments.views.ViewC;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements CustomFragment{



    //Bundle  will contain :
    // - toolbarFragmentHolderName
    // - centerFragmentHolderName
    private static final String TOOLBAR_FRAGMENT_HOLDER_NAME = "toolbar_fragment_holder_name";
    private static final int TOOLBAR_FRAGMENT = 0;
    private int toolbarFragmentHolderName;

    public static final String CENTER_FRAGMENT_HOLDER_NAME = "center_fragment_holder_name";
    private static final int FRAGMENT_A = 0;
    public static final int FRAGMENT_B = 1;
    private int centerFragmentHolderName;


    //The Bundle value of centerFragmentHolderName will allow to deduce the value of :
    // - centerFragmentHolderTag
    // - centerFragmentHolder

    // The container toolbarContainer must be fill with a View toolbarFragmentHolder
    // toolbarFragmentHolder will receive
    // -  ToolbarFragment instance
    private Screen toolbarFragmentHolder;
    private LinearLayout toolbarContainer;


    // The container centerContainer must be fill with a View centerFragmentHolder
    // centerFragmentHolder will receive either
    // - FragmentA instance
    // - FragmentB instance
    public static final String CENTER_FRAGMENT_HOLDER_STATE = "center_fragment_holder_state";
    private static final String FRAGMENT_A_TAG = "fragment_a";
    private static final String FRAGMENT_B_TAG = "fragment_b";
    private String centerFragmentHolderTag;
    private Screen centerFragmentHolder;
    private LinearLayout centerContainer;


    private static MainFragment instance;
    public static MainFragment getInstance() {
        if (instance == null)
            instance = new MainFragment();
        return instance;
    }


    public MainFragment() {
        // Required empty public constructor
        Log.w("NestedFragments", "MainFragment constructor this"+this+" - " + this.getClass().getSimpleName());
    }


    private void initState() {
        Log.e("NestedFragments", "initState" + this.getClass().getSimpleName());
        toolbarFragmentHolderName = TOOLBAR_FRAGMENT;
        centerFragmentHolderName = FRAGMENT_A;
    }

    public Bundle saveState() {
        Log.e("NestedFragments", "saveState " + this.getClass().getSimpleName());
        Bundle state = new Bundle();
        state.putInt(CENTER_FRAGMENT_HOLDER_NAME, centerFragmentHolderName);
        state.putBundle(CENTER_FRAGMENT_HOLDER_STATE, ((CustomFragment) centerFragmentHolder.getFragment()).saveState());
        return state;
    }

    private void restoreState(Bundle args) {
        Log.e("NestedFragments", "restoreState " + this.getClass().getSimpleName());
        centerFragmentHolderName = args.getInt(CENTER_FRAGMENT_HOLDER_NAME);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.w("LifeCycle", "onCreate " + this.getClass().getSimpleName());
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        instance=this;

        if (getArguments() == null)
            initState();
        else{
            Log.w("NestedFragments", "onCreate savedInstanceState " + savedInstanceState + " " + this.getClass().getSimpleName());
            restoreState(getArguments());
            Log.w("NestedFragments", "onCreate centerFragmentHolderName " + centerFragmentHolderName + " " + this.getClass().getSimpleName());}
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.w("LifeCycle", "onCreateView " + this.getClass().getSimpleName());
        View layout = inflater.inflate(R.layout.view_main, container, false);
        this.toolbarContainer = (LinearLayout) layout.findViewById(R.id.toolbarContainer);
        this.centerContainer = (LinearLayout) layout.findViewById(R.id.centerContainer);


        setContainers();
        return layout;
    }



    private void setContainers() {
        Log.w("NestedFragments", "setContainers " + getClass().getSimpleName());
        setToolbarContainer();
        setCenterContainer();

    }


    private void setToolbarContainer() {
        toolbarFragmentHolder = new Screen(Screen.TOOLBAR_CONTAINER_TOOLBAR_FRAGMENT_NAME).setContainer(toolbarContainer, getChildFragmentManager(), new ToolbarFragment(), null);
}

    private void setCenterContainer() {
        if (getArguments() != null) {
            switch (centerFragmentHolderName) {
                case FRAGMENT_A:
                    centerFragmentHolder =  new Screen(Screen.CENTER_CONTAINER_FRAGMENT_A_INIT_NAME).setContainer(centerContainer, getChildFragmentManager(), new FragmentA(), getArguments().getBundle(CENTER_FRAGMENT_HOLDER_STATE));
                    break;
                case FRAGMENT_B:
                    centerFragmentHolder =  new Screen(Screen.CENTER_CONTAINER_FRAGMENT_B_INIT_NAME).setContainer(centerContainer, getChildFragmentManager(), new FragmentB(), getArguments().getBundle(CENTER_FRAGMENT_HOLDER_STATE));
                    break;
                default:
                    break;
            }
        } else
            switch (centerFragmentHolderName) {
                case FRAGMENT_A:
                    centerFragmentHolder =  new Screen(Screen.CENTER_CONTAINER_FRAGMENT_A_INIT_NAME).setContainer(centerContainer, getChildFragmentManager(), new FragmentA(), null);
                    break;
                case FRAGMENT_B:
                    centerFragmentHolder =  new Screen(Screen.CENTER_CONTAINER_FRAGMENT_B_INIT_NAME).setContainer(centerContainer, getChildFragmentManager(), new FragmentB(), null);
                    break;
                default:
                    break;
            }

    }



    //*********************
    // LifeCycle Methods
    //*********************


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
//        outState.putBundle(MAIN_FRAGMENT_STATE, saveState());
//        outState.putBundle(CENTER_FRAGMENT_HOLDER_STATE, ((CustomFragment) centerFragmentHolder).saveState());
//        super.onSaveInstanceState(outState);
        Log.w("LifeCycle", "onSaveInstanceState outState " + outState + " " + this.getClass().getSimpleName());
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




    //*********************
    // Events
    //*********************


    public void onSetFragmentAClicked(View view) {
        centerFragmentHolderName = FRAGMENT_A;
        centerFragmentHolder=new Screen(Screen.CENTER_CONTAINER_FRAGMENT_A_INIT_NAME).navigate(centerContainer, getChildFragmentManager(), new FragmentA(), null);
    }


    public void onSetFragmentBClicked(View view) {
        centerFragmentHolderName = FRAGMENT_B;
        centerFragmentHolder=new Screen(Screen.CENTER_CONTAINER_FRAGMENT_B_INIT_NAME).navigate(centerContainer, getChildFragmentManager(), new FragmentB(), null);

    }



    //**********************************
    // Used only for testing
    //**********************************

    public static Bundle getBundleInit(){
        Bundle state = new Bundle();
        state.putInt(TOOLBAR_FRAGMENT_HOLDER_NAME, TOOLBAR_FRAGMENT);
        state.putInt(CENTER_FRAGMENT_HOLDER_NAME, FRAGMENT_A);
        state.putBundle(CENTER_FRAGMENT_HOLDER_STATE, FragmentA.getBundleInit());
        return state;
    }

    public static Bundle getBundleFragmentBInit(){
        Bundle state = new Bundle();
        state.putInt(TOOLBAR_FRAGMENT_HOLDER_NAME, TOOLBAR_FRAGMENT);
        state.putInt(CENTER_FRAGMENT_HOLDER_NAME, FRAGMENT_B);
        state.putBundle(CENTER_FRAGMENT_HOLDER_STATE, FragmentB.getBundleInit());
        return state;
    }

    public static Bundle getBundleFragmentBSample(){
        Bundle state = new Bundle();
        state.putInt(TOOLBAR_FRAGMENT_HOLDER_NAME, TOOLBAR_FRAGMENT);
        state.putInt(CENTER_FRAGMENT_HOLDER_NAME, FRAGMENT_B);
        state.putBundle(CENTER_FRAGMENT_HOLDER_STATE, FragmentB.getBundleSample());
        return state;
    }


}
