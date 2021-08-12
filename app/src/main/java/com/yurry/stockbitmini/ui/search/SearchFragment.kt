package com.yurry.stockbitmini.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.yurry.stockbitmini.R
import com.yurry.stockbitmini.databinding.FragmentNotImplementedBinding
import com.yurry.stockbitmini.ui.viewBinding
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(R.layout.fragment_not_implemented) {
    private val binding by viewBinding(FragmentNotImplementedBinding::bind)

    private val searchViewModel: SearchViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchViewModel.text.observe(viewLifecycleOwner, {
            binding.textDashboard.text = it
        })
    }
}