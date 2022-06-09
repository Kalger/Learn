package com.example.apparch

import android.app.Application
import android.os.Build
import android.util.Log
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // https://github.com/JakeWharton/timber/blob/trunk/timber-sample/src/main/java/com/example/timber/ExampleApp.java
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant()
        }
    }

    class CrashReportingTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) return

            FakeCrashLibrary.log(priority, tag, message)

            if (t != null) {
                if (priority == Log.ERROR) {
                    FakeCrashLibrary.logError(t)
                } else if (priority == Log.WARN) FakeCrashLibrary.logWarning(t)
            }

        }
    }
}

