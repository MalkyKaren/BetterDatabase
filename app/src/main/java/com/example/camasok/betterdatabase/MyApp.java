package com.example.camasok.betterdatabase;

import android.app.Application;
import android.content.Context;

/**
 * Created by Karen on 4/6/2016.
 */
public class MyApp extends Application {

    private static Context sContext;
    @Override
    public void onCreate() {
        sContext = getApplicationContext();
        super.onCreate();
    }

    public static Context getContext() {
        return sContext;
    }
}
