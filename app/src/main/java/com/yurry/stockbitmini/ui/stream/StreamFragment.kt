package com.yurry.stockbitmini.ui.stream

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.yurry.stockbitmini.R
import com.yurry.stockbitmini.databinding.FragmentNotImplementedBinding
import com.yurry.stockbitmini.ui.viewBinding
import org.koin.android.viewmodel.ext.android.viewModel

class StreamFragment: Fragment(R.layout.fragment_not_implemented) {
    private val binding by viewBinding(FragmentNotImplementedBinding::bind)

    private val streamViewModel: StreamViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        streamViewModel.text.observe(viewLifecycleOwner, {
            binding.textDashboard.text = it
        })
    }
}