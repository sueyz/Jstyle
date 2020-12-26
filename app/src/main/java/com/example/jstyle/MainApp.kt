package com.example.jstyle

//import air.com.my.bonuslink.member.BuildConfig
//import air.com.my.bonuslink.util.FirebaseAnalyticsUtil
import com.example.jstyle.util.SharedPreferencesUtil
import android.app.Application
import android.content.Context
//import com.facebook.stetho.Stetho
//import com.google.firebase.crashlytics.FirebaseCrashlytics
//import com.jakewharton.threetenabp.AndroidThreeTen
//import tech.linjiang.pandora.Pandora


class MainApp : Application() {
    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        SharedPreferencesUtil.init(applicationContext)
//        AndroidThreeTen.init(this) // to init timeZone info
//        FirebaseAnalyticsUtil.init(applicationContext)
//        if (!BuildConfig.DEBUG) {
//        enable this when is needed to debug the registration flow
//        Pandora.get().disableShakeSwitch()
//        }
//        this is to disable the crashlytic
//        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(!BuildConfig.DEBUG)
//        Stetho.initializeWithDefaults(this)
    }
}