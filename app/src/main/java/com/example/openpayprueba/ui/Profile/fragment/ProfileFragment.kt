package com.example.openpayprueba.ui.Profile.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.openpayprueba.databinding.FragmentProfileBinding
import com.example.openpayprueba.ui.Profile.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val profileViewModel: ProfileViewModel by viewModels()

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
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
                    val singlePopularPerson = response
                    binding.tvPrincipal.text = response.toString()
                    Log.d("MUSKI", singlePopularPerson.toString())
                }
            }

            if(uiModel.showClientError != null && !uiModel.showClientError.consumed){
                //When the api returns client error
                Log.d("MUSKI", "error")
            }

            if(uiModel.showServerError != null && !uiModel.showServerError.consumed){
                //When the api returns server error
                Log.d("MUSKI", "error")
            }
        })
    }
}