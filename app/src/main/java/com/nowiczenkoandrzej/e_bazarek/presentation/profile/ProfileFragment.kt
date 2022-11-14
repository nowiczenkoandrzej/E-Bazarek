package com.nowiczenkoandrzej.e_bazarek.presentation.profile

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.nowiczenkoandrzej.e_bazarek.utils.DataState
import com.nowiczenkoandrzej.e_bazarek.R
import com.nowiczenkoandrzej.e_bazarek.data.models.UserResponse
import com.nowiczenkoandrzej.e_bazarek.databinding.FragmentProfileBinding
import com.nowiczenkoandrzej.e_bazarek.extensions.*
import com.nowiczenkoandrzej.e_bazarek.presentation.activities.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var actionBarMenu: MenuItem

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObserver()
        addMenu()
        setListeners()
    }


    private fun subscribeObserver(){
        lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.userState.collect{ state ->
                    when(state){
                        is DataState.Success -> bindUserData(state.data!!)
                        is DataState.Loading -> displayUserLoading()
                        is DataState.Error -> displayUserError(state.message!!)
                    }
                }
            }
        }
    }

    private fun bindUserData(user: UserResponse){
        binding.userDataProgressBar.hide()
        binding.userContainer.show()
        if(user.image == ""){
            binding.ivAvatar.setImageResource(R.drawable.ic_baseline_default_avatar)
        }
        binding.tvName.text = user.name
        binding.tvEmail.text = user.email
        binding.tvRating.text = user.score.toString()

    }

    private fun displayUserLoading(){
        binding.userContainer.hide()
        binding.userDataProgressBar.show()
    }

    private fun displayUserError(message: String){
        binding.userDataProgressBar.hide()
        binding.userContainer.show()
        quickToast(message)
    }

    private fun addMenu(){
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.home_screen_menu, menu)
                actionBarMenu = menu.findItem(R.id.edit)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when(menuItem.itemId){
                    R.id.edit -> {
                        actionBarMenu.isVisible = false
                        displayEditScreen()
                        true
                    }
                    R.id.logout -> {
                        logout()
                        true
                    }
                    else -> false
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun displayEditScreen(){
        val btnSaveAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_left)
        val btnEditAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.show)

        binding.buttonSave.show()
        binding.buttonEditAvatar.show()
        binding.buttonEditUsername.show()

        binding.buttonSave.startAnimation(btnSaveAnim)
        binding.buttonEditAvatar.startAnimation(btnEditAnimation)
        binding.buttonEditUsername.startAnimation(btnEditAnimation)
        binding.ivAvatar.makeMoreTransparent()
    }

    private fun logout() {
        AlertDialog.Builder(requireActivity()).apply {
            setMessage("Are you sure?")

            setPositiveButton("Yes") { _, _ ->
                viewModel.logout()
                Intent(activity, LoginActivity::class.java).also { startActivity(it) }
            }

            setNegativeButton("no"){ dialog, _ ->
                dialog.dismiss()
            }

            show()
        }
    }

    private fun setListeners(){
        binding.buttonSave.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        val btnSaveAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_out_left)
        val btnEditAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.hide)

        binding.buttonSave.startAnimation(btnSaveAnim)
        binding.buttonEditAvatar.startAnimation(btnEditAnimation)
        binding.buttonEditUsername.startAnimation(btnEditAnimation)
        binding.ivAvatar.makeLessTransparent()

        binding.buttonSave.hide()
        binding.buttonEditAvatar.hide()
        binding.buttonEditUsername.hide()
        actionBarMenu.isVisible = true

    }


}