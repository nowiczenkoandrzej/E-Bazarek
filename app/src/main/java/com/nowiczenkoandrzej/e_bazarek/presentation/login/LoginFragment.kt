package com.nowiczenkoandrzej.e_bazarek.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.nowiczenkoandrzej.e_bazarek.utils.AccountState
import com.nowiczenkoandrzej.e_bazarek.databinding.FragmentLoginBinding
import com.nowiczenkoandrzej.e_bazarek.extensions.hide
import com.nowiczenkoandrzej.e_bazarek.extensions.quickToast
import com.nowiczenkoandrzej.e_bazarek.extensions.show
import com.nowiczenkoandrzej.e_bazarek.extensions.startApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        subscribeCollector()
    }

    private fun setListeners(){
        binding.buttonSignIn.setOnClickListener {
            setSingInButtonListener()
        }
        binding.buttonSingUp.setOnClickListener {
            setSingUpButtonListener()
        }
    }

    private fun setSingInButtonListener(){

        if(binding.inputEmail.text!!.isBlank() ||
            binding.inputPassword.text!!.isBlank()){
            quickToast("Invalid e-mail or password")
            return
        }

        val email = binding.inputEmail.text?.trim().toString()
        val password = binding.inputPassword.text?.trim().toString()
        viewModel.singIn(email, password)

    }

    private fun setSingUpButtonListener() = findNavController().navigate(
        LoginFragmentDirections
            .actionLoginFragmentToRegistrationFragment()
            .actionId)

    private fun subscribeCollector(){
        lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.loginState.collect{ state ->
                    when(state){
                        is AccountState.Success -> {
                            binding.progressBar.hide()
                            startApp(requireContext())
                        }
                        is AccountState.Failure -> {
                            binding.progressBar.hide()
                            quickToast(state.error)
                        }
                        is AccountState.Loading -> binding.progressBar.show()
                        is AccountState.Empty -> Unit
                    }
                }
            }
        }
    }

}