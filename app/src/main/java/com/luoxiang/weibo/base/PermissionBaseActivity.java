package com.luoxiang.weibo.base;

import android.support.annotation.NonNull;

import com.luoxiang.weibo.R;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * projectName: 	    WeiBo
 * packageName:	        com.luoxiang.weibo.base
 * className:	        PermissionBaseActivity
 * author:	            Luoxiang
 * time:	            2016/12/20	15:04
 * desc:	            权限管理类别的基类
 *
 * svnVersion:	        $Rev
 * upDateAuthor:	    Vincent
 * upDate:	            2016/12/20
 * upDateDesc:	        TODO
 */

public abstract class PermissionBaseActivity extends BaseActivity implements
                                                                  EasyPermissions.PermissionCallbacks{

    protected static final int RC_SETTINGS_SCREEN = 200;
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this, getString(R.string.rationale_ask_again))
                    .setTitle(getString(R.string.title_settings_dialog))
                    .setPositiveButton(getString(R.string.setting))
                    .setNegativeButton(getString(R.string.cancel), null /* click listener */)
                    .setRequestCode(RC_SETTINGS_SCREEN)
                    .build()
                    .show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

}
