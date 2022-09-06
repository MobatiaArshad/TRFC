package com.a71cities.trfc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.HapticFeedbackConstants
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.findNavController
import com.a71cities.trfc.databinding.ActivityMainBinding
import com.a71cities.trfc.extras.Pref
import com.a71cities.trfc.utils.BottomBar

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var bottomBar: BottomBar
    private var navControl: NavController? = null
    private var desitnation: NavController.OnDestinationChangedListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        val bottomBarArray = arrayListOf(
            binding.bottomBarInc.home,
            binding.bottomBarInc.news,
            binding.bottomBarInc.events,
            binding.bottomBarInc.account
        )

        bottomBar = BottomBar(bottomBarArray).apply {
            setSelection(0)
        }

        bottomBarArray.forEachIndexed { index, imageView ->
            imageView.setOnClickListener { v ->
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                bottomBar.setSelection(index)

                when(index) {
                    0 -> navControl?.navigate(R.id.homeFragment)
                    1 -> navControl?.navigate(R.id.newsFragment)
                    2 -> navControl?.navigate(R.id.eventFragment)
                    3 -> navControl?.navigate(R.id.profileFragment)
                }
            }
        }


        desitnation = NavController.OnDestinationChangedListener { controller, destination, arguments ->
            binding.bottomBarInc.root.isVisible = !arrayOf(R.id.signInFragment,R.id.signUpFragment).contains(destination.id)
        }
    }

    fun changeNav(index: Int) {
        bottomBar.setSelection(index)

        when(index) {
//            -3 -> navControl?.navigate(R.id.signUpFragment)
            -2 -> navControl?.navigate(R.id.signUpFragment)
            -1 -> navControl?.navigate(R.id.signInFragment)
            0 -> navControl?.navigate(R.id.homeFragment)
            1 -> navControl?.navigate(R.id.newsFragment)
            2 -> navControl?.navigate(R.id.eventFragment)
            3 -> navControl?.navigate(R.id.profileFragment)
        }
    }

    override fun onStart() {
        super.onStart()
        navControl = findNavController(binding.fragmentContainer.id)

        val inflaters = navControl?.navInflater
        val graph = inflaters?.inflate(R.navigation.home_nav)

        if (Pref.isLogged) graph?.setStartDestination(R.id.homeFragment)
        else graph?.setStartDestination(R.id.signInFragment)

        navControl!!.graph = graph!!
    }

    override fun onResume() {
        super.onResume()
        navControl?.addOnDestinationChangedListener(desitnation!!)
    }

    override fun onPause() {
        navControl?.removeOnDestinationChangedListener(desitnation!!)
        super.onPause()
    }

    override fun onBackPressed() {
        if (arrayOf(R.id.homeFragment, R.id.newsFragment, R.id.eventFragment, R.id.profileFragment).contains(navControl?.currentDestination!!.id)){
            navControl?.navigate(R.id.homeFragment)
            bottomBar.setSelection(0)
        } else if (navControl?.currentDestination!!.id == R.id.homeFragment) {
            finish()
            super.onBackPressed()
        } else {
            navControl?.navigateUp()
        }
    }
}