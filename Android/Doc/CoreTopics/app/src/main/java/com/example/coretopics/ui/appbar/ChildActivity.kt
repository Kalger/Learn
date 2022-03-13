package com.example.coretopics.ui.appbar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.ShareActionProvider
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuItemCompat
import com.example.coretopics.R
import timber.log.Timber


class ChildActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child)
        val toolbar = findViewById<Toolbar>(R.id.tool_bar)
        setSupportActionBar(toolbar)
        // Get a support ActionBar corresponding to this toolbar and enable the Up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.child_app_bar_menu, menu)
        setSearchAction(menu)
        setShareIcon(menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setSearchAction(menu: Menu?) {
        val listener = object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                return true
            }
        }
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchItem.setOnActionExpandListener(listener)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        R.id.action_favorite -> {
            Timber.d("action_favorite")
            true
        }
        R.id.action_setting -> {
            Timber.d("action_setting")
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun setShareIcon(menu: Menu?) {
        val shareItem = menu?.findItem(R.id.action_share)
        val myShareActionProvider: ShareActionProvider =
            MenuItemCompat.getActionProvider(shareItem) as ShareActionProvider

        val myShareIntent = Intent(Intent.ACTION_SEND)
        myShareIntent.type = "image/*"
        myShareIntent.putExtra(Intent.EXTRA_STREAM,
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQtqY16bfobbjivsZb2u6WEgzJ3C8PN0txWnA&usqp=CAU")
        myShareActionProvider.setShareIntent(myShareIntent)
    }


    companion object {
        fun start(ctx: Context) {
            ctx.startActivity(Intent(ctx, ChildActivity::class.java))
        }
    }
}