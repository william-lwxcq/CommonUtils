package com.example.commonutils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;


/**
 * Created by lw on 18-1-2.
 */

public class PermissionHelper {
    private static PermissionHelper helper;
    private Context mContext;
    private static final String PACKAGE = "package:";
    private PermissionsResultListener listener;
    private int requestCode = 0;
    private boolean mNeedFinish = false;

    public void setPermissionResultListener(PermissionsResultListener listener) {
        this.listener = listener;
    }

    protected PermissionHelper(Context context) {
        this.mContext = context;
    }

    public static PermissionHelper getInstance(Context context) {
        if (helper == null) {
            helper = new PermissionHelper(context);
        }
        return helper;
    }

    public boolean checkPermissions(String... permissions) {
        for (String per : permissions) {
            if (!checkPermissions(per)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkPermissions(String permissions) {
        return ContextCompat.checkSelfPermission(mContext, permissions) == PackageManager.PERMISSION_GRANTED;
    }

    //when you need ask the permission use this method
    public void requestPermissions(Activity activity, String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
        this.requestCode = requestCode;
    }

    public PermissionHelper onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, boolean finish) {
        this.mNeedFinish = finish;
        if (this.requestCode == requestCode) {
            // 获取被拒绝的权限列表
            ArrayList<String> deniedPermissions = new ArrayList<>();
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(mContext, permission) != PackageManager.PERMISSION_GRANTED) {
                    deniedPermissions.add(permission);
                }
            }
            // 判断被拒绝的权限中是否有包含必须具备的权限
            ArrayList<String> forceRequirePermissionsDenied =
                    checkForceRequirePermissionDenied(FORCE_REQUIRE_PERMISSIONS, deniedPermissions);
            if (forceRequirePermissionsDenied != null && forceRequirePermissionsDenied.size() > 0) {
                // 必备的权限被拒绝，
                if (mNeedFinish) {
                    showMissingPermissionDialog();
                } else {
                    if (listener != null) {
                        listener.onPermissionDenied("null");
                    }
                }
            } else {
                // 不存在必备的权限被拒绝，可以进首页
                if (listener != null) {
                    listener.onPermissionGranted();
                }
            }
        } else {
            listener.onPermissionDenied("request code don't match!");
        }

        return this;
    }

    protected static final ArrayList<String> FORCE_REQUIRE_PERMISSIONS = new ArrayList<String>() {
        {
            add(Manifest.permission.INTERNET);
            add(Manifest.permission.READ_EXTERNAL_STORAGE);
            add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    };


    private ArrayList<String> checkForceRequirePermissionDenied(
            ArrayList<String> forceRequirePermissions, ArrayList<String> deniedPermissions) {
        ArrayList<String> forceRequirePermissionsDenied = new ArrayList<>();
        if (forceRequirePermissions != null && forceRequirePermissions.size() > 0
                && deniedPermissions != null && deniedPermissions.size() > 0) {
            for (String forceRequire : forceRequirePermissions) {
                if (deniedPermissions.contains(forceRequire)) {
                    forceRequirePermissionsDenied.add(forceRequire);
                }
            }
        }
        return forceRequirePermissionsDenied;
    }

    // 显示缺失权限提示
    private void showMissingPermissionDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        final AlertDialog alertDialog = builder.create();

        builder.setMessage("当前应用缺少必要权限。\n\n请点击\"设置\"-\"权限\"-打开所需权限。\n\n最后点击两次后退按钮，即可返回。");
        // 拒绝, 退出应用
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startAppSettings();
            }
        });

        builder.show();
    }

    // 启动应用的设置
    public void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse(PACKAGE + mContext.getPackageName()));
        mContext.startActivity(intent);
    }
}
