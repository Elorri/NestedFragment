package com.elorri.nestedfragments.views;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.elorri.nestedfragments.R;
import com.elorri.nestedfragments.interfaces.CustomView;

/**
 * Created by Elorri-user on 19/08/2015.
 */
public class ViewC extends LinearLayout implements CustomView {


    public static String USER_NAME_HOLDER = "user_name_holder";
    public static String USER_NAME_HOLDER_DEFAULT = "Ex: Pierre";
    public String user_name_holder;
    private TextView user_name_container;


    public ViewC(Context context) {
        super(context);
    }


    public ViewC(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.w("NestedFragments", "Constructor " + this.getClass().getName());
    }


    public void initState() {
        user_name_holder = USER_NAME_HOLDER_DEFAULT;
        setContainers();
    }

    public void restoreState(Bundle state) {
        user_name_holder = state.getString(USER_NAME_HOLDER);
        setContainers();
        Log.w("NestedFragments", "restoreState " + this.getClass().getSimpleName());
    }

    public Bundle saveState() {
        user_name_holder = String.valueOf(user_name_container.getText());
        Bundle state = new Bundle();
        state.putString(USER_NAME_HOLDER, user_name_holder);
        return state;
    }

    public String getUser_name_holder() {
        saveState();
        return user_name_holder;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        user_name_container = (TextView) findViewById(R.id.user_name_container);
        initState();
    }



    private void setContainers() {
        Log.w("NestedFragments", "setContainers " + getClass().getSimpleName());
        user_name_container.setText(user_name_holder);
    }







}
