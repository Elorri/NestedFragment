package com.elorri.nestedfragments.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.elorri.nestedfragments.R;
import com.elorri.nestedfragments.fragments.FragmentA;
import com.elorri.nestedfragments.fragments.FragmentB;
import com.elorri.nestedfragments.fragments.ToolbarFragment;
import com.elorri.nestedfragments.interfaces.CustomView;

/**
 * Created by Elorri-user on 19/08/2015.
 */
public class MainView extends LinearLayout implements CustomView {



    public MainView(Context context) {
        super(context);
        Log.w("NestedFragments", "Constructor MainView(Context context) " + this.getClass().getSimpleName());
    }

    public MainView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Log.w("NestedFragments", "Constructor MainView(Context context, AttributeSet attrs, int defStyle) " + this.getClass().getSimpleName());
    }

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.w("NestedFragments", "Constructor MainView(Context context, AttributeSet attrs) " + this.getClass().getSimpleName());
    }
//
//    public View getView() {
//        Log.w("NestedFragments", "getView " + getClass().getSimpleName());
//        //View v= inflate(getContext(), R.layout.view_main, null);
//        // this method alone will call the constructor (even if it has already been called) and will follow by calling onFinishInflate. If there is any nested views they should be set up in onFinishInflate (only place where container views won't be null)
//        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View v = inflater.inflate(R.layout.view_main, this, false);
//        return v;
//    }
//
//    @Override
//    protected void onFinishInflate() {
//        Log.w("NestedFragments", "onFinishInflate " + this.getClass().getSimpleName());
//        super.onFinishInflate();
//        this.toolbarContainer = (LinearLayout) findViewById(R.id.toolbarContainer);
//        this.centerContainer = (LinearLayout) findViewById(R.id.centerContainer);
//
//        setHolders();
//        setContainers();
//
//    }
//
//
//
//    public static Bundle initState() {
//        toolbarViewHolderName = TOOLBAR_VIEW;
//        //centerViewHolderName = VIEW_A; if centerViewHolderName has not been set by an onCliked method it will be equals to 0 eg VIEW_A because all int are initialize to 0.
//        Bundle state = new Bundle();
//        state.putInt(TOOLBAR_VIEW_HOLDER_NAME, toolbarViewHolderName);
//        state.putInt(CENTER_VIEW_HOLDER_NAME, centerViewHolderName);
//        if (centerViewHolderName == VIEW_A)
//        state.putBundle(VIEW_A_STATE, ViewA.initState());
//        else state.putBundle(VIEW_B_STATE, ViewB.initState());
//        currentState = state;
//        return currentState;
//    }
//
//    public static Bundle saveState() {
//        Bundle state = new Bundle();
//        state.putInt(TOOLBAR_VIEW_HOLDER_NAME, toolbarViewHolderName);
//        state.putInt(CENTER_VIEW_HOLDER_NAME, centerViewHolderName);
//        state.putBundle(TOOLBAR_VIEW_HOLDER_STATE, ToolbarView.saveState());
//        if (centerViewHolderName == VIEW_A)
//            state.putBundle(VIEW_A_STATE, ViewA.saveState());
//        else state.putBundle(VIEW_B_STATE, ViewB.saveState());
//        currentState = state;
//        return currentState;
//    }
//
//
//    public static void restoreState(Bundle state) {
//        initState();
//
////        if (state == null) initState();
////        else {
////            Log.e("NestedFragments", "state.getInt(TOOLBAR_VIEW_HOLDER_NAME) " + state.getInt(TOOLBAR_VIEW_HOLDER_NAME));
////            toolbarViewHolderName = state.getInt(TOOLBAR_VIEW_HOLDER_NAME);
////            centerViewHolderName = state.getInt(CENTER_VIEW_HOLDER_NAME);
////            ToolbarView.restoreState(state.getBundle(TOOLBAR_VIEW_HOLDER_STATE));
////            if (centerViewHolderName == VIEW_A)
////                ViewA.restoreState(state.getBundle(VIEW_A_STATE));
////            else ViewB.restoreState(state.getBundle(VIEW_B_STATE));
////            currentState = state;
////        }
//    }
//
//
//
//
//
//
//
//
//
//
//    /*
//    * EVENTS
//     */
//
//    public void onSetFragmentAClicked(View view) {
//        centerViewHolderName = VIEW_A;
//        currentState.putInt(CENTER_VIEW_HOLDER_NAME, centerViewHolderName);
//        restoreState(currentState.getBundle(VIEW_A_STATE));
//        setHolders();
//        setContainers();
//    }
//
//    public void onSetFragmentBClicked(View view) {
//        centerViewHolderName = VIEW_B;
//        currentState.putInt(CENTER_VIEW_HOLDER_NAME, centerViewHolderName);
//        Log.w("NestedFragments", "currentState " + currentState);
//        restoreState(currentState.getBundle(VIEW_B_STATE));
//        setHolders();
//        setContainers();
//    }
}
