package com.elorri.nestedfragments.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elorri.nestedfragments.R;
import com.elorri.nestedfragments.interfaces.CustomFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentD extends Fragment {

    View layout;


    public FragmentD() {
        // Required empty public constructor
        Log.w("NestedFragments", "Constructor " + this.getClass().getSimpleName());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.w("NestedFragments", "onCreateView " + this.getClass().getSimpleName());
        layout= inflater.inflate(R.layout.view_d, container, false);
        return layout;
    }

    @Override
    public void onDetach() {
        Log.w("NestedFragments", "onDetach " + this.getClass().getSimpleName());
        super.onDetach();
    }

}
