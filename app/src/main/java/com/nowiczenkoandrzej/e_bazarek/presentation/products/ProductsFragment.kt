package com.nowiczenkoandrzej.e_bazarek.presentation.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nowiczenkoandrzej.e_bazarek.R
import com.nowiczenkoandrzej.e_bazarek.data.models.ProductResponse
import com.nowiczenkoandrzej.e_bazarek.databinding.FragmentProductsBinding
import com.nowiczenkoandrzej.e_bazarek.extensions.hide
import com.nowiczenkoandrzej.e_bazarek.extensions.quickToast
import com.nowiczenkoandrzej.e_bazarek.extensions.show
import com.nowiczenkoandrzej.e_bazarek.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private lateinit var binding: FragmentProductsBinding
    private lateinit var productsAdapter: ProductsAdapter

    private val viewModel: ProductsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycleView()
        subscribeCollector()
    }

    private fun setRecycleView(){
        productsAdapter = ProductsAdapter()
        binding.allProducts.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = productsAdapter
        }
    }

    private fun subscribeCollector(){
        lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.products.collect{ state ->
                    when(state){
                        is DataState.Success -> displayProducts(state.data!!)
                        is DataState.Error -> displayError(state.message!!)
                        is DataState.Loading -> binding.progressBar.show()
                    }
                }
            }
        }
    }

    private fun displayProducts(products: List<ProductResponse>){
        binding.progressBar.hide()
        productsAdapter.setProducts(products)
    }

    private fun displayError(message: String){
        binding.progressBar.hide()
        quickToast(message)
    }

}