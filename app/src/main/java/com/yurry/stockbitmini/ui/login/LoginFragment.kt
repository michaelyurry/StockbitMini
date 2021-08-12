package com.yurry.stockbitmini.ui.login

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.yurry.stockbitmini.R
import com.yurry.stockbitmini.Util
import com.yurry.stockbitmini.data.local.LOGIN_NORMAL
import com.yurry.stockbitmini.data.local.model.ProfileEntity
import com.yurry.stockbitmini.databinding.FragmentLoginBinding
import com.yurry.stockbitmini.ui.viewBinding
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.fragment_login) {
    private val binding by viewBinding(FragmentLoginBinding::bind)

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUserAlreadyLogin()
        initButtonListener()
        initFormValidationObserver()
        initBackPressed()
    }

    private fun initUserAlreadyLogin(){
        loginViewModel.isUserAlreadyLogin.observe(viewLifecycleOwner, {
            if(it){
                launchMainFragment()
            }
        })
    }

    private fun initButtonListener(){
        binding.loginButton.setOnClickListener {
            loginViewModel.login(
                ProfileEntity(
                binding.usernameEditText.text.toString().trim(),
                binding.passwordEditText.text.toString().trim(),
                LOGIN_NORMAL)
            )
            Util.hideSoftKeyboard(requireActivity())
        }
    }

    private fun launchMainFragment(){
        Navigation.findNavController(requireView()).navigate(R.id.action_navigation_login_to_navigation_main)
    }

    private fun initFormValidationObserver(){
        loginViewModel.formValidation.observe(viewLifecycleOwner, Observer{
            when(it){
                LoginViewModel.Validation.USERNAME_INVALID ->
                    binding.usernameEditText.error = getString(R.string.username_not_valid)
                LoginViewModel.Validation.PASSWORD_INVALID ->
                    binding.passwordEditText.error = getString(R.string.password_not_valid)
                else -> return@Observer
            }
        })
    }

    private fun initBackPressed(){
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }
}