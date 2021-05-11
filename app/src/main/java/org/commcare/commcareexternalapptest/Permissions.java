package org.commcare.commcareexternalapptest;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Permissions {
    public final static int ALL_PERMISSIONS_REQUEST = 1;

    public static final String ORG_COMMCARE_DALVIK_DEBUG_EXTERNAL_APP_PERMISSION = "org.commcare.dalvik.permission.EXTERNAL_ACTION";

    public static boolean acquireAllAppPermissions(Activity activity, int permRequestCode) {
        String[] permissions = getAppPermissions();

        if (missingAppPermission(activity, permissions)) {
            requestNeededPermissions(activity, permRequestCode);
            return true;
        } else {
            return false;
        }
    }

    private static boolean missingAppPermission(Activity activity,
                                                String[] permissions) {
        for (String perm : permissions) {
            if (missingAppPermission(activity, perm)) {
                return true;
            }
        }
        return false;
    }

    public static boolean missingAppPermission(Activity activity,
                                               String permission) {
        return ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_DENIED;
    }

    public static String[] getAppPermissions() {
        return new String[]{
                ORG_COMMCARE_DALVIK_DEBUG_EXTERNAL_APP_PERMISSION
        };
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static void requestNeededPermissions(Activity activity, int requestCode) {
        ActivityCompat.requestPermissions(activity, getAppPermissions(), requestCode);
    }

}
