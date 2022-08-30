package com.a71cities.trfc.views.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.a71cities.trfc.R
import com.a71cities.trfc.databinding.FragmentProfileBinding
import com.a71cities.trfc.utils.loadGlide

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)



        binding.profileImage.loadGlide("https://images.beinsports.com/3-5LwKWNKpt4D_i5wA34wc2Jelg=/full-fit-in/1000x0/mesutozil-cropped_6ijybiomqysb1in8drhjtcbwa.jpg")

    }

}