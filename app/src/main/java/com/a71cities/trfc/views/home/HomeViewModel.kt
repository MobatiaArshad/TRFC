package com.a71cities.trfc.views.home

import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.a71cities.trfc.R
import com.a71cities.trfc.application.MyApp
import com.a71cities.trfc.views.home.model.CategoriesData
import com.a71cities.trfc.views.home.model.SliderData

class HomeViewModel : ViewModel() {

    init {
        getBannerItems()
        getCategories()
    }

    fun getCategories(): MutableLiveData<ArrayList<CategoriesData>> =
        MutableLiveData(arrayListOf(
            CategoriesData(ContextCompat.getDrawable(MyApp.instance,R.drawable.academy_ico)!!,"Academy"),
            CategoriesData(ContextCompat.getDrawable(MyApp.instance,R.drawable.team_icon)!!,"Teams"),
            CategoriesData(ContextCompat.getDrawable(MyApp.instance,R.drawable.gallery_ico)!!,"Gallery")
        ))

    fun getBannerItems(): MutableLiveData<ArrayList<SliderData>> =
        MutableLiveData(
            arrayListOf(
                SliderData(
                    "https://travancoreroyals.in/wp-content/uploads/2022/01/Web-1920-%E2%80%93-1.png"
                ),
                SliderData(
                    "https://travancoreroyals.in/wp-content/uploads/2021/05/TRFC-TEAM.jpeg"
                ),
                SliderData(
                    "https://travancoreroyals.in/wp-content/uploads/2019/10/Photo_1572105767402.jpg"
                ),
                SliderData(
                    "https://travancoreroyals.in/wp-content/uploads/2020/01/wainag10-scaled-e1580031126406-1536x586.jpg"
                )
            )
        )

}