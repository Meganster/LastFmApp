package com.appsfactory.testtask.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.appsfactory.testtask.utils.getValue
import com.appsfactory.testtask.utils.makeSnackbar

abstract class BaseFragment<ViewBindingT : ViewBinding> : Fragment() {

    abstract val viewModel: BaseViewModel

    abstract val binding: ViewBindingT

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initObserveSnackBar()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun initObserveSnackBar() {
        viewModel.showSnackbar.observe(viewLifecycleOwner) {
            it.take()?.let { snackbarMessage ->
                binding.root.makeSnackbar(snackbarMessage.getValue(requireContext())).show()
            }
        }
    }
}