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
import android.widget.TextView;

import com.elorri.nestedfragments.R;
import com.elorri.nestedfragments.interfaces.CustomFragment;
import com.elorri.nestedfragments.views.ViewB;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentB extends Fragment implements CustomFragment{


    //Bundle will contain :
    // - helloHolder
    public static final String HELLO_HOLDER = "hello_holder";
    private static final String HELLO_HOLDER_DEFAULT = "Hello !";
    public static final String HELLO_HOLDER_WAITING_FOR_USER_NAME = "Hello ";
    private  String helloHolder;


    private  TextView helloContainer;



    public FragmentB() {
        // Required empty public constructor
    }


    private void initState() {
        Log.e("NestedFragments", "initState" + this.getClass().getSimpleName());
        helloHolder = HELLO_HOLDER_DEFAULT;
    }

    public Bundle saveState() {
        Log.e("NestedFragments", "saveState " + this.getClass().getSimpleName());
        Bundle state = new Bundle();
        state.putString(HELLO_HOLDER, helloHolder);
        return state;
    }

    private void restoreState(Bundle savedInstanceState) {
        if (savedInstanceState != null)
            helloHolder= savedInstanceState.getString(HELLO_HOLDER);
        else
            helloHolder= HELLO_HOLDER_DEFAULT;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.w("LifeCycle", "onCreate " + this.getClass().getSimpleName());
        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
        if (getArguments() == null)
            initState();
        else
            restoreState(getArguments());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.w("LifeCycle", "onCreateView " + this.getClass().getSimpleName());
        View layout=inflater.inflate(R.layout.view_b, container, false);

        this.helloContainer = (TextView) layout.findViewById(R.id.helloContainer);
        setContainers();
        return layout;
    }



    private void setContainers() {
        Log.w("NestedFragments", "setContainers " + getClass().getSimpleName());
        setHelloContainer();
    }

    private void setHelloContainer() {
        helloContainer.setText(helloHolder);
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
//        outState.putBundle(FRAGMENT_B_STATE, saveState());
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
        Bundle b=new Bundle();
        b.putString(HELLO_HOLDER, HELLO_HOLDER_DEFAULT);
        return b;
    }

    public static Bundle getBundleSample(){
        Bundle b=new Bundle();
        b.putString(HELLO_HOLDER, HELLO_HOLDER_WAITING_FOR_USER_NAME + "Sample Pierre !");
        return b;
    }
}
