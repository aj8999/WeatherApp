package com.wheatherapp.helper

import android.content.Context

import android.content.SharedPreferences


class SharedprefrenceManager {

    private val PREFS_NAME = "WheatherApp"
    private var context: Context? = null
    private var prefs: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    fun SharedPreferenceManager() {}

    fun SharedPreferenceManager(context: Context?) {
        this.context = context
    }

    fun connectDB() {
        prefs = context!!.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = prefs!!.edit()
    }

    fun closeDB() {
        editor!!.commit()
    }

    fun clear() {
        editor!!.clear()
    }

    fun setInt(key: String?, value: Int) {
        editor!!.putInt(key, value)
    }

    fun getInt(key: String?): Int {
        return prefs!!.getInt(key, 1)
    }

    fun setFloat(key: String?, value: Float) {
        editor!!.putFloat(key, value)
    }

    fun getFloat(key: String?): Float {
        return prefs!!.getFloat(key, 0f)
    }

    fun setBoolean(key: String?, value: Boolean) {
        editor!!.putBoolean(key, value)
    }

    fun getBoolean(key: String?): Boolean {
        return prefs!!.getBoolean(key, false)
    }

    fun setString(key: String?, value: String?) {
        editor!!.putString(key, value)
    }

    fun getString(key: String?): String? {
        return prefs!!.getString(key, "")
    }

    fun remove(key: String?) {
        editor!!.remove(key)
    }
}