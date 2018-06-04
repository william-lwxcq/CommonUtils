package com.example.commonutils;

/**
 * Created by lw on 18-1-2.
 */

public interface PermissionsResultListener {
    void onPermissionGranted();
    void onPermissionDenied(String message);
}
