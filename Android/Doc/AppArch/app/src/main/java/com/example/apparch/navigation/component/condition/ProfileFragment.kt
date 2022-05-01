package com.example.apparch.navigation.component.condition

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.apparch.R

class ProfileFragment : Fragment() {

    private val viewModel by activityViewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        val navController = findNavController()
        val currentBackStackEntry = navController.currentBackStackEntry!!
        val savedStateHandle = currentBackStackEntry.savedStateHandle
        // 從 loginFragment 退回的瞬間，observer 就會收到通知，比 onCreateView 還早觸發
        savedStateHandle.getLiveData<Boolean>(LoginFragment.LOGIN_SUCCESSFUL)
            .observe(currentBackStackEntry) { success ->
            Log.d(TAG, "observe LOGIN_SUCCESSFUL")
            if (!success) {
                val startDestination = navController.graph.startDestinationId
                val navOptions = NavOptions.Builder()
                    .setPopUpTo(startDestination, true)
                    .build()
                navController.navigate(startDestination, null, navOptions)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView")
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")

        viewModel.user.observe(viewLifecycleOwner) { user ->
            Log.d(TAG, "observe user")
            if (user != null) showWelcomeMsg()
            else findNavController().navigate(R.id.loginFragment)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")

    }

    private fun showWelcomeMsg() {
        Toast.makeText(requireContext(), "welcome", Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val TAG = "ProfileFragment"
    }

}