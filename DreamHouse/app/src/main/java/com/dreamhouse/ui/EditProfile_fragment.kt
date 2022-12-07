package com.dreamhouse.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dreamhouse.HomeActivity
import com.dreamhouse.R

class EditProfile_fragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_edit_profile, container, false)
    }

    fun voltar(view: View){
        activity?.let {
            startActivity(Intent(it, HomeActivity::class.java))
        }
    }
}


/*override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    // binding = ActivityMapBinding.inflate(inflater)
    viewModel = MapViewModel(this)
    viewModel.getHospitals()
    setActions()

    // return binding.root
    return inflater.inflate(R.layout.activity_map, container, false)
}*/