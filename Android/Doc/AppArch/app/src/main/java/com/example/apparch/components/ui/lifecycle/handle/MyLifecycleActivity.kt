package com.example.apparch.components.ui.lifecycle.handle

import android.content.Context
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.apparch.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber


internal class MyLocationListener(
    private val context: Context,
    private val callback: (Location) -> Unit
) {
    fun start() {}

    fun stop() {}
}

internal class MyLocationListener2(
    private val context: Context,
    private val lifecycle: Lifecycle,
    private val callback: (Location) -> Unit
) : DefaultLifecycleObserver{
    private var enabled = false


    override fun onStart(owner: LifecycleOwner) {
        if (enabled) {
            Timber.d("connect")
        }
    }

    override fun onStop(owner: LifecycleOwner) {
        Timber.d("disconnect if connected")
    }

    fun enable() {
        enabled = true
        lifecycle.addObserver(this)
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            Timber.d("connect if not connected")
        }
    }

}

class MyLifecycleActivity : AppCompatActivity() {
    private lateinit var myLocationListener: MyLocationListener
    private lateinit var myLocationListener2: MyLocationListener2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myLocationListener = MyLocationListener(this) { location ->

        }

        myLocationListener2 = MyLocationListener2(this, lifecycle) { location ->

        }
        lifecycleScope.launch {
            Timber.d("checkUserStatus")
            Util.checkUserStatus { result ->
                if (result) myLocationListener2.enable()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        myLocationListener.start()
    }

    override fun onStop() {
        super.onStop()
        myLocationListener.stop()
    }

}

private object Util {
    suspend fun checkUserStatus(callback: (Boolean) -> Unit) {
        delay(1000)
        callback.invoke(true)
    }
}

