package com.elorri.nestedfragments.pojo;

import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.elorri.nestedfragments.activities.MainActivity;
import com.elorri.nestedfragments.fragments.FragmentC;
import com.elorri.nestedfragments.fragments.MainFragment;

import java.util.ArrayList;

/**
 * Created by Elorri-user on 03/09/2015.
 */
public class CustomBackStack {

    private static CustomBackStack instance;

    private ArrayList<Screen> screens = new ArrayList<>();

    public static CustomBackStack getInstance() {
        if (instance == null)
            instance = new CustomBackStack();
        return instance;
    }

    public void add(Screen screen) {
        screens.add(screen);
        ArrayList<Screen> screenToRemove = updateCustomBackStack(screen);
        popBackStack(screenToRemove);
        Log.e("NestedFragments", "(screens.size()-1 != screen.getPreviousScreens().length : " + (screens.size() - 1 != screen.getPreviousScreens().length));
        if (screens.size() - 1 != screen.getPreviousScreens().length)
            recreateBackStacks(screen); //some screens are missing.
        ArrayList<Screen> screensToAdd = customBackStack_minus_backStack(screen);
        addToBackStack(screensToAdd);

        //printCustomBackStack();
        //printBackStack(screen.getFragmentManager());

    }

    private void addToBackStack(ArrayList<Screen> screensToAdd) {
        for (Screen screen : screensToAdd) {
            screen.addToBackStack();
        }
    }

    private ArrayList<Screen> customBackStack_minus_backStack(Screen screen) {
        ArrayList<String> screensNameToAdd = getScreensName(screens);
        screensNameToAdd.removeAll(getBackStackScreens(screen.getFragmentManager()));
        return getScreensFromNames(screensNameToAdd);
    }

    private ArrayList<Screen> getScreensFromNames(ArrayList<String> screensNames) {
        ArrayList<Screen> theScreens = new ArrayList<>();
        for (String screenName : screensNames) {
            theScreens.add(getScreenFromName(screenName, screens));
        }
        return theScreens;
    }

    private Screen getScreenFromName(String screenName, ArrayList<Screen> screens) {
        for (Screen screen : screens) {
            if (screen.getScreenName().equals(screenName)) return screen;
        }
        return null;
    }

    private ArrayList<String> getScreensName(ArrayList<Screen> screens) {
        ArrayList<String> screensName = new ArrayList<>();
        for (Screen s : screens) {
            screensName.add(s.getScreenName());
        }
        return screensName;
    }

    private ArrayList<String> getBackStackScreens(FragmentManager fragmentManager) {
        ArrayList<String> backStackScreens = new ArrayList<>();
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            backStackScreens.add(fragmentManager.getBackStackEntryAt(i).getName());
        }
        return backStackScreens;
    }


    private ArrayList<Screen> updateCustomBackStack(Screen screen) {
        //remove screens not needed anymore by creating a new Stack with only useful screens
        ArrayList<Screen> screens_new = new ArrayList<>();
        ArrayList<Screen> screenToRemove = new ArrayList<>();
        for (Screen aBackStackScreen : screens) {
            if ((screen.isInPreviousScreen(aBackStackScreen)) || (aBackStackScreen.getScreenName().equals(screen.getScreenName())))
                screens_new.add(aBackStackScreen);
            else
                screenToRemove.add(aBackStackScreen);
        }
//        Log.e("NestedFragments", "screen.getPreviousScreens()");
//        printCustomBackStack(screen.getPreviousScreens());
        screens = screens_new;
//        Log.e("NestedFragments", "CustomBackStack ap");
//        printCustomBackStack();
        return screenToRemove;
    }

    public static void printCustomBackStack(String[] screenBackStack) {
        int i = 0;
        for (String s : screenBackStack) {
            i++;
            Log.e("NestedFragments", "screen " + i + " : " + s);
        }
    }

    public  void printCustomBackStack() {
        Log.e("BackStack", "printCustomBackStack ");
        int i = 0;
        for (Screen s : screens) {
            i++;
           // Log.e("NestedFragments", "screen " + i + " : " + s.name());
            Log.e("BackStack", "screen " + i + " : " + s.getScreenName()+" - fragmentManager : "+s.getFragmentManager());
        }
    }


    /**
     * This method return the previous screens of the current screen + the screen himself
     */
    private String[] getScreenCompleteBackStack(Screen screen) {
        Log.e("NestedFragments", "getScreenCompleteBackStack screen.getPreviousScreens() ");
        //printCustomBackStack(screen.getPreviousScreens());
        String[] screenBackStack = new String[screen.getPreviousScreens().length + 1];
        for (int i = 0; i < screen.getPreviousScreens().length; i++) {
            screenBackStack[i] = screen.getPreviousScreens()[i];
        }
        screenBackStack[screen.getPreviousScreens().length] = screen.getScreenName();
        Log.e("NestedFragments", "getScreenCompleteBackStack screenBackStack ");
        //printCustomBackStack(screenBackStack);
        return screenBackStack;
    }

    private void popBackStack(ArrayList<Screen> screensToRemove) {
        Log.e("NestedFragments", "popBackStack");
        for (Screen screenToRemove : screensToRemove) {
            Log.e("LifeCycle", "pop " + screenToRemove.getFragment()+" "+screenToRemove.getContainer().getId()+" "+this.getClass().getSimpleName());
            Log.e("NestedFragments", "screensToRemove " + screenToRemove.getScreenName());
            screenToRemove.getFragmentManager().popBackStackImmediate(screenToRemove.getScreenName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    private void recreateBackStacks(Screen screen) {
        Log.e("NestedFragments", "recreateBackStacks " + this.getClass().getSimpleName());
        //printCustomBackStack();
        recreateCustomBackStack(screen);
        //printCustomBackStack();
        //printBackStack(screen.getFragmentManager());
        recreateBackStack();
        //printBackStack(screen.getFragmentManager());

    }

    private void recreateBackStack() {
        Log.e("NestedFragments", "recreateBackStack " + this.getClass().getSimpleName());
        clearBackStack();
        for(Screen screen:screens){
            Log.e("NestedFragments", "screen " +screen+" - "+this.getClass().getSimpleName());
            screen.addToBackStack();
        }
    }

    private void clearBackStack() {
        for(int i=0;i<MainActivity.getInstance().getSupportFragmentManager().getBackStackEntryCount();i++) {
            Log.e("LifeCycle", "pop " +this.getClass().getSimpleName());
            MainActivity.getInstance().getSupportFragmentManager().popBackStackImmediate();
        }
    }


    private void recreateCustomBackStack(Screen screen) {
        screens.clear();
        for (String ps : screen.getPreviousScreens()) {
            screens.add(createScreenFromString(ps));
        }
        screens.add(screen);
    }

    private Screen createScreenFromString(String ps) {
        Screen screen=null;
        if (ps.equals(Screen.CENTER_CONTAINER_FRAGMENT_A_INIT_NAME))
            screen= new Screen(Screen.CENTER_CONTAINER_FRAGMENT_A_INIT_NAME).setScreen(MainActivity.getInstance().getMainFragmentContainer(), MainActivity.getInstance().getSupportFragmentManager(), new MainFragment(), null);
        if (ps.equals(Screen.CENTER_CONTAINER_FRAGMENT_B_INIT_NAME))
            screen= new Screen(Screen.CENTER_CONTAINER_FRAGMENT_B_INIT_NAME).setScreen(MainActivity.getInstance().getMainFragmentContainer(), MainActivity.getInstance().getSupportFragmentManager(), new MainFragment(), MainFragment.getBundleFragmentBInit());
        if (ps.equals(Screen.MAIN_CONTAINER_FRAGMENT_C_INIT_NAME))
            screen= new Screen(Screen.MAIN_CONTAINER_FRAGMENT_C_INIT_NAME).setScreen(MainActivity.getInstance().getMainFragmentContainer(), MainActivity.getInstance().getSupportFragmentManager(), FragmentC.getInstance(), null);
        if (ps.equals(Screen.MAIN_CONTAINER_FRAGMENT_C_LAST_NAME))
            screen= new Screen(Screen.MAIN_CONTAINER_FRAGMENT_C_LAST_NAME).setScreen(MainActivity.getInstance().getMainFragmentContainer(), MainActivity.getInstance().getSupportFragmentManager(), FragmentC.getInstance(), FragmentC.getBundleSample());
        if (ps.equals(Screen.CENTER_CONTAINER_FRAGMENT_B_LAST_NAME))
            screen= new Screen(Screen.CENTER_CONTAINER_FRAGMENT_B_LAST_NAME).setScreen(MainActivity.getInstance().getMainFragmentContainer(), MainActivity.getInstance().getSupportFragmentManager(), MainFragment.getInstance(), MainFragment.getBundleFragmentBSample());
        Log.w("NestedFragments", "Screen.toString() "+screen.toString());
        return screen;
    }


    public static void printBackStack(FragmentManager fm) {
        Log.e("BackStack", "printBackStack ");
        for (int i = 0; i < fm.getBackStackEntryCount(); i++)
            Log.e("BackStack", "BackStack(" + (i + 1) + ")" + fm.getBackStackEntryAt(i).getName());
    }

}
