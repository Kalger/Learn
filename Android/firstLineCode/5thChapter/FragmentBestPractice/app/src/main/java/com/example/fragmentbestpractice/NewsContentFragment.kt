package com.example.fragmentbestpractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.fragmentbestpractice.databinding.NewsContentFragBinding

class NewsContentFragment : Fragment() {

    private lateinit var binding: NewsContentFragBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NewsContentFragBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun refresh(title: String, content: String) {
        binding.contentLayout.isVisible = true
        binding.newsTitle.text = title
        binding.newsContent.text = content
    }
}