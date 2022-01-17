package com.learn.miscphone.permissions

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.learn.miscphone.databinding.ActivityRequestBinding

private const val TAG = "RequestActivity"

class RequestActivity : AppCompatActivity() {

    private lateinit var ackRationaleBtn: Button
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    private lateinit var requestPermissionLauncherBoth: ActivityResultLauncher<Array<String>>
    private lateinit var requestMultiPermissionOneByOne: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ackRationaleBtn = binding.ackRationale

        // init activity result launcher
        singlePermission()
        multiplePermission()
        initRequestMultiplePermissionOneByOne()

        binding.permissionRequest1Direct.setOnClickListener {
            // You can directly ask for the permission.
            // The registered ActivityResultCallback gets the result of this request.
            requestPermissionLauncher.launch(
                Manifest.permission.RECORD_AUDIO
            )
            Log.d(TAG, "requestPermissionLauncher")
        }

        binding.permissionRequest1.setOnClickListener {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.RECORD_AUDIO
                ) == PackageManager.PERMISSION_GRANTED -> {
                    Log.d(TAG, "checkSelfPermission")
                    // You can use the API that requires the permission.
                }
                shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO) -> {
                    // In an educational UI, explain to the user why your app requires this
                    // permission for a specific feature to behave as expected. In this UI,
                    // include a "cancel" or "no thanks" button that allows the user to
                    // continue using your app without granting the permission.
                    showInContextUI()
                    Log.d(TAG, "shouldShowRequestPermissionRationale")
                }
                else -> {
                    // You can directly ask for the permission.
                    // The registered ActivityResultCallback gets the result of this request.
                    requestPermissionLauncher.launch(
                        Manifest.permission.RECORD_AUDIO
                    )
                    Log.d(TAG, "requestPermissionLauncher")
                }
            }
        }

        binding.permissionRequest2.setOnClickListener {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED -> {
                    Log.d(TAG, "checkSelfPermission")
                    // You can use the API that requires the permission.
                }
                shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION) -> {
                    // In an educational UI, explain to the user why your app requires this
                    // permission for a specific feature to behave as expected. In this UI,
                    // include a "cancel" or "no thanks" button that allows the user to
                    // continue using your app without granting the permission.
                    showInContextUI2()
                    Log.d(TAG, "shouldShowRequestPermissionRationale")
                }
                else -> {
                    // You can directly ask for the permission.
                    // The registered ActivityResultCallback gets the result of this request.
                    requestPermissionLauncher.launch(
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                    Log.d(TAG, "requestPermissionLauncher")
                }
            }
        }

        binding.permissionRequestBoth.setOnClickListener {
            val bothPermission = arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION, Manifest
                .permission
                .RECORD_AUDIO)
            when {
                hasPermission(*bothPermission) -> {
                    Log.d(TAG, "checkSelfPermission")
                    // You can use the API that requires the permission.
                }
                shouldShowRationale(*bothPermission) -> {
                    // In an educational UI, explain to the user why your app requires this
                    // permission for a specific feature to behave as expected. In this UI,
                    // include a "cancel" or "no thanks" button that allows the user to
                    // continue using your app without granting the permission.
                    showInContextUI2()
                    Log.d(TAG, "shouldShowRequestPermissionRationale")
                }
                else -> {
                    // You can directly ask for the permission.
                    // The registered ActivityResultCallback gets the result of this request.
                    Log.d(TAG, "requestPermissionLauncher")
                    requestPermissionLauncherBoth.launch(bothPermission)
                }
            }
        }

        binding.multiplePermissionRequestAudioOneByOne.setOnClickListener {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.RECORD_AUDIO
                ) == PackageManager.PERMISSION_GRANTED -> {
                    Log.d(TAG, "checkSelfPermission")
                    // You can use the API that requires the permission.
                }
                shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO) -> {
                    // In an educational UI, explain to the user why your app requires this
                    // permission for a specific feature to behave as expected. In this UI,
                    // include a "cancel" or "no thanks" button that allows the user to
                    // continue using your app without granting the permission.
                    showInContextUI3()
                    Log.d(TAG, "shouldShowRequestPermissionRationale")
                }
                else -> {
                    // You can directly ask for the permission.
                    // The registered ActivityResultCallback gets the result of this request.
                    requestMultiPermissionOneByOne.launch(
                        arrayOf(Manifest.permission.RECORD_AUDIO)
                    )
                    Log.d(TAG, "requestPermissionLauncher")
                }
            }
        }
        binding.multiplePermissionRequestLocOneByOne.setOnClickListener {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED -> {
                    Log.d(TAG, "checkSelfPermission")
                    // You can use the API that requires the permission.
                }
                shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO) -> {
                    // In an educational UI, explain to the user why your app requires this
                    // permission for a specific feature to behave as expected. In this UI,
                    // include a "cancel" or "no thanks" button that allows the user to
                    // continue using your app without granting the permission.
                    showInContextUILoc()
                    Log.d(TAG, "shouldShowRequestPermissionRationale")
                }
                else -> {
                    // You can directly ask for the permission.
                    // The registered ActivityResultCallback gets the result of this request.
                    requestMultiPermissionOneByOne.launch(
                        arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION)
                    )
                    Log.d(TAG, "requestPermissionLauncher")
                }
            }
        }
    }

    /**
     * use this, we avoid multiple callbacks
     */
    private fun initRequestMultiplePermissionOneByOne() {
        requestMultiPermissionOneByOne =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
                    results ->
                Log.d(TAG, "one by one results: $results")
                val audioResult = results[Manifest.permission.RECORD_AUDIO]
                val locationResult = results[Manifest.permission.ACCESS_COARSE_LOCATION]
                audioResult?.let { grant ->
                    if(grant) Log.d(TAG, "one by one audio grant")
                    else Log.d(TAG, "one by one audio deny")
                }
                locationResult?.let { grant ->
                    if(grant) Log.d(TAG, "one by one loc grant")
                    else Log.d(TAG, "one by one loc deny")
                }
            }
    }

    private fun hasPermission(vararg permissions: String) = permissions.all {
        ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun shouldShowRationale(vararg permissions: String) = permissions.any {
        shouldShowRequestPermissionRationale(it)
    }

    private fun multiplePermission() {
        // Register the permissions callback, which handles the user's response to the
        // system permissions dialog. Save the return value, an instance of
        // ActivityResultLauncher. You can use either a val, as shown in this snippet,
        // or a lateinit var in your onAttach() or onCreate() method.
        requestPermissionLauncherBoth =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions())
            {
                if (it.entries.all { it.value }) {
                    Log.d(TAG, "grant")
                } else {
                    Log.d(TAG, "one or all deny")
                }
            }

    }

    private fun singlePermission() {
        // Register the permissions callback, which handles the user's response to the
        // system permissions dialog. Save the return value, an instance of
        // ActivityResultLauncher. You can use either a val, as shown in this snippet,
        // or a lateinit var in your onAttach() or onCreate() method.
        requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                    Log.d(TAG, "grant")

                } else {
                    // Explain to the user that the feature is unavailable because the
                    // features requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                    Log.d(TAG, "deny")
                }
            }
    }

    private fun showInContextUI() {
        Toast.makeText(this, "說明為何需要這項權限", Toast.LENGTH_SHORT).show()
        ackRationaleBtn.isVisible = true
        // After the user acknowledges the rationale, continue to the next step.(request permission)
        ackRationaleBtn.setOnClickListener {
            ackRationaleBtn.isInvisible = true
            requestPermissionLauncher.launch(
                Manifest.permission.RECORD_AUDIO
            )
        }
    }

    private fun showInContextUI3() {
        Toast.makeText(this, "說明為何需要這項權限", Toast.LENGTH_SHORT).show()
        ackRationaleBtn.isVisible = true
        // After the user acknowledges the rationale, continue to the next step.(request permission)
        ackRationaleBtn.setOnClickListener {
            ackRationaleBtn.isInvisible = true
            requestMultiPermissionOneByOne.launch(
                arrayOf(Manifest.permission.RECORD_AUDIO)
            )
        }
    }

    private fun showInContextUILoc() {
        Toast.makeText(this, "說明為何需要這項權限", Toast.LENGTH_SHORT).show()
        ackRationaleBtn.isVisible = true
        // After the user acknowledges the rationale, continue to the next step.(request permission)
        ackRationaleBtn.setOnClickListener {
            ackRationaleBtn.isInvisible = true
            requestMultiPermissionOneByOne.launch(
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION)
            )
        }
    }

    private fun showInContextUI2() {
        Toast.makeText(this, "說明為何需要這項權限", Toast.LENGTH_SHORT).show()
        ackRationaleBtn.isVisible = true
        // After the user acknowledges the rationale, continue to the next step.(request permission)
        ackRationaleBtn.setOnClickListener {
            ackRationaleBtn.isInvisible = true
            requestPermissionLauncherBoth.launch(
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.RECORD_AUDIO)
            )
        }
    }
}