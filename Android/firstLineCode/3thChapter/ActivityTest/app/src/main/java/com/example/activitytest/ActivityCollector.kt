package com.example.activitytest

import android.app.Activity
import android.os.Process.killProcess
import android.os.Process.myPid

object ActivityCollector {
    private val activities = ArrayList<Activity>()

    fun addActivity(activity: Activity) {
        activities.add(activity)
    }

    fun removeActivity(activity: Activity) {
        activities.remove(activity)
    }

    fun finishAll() {
        for (activity in activities) {
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
        activities.clear()
        // 若要殺掉 process
//        killProcess(myPid())
    }
}