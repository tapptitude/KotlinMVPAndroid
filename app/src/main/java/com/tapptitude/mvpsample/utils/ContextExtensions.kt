package com.tapptitude.mvpsample.utils

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.content.ContextCompat

fun Context.hasPermissions(vararg permissions: String): Boolean {
    var arePermissionsGranted = true

    for (permission in permissions) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            arePermissionsGranted = false
        }
    }

    return arePermissionsGrantedAutomatically() || arePermissionsGranted
}

private fun arePermissionsGrantedAutomatically(): Boolean {
    return Build.VERSION.SDK_INT < Build.VERSION_CODES.M
}