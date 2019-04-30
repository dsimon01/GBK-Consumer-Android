package com.gbk.simoni.gbk;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseACL;


// This class is the first thing that executes when the project is run.

public class ParseServerConfig extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enables Local Data store.
        Parse.enableLocalDatastore(this);

        // Config keys with Parse Server.
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("a150e5d0e42666ddf6864edf392bed450d9bb305")
                .clientKey("ec3b36f8e6eea59e2a7d52b4358efaf7594aaca8")
                .server("http://35.178.251.100:80/parse/")
                .build()
        );

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

    }
}

// Public documentation about Parse Server: https://docs.parseplatform.org/android/guide/