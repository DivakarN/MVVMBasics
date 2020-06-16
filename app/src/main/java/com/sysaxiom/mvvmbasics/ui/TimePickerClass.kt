package com.sysaxiom.mvvmbasics.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.TimePicker
import android.widget.NumberPicker


class CustomIntervalTimePicker(context: Context, attrs: AttributeSet) : TimePicker(context, attrs) {
    private var timeChangedListener: TimePicker.OnTimeChangedListener? = null

    // We want to proxy all the calls to our member variable OnTimeChangedListener with our own
    // internal listener in order to make sure our overridden getCurrentMinute is called. Without
    // this some versions of android return the underlying minute index.
    private val internalTimeChangedListener =
        OnTimeChangedListener { view, hourOfDay, minute ->
            timeChangedListener!!.onTimeChanged(
                view,
                currentHour,
                currentMinute
            )
        }

    init {

        try {
            val classForId = Class.forName("com.android.internal.R\$id")
            val field = classForId.getField("minute")

            val minuteSpinner = this.findViewById(field.getInt(null)) as NumberPicker
            minuteSpinner.maxValue = 60 / TIME_PICKER_MINUTE_INTERVAL - 1
            val displayedValues = mutableListOf<String>()
            var i = 0
            while (i < 60) {
                displayedValues.add(String.format("%02d", i))
                i += TIME_PICKER_MINUTE_INTERVAL
            }
            minuteSpinner.displayedValues = displayedValues.toTypedArray()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun maxMinuteIndex(): Int {
        return 60 / TIME_PICKER_MINUTE_INTERVAL - 1
    }

    override fun setOnTimeChangedListener(onTimeChangedListener: TimePicker.OnTimeChangedListener) {
        super.setOnTimeChangedListener(internalTimeChangedListener)
        this.timeChangedListener = onTimeChangedListener
    }

    override fun getCurrentMinute(): Int {
        return super.getCurrentMinute() * TIME_PICKER_MINUTE_INTERVAL
    }

    override fun setCurrentMinute(currentMinute: Int) {
        var cleanMinute = currentMinute / TIME_PICKER_MINUTE_INTERVAL
        if (currentMinute % TIME_PICKER_MINUTE_INTERVAL > 0) {
            if (cleanMinute == maxMinuteIndex()) {
                cleanMinute = 0
                currentHour = currentHour + 1
            } else {
                cleanMinute++
            }
        }
        super.setCurrentMinute(cleanMinute)
    }

    companion object {

        private val TIME_PICKER_MINUTE_INTERVAL = 15
    }
}