package com.learn.miscphone.showcase.permissions

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.learn.miscphone.databinding.FragmentRequestBinding

private const val TAG = "RequestFragment"

class RequestFragment : Fragment() {


    private lateinit var requestLauncher: ActivityResultLauncher<Array<String>>
    private var _binding: FragmentRequestBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestLauncher = registerForActivityResult(
            ActivityResultContracts
            .RequestMultiplePermissions()) {
            val audioResult = it[android.Manifest.permission.RECORD_AUDIO]
            audioResult?.let { grant ->
                if (grant) Log.d(TAG, "grant")
                else Log.d(TAG, "deny")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRequestBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = _binding!!
        binding.requestAudio.setOnClickListener {
            when {
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.RECORD_AUDIO
                ) == PackageManager.PERMISSION_GRANTED -> {
                    Log.d(TAG, "checkSelfPermission")
                    // You can use the API that requires the permission.
                }
                shouldShowRequestPermissionRationale(android.Manifest.permission.RECORD_AUDIO) -> {
                    // In an educational UI, explain to the user why your app requires this
                    // permission for a specific feature to behave as expected. In this UI,
                    // include a "cancel" or "no thanks" button that allows the user to
                    // continue using your app without granting the permission.
                    Log.d(TAG, "shouldShowRequestPermissionRationale")
                }
                else -> {
                    // You can directly ask for the permission.
                    // The registered ActivityResultCallback gets the result of this request.
                    requestLauncher.launch(arrayOf(android.Manifest.permission.RECORD_AUDIO))
                    Log.d(TAG, "requestPermissionLauncher")
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}