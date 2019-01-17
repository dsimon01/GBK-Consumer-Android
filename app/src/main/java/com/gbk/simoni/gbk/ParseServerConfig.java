package com.gbk.simoni.gbk;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;


// This class is the first thing that executes when the project is run.

public class ParseServerConfig extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Config keys with Parse Server.
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("a150e5d0e42666ddf6864edf392bed450d9bb305")
                .clientKey("ec3b36f8e6eea59e2a7d52b4358efaf7594aaca8")
                .server("http://3.8.131.250:80/parse/")
                .build()
        );

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

    }
}

// Public documentation about Parse Server: https://docs.parseplatform.org/android/guide/