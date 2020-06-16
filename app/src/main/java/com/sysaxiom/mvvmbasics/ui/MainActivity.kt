package com.sysaxiom.mvvmbasics.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TimePicker
import com.sysaxiom.mvvmbasics.ui.navigationview.TabViewActivity
import com.sysaxiom.mvvmbasics.ui.recyclerview.AppointmentActivity
import com.sysaxiom.mvvmbasics.ui.navigationview.BottomNavigationViewActivity
import com.sysaxiom.mvvmbasics.ui.navigationview.NavigationViewActivity
import com.sysaxiom.mvvmbasics.ui.room.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var timePicker : TimePicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.sysaxiom.mvvmbasics.R.layout.activity_main)

        timePicker = findViewById(com.sysaxiom.mvvmbasics.R.id.timePicker)
        timePicker.setOnTimeChangedListener(mStartTimeChangedListener)

        bottomNavigationviewMVVM.setOnClickListener {
            Intent(this, BottomNavigationViewActivity::class.java).also {
                this.startActivity(it)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
        }

        navigationviewMVVM.setOnClickListener {
            Intent(this, NavigationViewActivity::class.java).also {
                this.startActivity(it)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
        }

        recyclerviewMVVM.setOnClickListener {
            Intent(this, AppointmentActivity::class.java).also {
                this.startActivity(it)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
        }

        tabViewMVVM.setOnClickListener {
            Intent(this, TabViewActivity::class.java).also {
                this.startActivity(it)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
        }

        roomMVVM.setOnClickListener {
            Intent(this, LoginActivity::class.java).also {
                this.startActivity(it)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
        }
    }

    private val mStartTimeChangedListener =
        TimePicker.OnTimeChangedListener { view, hourOfDay, minute ->
            updateDisplay(
                view,
                hourOfDay,
                minute
            )
        }

    private fun updateDisplay(timePicker: TimePicker, hourOfDay: Int, minute: Int) {
        var nextMinute = 0

        timePicker.setOnTimeChangedListener(mNullTimeChangedListener)

        if (minute >= 45 && minute <= 59)
            nextMinute = 45
        else if (minute >= 30)
            nextMinute = 30
        else if (minute >= 15)
            nextMinute = 15
        else if (minute > 0)
            nextMinute = 0
        else {
            nextMinute = 45
        }

        if (minute - nextMinute == 1) {
            if (minute >= 45 && minute <= 59)
                nextMinute = 0
            else if (minute >= 30)
                nextMinute = 45
            else if (minute >= 15)
                nextMinute = 30
            else if (minute > 0)
                nextMinute = 15
            else {
                nextMinute = 15
            }
        }

        timePicker.minute = nextMinute

        timePicker.setOnTimeChangedListener(mStartTimeChangedListener)

    }

    private val mNullTimeChangedListener =
        TimePicker.OnTimeChangedListener { view, hourOfDay, minute -> }

}
