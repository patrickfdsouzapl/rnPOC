package com.raul;
import android.content.Context;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import androidx.appcompat.app.AppCompatActivity;
//import com.spider.devg7crplus.R;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SipController sipController = new SipController();
    private CallState callState = CallState.IDLE;
    private AudioManager audioManager;
    public  static String tCallId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main1);
//
//        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//
//        final Button affirmativeButton = (Button) findViewById(R.id.affirmativeButton);
//        final Button negativeButton = (Button) findViewById(R.id.negativeButton);
//        final RadioButton user1001RadioButton = (RadioButton) findViewById(R.id.user1001RadioButton);
//        final RadioButton callee1001RadioButton = (RadioButton) findViewById(R.id.callee1001RadioButton);
//        final RadioButton callee1002RadioButton = (RadioButton) findViewById(R.id.callee1002RadioButton);
//        final RadioButton callee1003RadioButton = (RadioButton) findViewById(R.id.callee1003RadioButton);
//        final RadioButton callee1004RadioButton = (RadioButton) findViewById(R.id.callee1004RadioButton);
//        final RadioButton audioOnlyRadioButton = (RadioButton) findViewById(R.id.audioOnlyRadioButton);
//        final RadioButton audioAndVideoRadioButton = (RadioButton) findViewById(R.id.audioAndVideoRadioButton);
//        affirmativeButton.setText("Connect");
//        negativeButton.setText("");
//
//        AssetManager assetManager = getResources().getAssets();
//        String[] files = null;
//
//        try {
//            files = assetManager.list("Files");
//        } catch (Exception e) {
//            Log.d("test", "ERROR : " + e.toString());
//        }
//
//        for (int i = 0; i < files.length; i++) {
//            InputStream in = null;
//            OutputStream out = null;
//            FileOutputStream fileOutStream = null;
//            try {
//                Log.d("test", "file names : " + files[i]);
//
//                in = assetManager.open("Files/" + files[i]);
//                out = new FileOutputStream(getApplicationContext().getFilesDir() + files[i]);
//
//                File file = new File(getApplicationContext().getFilesDir(), files[i]);
//
//                byte[] buffer = new byte[65536 * 2];
//                int read;
//                while ((read = in.read(buffer)) != -1) {
//                    out.write(buffer, 0, read);
//                }
//                in.close();
//                in = null;
//
//                out.flush();
//                fileOutStream = new FileOutputStream(file);
//                fileOutStream.write(buffer);
//                out.close();
//                out = null;
//                Log.d("test", "File Copied in storage");
//            } catch (Exception e) {
//                Log.d("test", "ERROR: " + e.toString());
//            }
//        }
//
//        ServerSettings serverSettings = new ServerSettings();
//        serverSettings.username = "43211";
//        serverSettings.password = "popup";
//        serverSettings.domain = "172.104.24.13:5091";
//        serverSettings.dnsServer = "8.8.8.8";
//        serverSettings.proxyServer = "172.104.24.13:5091";
//        serverSettings.transport = "tcp";
//        serverSettings.mediaType = "srtp"; //srtp-mand
//        serverSettings.regTimer = "10";
//        serverSettings.peerCallID = "";
//        serverSettings.token = "";
//        serverSettings.configPath = "/data/data/com.raul/";
//
//        sipController.init(this, serverSettings);
//
//        affirmativeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (callState == CallState.IDLE) {
//                    String username = "";
//                    String password = "123456";
//                    if (user1001RadioButton.isChecked())
//                        username = "43211";
//                    sipController.registerUser(username, password);
//
//                    Log.e(TAG, "Regiseter called...");
//
//                    affirmativeButton.setText("Call");
//                    negativeButton.setText("Disconnect");
//                    callState = CallState.CONNECTED;
//                } else if (callState == CallState.CONNECTED) {
//                    String callee = "";
//                    if (callee1001RadioButton.isChecked())
//                        callee = "1234";
//                    else if (callee1002RadioButton.isChecked())
//                        callee = "4321";
//                    else if (callee1003RadioButton.isChecked())
//                        callee = "43211";
//                    else if (callee1004RadioButton.isChecked())
//                        callee = "12344";
//                    String sipUrl = callee;
//                    if (audioOnlyRadioButton.isChecked()) {
//                        audioManager.setSpeakerphoneOn(false);
//                    } else if (audioAndVideoRadioButton.isChecked()) {
//                        audioManager.setSpeakerphoneOn(true);
//                    }
//                    tCallId = sipController.makeCall(sipUrl);
//                    affirmativeButton.setText("");
//                    negativeButton.setText("End Call");
//                    callState = CallState.IN_CALL;
//                } else if (callState == CallState.INCOMING_CALL) {
//                    if (audioOnlyRadioButton.isChecked()) {
//                        audioManager.setSpeakerphoneOn(false);
//                    } else if (audioAndVideoRadioButton.isChecked()) {
//                        audioManager.setSpeakerphoneOn(true);
//                    }
//                    sipController.answer(tCallId);
//                    affirmativeButton.setText("");
//                    negativeButton.setText("End Call");
//                    callState = CallState.IN_CALL;
//                }
//            }
//        });
//
//        negativeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (callState == CallState.IN_CALL) {
//                    sipController.endCall(tCallId);
//                    affirmativeButton.setText("Call");
//                    negativeButton.setText("Disconnect");
//                    callState = CallState.CONNECTED;
//                } else if (callState == CallState.INCOMING_CALL) {
//                    sipController.endCall(tCallId);
//                    affirmativeButton.setText("Call");
//                    negativeButton.setText("Disconnect");
//                    callState = CallState.CONNECTED;
//                } else if (callState == CallState.CONNECTED) {
//                    sipController.unregisterUser();
//                    affirmativeButton.setText("Connect");
//                    negativeButton.setText("");
//                    callState = CallState.IDLE;
//                }
//            }
//        });
//
//        sipController.registerOnCallEventListener(new SipController.OnCallEventListener() {
//            @Override
//            public void onCallEvent(SipController.CallEvent event, String user, String callId) {
//                final Button affirmativeButton = (Button) findViewById(R.id.affirmativeButton);
//                final Button negativeButton = (Button) findViewById(R.id.negativeButton);
//                if (event == SipController.CallEvent.INCOMING_CALL) {
//                    tCallId = callId;
//                    runOnUiThread(new Runnable() {
//                        public void run() {
//                            affirmativeButton.setText("Answer");
//                            negativeButton.setText("Decline");
//                            callState = CallState.INCOMING_CALL;
//                        }
//                    });
//                } else if (event == SipController.CallEvent.TERMINATE_CALL) {
//                    runOnUiThread(new Runnable() {
//                        public void run() {
//                            affirmativeButton.setText("Call");
//                            negativeButton.setText("Disconnect");
//                            callState = CallState.CONNECTED;
//                        }
//                    });
//                }
//
//            }
//        });
//
//        sipController.registerOnRegistrationEventListener(new SipController.OnRegistrationEventListener() {
//            @Override
//            public void onRegistrationEvent(SipController.RegistrationEvent event, String user) {
//
//            }
//        });
//
//
//        sipController.registerOnErrorEventListener(new SipController.OnErrorEventListener() {
//            @Override
//            public void onErrorEvent(SipController.ErrorType type, String message) {
//                Log.e(TAG, String.format("Error - %s", message));
//                final Button affirmativeButton = (Button) findViewById(R.id.affirmativeButton);
//                final Button negativeButton = (Button) findViewById(R.id.negativeButton);
//                if (callState == CallState.IN_CALL && type == SipController.ErrorType.WEBRTC_ERROR) {
//                    runOnUiThread(new Runnable() {
//                        public void run() {
//                            sipController.endCall(tCallId);
//                            affirmativeButton.setText("Call");
//                            negativeButton.setText("Disconnect");
//                            callState = CallState.CONNECTED;
//                        }
//                    });
//                } else if ((callState == CallState.IN_CALL ||
//                        callState == CallState.INCOMING_CALL) &&
//                        type == SipController.ErrorType.SIP_SESSION_ERROR) {
//                    runOnUiThread(new Runnable() {
//                        public void run() {
//                            if (callState == CallState.IN_CALL) {
//                                sipController.endCall(tCallId);
//                            } else {
//                                sipController.endCall(tCallId);
//                            }
//                            affirmativeButton.setText("Call");
//                            negativeButton.setText("Disconnect");
//                            callState = CallState.CONNECTED;
//                        }
//                    });
//                } else if ((callState == CallState.IN_CALL ||
//                        callState == CallState.INCOMING_CALL ||
//                        callState == CallState.CONNECTED) &&
//                        type == SipController.ErrorType.SIP_CONNECTION_ERROR) {
//                    runOnUiThread(new Runnable() {
//                        public void run() {
//                            if (callState == CallState.CONNECTED) {
//                                if (callState == CallState.IN_CALL) {
//                                    sipController.endCall(tCallId);
//                                } else if (callState == CallState.INCOMING_CALL) {
//                                    sipController.endCall(tCallId);
//                                }
//                                sipController.unregisterUser();
//                                affirmativeButton.setText("Connect");
//                                negativeButton.setText("");
//                                callState = CallState.IDLE;
//                            }
//                        }
//                    });
//                }
//
//            }
//        });
//        sipController.registerOnLogEventListener(new SipController.OnLogEventListener() {
//            @Override
//            public void onLogEvent(String log) {
//                Log.d(TAG, log);
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, String.format("Calling on Destroy .... - "));
        sipController.deleteStack();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    private enum CallState {
        IDLE,
        CONNECTED,
        INCOMING_CALL,
        IN_CALL
    }
}
