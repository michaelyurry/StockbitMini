package com.yurry.stockbitmini.ui.chat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.yurry.stockbitmini.R
import com.yurry.stockbitmini.databinding.ActivityMainBinding.bind
import com.yurry.stockbitmini.databinding.FragmentNotImplementedBinding
import com.yurry.stockbitmini.ui.viewBinding

class ChatFragment : Fragment(R.layout.fragment_not_implemented) {
    private val binding by viewBinding(FragmentNotImplementedBinding::bind)

    private val chatViewModel: ChatViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chatViewModel.text.observe(viewLifecycleOwner, {
            binding.textDashboard.text = it
        })
    }
}