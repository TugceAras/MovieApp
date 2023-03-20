package com.tugcearas.movieapp.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tugcearas.movieapp.R
import com.tugcearas.movieapp.databinding.FragmentLoginScreenBinding
import com.tugcearas.movieapp.util.extensions.click
import com.tugcearas.movieapp.util.extensions.toastMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginScreen : Fragment(),ClickLoginButton {

   private lateinit var binding: FragmentLoginScreenBinding
   private val loginViewModel: LoginVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login_screen,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.login = loginViewModel

        // set the loginCallback instance
        loginViewModel.loginCallback = this
        initObserve()
        clickLoginButton()
        loginWithWebsite()
    }

    private fun initObserve(){
        loginViewModel.message.observe(viewLifecycleOwner){message->
            requireContext().toastMessage(message = message!!)
        }
    }

    private fun clickLoginButton(){
        binding.loginButton.click {
            if (loginViewModel.username.value!!.isEmpty()) {
                requireContext().toastMessage(getString(R.string.check_username))
                return@click
            }
            if (loginViewModel.password.value!!.isEmpty()) {
                requireContext().toastMessage(getString(R.string.check_password))
                return@click
            }
            else {
                loginViewModel.createToken()
            }
        }
    }

    override fun onValidationSuccess() {
        val action = LoginScreenDirections.actionLoginScreenToPopularMovieScreen()
        findNavController().navigate(action)
    }

    private fun loginWithWebsite(){
        binding.websiteButton.click {
           val action = LoginScreenDirections.actionLoginScreenToLoginWebView()
            findNavController().navigate(action)
        }
    }
}