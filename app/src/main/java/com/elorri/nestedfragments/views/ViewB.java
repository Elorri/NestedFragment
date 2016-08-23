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
public class ViewB extends LinearLayout implements CustomView {
    private static Bundle currentState;


    private static final String HELLO_HOLDER = "hello_holder";
    private static final String HELLO_HOLDER_DEFAULT = "Hello !";
    private static String helloHolder;
    private static TextView helloContainer;


    public ViewB(Context context) {
        super(context);
    }


    public ViewB(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.w("NestedFragments", "Constructor " + this.getClass().getSimpleName());
    }


//    @Override
//    protected void onFinishInflate() {
//        Log.w("NestedFragments", "onFinishInflate " + this.getClass().getSimpleName());
//        super.onFinishInflate();
//
//        this.helloContainer = (TextView) findViewById(R.id.helloContainer);
//
//        setHolders();
//        setContainers();
//    }
//
//
//
//    public View getView() {
//        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View v = inflater.inflate(R.layout.view_b, this, false);
//        return v;
//    }
//
//    public static Bundle initState() {
//        Bundle state = new Bundle();
//        helloHolder = initHelloHolder();
//        state.putString(HELLO_HOLDER, helloHolder);
//        currentState = state;
//        return currentState;
//    }
//
//    private static String initHelloHolder() {
//        if (!ViewC.user_name_holder.equals(ViewC.USER_NAME_HOLDER_DEFAULT))
//            return helloHolder = "Hello " + ViewC.user_name_holder;
//        else return helloHolder = HELLO_HOLDER_DEFAULT;
//    }
//
//    public static Bundle saveState() {
//        helloHolder = String.valueOf(helloContainer.getText());
//
//        Bundle state = new Bundle();
//        state.putString(HELLO_HOLDER, helloHolder);
//        currentState = state;
//        return currentState;
//
//    }
//
//    public static void restoreState(Bundle state) {
//        Log.w("NestedFragments", "restoreState " + state);
//        if (state == null) initState();
//        else {
//            helloHolder = state.getString(HELLO_HOLDER);
//            currentState = state;
//        }
//    }

}