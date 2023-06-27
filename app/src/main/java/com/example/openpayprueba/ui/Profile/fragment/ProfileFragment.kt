package com.example.openpayprueba.ui.Profile.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.openpayprueba.R
import com.example.openpayprueba.ui.Profile.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val profileViewModel: ProfileViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileViewModel.getPopularPeople()

        profileViewModel.getPopularPeopleState.observe(viewLifecycleOwner, Observer {
            val uiModel = it ?: return@Observer

            if(uiModel.showProgress){
                //Here when is called and is not success or failed
            }

            if(uiModel.showSuccess != null && !uiModel.showSuccess.consumed){
                uiModel.showSuccess?.consume()?.let { response ->
                 //When the api call returns success
                }
            }
        })
    }
}