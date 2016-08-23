package com.elorri.nestedfragments.pojo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;

/**
 * Created by Elorri-user on 03/09/2015.
 */
public class Screen {

    public static final String CENTER_CONTAINER_FRAGMENT_A_INIT_NAME = "CENTER_CONTAINER_FRAGMENT_A_INIT";
    public static final String CENTER_CONTAINER_FRAGMENT_B_INIT_NAME = "CENTER_CONTAINER_FRAGMENT_B_INIT";
    public static final String MAIN_CONTAINER_FRAGMENT_C_INIT_NAME = "MAIN_CONTAINER_FRAGMENT_C_INIT";
    public static final String MAIN_CONTAINER_FRAGMENT_C_LAST_NAME = "MAIN_CONTAINER_FRAGMENT_C_LAST";
    public static final String CENTER_CONTAINER_FRAGMENT_B_LAST_NAME = "CENTER_CONTAINER_FRAGMENT_B_LAST";
    public static final String TOOLBAR_CONTAINER_TOOLBAR_FRAGMENT_NAME = "TOOLBAR_CONTAINER_TOOLBAR_FRAGMENT";
    private String screenName;

    CustomBackStack customBackStack;

    View container;
    FragmentManager fragmentManager;
    Fragment fragment;
    Bundle state;

    String[] previousScreens;

    public Screen(String screenName) {
        this.customBackStack = CustomBackStack.getInstance();
        this.screenName = screenName;

        Log.e("NestedFragments", "Screen constructor " + this.getClass().getSimpleName());
        Log.e("NestedFragments", "Screen name " + screenName);
        if (screenName.equals(CENTER_CONTAINER_FRAGMENT_A_INIT_NAME))
            previousScreens = new String[]{};
        if (screenName.equals(CENTER_CONTAINER_FRAGMENT_B_INIT_NAME))
            previousScreens = new String[]{CENTER_CONTAINER_FRAGMENT_A_INIT_NAME};
        if (screenName.equals(MAIN_CONTAINER_FRAGMENT_C_INIT_NAME))
            previousScreens = new String[]{CENTER_CONTAINER_FRAGMENT_A_INIT_NAME, CENTER_CONTAINER_FRAGMENT_B_INIT_NAME};
        if (screenName.equals(MAIN_CONTAINER_FRAGMENT_C_LAST_NAME))
            previousScreens = new String[]{CENTER_CONTAINER_FRAGMENT_A_INIT_NAME, CENTER_CONTAINER_FRAGMENT_B_INIT_NAME};
        if (screenName.equals(CENTER_CONTAINER_FRAGMENT_B_LAST_NAME))
            previousScreens = new String[]{CENTER_CONTAINER_FRAGMENT_A_INIT_NAME};
        if (screenName.equals(TOOLBAR_CONTAINER_TOOLBAR_FRAGMENT_NAME))
            previousScreens = new String[]{};
    }


    public boolean isInPreviousScreen(Screen screen) {
        for (String ps : this.previousScreens) {
            if (ps.equals(screenName)) return true;
        }
        return false;
    }

    public Screen setScreen(View container, FragmentManager fragmentManager, Fragment fragment, Bundle state) {
        this.container = container;
        this.fragmentManager = fragmentManager;
        this.fragment = fragment;
        this.state = state;
        return this;

    }

    public String toString() {
        return screenName + " - " + container + " - " + fragmentManager + " - " + fragment + " - " + state;
    }


    public Screen navigate(View container, FragmentManager fragmentManager, Fragment fragment, Bundle state) {
        Log.e("NestedFragments", "navigate" + this.getClass().getSimpleName());
        CustomBackStack.printBackStack(fragmentManager);
        setScreen(container, fragmentManager, fragment, state);
        addToBackStack();
        customBackStack.add(this);
        CustomBackStack.printBackStack(fragmentManager);
        return this;
    }

    public void addToBackStack() {
        Log.e("LifeCycle", "commit " + fragment+" "+container.getId()+" "+this.getClass().getSimpleName());
        fragment.setArguments(state);
        fragmentManager.beginTransaction().replace(container.getId(), fragment).addToBackStack(screenName).commit();
        fragmentManager.executePendingTransactions();
    }


    public Screen setContainer(View container, FragmentManager fragmentManager, Fragment fragment, Bundle state) {
        Log.e("NestedFragments", "setContainer " + this.getClass().getSimpleName() + " " + screenName);
        CustomBackStack.printBackStack(fragmentManager);
        setScreen(container, fragmentManager, fragment, state);
        fragment.setArguments(state);
        fragmentManager.beginTransaction().replace(container.getId(), fragment).commit();
        fragmentManager.executePendingTransactions();
        CustomBackStack.printBackStack(fragmentManager);
        return this;
    }

//****************************
// PUBLIC GETTERs and SETTERs
//****************************


    public String getScreenName() {
        return screenName;
    }

    public String[] getPreviousScreens() {
        return previousScreens;
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public View getContainer() {
        return container;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public Bundle getState() {
        return state;
    }
}
