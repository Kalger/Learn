package com.learn.miscphone

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment
import com.learn.miscphone.constranitlayout.FlowActivity
import com.learn.miscphone.misc.style.ThemeAttrActivity
import com.learn.miscphone.permissions.RequestActivity
import com.learn.miscphone.template.basicactivity.BasicActivity
import com.learn.miscphone.template.navdraweractivity.NavDrawerActivity

class MainFragment : ListFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.Planets, android.R.layout
            .simple_list_item_1)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        when (position) {
            0 -> startActivity(Intent(requireContext(), RequestActivity::class.java))
            1 -> startActivity(Intent(requireContext(), BasicActivity::class.java))
            2 -> startActivity(Intent(requireContext(), NavDrawerActivity::class.java))
            3 -> startActivity(Intent(requireContext(), FlowActivity::class.java))
            4 -> startActivity(Intent(requireContext(), ThemeAttrActivity::class.java))
        }
    }
}