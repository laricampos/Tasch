package com.sample.tasch.ui.catalog

import android.app.AlertDialog
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.tasch.R
import com.sample.tasch.data.model.Product
import com.sample.tasch.di.ViewModelFactory
import com.sample.tasch.ui.MainActivity
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_catalog.*
import javax.inject.Inject


class CatalogFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: CatalogViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(CatalogViewModel::class.java)
    }

    private val catalogAdapter = CatalogAdapter(::onProductClick)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_catalog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).showToolbarAppName()

        catalog_all_products_list.apply {
            adapter = catalogAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            addItemDecoration(CatalogListItemDecorator(requireContext()))
        }

        viewModel.catalog.observe(viewLifecycleOwner, Observer<List<Product>> {
            initCatalog(it)
        })

        viewModel.wishList.observe(viewLifecycleOwner, Observer<List<Product>> {
            initWishList(it)
        })

        catalog_checkout_button.setOnClickListener {
            showCheckoutConfirmationDialog()
        }
    }

    private fun showCheckoutConfirmationDialog() {
        AlertDialog.Builder(requireContext())
            .setMessage(R.string.catalog_checkout_dialog_text)
            .setNegativeButton(android.R.string.cancel) { dialog, _ -> dialog.dismiss() }
            .setPositiveButton(android.R.string.ok) { dialog, _ ->
                dialog.dismiss()
                viewModel.checkout()
            }
            .create()
            .show()
    }

    private fun onProductClick(product: Product) {
        findNavController().navigate(CatalogFragmentDirections.actionProductDetailsFragmnet(product))
    }

    private fun initCatalog(catalog: List<Product>) {
        catalogAdapter.catalog = catalog
    }

    private fun initWishList(wishList: List<Product>) {
        catalog_wish_list_empty_view.isVisible = wishList.isEmpty()

        val totalPrice = wishList.sumByDouble { it.price.toDouble() }

        catalog_wish_list_total.text = getString(R.string.catalog_wish_list_total, totalPrice)

        val subtotalPrice = getString(R.string.catalog_subtotal, totalPrice)
        val boldText = getString(R.string.catalog_subtotal_bold)
        val spannable = SpannableString(subtotalPrice)
        val boldSpan = StyleSpan(Typeface.BOLD)
        spannable.setSpan(boldSpan, 0, boldText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        catalog_subtotal_text.text = spannable

        catalog_wish_list_container.removeAllViews()
        wishList.forEach { product ->
            val wishListItem = WishListItem(requireContext(), product)
            catalog_wish_list_container.addView(wishListItem)
            wishListItem.setOnClickListener {
                onProductClick(product)
            }
        }
    }
}