package com.nowiczenkoandrzej.e_bazarek.presentation.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nowiczenkoandrzej.e_bazarek.R
import com.nowiczenkoandrzej.e_bazarek.data.models.ProductResponse

class ProductsAdapter(): RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    private val products = ArrayList<ProductResponse>()

    fun setProducts(list: List<ProductResponse>){
        products.clear()
        products.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.product_row_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.setImageResource(R.drawable.ic_baseline_default_avatar)
        holder.title.text = products[position].title
        holder.description.text = products[position].description
        holder.price.text = "$${products[position].price.toString()}"
    }

    override fun getItemCount(): Int = products.size



    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var image: ImageView
        var title: TextView
        var description: TextView
        var price: TextView

        init {
            image = itemView.findViewById(R.id.iv_product)
            title = itemView.findViewById(R.id.tv_title)
            description = itemView.findViewById(R.id.tv_description)
            price = itemView.findViewById(R.id.tv_price)
        }


    }
}