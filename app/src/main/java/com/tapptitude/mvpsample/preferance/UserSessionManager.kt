package com.tapptitude.mvpsample.preferance

import android.content.SharedPreferences
import android.text.TextUtils
import com.google.gson.Gson
import com.tapptitude.mvpsample.data.model.User
import javax.inject.Inject

/**
 * @author Radu Dorin
 */

private const val USER_DATA_KEY = "USER_DATA_KEY"
private const val SESSION_TOKEN_KEY = "SESSION_TOKEN_KEY"
private const val IP_INFO_KEY = "IP_INFO_KEY"

class UserSessionManager @Inject constructor(
        private val gson: Gson,
        private val userPreference: SharedPreferences
) {

    fun getUser(): User? {
        val stringUserData = userPreference.getString(USER_DATA_KEY, "")

        if (TextUtils.isEmpty(stringUserData)) {
            return null
        }

        return gson.fromJson(stringUserData, User::class.java)
    }

    fun saveUser(user: User) {
        val stringUserData = gson.toJson(user)

        userPreference.edit()
                .putString(USER_DATA_KEY, stringUserData)
                .apply()
    }

    fun saveIp(ip: String) {
        userPreference.edit().putString(IP_INFO_KEY, ip).apply()
    }

    fun isUserLoggedIn(): Boolean {
        return getSessionToken() != null
    }

    fun saveSessionToken(newSessionToken: String) {
        userPreference.edit().putString(SESSION_TOKEN_KEY, newSessionToken).apply()
    }

    fun getSessionToken(): String? {
        return userPreference.getString(SESSION_TOKEN_KEY, null)
    }

    fun clearSession() {
        userPreference.edit()
                .clear()
                .apply()
    }
}