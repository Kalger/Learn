package com.example.apparch.navigation.component.condition

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.findNavController
import com.example.apparch.R


class LoginFragment : Fragment() {

    private val userViewModel by activityViewModels<UserViewModel>()
    private lateinit var savedStateHandle: SavedStateHandle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
        savedStateHandle.set(LOGIN_SUCCESSFUL, false)

        val cbSuccess = view.findViewById<CheckBox>(R.id.cb_success)
        view.findViewById<Button>(R.id.login).setOnClickListener { login(cbSuccess.isChecked) }
    }

    private fun login(success: Boolean) {
        userViewModel.login(success).observe(viewLifecycleOwner) { result ->
            if (result.success) {
                savedStateHandle.set(LOGIN_SUCCESSFUL, true)
                findNavController().popBackStack()
            } else showErrorMsg()
        }
    }

    private fun showErrorMsg() {
        Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val LOGIN_SUCCESSFUL = "LOGIN_SUCCESSFUL"
    }

}