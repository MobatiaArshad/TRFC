package com.a71cities.trfc.utils

import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.a71cities.trfc.R
import com.a71cities.trfc.application.MyApp

class BottomBar(val array: ArrayList<ImageView>, val selection: Int = 0) {

    fun setSelection(position: Int){
        array.forEachIndexed { index, image ->
            if (index == position){
                image.setColorFilter(ContextCompat.getColor(MyApp.instance, R.color.unselected_yellow))
            } else {
                image.setColorFilter(ContextCompat.getColor(MyApp.instance, R.color.selected_red))
            }
        }
    }

}