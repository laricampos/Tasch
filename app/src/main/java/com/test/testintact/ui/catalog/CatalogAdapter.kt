package com.test.testintact.ui.catalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.testintact.R
import com.test.testintact.data.model.Product
import kotlinx.android.synthetic.main.view_catalog_item.view.*

class CatalogAdapter : RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder>() {

    var catalog: List<Product> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder =
        CatalogViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_catalog_item, parent, false))

    override fun getItemCount(): Int = catalog.size

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        holder.bind(catalog[position])
    }

    class CatalogViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(product: Product) {
            Glide.with(view.context)
                .load(product.imageUrl)
                .into(view.catalog_item_image)
            view.catalog_item_title.text = product.name
        }
    }
}