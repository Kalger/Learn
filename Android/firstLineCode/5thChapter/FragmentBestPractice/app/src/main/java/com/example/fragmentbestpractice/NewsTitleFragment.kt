package com.example.fragmentbestpractice

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentbestpractice.databinding.NewsTitleFragBinding

class NewsTitleFragment : Fragment(), LifecycleObserver {
    private var isTwoPane = false
    private var _binding: NewsTitleFragBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.lifecycle?.addObserver(this)
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onActivityCreateEvent() {
        Log.d("NewsTitleFragment", "onActivityCreateEvent")
        isTwoPane = requireActivity().findViewById<FragmentContainerView>(R.id.newsContentLayout) != null
        activity?.lifecycle?.removeObserver(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NewsTitleFragBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.newsTitleRecyclerView.adapter = NewsAdapter(getNews())
    }

    private fun getNews(): List<News> {
        val newsList = ArrayList<News>()
        for (i in 1..50) {
            val news = News("This is news title $i", getRandomLengthString("This is news content " +
                    "$i. "))
            newsList.add(news)
        }
        return newsList
    }

    private fun getRandomLengthString(str: String): String {
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(n) {
            builder.append(str)
        }
        return builder.toString()
    }

    inner class NewsAdapter(val newsList: List<News>) : RecyclerView.Adapter<NewsAdapter
    .ViewHolder>() {
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val newsTitle: TextView = view.findViewById(R.id.newsTitle)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent,
                false)
            val holder = ViewHolder(view)
            holder.itemView.setOnClickListener {
                val news = newsList[holder.adapterPosition]
                if (isTwoPane) {
                    val fragment = parentFragmentManager.findFragmentById(R.id.newsContentFrag)
                            as NewsContentFragment
                    fragment.refresh(news.title, news.content)
                } else {
                    NewsContentActivity.actionStart(parent.context, news.title, news.content)
                }
            }
            return holder
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val news = newsList[position]
            holder.newsTitle.text = news.title
        }

        override fun getItemCount() = newsList.size
    }
}