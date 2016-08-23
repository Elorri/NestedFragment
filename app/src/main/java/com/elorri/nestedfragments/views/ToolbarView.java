package com.elorri.nestedfragments.views;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.elorri.nestedfragments.R;
import com.elorri.nestedfragments.activities.MainActivity;
import com.elorri.nestedfragments.fragments.MainFragment;
import com.elorri.nestedfragments.interfaces.CustomView;

/**
 * Created by Elorri-user on 19/08/2015.
 */
public class ToolbarView extends LinearLayout implements CustomView {

    private Bundle currentState;


    public ToolbarView(Context context) {
        super(context);
    }

    public ToolbarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.w("NestedFragments", "Constructor " + this.getClass().getName());
    }


    public static Bundle saveState() {
        return null;
    }

    public static void restoreState(Bundle state) {
    }


}
