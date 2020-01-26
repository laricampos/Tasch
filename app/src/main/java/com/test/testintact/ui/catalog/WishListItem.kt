package com.test.testintact.ui.catalog

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.test.testintact.R
import com.test.testintact.data.model.Product
import kotlinx.android.synthetic.main.view_wish_list_item.view.*


class WishListItem(context: Context, product: Product) : FrameLayout(context) {

    init {
        inflate(context, R.layout.view_wish_list_item, this)

        Glide.with(context).load(product.imageUrl).into(wish_list_item_image)
        wish_list_item_price.text = context.getString(R.string.catalog_price, product.price)
        wish_list_item_name.text = product.name
        wish_list_item_description.text = product.smallDescription
        wish_list_item_out_of_stock.isVisible = product.quantity == 0

        if (product.quantity > 0) {
            product.colors.forEach {
                addColourSquare(Color.parseColor(it))
            }
        }
    }

    private fun addColourSquare(@ColorInt colour: Int) {
        val view = View(context)
        val squareSide = context.resources.getDimension(R.dimen.small_colour_square_width).toInt()
        val marginEnd = context.resources.getDimension(R.dimen.tiny_margin).toInt()
        view.layoutParams = LayoutParams(squareSide, squareSide).apply {
            setMargins(0, 0, marginEnd, 0)
        }
        view.setBackgroundColor(colour)
        wish_list_item_colour_container.addView(view)
    }
}