package com.example.apparch.components.ui.lifecycle.coroutine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.example.apparch.R
import com.example.apparch.components.ui.lifecycle.livedata.user
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import timber.log.Timber
import kotlin.time.Duration.Companion.seconds

class MyFragment : Fragment() {

    val viewModel by viewModels<MyViewModel>()

    init {

//        caseWhenStarted()
        caseLaunchWhenStarted()
//        caseWhenStartedTest()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_con_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        caseLifecycleScope()
//        caseRepeatOnLifecycle()
//        caseFlowWithLifecycle()
//        caseMultipleFlowsParallel()
    }

    private fun caseLaunchWhenStarted() {
        lifecycleScope.launchWhenStarted {
            try {
                // Call some suspend functions.
                Timber.d("launchWhenStarted start")
                delay(5.seconds)
                Timber.d("launchWhenStarted end")
            } catch (exception: CancellationException){
                if (lifecycle.currentState == Lifecycle.State.DESTROYED)
                    Timber.d("CancellationException")
            } finally {
                if (lifecycle.currentState == Lifecycle.State.DESTROYED)
                    Timber.d("launchWhenStarted finally")

                /**
                [Lifecycle.whenStateAtLeast] 有解釋 finally block might execute after Lifecycle is DESTROYED.
                 */
                if (lifecycle.currentState >= Lifecycle.State.STARTED) {
                    // Here, since we've checked, it is safe to run any
                    // Fragment transactions.
                }
            }
        }
    }

    private fun caseWhenStarted() {
        lifecycleScope.launch {
            whenStarted {
                showLoading()
                val canAccess = withContext(Dispatchers.IO) {
                    checkUserAccess()
                }
                hideLoading()
                if (canAccess) findNavController().popBackStack()
                else {
                    // show content
                }

            }
        }
    }

    private fun caseWhenStartedTest() {
        lifecycleScope.launch {
            whenStarted {
                Timber.d("whenStarted start")
                delay(5.seconds)
                Timber.d("whenStarted end")
            }
        }
    }

    private fun hideLoading() {
    }

    private fun checkUserAccess(): Boolean {
        return false
    }

    private fun showLoading() {

    }

    private fun caseMultipleFlowsParallel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Because collect is a suspend function, if you want to
                // collect multiple flows in parallel, you need to do so in
                // different coroutines.
                launch {
                    viewModel.someDataFlow.collect { }
                }
                launch {
                    viewModel.someDataFlow2.collect {

                    }
                }
            }
        }
    }

    private fun caseFlowWithLifecycle() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.someDataFlow.flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .collect {
                Timber.d(it)
            }
        }
    }

    private fun caseRepeatOnLifecycle() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                Timber.d("repeatOnLifecycle")
                viewModel.someDataFlow.collect {

                }
            }
        }
    }

    private fun caseLifecycleScope() {
        val textView = TextView(requireContext())
        viewLifecycleOwner.lifecycleScope.launch {
            val params = TextViewCompat.getTextMetricsParams(textView)
            val precomputedText = withContext(Dispatchers.Default) {
                // This can be expensive, so computing this on a background thread before your text
                // will be presented can save work on the UI thread
                PrecomputedTextCompat.create("test", params)
            }
            TextViewCompat.setPrecomputedText(textView, precomputedText)
        }
    }
}