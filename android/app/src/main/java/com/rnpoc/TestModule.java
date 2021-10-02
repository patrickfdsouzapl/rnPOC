package com.rnpoc;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.raul.ServerSettings;
import com.raul.SipController;

public class TestModule extends ReactContextBaseJavaModule {
    TestModule(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "TestModule";
    }

    @ReactMethod
    public void testToast(String text) {
        ReactApplicationContext reactContext = this.getReactApplicationContext();
        Toast.makeText(this.getReactApplicationContext(), text, Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sendEvent(reactContext, "EventReminder", Arguments.createMap());

                try {
                    SipController sipController = new SipController();
                    sipController.init(reactContext, getSettings(reactContext.getApplicationContext()));

//            Log.d("SIPINTIALIZATION", "sip controller initialize" + " with username " + endPointUserIdMobile + " and pass " + endPointPassword);
                } catch (Exception ex) {
                    Log.d("SIPINTIALIZATION", "sip controller excetption " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }, 1000);
    }

    @ReactMethod
    public void initSIP() {
        ReactApplicationContext reactContext = this.getReactApplicationContext();
        Context appContext = reactContext.getApplicationContext();
        try {
            SipController sipController = new SipController();
            sipController.init(reactContext, getSettings(appContext));

//            Log.d("SIPINTIALIZATION", "sip controller initialize" + " with username " + endPointUserIdMobile + " and pass " + endPointPassword);
        } catch (Exception ex) {
            Log.d("SIPINTIALIZATION", "sip controller excetption " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void sendEvent(ReactContext reactContext,
                           String eventName,
                           @Nullable WritableMap params) {
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }

    private ServerSettings getSettings(Context applicationContext){
        ServerSettings serverSettings = new ServerSettings();
        serverSettings.username = "6a67ac8c-f714-4b33-badd-56ed1b6821aa-mob";
        serverSettings.password = "e6qpeU44Tu";
                /*serverSettings.username = "mobileTest2";
                serverSettings.password = "g7cr";*/
        serverSettings.domain = "g7cr.in";
        serverSettings.dnsServer = "8.8.8.8";
        serverSettings.proxyServer = "78.159.97.108:9060";
        serverSettings.transport = "tcp";// tcp - udp
        serverSettings.mediaType = "srtp";// mand - srtp
        serverSettings.regTimer = "10";
        serverSettings.peerCallID = "";
        serverSettings.token = "";
        serverSettings.url = "";
        serverSettings.disconnectUrl = "";
        serverSettings.configPath = "/data/data/" + applicationContext.getPackageName() + "/files/";
//        Log.d("SIPINTIALIZATION", "sip controller dns " + sharedPreferenceHelper.getDnsServer());
//        Log.d("SIPINTIALIZATION", "sip controller proxy " + sharedPreferenceHelper.getProxyServer());

        return  serverSettings;
    }
}
