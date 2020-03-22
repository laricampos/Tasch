package com.sample.tasch.ui

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import com.sample.tasch.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    fun setToolbarTitle(title: String) {
        toolbar_title.text = title
        toolbar_title.isVisible = true
        toolbar_app_name.isVisible = false
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun showToolbarAppName() {
        toolbar_title.isVisible = false
        toolbar_app_name.isVisible = true
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onSupportNavigateUp(): Boolean {
        return (Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()
                || super.onSupportNavigateUp())
    }
}