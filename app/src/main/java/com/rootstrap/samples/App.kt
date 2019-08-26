package com.rootstrap.samples

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.rootstrap.samples.fake.FakeData
import com.rootstrap.samples.util.ioThread

val AppContext by lazy {
    App.context!!
}

const val ALREADY_LOAD_FAKE_DATA = "LOAD_FAKE_DATA"

class App : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
        var preference: SharedPreferences? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        preference = PreferenceManager.getDefaultSharedPreferences(context)

        ioThread.execute { preloadFakeData() }
    }

    private fun preloadFakeData() {
        if (preference!!.getBoolean(ALREADY_LOAD_FAKE_DATA, true)) {
            FakeData().loadFakeUsers()
            preference!!.edit().putBoolean(ALREADY_LOAD_FAKE_DATA, false).apply()
        }
    }
}
