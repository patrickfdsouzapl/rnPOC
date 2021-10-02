package com.raul;

import android.content.Context;
import android.util.Log;


public class SipController {

    private static final String TAG = "SipController";

    static {
        try {
            System.loadLibrary("native-lib");
            Log.e(TAG, "Success");

        } catch (UnsatisfiedLinkError exc) {
            Log.e(TAG, exc.toString());
            Log.e(TAG, "native-lib library not found");
        }
    }

        private OnRegistrationEventListener onRegistrationEventListener;
    private OnCallEventListener onCallEventListener;
    private OnLogEventListener onLogEventListener;
    private OnErrorEventListener onErrorEventListener;

    public synchronized void onRegistration(RegistrationEvent event, String user) {
        Log.d(TAG, "onRegistration");
        if (onRegistrationEventListener != null)
            onRegistrationEventListener.onRegistrationEvent(event, user);
    }

    public synchronized void onCall(CallEvent event, String user, String callId) {
        Log.d(TAG, "onCall");
        if (onCallEventListener != null)
            onCallEventListener.onCallEvent(event, user, callId);
    }

    public synchronized void onLog(String log) {
        Log.d(TAG, "onLog: " + log);
        if (onLogEventListener != null)
            onLogEventListener.onLogEvent(log);
    }

    public synchronized void onError(ErrorType type, String error) {
        Log.d(TAG, "onError: " + error);
        if (onErrorEventListener != null)
            onErrorEventListener.onErrorEvent(type, error);
    }

    public synchronized void registerOnRegistrationEventListener(OnRegistrationEventListener listener) {
        onRegistrationEventListener = listener;
    }

    public synchronized void registerOnCallEventListener(OnCallEventListener listener) {
        onCallEventListener = listener;
    }

    public synchronized void registerOnLogEventListener(OnLogEventListener listener) {
        onLogEventListener = listener;
    }

    public synchronized void registerOnErrorEventListener(OnErrorEventListener listener) {
        onErrorEventListener = listener;
    }

    public native void init(Context context, ServerSettings serverSettings);

    public native void registerUser(String username, String password);

    public native void unregisterUser();

    public native void deleteStack();

    public native String makeCall(String sipUri);

    public native void answer(String tCallId);

                    public native void endCall(String tCallId);

    public enum RegistrationEvent {
        REGISTERED,
        REGISTRED_FAILED,
        NOT_REGISTERED
    }

    public enum CallEvent {
        INCOMING_CALL,
        TERMINATE_CALL,
        CALL_RINGING,
        CALL_PROGRESS,
        CALL_ACCEPTED,
        CALL_NO_AUDIO,
        CALL_GOT_AUDIO
    }
    public enum ErrorType {
        WEBRTC_ERROR,
        SIP_CONNECTION_ERROR,
        SIP_SESSION_ERROR
    }
    public static interface OnRegistrationEventListener {
        void onRegistrationEvent(RegistrationEvent event, String user);
    }
    public static interface OnCallEventListener {
        void onCallEvent(CallEvent event, String user, String callId);
    }
    public static interface OnLogEventListener {
        void onLogEvent(String log);
    }
    public static interface OnErrorEventListener {
        void onErrorEvent(ErrorType type, String message);
    }
}
