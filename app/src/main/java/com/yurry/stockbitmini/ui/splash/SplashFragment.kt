package com.yurry.stockbitmini.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.yurry.stockbitmini.R
import com.yurry.stockbitmini.databinding.FragmentSplashBinding
import com.yurry.stockbitmini.ui.viewBinding
import org.koin.android.viewmodel.ext.android.viewModel

class SplashFragment : Fragment(R.layout.fragment_splash) {
    private val binding by viewBinding(FragmentSplashBinding::bind)

    private val splashViewModel: SplashViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        splashViewModel.isUserAlreadyLogin.observe(viewLifecycleOwner, {
            Handler(Looper.getMainLooper()).postDelayed({
                if(it){
                    findNavController().navigate(R.id.action_navigation_splash_to_navigation_main)
                } else{
                    findNavController().navigate(R.id.action_navigation_splash_to_navigation_login)
                }
            }, 1500)
        })
    }
}