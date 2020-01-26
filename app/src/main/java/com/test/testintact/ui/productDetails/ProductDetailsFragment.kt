package com.test.testintact.ui.productDetails

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.test.testintact.R
import com.test.testintact.di.ViewModelFactory
import com.test.testintact.extensions.centimeterToInch
import com.test.testintact.ui.MainActivity
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_product_details.*
import javax.inject.Inject

class ProductDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: ProductDetailsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(ProductDetailsViewModel::class.java)
    }

    private val args: ProductDetailsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_product_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setupWithProduct()
    }

    private fun setupWithProduct() {
        args.product.apply {
            (requireActivity() as MainActivity).setToolbarTitle(name)
            product_details_description.text = description
            product_details_price.text = getString(R.string.catalog_price, price)
            Glide.with(requireContext()).load(imageUrl).into(product_details_image)
            colors.forEach {
                addColourSquare(Color.parseColor(it))
            }
            product_details_sizes.text = getString(
                R.string.product_details_size,
                size.height, size.height.centimeterToInch(),
                size.width, size.width.centimeterToInch(),
                size.depth, size.depth.centimeterToInch()
            )
        }
        product_details_add_remove_wish_list_button.apply {
            val isInWishList = viewModel.isProductIsWishList(args.product)
            text = getString(
                if (isInWishList) R.string.product_details_remove_wish_list
                else R.string.product_details_add_wish_list
            )
            background = requireContext().getDrawable(
                if (isInWishList) R.drawable.secondary_button_background
                else R.drawable.primary_button_background
            )
            setOnClickListener {
                if (isInWishList) viewModel.removeFromWishList(args.product)
                else viewModel.addToWishList(args.product)
                findNavController().navigateUp()
            }
        }
    }

    private fun addColourSquare(@ColorInt colour: Int) {
        val view = View(context)
        val squareSide = resources.getDimension(R.dimen.colour_square_width).toInt()
        val marginEnd = resources.getDimension(R.dimen.small_margin).toInt()
        view.layoutParams = FrameLayout.LayoutParams(squareSide, squareSide).apply {
            setMargins(0, 0, marginEnd, 0)
        }
        view.background = requireContext().getDrawable(R.drawable.colour_square_background)
        view.backgroundTintList = ColorStateList.valueOf(colour)
        product_details_colour_container.addView(view)
    }
}