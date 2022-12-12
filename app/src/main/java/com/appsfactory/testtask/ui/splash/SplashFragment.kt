package com.appsfactory.testtask.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.appsfactory.testtask.R
import com.appsfactory.testtask.databinding.FragmentSplashBinding
import com.appsfactory.testtask.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isAuthSucceeded.collect { event ->
                    event?.take()?.let {
                        findNavController().navigate(R.id.action_screenSplash_to_screenFavoriteAlbums)
                    }
                }
            }
        }
    }
}