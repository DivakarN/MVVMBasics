package com.sysaxiom.mvvmbasics.ui.navigationview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sysaxiom.mvvmbasics.R
import kotlinx.android.synthetic.main.activity_bottom_navigation_view.*
import kotlinx.android.synthetic.main.activity_navigation_view.*
import kotlinx.android.synthetic.main.activity_navigation_view.drawer_layout

class BottomNavigationViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation_view)

        val navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupWithNavController(bottom_nav_view, navController)
    }
}
