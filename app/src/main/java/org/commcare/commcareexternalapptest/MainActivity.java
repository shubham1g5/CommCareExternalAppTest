package org.commcare.commcareexternalapptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import static org.commcare.commcareexternalapptest.Permissions.ORG_COMMCARE_DALVIK_DEBUG_EXTERNAL_APP_PERMISSION;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Permissions.acquireAllAppPermissions(this, 1);
        Intent i = new Intent("org.commcare.dalvik.api.action.ExternalAction");
        i.setComponent(new ComponentName("org.commcare.dalvik.debug",
                "org.commcare.provider.ExternalApiReceiver"));
        i.putExtra("commcareaction", "logout");

        sendBroadcast(i, ORG_COMMCARE_DALVIK_DEBUG_EXTERNAL_APP_PERMISSION);
    }
}
