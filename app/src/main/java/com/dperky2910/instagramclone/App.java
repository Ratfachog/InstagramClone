package com.dperky2910.instagramclone;

import android.app.Application;
import com.parse.Parse;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("7fMqFVhbL4DfbzRnzd1xRhT4uIzJtsS9hxJ3B4Ir")
                // if defined
                .clientKey("FbHwnB62Ek1E3tZdYvpn7PvkriI8Jb07upEndk2q")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
