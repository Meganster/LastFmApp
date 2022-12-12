package com.appsfactory.testtask.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.appsfactory.testtask.utils.getValue
import com.appsfactory.testtask.utils.makeSnackbar
import kotlinx.coroutines.launch

abstract class BaseFragment<ViewBindingT : ViewBinding> : Fragment() {

    abstract val viewModel: BaseViewModel

    abstract val binding: ViewBindingT

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initObserveSnackBar()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun initObserveSnackBar() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.showSnackbar.collect {
                    it?.take()?.let { snackbarMessage ->
                        binding.root.makeSnackbar(snackbarMessage.getValue(requireContext())).show()
                    }
                }
            }
        }
    }
}