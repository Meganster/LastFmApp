package com.appsfactory.testtask.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.appsfactory.testtask.R
import com.appsfactory.testtask.databinding.FragmentSplashBinding
import com.appsfactory.testtask.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override lateinit var binding: FragmentSplashBinding
    override val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentSplashBinding.inflate(inflater)

        initObservers()

        viewModel.completeAuth()

        return binding.root
    }

    private fun initObservers() {
        viewModel.isAuthSucceeded.observe(viewLifecycleOwner) {
            it.take()?.let {
                findNavController().navigate(R.id.action_screenSplash_to_screenFavoriteAlbums)
            }
        }
    }
}