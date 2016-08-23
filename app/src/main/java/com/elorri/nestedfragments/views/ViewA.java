package com.elorri.nestedfragments.views;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.elorri.nestedfragments.R;
import com.elorri.nestedfragments.interfaces.CustomView;

/**
 * Created by Elorri-user on 19/08/2015.
 */
public class ViewA extends LinearLayout implements CustomView {

    private static Bundle currentState;


    public ViewA(Context context) {
        super(context);

    }


    public ViewA(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.w("NestedFragments", "Constructor " + this.getClass().getName());
    }


    public View getView() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(R.layout.view_a, this, false);
    }


    public static Bundle initState() {
        currentState = new Bundle();
        return currentState;
    }

    public static Bundle saveState() {
        return null;
    }

    public static void restoreState(Bundle state) {
        if (state == null) initState();
    }
}
