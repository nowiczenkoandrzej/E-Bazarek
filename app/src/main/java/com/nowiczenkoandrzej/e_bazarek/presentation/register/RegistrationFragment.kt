package com.nowiczenkoandrzej.e_bazarek.presentation.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.nowiczenkoandrzej.e_bazarek.utils.AccountState
import com.nowiczenkoandrzej.e_bazarek.databinding.FragmentRegistrationBinding
import com.nowiczenkoandrzej.e_bazarek.extensions.hide
import com.nowiczenkoandrzej.e_bazarek.extensions.quickToast
import com.nowiczenkoandrzej.e_bazarek.extensions.show
import com.nowiczenkoandrzej.e_bazarek.extensions.startApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding

    private val viewModel: RegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSingUpButtonListener()
        subscribeCollector()
    }

    private fun setSingUpButtonListener(){

        binding.buttonSignUp.setOnClickListener {
            if(isInputNotValid()) return@setOnClickListener
            createAccount()
        }
    }

    private fun isInputNotValid(): Boolean {
        return if(binding.inputEmail.text!!.isBlank() ||
            binding.inputPassword.text!!.isBlank() ||
            binding.inputRepeatPassword.text!!.isBlank()){

            quickToast("You have to fill all fields")
            true
        } else
            false
    }

    private fun createAccount(){
        val email = binding.inputEmail.text?.trim().toString()
        val password = binding.inputPassword.text?.trim().toString()
        val repeatedPassword = binding.inputRepeatPassword.text?.trim().toString()

        if(password == repeatedPassword)
            viewModel.createUser(email, password)
        else
            quickToast("Passwords are not the same")
    }

    private fun subscribeCollector(){
        lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.registrationState.collect{ state ->
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